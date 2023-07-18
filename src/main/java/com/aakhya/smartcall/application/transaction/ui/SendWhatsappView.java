package com.aakhya.smartcall.application.transaction.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.PermitAll;

import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.klaudeta.LitPagination.PageChangeEvent;
import org.vaadin.klaudeta.PaginatedGrid;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.service.BranchService;
import com.aakhya.smartcall.application.admin.service.GenericClassifierService;
import com.aakhya.smartcall.application.product.entity.Product;
import com.aakhya.smartcall.application.product.service.ProductService;
import com.aakhya.smartcall.application.security.SecurityService;
import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.security.service.UserService;
import com.aakhya.smartcall.application.transaction.activity.entity.MessageTemplate;
import com.aakhya.smartcall.application.transaction.activity.service.MessageTemplateService;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhya.smartcall.application.transaction.data.service.TransactionDataSetService;
import com.aakhyatech.smartcall.application.utils.MessageUtils;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.selection.SelectionEvent;
//import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route(value = "sendwhatsapp", layout = TransactionMainLayout.class)
@PermitAll
public class SendWhatsappView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474642139276717277L;
//	SplitLayout gridLayout = new SplitLayout();
//	PaginatedGrid<T, F> grid = new 
	PaginatedGrid<TransactionDataSet, ?> grid = new PaginatedGrid<>(TransactionDataSet.class);
	List<TransactionDataSet> allDataSets = new ArrayList<TransactionDataSet>();
	List<TransactionDataSet> selectedDataSets = new ArrayList<TransactionDataSet>();
	ComboBox<Branch> parentBranchCB;
	NumberField mobileNumber;
	NumberField pincode;
	NumberField loanAccountNumber;
	TextField firstName;
	ComboBox<Branch> branchCB;
	ComboBox<Product> productCB;
	ComboBox<User> userCB;
	ComboBox<String> queueCB;
	ComboBox<GenericClassifier> dpdQueueCB;
	Label noOfRecordsFetched;
	Button reset;
	Button search;
//	Button printDocs;
	AssignmentForm form;
