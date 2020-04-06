package com.example.location.util;


import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SmallViews {
	public static Alert view = new Alert(AlertType.CONFIRMATION);
	public static DialogPane dialog = new DialogPane();
	
	public static String dialogView(String title,String text) {
		view.setTitle(title);
		view.setDialogPane(dialog);
	    dialog.getButtonTypes().setAll(ButtonType.OK,ButtonType.CLOSE);
	    TextField tf = new TextField();
	    tf.setPrefWidth(200);
	    tf.setLayoutX(25);
	    tf.setLayoutY(50);
	    tf.setPrefHeight(25);
	    tf.setPromptText(text);
	    tf.setAlignment(Pos.CENTER);
	    Pane pane = new Pane();
	    pane.getChildren().add(tf);
	    dialog.setContent(pane);
	    view.showAndWait();
	    if(view.getResult() == ButtonType.OK) {
	    	return tf.getText();
	    }else {
	    	return null;
	    }
	    
	}
}
