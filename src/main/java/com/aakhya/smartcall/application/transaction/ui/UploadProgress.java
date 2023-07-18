package com.aakhya.smartcall.application.transaction.ui;

import java.util.List;

import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhya.smartcall.application.transaction.data.service.TransactionDataSetService;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;

public class UploadProgress extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -206508260649540003L;
	TransactionDataSetService transactionDataSetSerive;
	List<TransactionDataSet> transactionDataSets;
	private Long initialCount;
	private Long createCount;
	ProgressBar progressBar;

	public UploadProgress(TransactionDataSetService transactionDataSetSerive,
			List<TransactionDataSet> transactionDataSets,
			Long initialCount,
			Long createCount) {
		VerticalLayout mainContent = new VerticalLayout();
		this.transactionDataSetSerive = transactionDataSetSerive;
		this.transactionDataSets = transactionDataSets;
		this.initialCount = initialCount;
		this.createCount = createCount;
		progressBar = new ProgressBar();
		progressBar.setIndeterminate(true);

		Div progressBarLabel = new Div();
		progressBarLabel.setText("Updating Transaction Data");

		Div progressBarSubLabel = new Div();
		progressBarSubLabel.getStyle().set("font-size",
		        "var(--lumo-font-size-xs)");
//		progressBarSubLabel.setText("Process can take upwards of 6 minutes");
		mainContent.add(progressBarLabel, progressBar, progressBarSubLabel);
		add(mainContent);
		startProcess();
		checkIfTaskComplete();
	}
	
	private void startProcess() {
		ExecuteTransactionUpdate saveTransactionData = new ExecuteTransactionUpdate(transactionDataSets);
		saveTransactionData.run();
		
	}

	private void checkIfTaskComplete() {
		long totalComplete = initialCount;
		double percentageComplete = 0;
		while(totalComplete==initialCount+createCount) {
			totalComplete = transactionDataSetSerive.getDataSetCount();
			percentageComplete = totalComplete/(initialCount+createCount);
			progressBar.setValue(percentageComplete);
		}
		this.close();
	}
	
	class ExecuteTransactionUpdate implements Runnable{

		List<TransactionDataSet> transactionDataSets;
		ExecuteTransactionUpdate(List<TransactionDataSet> transactionDataSets){
			this.transactionDataSets = transactionDataSets;
		}
		@Override
		public void run() {
			
			transactionDataSetSerive.createTransactionDataSet(transactionDataSets);//,this::percentageUpdated,this::processingSucceeded);
		}
		
	}
}