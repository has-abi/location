package com.example.location.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.User;
import com.example.location.config.StageManager;
import com.example.location.service.facade.UserService;
import com.example.location.util.HandMessages;
import com.example.location.util.Session;
import com.example.location.views.FxmlView;

import ch.qos.logback.core.db.dialect.MsSQLDialect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Controller
public class LoginController {
	@Lazy
	@Autowired
	private StageManager stageManager;

	@Lazy
	@Autowired
	private UserService userService;
	@FXML
	private TextField email;

	@FXML
	private PasswordField pwd;

	@FXML
	private Label warning;

	@FXML
	void loadRegisterView(ActionEvent event) {
		this.stageManager.switchScene(FxmlView.REGISTER);
	}

	@FXML
	void login(ActionEvent event) {
		HandMessages messgInfo = new HandMessages();
		User user = new User();
		if (validateLogin() < 0) {
			messgInfo.warningMessage("tout les champs sont obligatoirs!");
		} else {
			user.setEmail(this.email.getText());
			user.setPassword(this.pwd.getText());
			if (userService.login(user) == -1) {
				messgInfo.warningMessage("vous n'avez pas de compte veuillez le créer ! ");
			} else if (userService.login(user) == -2) {
				messgInfo.warningMessage("Mot de passe Incorrect!");
			} else {
				if (userService.login(user) == 2) {
					User userLogged = userService.findByEmail(user.getEmail());
					Session.setSessionUser(userLogged, "admin");
					stageManager.switchScene(FxmlView.ADMIN);
				} else {
					User userLogged = userService.findByEmail(user.getEmail());
					Session.setSessionUser(userLogged, "user");
					stageManager.switchScene(FxmlView.WELCOME);
				}
			}
		}
	}

	public int validateLogin() {
		if (Strings.isBlank(this.email.getText()) || Strings.isBlank(this.pwd.getText())) {
			return -1;
		} else {
			return 1;
		}
	}

}
