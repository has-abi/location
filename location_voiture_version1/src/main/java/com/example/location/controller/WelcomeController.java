package com.example.location.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.Voiture;
import com.example.location.config.StageManager;
import com.example.location.service.facade.VoitureService;
import com.example.location.util.Session;
import com.example.location.views.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

@Controller
public class WelcomeController {
	@Lazy
	@Autowired
	private StageManager stageManager;
	@FXML
	private AnchorPane parent;
	@FXML
	private Pane home;
	private Button ajouter;
	private Button plus;
	private ImageView image;
	private Label car_name;
	private Label car_price;
	@Lazy
	@Autowired
	private VoitureService voitureService;
	@FXML
	private Button about;

	@FXML
	private Button contact;

	@FXML
	private Label user_location;

	@FXML
	private Pane myCars;

	@FXML
	private Pane _about;

	@FXML
	private Pane _contact;

	private Button next;
	private Button prev;

	@FXML
	void handleAbout(ActionEvent event) {
		this.parent.getChildren().setAll(this._about);
	}

	@FXML
	void handleClose(MouseEvent event) {
		
	}

	@FXML
	void handleContact(ActionEvent event) {
		this.parent.getChildren().setAll(this._contact);
	}

	@FXML
	void handleHome(ActionEvent event) {
		this.parent.getChildren().setAll(this.home);
	}

	@FXML
	void handleMyCars(ActionEvent event) {
		this.parent.getChildren().setAll(this.myCars);
	}

	private List<Voiture> listVoitures;

	@FXML
	public void initialize() {
		this.listVoitures = voitureService.findAllWithPagination("first");
		this.next = new Button("Après");
		this.next.setLayoutX(502);
		this.next.setLayoutY(522);
		this.next.setPrefHeight(27);
		this.next.setPrefWidth(141);
		this.next.setStyle("-fx-background-color: transparent;-fx-text-fill:#545353");
		this.home.getChildren().add(this.next);
		this.prev = new Button("Avant");
		this.prev.setStyle("-fx-background-color: transparent;-fx-text-fill:#545353");
		this.prev.setLayoutX(14);
		this.prev.setLayoutY(522);
		this.prev.setPrefHeight(27);
		this.prev.setPrefWidth(141);
		this.next.addEventHandler(ActionEvent.ACTION, e -> {
			this.home.getChildren().clear();
			chargerLesVoitures(voitureService.findAllWithPagination("next"));
			this.home.getChildren().add(this.prev);
		});
		this.prev.addEventHandler(ActionEvent.ACTION, e -> {
			this.home.getChildren().clear();
			this.home.getChildren().add(this.next);
			chargerLesVoitures(voitureService.findAllWithPagination("prev"));
		});
		chargerLesVoitures(this.listVoitures);
	}

	public void chargerLesVoitures(List<Voiture> voitures) {
		float addx = 0;
		float addy = 0;
		int counter = 0;

		for (Voiture voiture : voitures) {
			if (counter > 0 && counter % 4 == 0) {
				addy += 264;
				addx = 0;
			}
			car_name = new Label();
			car_price = new Label();
			this.image = new ImageView();
			this.image.setFitHeight(95.0);
			this.image.setFitWidth(141.0);
			this.image.setLayoutX(13.0 + addx);
			this.image.setLayoutY(40.0 + addy);
			this.image.setPickOnBounds(true);
			this.image.setPreserveRatio(true);
			try {
				this.image.setImage(new Image(new FileInputStream(
						"C:\\Users\\21261\\git\\locationV\\location_voiture_version1\\src\\main\\resources\\storage\\"
								+ voiture.getImage())));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			this.car_name.setLayoutX(25 + addx);
			this.car_name.setLayoutY(141 + addy);
			this.car_name.setPrefHeight(17);
			this.car_name.setPrefWidth(127);
			this.car_name.setText(voiture.getLibelle());
			this.car_name.setStyle("-fx-text-fill:black");
			this.car_price.setLayoutX(25 + addx);
			this.car_name.setAlignment(Pos.CENTER);
			this.car_price.setLayoutY(163 + addy);
			this.car_price.setPrefHeight(17);
			this.car_price.setPrefWidth(127);
			this.car_price.setAlignment(Pos.CENTER);
			this.car_price.setStyle("-fx-text-fill:black");
			this.car_price.setText(Double.toString(voiture.getCoutParJour()));
			this.ajouter = new Button("ajouter");
			this.ajouter.setStyle("-fx-background-color:#42f560;-fx-text-fill:white");
			this.ajouter.setLayoutX(16.0 + addx);
			this.ajouter.setLayoutY(189.0 + addy);
			this.ajouter.setPrefHeight(25.0);
			this.ajouter.setPrefWidth(141.0);
			this.ajouter.setMnemonicParsing(false);

			this.plus = new Button("plus");
			this.plus.setStyle("-fx-background-color:#0158FC;-fx-text-fill:white");
			this.plus.setLayoutX(16.0 + addx);
			this.plus.setLayoutY(220.0 + addy);
			this.plus.setPrefHeight(25.0);
			this.plus.setPrefWidth(141.0);
			this.plus.setMnemonicParsing(false);
			this.plus.addEventHandler(ActionEvent.ACTION, e -> {
				Session.SetSessionVoiture(voiture);
				this.stageManager.switchScene(FxmlView.MORE);

			});
			this.home.getChildren().addAll(ajouter, plus, image, car_name, car_price);
			addx += 160;
			counter++;

		}
	}

}
