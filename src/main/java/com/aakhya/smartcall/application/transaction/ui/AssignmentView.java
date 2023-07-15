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
import com.aakhya.smartcall.application.transaction.activity.service.ActivityService;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhya.smartcall.application.transaction.data.service.TransactionDataSetService;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.selection.SelectionEvent;
//import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route(value = "transaction", layout = TransactionMainLayout.class)
@PermitAll
public class AssignmentView extends VerticalLayout {

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
	Button printDocs;
	AssignmentForm form;
	HorizontalLayout secondaryComponent;
	TransactionDataSetService service;
	BranchService branchService;
	UserService userService;
	GenericClassifierService genericClassifierService;
	ActivityService activityService;
	SecurityService securityService;
	ProductService productService;
	VerticalLayout content;
	ComboBox<User> assignTo;
	Button assign;
	Checkbox selectAll;
	List<Div> errorMessages = new ArrayList<Div>();
	List<User> users;
	Map<Integer, Set<TransactionDataSet>> pageSelections = new HashMap<Integer, Set<TransactionDataSet>>();

	public AssignmentView(TransactionDataSetService service, UserService userService, BranchService branchService,
			GenericClassifierService genericClassifierService, ActivityService activityService,
			ProductService productService, SecurityService securityService) {
		this.service = service;
		this.branchService = branchService;
		this.userService = userService;
		this.genericClassifierService = genericClassifierService;
		this.productService = productService;
		this.activityService = activityService;
		this.securityService = securityService;
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
		content = new VerticalLayout(grid, secondaryComponent);
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
		assignTo = new ComboBox<User>();

		users = userService.findUsersByRole(2L);
		assignTo.setItems(users);
		if (null != branchCB && null != branchCB.getValue()) {
			if (null != users && !users.isEmpty()) {
				Branch selectedBranch = branchCB.getValue();
				for (User user : users) {
					if (user.getBranchCode().equals(selectedBranch.getBranchCode()))
						assignTo.setValue(user);
				}
			}
		} else
			assignTo.setValue(null);
		assignTo.setItemLabelGenerator(User::getUserName);
		assign = new Button("Assign");
		assign.addClickListener(click -> confirmAssign());
		secondaryComponent = new HorizontalLayout();
		Label assignLable = new Label("Assign To");
		secondaryComponent.setSizeFull();
		secondaryComponent.add(assignLable, assignTo, assign);
		secondaryComponent.setVerticalComponentAlignment(Alignment.CENTER, assignLable, assignTo, assign);
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
		assignTo = new ComboBox<User>();

		users = userService.findUsersByRole(2L);
		assignTo.setItems(users);
		if (null != branchCB && null != branchCB.getValue()) {
			if (null != users && !users.isEmpty()) {
				Branch selectedBranch = branchCB.getValue();
				for (User user : users) {
					if (user.getBranchCode().equals(selectedBranch.getBranchCode()))
						assignTo.setValue(user);
				}
			}
		} else
			assignTo.setValue(null);
		assignTo.setItemLabelGenerator(User::getUserName);
		assign = new Button("Assign");
		assign.addClickListener(click -> confirmAssign());
		HorizontalLayout secondaryComponent = new HorizontalLayout();
		Label assignLable = new Label("Assign To");
		secondaryComponent.setSizeFull();
		secondaryComponent.add(assignLable, assignTo, assign);
		secondaryComponent.setVerticalComponentAlignment(Alignment.CENTER, assignLable, assignTo, assign);

//		gridLayout.addToSecondary(secondaryComponent);

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
		branchCB.setItems(branchService.findAllBranches(null));
		branchCB.setItemLabelGenerator(Branch::getBranchName);
		branchCB.addValueChangeListener(e -> branchValueChanged());
		productCB = new ComboBox<Product>("Product");
		productCB.setWidth(100, Unit.PERCENTAGE);
		List<String> products = new ArrayList<String>();
		products.add("Personal Loan");
		products.add("Home Loan");
		productCB.setItems(productService.findAll(null));
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
		printDocs = new Button("Print Documents");
		printDocs.addClickListener(e -> printDocuments());

		HorizontalLayout filterRow1 = new HorizontalLayout(mobileNumber, parentBranchCB , branchCB, productCB, userCB);
		filterRow1.setWidth(100f, Unit.PERCENTAGE);
		HorizontalLayout filterRow2 = new HorizontalLayout(pincode, loanAccountNumber, queueCB, dpdQueueCB, firstName);
		filterRow2.setWidth(100f, Unit.PERCENTAGE);
		HorizontalLayout filterRow3 = new HorizontalLayout(reset, searchButton, noOfRecordsFetched, new Label(""),
				selectAll,new Label(""), printDocs);
		filterRow3.setWidth(100f, Unit.PERCENTAGE);
		filterRow3.setVerticalComponentAlignment(Alignment.CENTER, reset, searchButton, noOfRecordsFetched,
				selectAll,new Label(""), new Label(""), printDocs);
		VerticalLayout toolbar = new VerticalLayout(filterRow1, filterRow2, filterRow3);
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
		Dialog dialog = new Dialog();

		dialog.setHeaderTitle("Transaction Data Detail");
		TransactionDataSetView transactionDataSetView = new TransactionDataSetView(transactionDataSet,
				genericClassifierService);
		Button cancelButton = new Button("Cancel", e -> dialog.close());
		dialog.add(transactionDataSetView);
		dialog.getFooter().add(cancelButton);
		dialog.open();
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
		User userToAssignTo = assignTo.getValue();
		List<TransactionDataSet> selectedData = new ArrayList<TransactionDataSet>();
		if(selectAll.getValue()) {
			selectedData.addAll(allDataSets);
		}else if(null != pageSelections && !pageSelections.isEmpty()) {
			for(Integer page:pageSelections.keySet()) {
				selectedData.addAll(pageSelections.get(page));
			}
		}
		if (null != userToAssignTo && null != selectedData && !selectedData.isEmpty()) {
			valid = true;
		} else {
			errorMessages.removeAll(errorMessages);
			if (null == userToAssignTo)
				errorMessages.add(new Div(new Text("Please select user to assign")));
			if (null == selectedData || selectedData.isEmpty())
				errorMessages.add(new Div(new Text("Please select one or more records")));
		}
		return valid;
	}

