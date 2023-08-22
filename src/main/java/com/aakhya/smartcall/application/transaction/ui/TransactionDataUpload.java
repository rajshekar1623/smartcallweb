package com.aakhya.smartcall.application.transaction.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;

import com.aakhya.smartcall.application.transaction.data.entity.TemporaryTransaction;
import com.aakhya.smartcall.application.transaction.data.service.TransactionDataSetService;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route(value = "fileUpload", layout = TransactionMainLayout.class)
@PermitAll
public class TransactionDataUpload extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5150502074584480853L;
	TransactionDataSetService transactionDataSetService;
	public static String BASE_PATH = "E:\\Tools\\smartcall\\";
	private File file;
	@SuppressWarnings("unused")
	private String originalFileName,mimeType;
//	TransactionDataUploadEntity uploadEntity;
	List<TemporaryTransaction> transactionDataSets;
	private Map<String, String> firstNameErrorRecords;
	private Map<String, String> mobileNoErrorRecords;
	private Map<String, String> loanAcNoErrorRecords;
	private Map<String, String> branchCodeErrorRecords;
	private Map<String, String> principleDueErrorRecords;
	private Map<String, String> interestDueErrorRecords;
	private Map<String, String> interestRateErrorRecords;
	private Map<String, String> lastInterestPaidDateErrorRecords;
	private Map<String, String> npaDateErrorRecords;
	private boolean invalidRecordsExisit;
	ProgressBar progressBar;
	
	
	TransactionDataUpload(TransactionDataSetService transactionDataSetService) {
		this.transactionDataSetService = transactionDataSetService;
		setSizeFull();
//		VerticalLayout firstComponent = new VerticalLayout();
		VerticalLayout upperComponent = new VerticalLayout();

		HorizontalLayout row1 = new HorizontalLayout();
		row1.setWidth(100, Unit.PERCENTAGE);
		Upload upload = new Upload(this::receiveUpload);
		upload.setMaxFileSize(1000000000);
		Div output = new Div(new Text("(no file uploaded yet)"));
		VerticalLayout uploadComponent = new VerticalLayout();
		uploadComponent.add(upload, output);

		H3 recordsFromFile = new H3("No of Records fetched");
		row1.add(uploadComponent, recordsFromFile);
		Grid<DataSetExceptions> exceptionsGrid = new Grid<>(DataSetExceptions.class);
		exceptionsGrid.setSizeFull();
		exceptionsGrid.getStyle().set("border", "2px solid Grey");
		exceptionsGrid.getStyle().set("border-radius", "10px");
		exceptionsGrid.setColumns("columnName", "noOfValidRecords", "noOfInvalidRecords");
		exceptionsGrid.addColumn(new ComponentRenderer<>(dataSetExceptions -> {
			Button viewDetails = new Button("Export");
			viewDetails.addClickListener(e -> exportDataSet(dataSetExceptions));
			return viewDetails;
		}));
		Button uploadToDatabase = new Button("Upload to Database");
		uploadToDatabase.addClickListener(e -> uploadToDb());
		progressBar = new ProgressBar();
		progressBar.setIndeterminate(false);
		progressBar.setVisible(false);
		upperComponent.add(row1, exceptionsGrid,uploadToDatabase,progressBar);
		
//		firstComponent.add(upperComponent,progressBar);

		Grid<TemporaryTransaction> grid = new Grid<>(TemporaryTransaction.class);
		grid.setSizeFull();
		grid.getStyle().set("border", "2px solid Grey");
		grid.getStyle().set("border-radius", "10px");
		grid.setColumns("firstname", "dob", "mobilenumber","branchcode", "branchname", "loanaccountnumber");
		grid.getColumnByKey("firstname").setHeader("First Name");
		grid.getColumnByKey("dob").setHeader("Date of Birth");
		grid.getColumnByKey("mobilenumber").setHeader("Mobile Number");
		grid.getColumnByKey("branchcode").setHeader("Branch Code");
		grid.getColumnByKey("branchname").setHeader("Branch Name");
		grid.getColumnByKey("loanaccountnumber").setHeader("Loan A/c #");
		SplitLayout content = new SplitLayout(upperComponent, grid);
		content.setSizeFull();
		content.setOrientation(Orientation.VERTICAL);
		content.setSplitterPosition(75);
		add(content);
		// Configure upload component
//        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");
		upload.addSucceededListener(event -> {
			output.removeAll();
			File uploadedFile = new File(BASE_PATH + event.getFileName());
			try {
				FileInputStream fis = new FileInputStream(uploadedFile);
				transactionDataSets = transactionDataSetService.uploadDataNew(fis);
//				transactionDataSets = uploadEntity.getUploadedData();
				recordsFromFile.setText(recordsFromFile.getText() + ":" + transactionDataSets.size());
				grid.setItems(transactionDataSets);
				exceptionsGrid.setItems(checkDataException(transactionDataSets));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		upload.addFailedListener(event -> {
			output.removeAll();
			output.add(new Text("Upload failed: " + event.getReason()));
		});
	}

	/**
	 * Load a file from local filesystem.
	 *
	 */
	public InputStream loadFile() {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
					"Failed to create InputStream for: '" + this.file.getAbsolutePath(), e);
		}
		return null;
	}

	/**
	 * Receive a uploaded file to a file.
	 */
	public OutputStream receiveUpload(String originalFileName, String MIMEType) {
		this.originalFileName = originalFileName;
		this.mimeType = MIMEType;
		try {
			// Create a temporary file for example, you can provide your file here.
//            this.file = File.createTempFile("prefix-", "-suffix");
//            file.deleteOnExit();
			this.file = new File(BASE_PATH + originalFileName);
			return new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
					"Failed to create InputStream for: '" + this.file.getAbsolutePath(), e);
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
					"Failed to create InputStream for: '" + this.file.getAbsolutePath() + "'", e);
		}

		return null;
	}

	private List<DataSetExceptions> checkDataException(List<TemporaryTransaction> dataSets) {
		firstNameErrorRecords = new HashMap<String, String>();
		mobileNoErrorRecords = new HashMap<String, String>();
		loanAcNoErrorRecords = new HashMap<String, String>();
		branchCodeErrorRecords = new HashMap<String, String>();
		principleDueErrorRecords = new HashMap<String, String>();
		interestDueErrorRecords = new HashMap<String, String>();
		interestRateErrorRecords = new HashMap<String, String>();
		lastInterestPaidDateErrorRecords = new HashMap<String, String>();
		npaDateErrorRecords = new HashMap<String, String>();
		invalidRecordsExisit = false;
		List<DataSetExceptions> dataSetExceptions = new ArrayList<DataSetExceptions>();
		Integer firstNameInvalidCount = 0;
		Integer firstNameValidCount = 0;
		Integer mobileNumberInvalidCount = 0;
		Integer mobileNumberValidCount = 0;
		Integer loanAccountNumberInvalidCount = 0;
		Integer loanAccountNumberValidCount = 0;
		Integer branchCodeInvalidCount = 0;
		Integer branchCodeValidCount = 0;
		Integer principleDueInvalidCount = 0;
		Integer principleDueValidCount = 0;
		Integer interestDueInvalidCount = 0;
		Integer interestDueValidCount = 0;
		Integer interestRateInvalidCount = 0;
		Integer interestRateValidCount = 0;
		Integer lastInterestPaidDateInvalidCount = 0;
		Integer lastInterestPaidDateValidCount = 0;
		Integer npaDateInvalidCount = 0;
		Integer npaDateValidCount = 0;
		for (TemporaryTransaction dataSet : dataSets) {
			if (null != dataSet.getFirstname() && dataSet.getFirstname().replaceAll(" ", "").length() > 0)
				firstNameValidCount++;
			else {
				firstNameErrorRecords.put(dataSet.getLoanaccountnumber(), dataSet.getFirstname());
				firstNameInvalidCount++;
				invalidRecordsExisit = true;
			}
			if (null != dataSet.getMobilenumber() && !dataSet.getMobilenumber().isEmpty()
					&& dataSet.getMobilenumber().length() == 10)
				mobileNumberValidCount++;
			else {
				if (null != dataSet.getMobilenumber())
					mobileNoErrorRecords.put(dataSet.getLoanaccountnumber(), dataSet.getMobilenumber());
				else
					mobileNoErrorRecords.put(dataSet.getLoanaccountnumber(), null);
				mobileNumberInvalidCount++;
				invalidRecordsExisit = true;
			}
			if (null != dataSet.getLoanaccountnumber() && !dataSet.getLoanaccountnumber().isEmpty())
				loanAccountNumberValidCount++;
			else {
				if (null != dataSet.getLoanaccountnumber())
					loanAcNoErrorRecords.put(dataSet.getLoanaccountnumber(), dataSet.getLoanaccountnumber().toString());
				else
					loanAcNoErrorRecords.put(dataSet.getLoanaccountnumber(), null);
				loanAccountNumberInvalidCount++;
				invalidRecordsExisit = true;
			}
			if (null != dataSet.getBranchcode() && dataSet.getBranchcode().replaceAll(" ", "").length() > 0)
				branchCodeValidCount++;
			else {
				branchCodeErrorRecords.put(dataSet.getLoanaccountnumber(), dataSet.getLoanaccountnumber());
				branchCodeInvalidCount++;
				invalidRecordsExisit = true;
			}
			if (null != dataSet.getPrincipledue() && dataSet.getPrincipledue().compareTo(BigDecimal.ZERO) == 1)
				principleDueValidCount++;
			else {
				if (null != dataSet.getPrincipledue())
					principleDueErrorRecords.put(dataSet.getLoanaccountnumber(), dataSet.getPrincipledue().toString());
				else
					principleDueErrorRecords.put(dataSet.getLoanaccountnumber(), null);
				principleDueInvalidCount++;
				invalidRecordsExisit = true;
			}
			if (null != dataSet.getInterestdue() && dataSet.getInterestdue().compareTo(BigDecimal.ZERO) == 1)
				interestDueValidCount++;
			else {
				if (null != dataSet.getInterestdue())
					interestDueErrorRecords.put(dataSet.getLoanaccountnumber(), dataSet.getInterestdue().toString());
				else
					interestDueErrorRecords.put(dataSet.getLoanaccountnumber(), null);
				interestDueInvalidCount++;
				invalidRecordsExisit = true;
			}
			if (null != dataSet.getInterestrate() && dataSet.getInterestrate().compareTo(BigDecimal.ZERO) == 1)
				interestRateValidCount++;
			else {
				if (null != dataSet.getInterestrate())
					interestRateErrorRecords.put(dataSet.getLoanaccountnumber(), dataSet.getInterestrate().toString());
				else
					interestRateErrorRecords.put(dataSet.getLoanaccountnumber(), null);
				interestRateInvalidCount++;
				invalidRecordsExisit = true;
			}
			if (null != dataSet.getLastinterestapplieddate())
				lastInterestPaidDateValidCount++;
			else {
				if (null != dataSet.getLastinterestapplieddate()) {
					String lipd = new SimpleDateFormat("dd-MM=yyyy").format(dataSet.getLastinterestapplieddate());
					lastInterestPaidDateErrorRecords.put(dataSet.getLoanaccountnumber(), lipd);
				} else
					lastInterestPaidDateErrorRecords.put(dataSet.getLoanaccountnumber(), null);
				lastInterestPaidDateInvalidCount++;
				invalidRecordsExisit = true;
			}
			if (null != dataSet.getNpadate())
				npaDateValidCount++;
			else {
				if (null != dataSet.getNpadate()) {
					String npaDate = new SimpleDateFormat("dd-MM=yyyy").format(dataSet.getNpadate());
					npaDateErrorRecords.put(dataSet.getLoanaccountnumber(), npaDate);
				} else
					npaDateErrorRecords.put(dataSet.getLoanaccountnumber(), null);
				npaDateInvalidCount++;
				invalidRecordsExisit = true;
			}
		}
		dataSetExceptions.add(new DataSetExceptions("First Name", firstNameValidCount, firstNameInvalidCount));
		dataSetExceptions.add(new DataSetExceptions("Mobile Number", mobileNumberValidCount, mobileNumberInvalidCount));
		dataSetExceptions
				.add(new DataSetExceptions("Loan A/c #", loanAccountNumberValidCount, loanAccountNumberInvalidCount));
		dataSetExceptions.add(new DataSetExceptions("Branch Code", branchCodeValidCount, branchCodeInvalidCount));
		dataSetExceptions.add(new DataSetExceptions("Principle Due", principleDueValidCount, principleDueInvalidCount));
		dataSetExceptions.add(new DataSetExceptions("Interest Due", interestDueValidCount, interestDueInvalidCount));
		dataSetExceptions.add(new DataSetExceptions("Interest Rate", interestRateValidCount, interestRateInvalidCount));
		dataSetExceptions.add(new DataSetExceptions("Last Interest Applied Date", lastInterestPaidDateValidCount,
				lastInterestPaidDateInvalidCount));
		dataSetExceptions.add(new DataSetExceptions("NPA Date", npaDateValidCount, npaDateInvalidCount));
		return dataSetExceptions;
	}

	public void exportDataSet(DataSetExceptions dataSetExceptions) {
		DataSetExceptionDetailsView dataSetExceptionDetailsView = null;
		if ("First Name".equals(dataSetExceptions.getColumnName())) {
			if(null != firstNameErrorRecords && !firstNameErrorRecords.isEmpty())
			dataSetExceptionDetailsView = new DataSetExceptionDetailsView("firstName",
					firstNameErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in First Name");
		}else if("Mobile Number".equals(dataSetExceptions.getColumnName())) {
			if(null != mobileNoErrorRecords && !mobileNoErrorRecords.isEmpty())
				dataSetExceptionDetailsView = new DataSetExceptionDetailsView("mobileNumber",
						mobileNoErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in Mobile Number");
		}else if("Loan A/c #".equals(dataSetExceptions.getColumnName())) {
			if(null != loanAcNoErrorRecords && !loanAcNoErrorRecords.isEmpty())
				dataSetExceptionDetailsView = new DataSetExceptionDetailsView("loanAccountNumber",
						loanAcNoErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in Loan Account Number");
		}else if("Branch Code".equals(dataSetExceptions.getColumnName())) {
			if(null != branchCodeErrorRecords && !branchCodeErrorRecords.isEmpty())
				dataSetExceptionDetailsView = new DataSetExceptionDetailsView("branchCode",
						branchCodeErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in Branch Code");
		}else if("Principle Due".equals(dataSetExceptions.getColumnName())) {
			if(null != principleDueErrorRecords && !principleDueErrorRecords.isEmpty())
				dataSetExceptionDetailsView = new DataSetExceptionDetailsView("principleDue",
						principleDueErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in Principle Due");
		}else if("Interest Due".equals(dataSetExceptions.getColumnName())) {
			if(null != interestDueErrorRecords && !interestDueErrorRecords.isEmpty())
				dataSetExceptionDetailsView = new DataSetExceptionDetailsView("interestDue",
						interestDueErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in Interest Due");
		}else if("Interest Rate".equals(dataSetExceptions.getColumnName())) {
			if(null != interestRateErrorRecords && !interestRateErrorRecords.isEmpty())
				dataSetExceptionDetailsView = new DataSetExceptionDetailsView("interestRate",
						interestRateErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in Interest Rate");
		}else if("Last Interest Applied Date".equals(dataSetExceptions.getColumnName())) {
			if(null != lastInterestPaidDateErrorRecords && !lastInterestPaidDateErrorRecords.isEmpty())
				dataSetExceptionDetailsView = new DataSetExceptionDetailsView("lastInterestPaidDate",
						lastInterestPaidDateErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in Last Interest Paid Date ");
		}else if("NPA Date".equals(dataSetExceptions.getColumnName())) {
			if(null != npaDateErrorRecords && !npaDateErrorRecords.isEmpty())
				dataSetExceptionDetailsView = new DataSetExceptionDetailsView("npaDate",
						npaDateErrorRecords);
			else
				MessageUtils.showSaveNotification("No errors in NPA Date");
		}
		if(null != dataSetExceptionDetailsView) {
			Dialog dialog = new Dialog();
			dialog.setWidth("400px");
			dialog.setResizable(true);		
			dialog.setHeaderTitle("Error Details");
			Button cancelButton = new Button("Cancel", e -> dialog.close());
			dialog.add(dataSetExceptionDetailsView);
			dialog.getFooter().add(cancelButton);
			dialog.open();
		}
	}

	private void uploadToDb() {
		if(null != transactionDataSets && !transactionDataSets.isEmpty()) {
			ConfirmDialog dialog = new ConfirmDialog();
			dialog.setHeader("Upload Confirmation");
			if(invalidRecordsExisit)
			dialog.setText("There are invlaid data elements in the file. Do you still want to proceed?");
			else
			dialog.setText("Are you sure you want to upload the data?");
			dialog.setCancelable(true);
			dialog.setConfirmText("Proceed");
			dialog.addConfirmListener(event -> proceedUpload());
			dialog.open();
		}else {
			Notification.show("No data to upload", 150000, Notification.Position.MIDDLE);
		}
	}
	
	private void proceedUpload() {
		try {
//			Long initialCount = transactionDataSetService.getDataSetCount();
//			progressBar.setVisible(true);
//			UploadProgress dialog = new UploadProgress(transactionDataSetService,transactionDataSets,initialCount,uploadEntity.getCreateCount());
//			dialog.setWidth("800px");
//			dialog.setHeight("250px");
//			dialog.setResizable(true);		
//			dialog.setHeaderTitle("Error Details");
//			Button cancelButton = new Button("Cancel", e -> dialog.close());
//			dialog.getFooter().add(cancelButton);
//			dialog.setModal(true);
//			dialog.open();
			transactionDataSetService.uploadDataFromFile(transactionDataSets);
			MessageUtils.successMessage("File was uploaded successfully!!!");
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Upload failed", 15000, Notification.Position.MIDDLE);
		}
	}
	
//	private void percentageUpdated(Float percentage) {
//		UI.getCurrent().access(() -> progressBar.setValue(percentage));
//	}
//	
//	private void processingSucceeded() {
//		UI.getCurrent().access(() ->{
//			progressBar.setVisible(false);
//			Notification.show("Data uploaded successfully");
//		});
//	}
}