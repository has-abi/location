package com.example.location.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import com.example.location.bean.Voiture;
import com.example.location.config.StageManager;
import com.example.location.service.facade.AgenceService;
import com.example.location.service.facade.CategorieService;
import com.example.location.service.facade.MarqueService;
import com.example.location.service.facade.VoitureService;
import com.example.location.util.FileUtil;
import com.example.location.util.Notification;
import com.example.location.util.Session;
import com.example.location.views.FxmlView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

@Component
public class ModifierVoitureController implements Initializable{
		@Lazy
		@Autowired
		private StageManager stageManager;
		@Lazy
		@Autowired
		private VoitureService VoitureService;
		@Lazy
		@Autowired
		private CategorieService categorieService;
		@Lazy
		@Autowired
		private MarqueService marqueService;
		@Lazy
		@Autowired
		private AgenceService agenceService;
		
		@FXML
	    private TextArea modif_descreption;

	    @FXML
	    private ComboBox<String> modif_agence;

	    @FXML
	    private ComboBox<String> modif_marque;

	    @FXML
	    private ComboBox<String> modif_categorie;

	    @FXML
	    private Label modif_image;

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
	    private TextField modif_libelle;

	    @FXML
	    private TextField modif_matricule;

	    @FXML
	    private TextField modif_couleur;

	    @FXML
	    private TextField modif_prix;

		@FXML
		void handleClose(MouseEvent event) {
			Platform.exit();
			System.exit(0);
		}
	    @FXML
	    void handlBack(ActionEvent event) {
	    	this.stageManager.switchScene(FxmlView.ADMIN);
	    }

	    @FXML
	    void handlModifier(ActionEvent event) {
	    	Voiture voiture = new Voiture();
	    	voiture.setId(Session.getSessionVoiture("voiture").getId());
	    	voiture.setAgence(agenceService.findByName(this.modif_agence.getSelectionModel().getSelectedItem()));
	    	voiture.setCategorie(categorieService.findByName(this.modif_categorie.getSelectionModel().getSelectedItem()));
	    	voiture.setMarque(marqueService.findByBrand(this.modif_marque.getSelectionModel().getSelectedItem()));
	    	voiture.setColor(this.modif_couleur.getText());
	    	voiture.setCoutParJour(Double.parseDouble(this.modif_prix.getText()));
	    	voiture.setDiscreption(this.modif_descreption.getText());
	    	voiture.setImage(this.modif_image.getText());
	    	voiture.setLibelle(this.modif_libelle.getText());
	    	voiture.setMatricule(this.modif_matricule.getText());
	    	if(VoitureService.save(voiture)==1) {
	    		Notification.successNotification("voiture modifier avec succée!");
	    	}else {
	    		Notification.warningNotification("il'ya un problème! la voiture n'est pas modifier");
	    	}
	    }

	    @FXML
	    void uploadImage(ActionEvent event) {
	    	String image = FileUtil.getNameFromFileChooser(new FileChooser());
	    	if(image !=null) {
	    		this.modif_image.setText(image);
	    	}
	    }

		@Override
		public void initialize(URL url, ResourceBundle bundle) {
			//left side of the view infos about the car
			Voiture v = Session.getSessionVoiture("voiture");
			this.voiture_categorie.setText(v.getCategorieNom());
			this.voiture_couleur.setText(v.getColor());
			this.voiture_description.setText(v.getDiscreption());
			this.voiture_marque.setText(v.getMarqueBrande());
			this.voiture_image.setImage(new Image("/storage/"+ v.getImage()));
			this.voiture_nom.setText(v.getLibelle());
			this.voiture_prix.setText(Double.toString(v.getCoutParJour()));
			//right side modify inputs
			this.agenceService.findAll().forEach(agence->this.modif_agence.getItems().add(agence.getAdress()));
			this.categorieService.findAll().forEach(categorie->this.modif_categorie.getItems().add(categorie.getName()));
			this.marqueService.findAll().forEach(marque->this.modif_marque.getItems().add(marque.getBrand()));
			this.modif_agence.getSelectionModel().select(v.getAgenceName());
			this.modif_categorie.getSelectionModel().select(v.getCategorieNom());
			this.modif_marque.getSelectionModel().select(v.getMarqueBrande());
			this.modif_couleur.setText(v.getColor());
			this.modif_descreption.setText(v.getDiscreption());
			this.modif_image.setText(v.getImage());
			this.modif_libelle.setText(v.getLibelle());
			this.modif_matricule.setText(v.getMatricule());
			this.modif_prix.setText(Double.toString(v.getCoutParJour()));
		}
}
