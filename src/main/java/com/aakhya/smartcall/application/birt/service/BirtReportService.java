package com.aakhya.smartcall.application.birt.service;




import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.DocxRenderOption;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IPDFRenderOption;
import org.eclipse.birt.report.engine.api.IParameterDefn;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.aakhya.smartcall.application.birt.entity.OutputType;
import com.aakhya.smartcall.application.birt.entity.Report;


@Service
public class BirtReportService implements ApplicationContextAware, DisposableBean {
	
//	private static final Logger logger = Logger.getLogger(BirtReportService.class);
    @Value("${reports.relative.path}")
    private String reportsPath;
    @Value("${images.relative.path}")
    private String imagesPath;

    private HTMLServerImageHandler htmlImageHandler = new HTMLServerImageHandler();

    @SuppressWarnings("unused")
	@Autowired
    private ResourceLoader resourceLoader;
    @SuppressWarnings("unused")
	@Autowired
    private ServletContext servletContext;

    private IReportEngine birtEngine;
    private ApplicationContext context;
    private String imageFolder;

    private Map<String, IReportRunnable> reports = new HashMap<>();

    @SuppressWarnings("unchecked")
    @PostConstruct
    protected void initialize() throws BirtException {
        EngineConfig config = new EngineConfig();
        config.getAppContext().put("spring", this.context);
        Platform.startup(config);
        IReportEngineFactory factory = (IReportEngineFactory) Platform
          .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
        birtEngine = factory.createReportEngine(config);
        imageFolder = System.getProperty("user.dir") + File.separatorChar + reportsPath + imagesPath;
        loadReports();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Load report files to memory
     *
     */
    public void loadReports() throws EngineException {
//    	logger.debug("Victory through the blood of JESUS :: "+reportsPath);
        File folder = new File(reportsPath);
        if(null != folder.list()){
	        for (String file : folder.list()) {
	            if (!file.endsWith(".rptdesign")) {
	                continue;
	            }
	
	            reports.put(file.replace(".rptdesign", ""),
	              birtEngine.openReportDesign(folder.getAbsolutePath() + File.separator + file));
	
	        }
        }else{
        	try {
				File reportsFolder = ResourceUtils.getFile(reportsPath);
				if(null != reportsFolder.list()){
			        for (String file : reportsFolder.list()) {
			            if (!file.endsWith(".rptdesign")) {
			                continue;
			            }
			
			            reports.put(file.replace(".rptdesign", ""),
			              birtEngine.openReportDesign(folder.getAbsolutePath() + File.separator + file));
			
			        }
		        }else{
//		        	logger.debug("Victory through the blood of JESUS :: The folder is not loaded");
		        }
			} catch (FileNotFoundException e) {
//				logger.debug("Victory through the blood of JESUS :: The folder is not loaded");
				e.printStackTrace();
			}
        }
    }

    public List<Report> getReports() {
        List<Report> response = new ArrayList<>();
        for (Map.Entry<String, IReportRunnable> entry : reports.entrySet()) {
            IReportRunnable report = reports.get(entry.getKey());
            IGetParameterDefinitionTask task = birtEngine.createGetParameterDefinitionTask(report);
            Report reportItem = new Report(report.getDesignHandle().getProperty("title").toString(), entry.getKey());
            for (Object h : task.getParameterDefns(false)) {
                IParameterDefn def = (IParameterDefn) h;
                reportItem.getParameters()
                  .add(new Report.Parameter(def.getPromptText(), def.getName(), getParameterType(def)));
            }
            response.add(reportItem);
        }
        return response;
    }

    private Report.ParameterType getParameterType(IParameterDefn param) {
        if (IParameterDefn.TYPE_INTEGER == param.getDataType()) {
            return Report.ParameterType.INT;
        }
        return Report.ParameterType.STRING;
    }

    public void generateMainReport(String reportName, OutputType output, HttpServletResponse response, HttpServletRequest request) {
        switch (output) {
        case HTML:
            generateHTMLReport(reports.get(reportName), response, request);
            break;
        case PDF:
            generatePDFReport(reports.get(reportName), response, request);
            break;
        case DOCX:
        	generateDOCXReport(reports.get(reportName), response, request);
        	break;
        case BIRT:
        	generateBirtReport(reports.get(reportName), response, request);
        	break;
        default:
            throw new IllegalArgumentException("Output type not recognized:" + output);
        }
    }

    /**
     * Generate a report as HTML
     */
    @SuppressWarnings("unchecked")
    private void generateHTMLReport(IReportRunnable report, HttpServletResponse response, HttpServletRequest request) {
        IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask(report);
        response.setContentType(birtEngine.getMIMEType("html"));
        IRenderOption options = new RenderOption();
        HTMLRenderOption htmlOptions = new HTMLRenderOption(options);
        htmlOptions.setOutputFormat("html");
//        logger.debug("01-Jul-2020 :: The image directory is :: "+imageFolder);
        htmlOptions.setBaseImageURL("\\images");
        htmlOptions.setImageDirectory(imageFolder);
        htmlOptions.setImageHandler(htmlImageHandler);
        Map<String,String[]> parameters = request.getParameterMap();
        if(null != parameters){
        	for(String key:parameters.keySet()){
        		runAndRenderTask.setParameterValue(key, request.getParameter(key));
        	}
        }
        runAndRenderTask.setRenderOption(htmlOptions);
        runAndRenderTask.getAppContext().put(EngineConstants.APPCONTEXT_BIRT_VIEWER_HTTPSERVET_REQUEST, request);

        try {
            htmlOptions.setOutputStream(response.getOutputStream());
            runAndRenderTask.run();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            runAndRenderTask.close();
        }
    }

    /**
     * Generate a report as PDF
     */
    @SuppressWarnings("unchecked")
    private void generatePDFReport(IReportRunnable report, HttpServletResponse response, HttpServletRequest request) {
        IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask(report);
        response.setContentType(birtEngine.getMIMEType("pdf"));
        IRenderOption options = new RenderOption();
        PDFRenderOption pdfRenderOption = new PDFRenderOption(options);
        pdfRenderOption.setOutputFormat("pdf");
        Map<String,String[]> parameters = request.getParameterMap();
        if(null != parameters){
        	for(String key:parameters.keySet()){
        		runAndRenderTask.setParameterValue(key, request.getParameter(key));
        	}
        }
        runAndRenderTask.setRenderOption(pdfRenderOption);
        runAndRenderTask.getAppContext().put(EngineConstants.APPCONTEXT_PDF_RENDER_CONTEXT, request);
        try {
            pdfRenderOption.setOutputStream(response.getOutputStream());
            runAndRenderTask.run();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            runAndRenderTask.close();
        }
    }
    
    /**
     * Generate a report as HTML
     */
    @SuppressWarnings("unchecked")
    private void generateDOCXReport(IReportRunnable report, HttpServletResponse response, HttpServletRequest request) {
    	IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask(report);
        response.setContentType(birtEngine.getMIMEType("doc"));
    	DocxRenderOption docxRenderOption = new DocxRenderOption();
    	docxRenderOption.setOutputFormat("docx"); // getBirtFormat returns doc in order to make it happen
    	docxRenderOption.setEmitterID("org.eclipse.birt.report.engine.emitter.docx"); // this does not work... why? Even when format is docx it does not work
    	docxRenderOption.setOption(IRenderOption.HTML_PAGINATION, Boolean.TRUE);
    	docxRenderOption.setOption(IRenderOption.RENDER_DPI, 96);
    	docxRenderOption.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
    	Map<String,String[]> parameters = request.getParameterMap();
        if(null != parameters){
        	for(String key:parameters.keySet()){
        		runAndRenderTask.setParameterValue(key, request.getParameter(key));
        	}
        }
        runAndRenderTask.setRenderOption(docxRenderOption);
        runAndRenderTask.getAppContext().put(EngineConstants.APPCONTEXT_PDF_RENDER_CONTEXT, request);
        try {
        	docxRenderOption.setOutputStream(response.getOutputStream());
            runAndRenderTask.run();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            runAndRenderTask.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    private void generateBirtReport(IReportRunnable report, HttpServletResponse response, HttpServletRequest request) {
    	IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask(report);
        response.setContentType(birtEngine.getMIMEType("html"));
        RenderOption renderOption = new RenderOption();
        
//        DocxRenderOption docxRenderOption = new DocxRenderOption();
//    	docxRenderOption.setOutputFormat("docx"); // getBirtFormat returns doc in order to make it happen
//        renderOption.setEmitterID("org.eclipse.birt.report.engine.emitter.docx"); // this does not work... why? Even when format is docx it does not work
        renderOption.setOption(IRenderOption.HTML_PAGINATION, Boolean.TRUE);
        renderOption.setOption(IRenderOption.RENDER_DPI, 96);
        renderOption.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
    	Map<String,String[]> parameters = request.getParameterMap();
        if(null != parameters){
        	for(String key:parameters.keySet()){
        		runAndRenderTask.setParameterValue(key, request.getParameter(key));
        	}
        }
        runAndRenderTask.setRenderOption(renderOption);
        runAndRenderTask.getAppContext().put(EngineConstants.APPCONTEXT_PDF_RENDER_CONTEXT, request);
        try {
        	renderOption.setOutputStream(response.getOutputStream());
            runAndRenderTask.run();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            runAndRenderTask.close();
        }
    }

    @Override
    public void destroy() {
        birtEngine.destroy();
        Platform.shutdown();
    }
}