	private void confirmAssign() {
		if (validate()) {
			System.out.println("Inside Confirm");
			Branch branch = branchCB.getValue();
			User assignedToUser = userCB.getValue();
			User assignToUser = assignTo.getValue();
			if (null != branch && null != assignToUser && null != branch.getBranchCode()
					&& null != assignToUser.getBranchCode()
					&& !branch.getBranchCode().equals(assignToUser.getBranchCode())) {
				ConfirmDialog dialog = new ConfirmDialog();
				dialog.setHeader("Assign Data to Users");
				dialog.setText("The account belongs to " + branch.getBranchCode()
						+ " Branch and the user being assigned to belongs to " + assignToUser.getBranchCode()
						+ " Branch.Do you still want to continue?");
				dialog.setCancelable(true);
				dialog.setConfirmText("Assign");
				dialog.addConfirmListener(event -> confirmFinalAssign());
				dialog.open();
			} else if (null != assignedToUser && null != assignedToUser.getBranchCode() && null != assignToUser
					&& null != assignToUser.getBranchCode()
					&& !assignToUser.getBranchCode().equals(assignedToUser.getBranchCode())) {
				ConfirmDialog dialog = new ConfirmDialog();
				dialog.setHeader("Assign Data to Users");
				dialog.setText("The account belongs to " + assignedToUser.getBranchCode()
						+ " Branch and the user being assigned to belongs to " + assignToUser.getBranchCode()
						+ " Branch.Do you still want to continue?");
				dialog.setCancelable(true);
				dialog.setConfirmText("Assign");
				dialog.addConfirmListener(event -> confirmFinalAssign());
				dialog.open();
			} else
				confirmFinalAssign();
		} else {
			Dialog dialog = new Dialog();
			dialog.setHeaderTitle("Validation Failed");
			Button closeButton = new Button(new Icon("lumo", "cross"));
			closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
			closeButton.getElement().setAttribute("aria-label", "Close");
			closeButton.addClickListener(event -> {
				dialog.close();
			});
			VerticalLayout layout = new VerticalLayout();
			if (!errorMessages.isEmpty()) {
				for (Div div : errorMessages) {
					layout.add(div);
				}
			}
			layout.add(closeButton);
			layout.setAlignItems(Alignment.CENTER);
			dialog.add(layout);
			dialog.open();
//			Notification.show(errorMessage.toString(), 5000, Notification.Position.MIDDLE);
		}
//			System.out.println("Validation Failed");
	}

	private void confirmFinalAssign() {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Assign Data to Users");
		dialog.setText("Are you sure you want to assign");

		dialog.setCancelable(true);

		dialog.setConfirmText("Assign");
		dialog.addConfirmListener(event -> assign());
		dialog.open();
	}

	private void assign() {
		UserDetails authenticatedUser = securityService.getAuthenticatedUser();
		User branchMgrUser = new User();
		branchMgrUser.setUserId(authenticatedUser.getUsername());
		branchMgrUser.setUserName(authenticatedUser.getUsername());
		User userToAssignTo = assignTo.getValue();
		List<TransactionDataSet> selectedData = new ArrayList<TransactionDataSet>();
		if(selectAll.getValue()) {
			selectedData.addAll(allDataSets);
		}else if(null != pageSelections && !pageSelections.isEmpty()) {
			for(Integer page:pageSelections.keySet()) {
				selectedData.addAll(pageSelections.get(page));
			}
		}
		User assignedTo = userCB.getValue();
		if (null != assignedTo && null != assignedTo.getUserId())
			activityService.assignToUser(selectedData, branchMgrUser, assignedTo.getUserId(), userToAssignTo);
		else
			activityService.assignToUser(selectedData, branchMgrUser, null, userToAssignTo);
		reset();
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

	private void updateList() {
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
		User selectedUser = userCB.getValue();
		if (null != clusterBranch)
			parentBranchCode = clusterBranch.getBranchCode();
		if (null != selectedBranch)
			branchCode = selectedBranch.getBranchCode();
		if (null != selectedProduct)
			product = selectedProduct.getProductName();
		if (null != selectedUser) {
			assignedTo = selectedUser.getUserId();
			assign.setText("Re Assign");
		} else
			assign.setText("Assign");
		allDataSets = service.findTransactionDataSetForAssignment(mobileNumberNum, parentBranchCode,
				branchCode, assignedTo, product, selectedQueue, selectedDpdQueue, pincodeNum, loanAccountNumberNum,
				memberName);
		String label = "No of Records fetched : " + allDataSets.size();
		noOfRecordsFetched.setText(label);
		grid.setItems(allDataSets);
	}

	private void branchValueChanged() {
		if (null != branchCB.getValue()) {
			if (null != users && !users.isEmpty()) {
				Branch selectedBranch = branchCB.getValue();
				for (User user : users) {
					if (user.getBranchCode().equals(selectedBranch.getBranchCode()))
						assignTo.setValue(user);
				}
			}
		} else
			assignTo.setValue(null);
	}
}