//	HorizontalLayout secondaryComponent;
	TransactionDataSetService service;
	BranchService branchService;
	UserService userService;
	GenericClassifierService genericClassifierService;
	SecurityService securityService;
	ProductService productService;
	MessageTemplateService messageTemplateService;
	VerticalLayout content;
	ComboBox<MessageTemplate> messageTemplates;
	Button sendWhatsapp,previewWhatsapp;
	Checkbox selectAll;
	List<Div> errorMessages = new ArrayList<Div>();
	Map<Integer, Set<TransactionDataSet>> pageSelections = new HashMap<Integer, Set<TransactionDataSet>>();
	Dialog editTransactionDialog;
	
	public SendWhatsappView(TransactionDataSetService service, UserService userService, BranchService branchService,
			GenericClassifierService genericClassifierService,
			ProductService productService, SecurityService securityService,MessageTemplateService messageTemplateService) {
		this.service = service;
		this.branchService = branchService;
		this.userService = userService;
		this.genericClassifierService = genericClassifierService;
		this.productService = productService;
		this.securityService = securityService;
		this.messageTemplateService = messageTemplateService;
		addClassName("assignment-view");
		setSizeFull();
		getStyle().set("border", "2px solid Grey");
		getStyle().set("border-radius", "10px");
		configureGrid();
		configureForm(service, branchService);
//		gridLayout.setOrientation(Orientation.VERTICAL);
//		gridLayout.setSplitterPosition(80);

		add(getToolbar(), getContent());
		updateList();
		closeEditor();
	}

	private Component getContent() {
		content = new VerticalLayout(grid);
//      VerticalLayout content = new VerticalLayout(grid, form);

//      content.setFlexGrow(2, grid); 
//      content.setFlexGrow(1, form);
//		content.setOrientation(SplitLayout.Orientation.VERTICAL);
//		content.setSplitterPosition(20);
		content.addClassNames("content");
		content.setSizeFull();
		return content;
	}

	private void configureForm(TransactionDataSetService service, BranchService branchService) {
		form = new AssignmentForm();
//        form.setWidth("25em");
		form.addListener(AssignmentForm.SaveEvent.class, this::saveUser);
		form.addListener(AssignmentForm.DeleteEvent.class, this::deleteContact);
		form.addListener(AssignmentForm.CloseEvent.class, e -> closeEditor());
	}

	private void previewWhatsapp() {
		MessageTemplate selectedTemplate = messageTemplates.getValue();
		if(null != selectedTemplate) {
			String message = selectedTemplate.getMessageString();
			TransactionDataSet tds = allDataSets.get(0);
			if(null != tds) {
//				System.out.println(message.replace("$memberName", tds.getFirstName()));
				String formatedMessage = message.replace("$memberName", tds.getFirstName())
						.replace("$accountNumber", tds.getLoanAccountNumber())
						.replace("$product", tds.getGenericString2())
						.replace("$osbalance", tds.getGenericDecimal4().toString());
//				System.out.println(formatedMessage);
				MessageUtils.showPreviewMessage(formatedMessage);
			}
		}
	}

	private void saveUser(AssignmentForm.SaveEvent event) {
		service.save(event.getTransactionDataSet());
		updateList();
		closeEditor();
	}

	private void deleteContact(AssignmentForm.DeleteEvent event) {
		service.delete(event.getTransactionDataSet());
		updateList();
		closeEditor();
	}

	private void configureGrid() {

		grid.addClassNames("transactionDataSet-grid");
		grid.setSizeFull();
		grid.getStyle().set("border", "2px solid Grey");
		grid.getStyle().set("border-radius", "10px");
		grid.setColumns("firstName", "dateOfBirth", "genericNumber1", "genericString4", "actionStatus");
		grid.addColumn(new ComponentRenderer<>(transactionDataSet -> {
			Button viewDetails = new Button("View Details");
			viewDetails.addClickListener(e -> editTransactionDataSet(transactionDataSet));
			return viewDetails;
		}));
		grid.getColumnByKey("genericNumber1").setHeader("Mobile Number");
		grid.getColumnByKey("actionStatus").setHeader("Status");
		grid.getColumnByKey("genericString4").setHeader("Branch");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setPageSize(5);
		grid.setPaginatorSize(5);
		grid.addSelectionListener(e -> selectionListner(e));
		grid.addPageChangeListener(event -> pageChange(event));
//		gridLayout.addToPrimary(grid);
		
	}

	private void selectionListner(SelectionEvent<Grid<TransactionDataSet>, 
			TransactionDataSet> e) {
		Set<TransactionDataSet> pageSelection = e.getAllSelectedItems();
		Integer currentPage = grid.getPage();
		if(null != pageSelection && !pageSelection.isEmpty())
		pageSelections.put(currentPage, pageSelection);
	}

	private void pageChange(PageChangeEvent event) {
		Integer newPageNo = event.getNewPage();
		System.out.println("New page no is :: "+newPageNo);
		Set<TransactionDataSet> newPageSelection = pageSelections.get(newPageNo);
		if (null != newPageSelection && !newPageSelection.isEmpty()) {
			System.out.println("No of selected items for the page :: "+newPageSelection.size());
			for (TransactionDataSet dataSet : newPageSelection) {
				grid.select(dataSet);
			}
		}
	}

	private VerticalLayout getToolbar() {
		mobileNumber = new NumberField("Mobile Number");
		mobileNumber.setWidth(100, Unit.PERCENTAGE);
		pincode = new NumberField("Pincode");
		pincode.setWidth(100, Unit.PERCENTAGE);
		loanAccountNumber = new NumberField("Loan A/C Number");
		loanAccountNumber.setWidth(100, Unit.PERCENTAGE);
		firstName = new TextField("Member Name");
		firstName.setWidth(100, Unit.PERCENTAGE);
		parentBranchCB = new ComboBox<Branch>("Cluster");
		parentBranchCB.setWidth(100, Unit.PERCENTAGE);
		parentBranchCB.setItems(branchService.findClusters());
		parentBranchCB.setItemLabelGenerator(Branch::getBranchName);
		parentBranchCB.addValueChangeListener(e -> changeBranches(e));
		branchCB = new ComboBox<Branch>("Branch");
		branchCB.setWidth(100, Unit.PERCENTAGE);
		branchCB.setItems(branchService.findAllBranches(null,null));
		branchCB.setItemLabelGenerator(Branch::getBranchName);
		productCB = new ComboBox<Product>("Product");
		productCB.setWidth(100, Unit.PERCENTAGE);
		List<String> products = new ArrayList<String>();
		products.add("Personal Loan");
		products.add("Home Loan");
		productCB.setItems(productService.findAll(null,null));
		productCB.setItemLabelGenerator(Product::getProductName);
		userCB = new ComboBox<User>("Assigned To");
		userCB.setWidth(100, Unit.PERCENTAGE);
		userCB.setItems(userService.findAllUsers(null, null));
		userCB.setItemLabelGenerator(User::getUserName);

		queueCB = new ComboBox<String>("Queue");
		queueCB.setWidth(100, Unit.PERCENTAGE);
		queueCB.setItems("Marketing", "NPA", "Welcome Call");

		dpdQueueCB = new ComboBox<GenericClassifier>("DPD Queue");
		dpdQueueCB.setWidth(100, Unit.PERCENTAGE);
		dpdQueueCB.setItems(new GenericClassifier(3738L, "30 day DPD"), new GenericClassifier(3739L, "60 day DPD"),
				new GenericClassifier(3740L, "Above 60 DPD"));
		dpdQueueCB.setItemLabelGenerator(GenericClassifier::getDescription);

		noOfRecordsFetched = new Label();

		Button reset = new Button("Reset");
		reset.addClickListener(click -> reset());
		Button searchButton = new Button("Search");
		searchButton.addClickListener(click -> updateList());
		selectAll = new Checkbox("Select all records from all pages");
//		printDocs = new Button("Print Documents");
//		printDocs.addClickListener(e -> printDocuments());
		messageTemplates = new ComboBox<MessageTemplate>("Send Whatsapp");

		messageTemplates.setItems(messageTemplateService.findApprovedMessageTemplates("WHATSAPP"));
		
		messageTemplates.setItemLabelGenerator(MessageTemplate::getMessageTitle);
		previewWhatsapp = new Button("Preview Whatsapp Message");
		previewWhatsapp.addClickListener(click -> previewWhatsapp());
		sendWhatsapp = new Button("Send");
		sendWhatsapp.addClickListener(click -> confirmSend());
		
		HorizontalLayout filterRow1 = new HorizontalLayout(mobileNumber, parentBranchCB , branchCB, productCB, userCB);
		filterRow1.setWidth(100f, Unit.PERCENTAGE);
		HorizontalLayout filterRow2 = new HorizontalLayout(pincode, loanAccountNumber, queueCB, dpdQueueCB, firstName);
		filterRow2.setWidth(100f, Unit.PERCENTAGE);
		HorizontalLayout filterRow3 = new HorizontalLayout(reset, searchButton, noOfRecordsFetched, new Label(""),
				selectAll,new Label(""));
		filterRow3.setWidth(100f, Unit.PERCENTAGE);
		filterRow3.setVerticalComponentAlignment(Alignment.CENTER, reset, searchButton, noOfRecordsFetched,
				selectAll,new Label(""), new Label(""));
		HorizontalLayout filterRow4 = new HorizontalLayout(messageTemplates,previewWhatsapp, sendWhatsapp);
		filterRow3.setWidth(100f, Unit.PERCENTAGE);
		filterRow4.add();
		filterRow4.setVerticalComponentAlignment(Alignment.CENTER, messageTemplates,previewWhatsapp, sendWhatsapp);
		
		VerticalLayout toolbar = new VerticalLayout(filterRow1, filterRow2, filterRow3, filterRow4);
		toolbar.setWidth(100f, Unit.PERCENTAGE);
		toolbar.addClassName("toolbar");
		return toolbar;
	}

	private void changeBranches(ComponentValueChangeEvent<ComboBox<Branch>, Branch> e) {
		Branch selectedCluster = e.getValue();
		if(null != selectedCluster) {
			branchCB.setValue(null);
			branchCB.setItems(branchService.getChildBranches(selectedCluster));
		}
	}

	public void editTransactionDataSet(TransactionDataSet transactionDataSet) {
//		if (transactionDataSet == null) {
//			closeEditor();
//		} else {
//			form.setTransactionDataSet(transactionDataSet);
//			form.setVisible(true);
//			addClassName("editing");
//		}
		editTransactionDialog = new Dialog();

		editTransactionDialog.setHeaderTitle("Transaction Data Detail");
//		TransactionDataSetView transactionDataSetView = new TransactionDataSetView(this,service,transactionDataSet,
//				genericClassifierService);
//		Button saveButton = new Button("Save", e -> transactionDataSetView.save());
//		Button cancelButton = new Button("Cancel", e -> editTransactionDialog.close());
//		editTransactionDialog.add(transactionDataSetView);
//		editTransactionDialog.getFooter().add(saveButton,cancelButton);
		editTransactionDialog.open();
	}

	public void printDocuments() {
		Dialog dialog = new Dialog();

		dialog.setHeaderTitle("Print Documents");
		List<TransactionDataSet> selectedData = new ArrayList<TransactionDataSet>();
		selectedData.addAll(grid.getSelectedItems());
		PrintDocTemplates transactionDataSetView = new PrintDocTemplates(genericClassifierService, selectedData);
		Button cancelButton = new Button("Cancel", e -> dialog.close());
		dialog.add(transactionDataSetView);
		dialog.getFooter().add(cancelButton);
		dialog.open();
	}

	private void closeEditor() {
		form.setTransactionDataSet(null);
		form.setVisible(false);
		removeClassName("editing");
	}

	private boolean validate() {
		boolean valid = false;
		MessageTemplate selectedTemplate = messageTemplates.getValue();
		List<TransactionDataSet> selectedData = new ArrayList<TransactionDataSet>();
		if(selectAll.getValue()) {
			selectedData.addAll(allDataSets);
		}else if(null != pageSelections && !pageSelections.isEmpty()) {
			for(Integer page:pageSelections.keySet()) {
				selectedData.addAll(pageSelections.get(page));
			}
		}
		if (null != selectedTemplate && null != selectedData && !selectedData.isEmpty()) {
			valid = true;
		} else {
			errorMessages.removeAll(errorMessages);
			if (null == selectedTemplate)
				errorMessages.add(new Div(new Text("Please select message template")));
			if (null == selectedData || selectedData.isEmpty())
				errorMessages.add(new Div(new Text("Please select one or more records")));
		}
		return valid;
	}

	private void confirmSend() {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Send Whatsapp message to members");
		dialog.setText("Are you sure you want to send whatsapp message?");
		dialog.setCancelable(true);
		dialog.setConfirmText("Assign");
		dialog.addConfirmListener(event -> sendWhatsapp());
		dialog.open();
	}

	private void sendWhatsapp() {
		if(validate()) {
		@SuppressWarnings("unused")
		UserDetails authenticatedUser = securityService.getAuthenticatedUser();
		List<TransactionDataSet> selectedData = new ArrayList<TransactionDataSet>();
		if(selectAll.getValue()) {
			selectedData.addAll(allDataSets);
		}else if(null != pageSelections && !pageSelections.isEmpty()) {
			for(Integer page:pageSelections.keySet()) {
				selectedData.addAll(pageSelections.get(page));
			}
		}
		//TODO call send SMS service here
		reset();
		}
	}

	public void reset() {
		mobileNumber.setValue(null);
		pincode.setValue(null);
		loanAccountNumber.setValue(null);
		firstName.setValue("");
		parentBranchCB.setValue(null);
		branchCB.setValue(null);
		productCB.setValue(null);
		userCB.setValue(null);
		queueCB.setValue(null);
		dpdQueueCB.setValue(null);
		grid.setItems(new ArrayList<TransactionDataSet>());
		String label = "No of Records fetched : 0";
		noOfRecordsFetched.setText(label);
	}

	public void updateList() {
		if(null != editTransactionDialog)
			editTransactionDialog.close();
		String parentBranchCode = null;
		String branchCode = null, product = null, assignedTo = null;
		Double mobileNumberDouble = mobileNumber.getValue();
		Long mobileNumberNum = null;
		if (null != mobileNumberDouble)
			mobileNumberNum = mobileNumberDouble.longValue();
		Double pincodeDouble = pincode.getValue();
		Long pincodeNum = null;
		if (null != pincodeDouble)
			pincodeNum = pincodeDouble.longValue();
		Double loanAccountNumberDouble = loanAccountNumber.getValue();
		Long loanAccountNumberNum = null;
		if (null != loanAccountNumberDouble)
			loanAccountNumberNum = loanAccountNumberDouble.longValue();
		String memberName = firstName.getValue();
		Branch clusterBranch = parentBranchCB.getValue();
		Branch selectedBranch = branchCB.getValue();
		Product selectedProduct = productCB.getValue();
		String selectedQueue = queueCB.getValue();
		GenericClassifier selectedDpdQueue = dpdQueueCB.getValue();
		if (null != clusterBranch)
			parentBranchCode = clusterBranch.getBranchCode();
		if (null != selectedBranch)
			branchCode = selectedBranch.getBranchCode();
		if (null != selectedProduct)
			product = selectedProduct.getProductName();
		allDataSets = service.findTransactionDataSetForAssignment(mobileNumberNum, parentBranchCode,
				branchCode, assignedTo, product, selectedQueue, selectedDpdQueue, pincodeNum, loanAccountNumberNum,
				memberName);
		String label = "No of Records fetched : " + allDataSets.size();
		noOfRecordsFetched.setText(label);
		grid.setItems(allDataSets);
	}
}
