package com.aakhya.smartcall.application.birt.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.eclipse.birt.report.engine.api.EngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aakhya.smartcall.application.birt.entity.OutputType;
import com.aakhya.smartcall.application.birt.entity.Report;
import com.aakhya.smartcall.application.birt.service.BirtReportService;

@Controller
public class BirtReportController {
//    private static final Logger log = Logger.getLogger(BirtReportController.class);

    @Autowired
    private BirtReportService reportService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "/report")
    @ResponseBody
    public List<Report> listReports() {
        return reportService.getReports();
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "/report/reload")
    @ResponseBody
    public ResponseEntity reloadReports(HttpServletResponse response) {
        try {
//            log.info("Reloading reports");
            reportService.loadReports();
        } catch (EngineException e) {
//            log.error("There was an error reloading the reports in memory: ", e);
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/report/{name}")
    @ResponseBody
    public void generateFullReport(HttpServletResponse response, HttpServletRequest request,
                                   @PathVariable("name") String name, @RequestParam("output") String output) {
//        log.info("Generating full report: " + name + "; format: " + output);
        OutputType format = OutputType.from(output);
        reportService.generateMainReport(name, format, response, request);
    }
}
