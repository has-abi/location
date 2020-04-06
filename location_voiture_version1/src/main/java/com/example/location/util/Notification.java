package com.example.location.util;

import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Notification {
	public static Notifications notifications = Notifications.create();
	
	public static void successNotification(String message) {
		Image image = new Image("/notification/success.png");
		notifications.darkStyle().position(Pos.TOP_RIGHT).title("Success").text(message).graphic(new ImageView(image)).show();
	}
	public static void  warningNotification(String message) {
		Image image = new Image("/notification/warning.png");
		notifications.darkStyle().position(Pos.TOP_RIGHT).title("warning").text(message).graphic(new ImageView(image)).show();
	}
	public static void  infoNotification(String message) {
		Image image = new Image("/notification/info.png");
		notifications.darkStyle().position(Pos.TOP_RIGHT).title("info").text(message).graphic(new ImageView(image)).show();
	}
	public static void  errorNotification(String message) {
		Image image = new Image("/notification/error.png");
		notifications.darkStyle().position(Pos.TOP_RIGHT).title("error").text(message).graphic(new ImageView(image)).show();
	}
}
