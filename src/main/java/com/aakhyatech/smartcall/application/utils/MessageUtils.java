package com.aakhyatech.smartcall.application.utils;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.notification.Notification;

public class MessageUtils {

	public static void confirmSaveMessageBox(SmartCallWebForm form,String objectName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Saving "+objectName);
		dialog.setText("Are you sure you want to save "+objectName+"?");

		dialog.setCancelable(true);

		dialog.setConfirmText("Save");
		dialog.addConfirmListener(event -> form.save());
		dialog.open();
	}
	
	public static void confirmPainMessageBox(SmartCallWebForm form,String header,String painText) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader(header);
		dialog.setText(painText);

		dialog.setCancelable(true);

		dialog.setConfirmText("Proceed");
		dialog.addConfirmListener(event -> form.save());
		dialog.open();
	}
	
	public static void confirmDeleteMessageBox(SmartCallWebForm form,String objectName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Delete "+objectName);
		dialog.setText("Are you sure you want to delete "+objectName+"?");

		dialog.setCancelable(true);

		dialog.setConfirmText("Delete");
		dialog.addConfirmListener(event -> form.delete());
		dialog.open();
	}
	
	public static void showSaveNotification(String objectName) {
		Notification.show(objectName+" Saved Successfully", 5000, Notification.Position.MIDDLE);
	}
	
	public static void showDeleteNotification(String objectName) {
		Notification.show(objectName+" Deleted Successfully", 5000, Notification.Position.MIDDLE);
	}
	
	public static void showPlainNotification(String objectName) {
		Notification.show(objectName, 5000, Notification.Position.MIDDLE);
	}
}
