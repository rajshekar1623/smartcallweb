package com.aakhyatech.smartcall.application.utils;

import com.aakhya.smartcall.application.SmartCallWebForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MessageUtils {

	public static void confirmSaveMessageBox(SmartCallWebForm form, String objectName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Saving " + objectName);
		dialog.setText("Are you sure you want to save " + objectName + "?");

		dialog.setCancelable(true);

		dialog.setConfirmText("Save");
		dialog.addConfirmListener(event -> form.save());
		dialog.open();
	}

	public static void confirmPainMessageBox(SmartCallWebForm form, String header, String painText) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader(header);
		dialog.setText(painText);

		dialog.setCancelable(true);

		dialog.setConfirmText("Proceed");
		dialog.addConfirmListener(event -> form.save());
		dialog.open();
	}

	public static void confirmDeleteMessageBox(SmartCallWebForm form, String objectName) {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Delete " + objectName);
		dialog.setText("Are you sure you want to delete " + objectName + "?");

		dialog.setCancelable(true);

		dialog.setConfirmText("Delete");
		dialog.addConfirmListener(event -> form.delete());
		dialog.open();
	}

	public static void validationMessage(String text) {
		Dialog dialog = new Dialog();
		dialog.setHeaderTitle("Validation Error");
		dialog.setResizable(false);
		VerticalLayout content = new VerticalLayout();
		H3 message = new H3(text);
		Button ok = new Button("ok");
		ok.addClickListener(e -> dialog.close());
		content.add(message, ok);
		dialog.add(content);
		dialog.setModal(true);
		dialog.open();
	}
	
	public static void successMessage(String text) {
		Dialog dialog = new Dialog();
		dialog.setHeaderTitle("Success!!!");
		dialog.setResizable(false);
		VerticalLayout content = new VerticalLayout();
		H3 message = new H3(text);
		Button ok = new Button("ok");
		ok.addClickListener(e -> dialog.close());
		content.add(message, ok);
		dialog.add(content);
		dialog.setModal(true);
		dialog.open();
	}

	public static void showSaveNotification(String objectName) {
		Notification.show(objectName + " Saved Successfully", 5000, Notification.Position.MIDDLE);
	}

	public static void showDeleteNotification(String objectName) {
		Notification.show(objectName + " Deleted Successfully", 5000, Notification.Position.MIDDLE);
	}

	public static void showPlainNotification(String messageStr) {
		Dialog dialog = new Dialog();
		dialog.setHeaderTitle("Result");
		dialog.setResizable(false);
		VerticalLayout content = new VerticalLayout();
		H3 message = new H3(messageStr);
		Button ok = new Button("OK");
		ok.addClickListener(e -> dialog.close());
		content.add(message, ok);
		dialog.add(content);
		dialog.setModal(true);
		dialog.open();
	}
	
	public static void showPreviewMessage(String messageStr) {
		Dialog dialog = new Dialog();
		dialog.setHeaderTitle("Preview");
		dialog.setResizable(false);
		VerticalLayout content = new VerticalLayout();
		H3 message = new H3(messageStr);
		Button ok = new Button("OK");
		ok.addClickListener(e -> dialog.close());
		content.add(message, ok);
		dialog.add(content);
		dialog.setModal(true);
		dialog.open();
	}
}
