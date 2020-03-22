package com.example.location.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.Voiture;
import com.example.location.config.StageManager;
import com.example.location.util.Session;
import com.example.location.views.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

@Controller
public class DetailsController {
	@FXML
	private AnchorPane parent;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@FXML
	private ImageView voiture_image;

	@FXML
	private Label voiture_nom;

	@FXML
	private Label voiture_marque;

	@FXML
	private Label voiture_categorie;

	@FXML
	private Label voiture_prix;

	@FXML
	private Label voiture_couleur;

	@FXML
	private Label voiture_description;

	@FXML
	private Button back;

	@FXML
	public void initialize(){
		Voiture voiture = Session.getSessionVoiture("voiture");
		try {
			this.voiture_image.setImage(new Image(new FileInputStream(
					"C:\\Users\\21261\\git\\locationV\\location_voiture_version1\\src\\main\\resources\\storage\\"
							+ voiture.getImage())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.voiture_nom.setText(voiture.getLibelle());
		this.voiture_marque.setText(voiture.getMarqueBrand());
		this.voiture_categorie.setText(voiture.getCategorieName());
		this.voiture_prix.setText("Le coût par Jour :"+Double.toString(voiture.getCoutParJour())+"DH");
		this.voiture_description.setText("Description: "+voiture.getDiscreption());
		this.voiture_couleur.setText("Couleur"+voiture.getColor());
		Session.removeSessionVoiture();
	}
	@FXML
	public void wel_back(ActionEvent event) {
		this.stageManager.switchScene(FxmlView.WELCOME);
		
	}
}
