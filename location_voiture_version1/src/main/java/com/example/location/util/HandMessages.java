package com.example.location.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class HandMessages {

	public void show(String message, String type) {
		if(type.equals("warning")) {
			warningMessage(message);
		}
		if(type.equals("danger")) {
			dangerMessage(message);
		}
		if(type.equals("success")) {
			successMessage(message);
		}
	}
	
	public int warningMessage(String message) {
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Message d'alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK) {
        	return 1;
        }else {
        	return -1;
        }
       
	}
	
	public void dangerMessage(String message) {
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Message d'erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
	}
	
	public void successMessage(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Message de Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
	}
}
