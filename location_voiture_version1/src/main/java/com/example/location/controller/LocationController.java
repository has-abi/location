package com.example.location.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.Voiture;
import com.example.location.service.facade.VoitureService;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

@Controller
public class LocationController {

//	@Lazy
//	@Autowired
//	private StageManager stageManager;
//	@FXML
//	private Button loadAdress;
//	public void loadAddressView(ActionEvent event) throws IOException {
//		stageManager.switchScene(FxmlView.ADDRESS);
//	}
//	@FXML
//	private TextField paye;
//	@FXML
//	private TextField ville;
//	@FXML
//	private TextField nbr;
//	@FXML
//	private TextField region;
//	@FXML
//	private TextField province;
//	@Lazy
//	@Autowired
//	private AddressService addressService;
//	public void insertAddress(ActionEvent event) {
//		Address address = new Address();
//		address.setNumb(Integer.parseInt(this.nbr.getText()));
//		address.setPaye(this.paye.getText());
//		address.setProvince(this.province.getText());
//		address.setRegion(this.region.getText());
//		address.setVille(this.ville.getText());
//		addressService.save(address);
//	}
	@FXML
	private Pane main;
	private Button ajouter;
	private Button plus;
	private ImageView image;
	private Label car_name;
	private Label car_price;
	@Lazy
	@Autowired
	private VoitureService voitureService;

	@FXML
	public void initialize()  {
		float addx=0;
		float addy = 0;
		int counter=0;
		List<Voiture> listVoitures = voitureService.findAll();
		for(Voiture voiture : listVoitures) {
			if(counter>0 && counter%4==0) {
				addy += 264;
				addx=0;
			}
			car_name = new Label();
			car_price= new Label();
			this.image=new ImageView();
			this.image.setFitHeight(95.0);
			this.image.setFitWidth(141.0);
			this.image.setLayoutX(13.0+ addx);
			this.image.setLayoutY(26.0 + addy);
			this.image.setPickOnBounds(true);
			this.image.setPreserveRatio(true);
			try {
				this.image.setImage(new Image(new FileInputStream("C:\\Users\\21261\\git\\locationV\\location_voiture_version1\\src\\main\\resources\\storage\\"+voiture.getImage())));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.car_name.setLayoutX(25+addx);
			this.car_name.setLayoutY(127+addy);
			this.car_name.setPrefHeight(17);
			this.car_name.setPrefWidth(127);
			this.car_name.setText(voiture.getLibelle());
			this.car_name.setStyle("-fx-text-fill:black");
			this.car_price.setLayoutX(25+addx);
			this.car_name.setAlignment(Pos.CENTER);
			this.car_price.setLayoutY(149+addy);
			this.car_price.setPrefHeight(17);
			this.car_price.setPrefWidth(127);
			this.car_price.setAlignment(Pos.CENTER);
			this.car_price.setStyle("-fx-text-fill:black");
			this.car_price.setText(Double.toString(voiture.getCoutParJour()));
			this.ajouter = new Button("ajouter");
			this.ajouter.setStyle("-fx-background-color:#42f560;-fx-text-fill:white");
			this.ajouter.setLayoutX(16.0 + addx);
			this.ajouter.setLayoutY(175.0 + addy);
			this.ajouter.setPrefHeight(25.0);
			this.ajouter.setPrefWidth(141.0);
			this.ajouter.setMnemonicParsing(false);

			this.plus = new Button("plus");
			this.plus.setStyle("-fx-background-color:#0158FC;-fx-text-fill:white");
			this.plus.setLayoutX(16.0 + addx);
			this.plus.setLayoutY(206.0 + addy);
			this.plus.setPrefHeight(25.0);
			this.plus.setPrefWidth(141.0);
			this.plus.setMnemonicParsing(false);
			this.main.getChildren().addAll(ajouter, plus,image,car_name,car_price);
			addx += 160;
			counter++;
		}
		
		
			
		

	}
	
	
}
