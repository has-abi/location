package com.example.location.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.User;
import com.example.location.config.StageManager;
import com.example.location.service.facade.UserService;
import com.example.location.util.HandMessages;
import com.example.location.util.Notification;
import com.example.location.util.Session;
import com.example.location.views.FxmlView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

@Controller
public class RegisterController {
	@Lazy
	@Autowired
	private StageManager stageManager;
	
	@Lazy
	@Autowired
	private UserService userService;
    @FXML
    private TextField email;
    
    @FXML
    private TextField address;

    @FXML
    private PasswordField pwd;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private Label success;
    
    @FXML
    private ToggleGroup gender;
  
    @FXML
    void handleClose(MouseEvent event) {
    	Platform.exit();
		System.exit(0);
    }
    @FXML
    void handleLogin(ActionEvent event) {
    	this.stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    void handleRegister(ActionEvent event){
    	HandMessages messgInfo = new HandMessages();
    	User user = new User();
    	if(validateRegisterForm() == -1) {
    		String style = "-fx-background-color:transparent;-fx-border-color:red;-fx-border-radius:7px;-fx-text-fill: #ACABAB";
    		this.email.setStyle(style);
    		this.pwd.setStyle(style);
    		this.address.setStyle(style);
    		messgInfo.show("tout les champs sonts necessaires !", "warning");
    	}else {
    		user.setEmail(this.email.getText());
    		user.setPassword(this.pwd.getText());
    		user.setAdress(this.address.getText());
    		if(this.male.isSelected()) {
    			user.setGender(this.male.getText());
    		}
    		if(this.female.isSelected()) {
    			user.setGender(this.female.getText());
    		}
    		if(userService.register(user) < 0) {
    			messgInfo.dangerMessage("Email utilisé essayer un autre !");
    		}else {
    			Session.setSessionUser(user, "user");
    			Notification.successNotification("vous avez s'inscrire avec succée!");
    			this.stageManager.switchScene(FxmlView.WELCOME);
    		}
    	}
    	
    }
    
    public int validateRegisterForm(){
    	if(Strings.isBlank(this.email.getText()) || Strings.isBlank(pwd.getText()) || Strings.isBlank(this.address.getText())
    			|| (!this.male.isSelected() && !this.female.isSelected())){
    		return -1;
    	}else {
    		return 1;
    	}
    }
    
    
}
