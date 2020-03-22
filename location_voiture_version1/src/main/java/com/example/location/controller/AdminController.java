package com.example.location.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.Voiture;
import com.example.location.config.StageManager;
import com.example.location.service.facade.CategorieService;
import com.example.location.service.facade.MarqueService;
import com.example.location.service.facade.VoitureService;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

@Controller
public class AdminController implements Initializable{
	@Lazy
	@Autowired
	private StageManager stageManager;
	  @FXML
	    private Label user_location;
	  @FXML
	    private Pane home;
	    @FXML
	    private Pane searchCar;
	    @FXML
	    private AnchorPane parent;

	    @FXML
	    private TableView<Voiture> table_car;
	    
	    @FXML
	    private TableColumn<Voiture,String >table_categorie;
	    
	    @FXML
	    private TableColumn<Voiture,String > table_libelle;

	    @FXML
	    private TableColumn<Voiture,String > table_matricule;

	    @FXML
	    private TableColumn<Voiture, Double> table_prix;

	    @FXML
	    private TableColumn<?, ?> table_price;

	    @FXML
	    private TableColumn<Voiture,String > table_couleur;

	    @FXML
	    private TableColumn<Voiture,String > table_marque;

	    @FXML
	    private TableColumn<Button, Button> delete;
	    
	    @FXML
	    private TableColumn<Voiture, Long> table_id;
	    @FXML
	    private TableColumn<Voiture,String > table_agence;
	    @FXML
	    private TableColumn<Button,Button> modify;
	    

	    @FXML
	    private ImageView img_2;

	    @FXML
	    private ImageView img_1;

	    @FXML
	    private Label car_name_2;

	    @FXML
	    private Label _mat_2;

	    @FXML
	    private Label car_price_2;

	    @FXML
	    private Label car_name_1;

	    @FXML
	    private Label _mat_1;

	    @FXML
	    private Label car_price_1;

	    @FXML
	    private Button next;

	    @FXML
	    private ImageView img_4;

	    @FXML
	    private ImageView img_3;

	    @FXML
	    private Label car_name_4;

	    @FXML
	    private Label _mat_4;

	    @FXML
	    private Label car_price_4;

	    @FXML
	    private Label car_name_3;

	    @FXML
	    private Label _mat_3;

	    @FXML
	    private Label car_price_3;

	    @FXML
	    private Pane searchUser;

	    @FXML
	    private TableView<?> table_user;

	    @FXML
	    private TableColumn<?, ?> col_email;

	    @FXML
	    private TableColumn<?, ?> col_location;

	    @FXML
	    private TableColumn<?, ?> col_genderr;

	    @FXML
	    private Pane addCar;

	    @FXML
	    private Label msg;

	    @FXML
	    private TextField car_name;

	    @FXML
	    private TextField matricule;

	    @FXML
	    private TextField color;

	    @FXML
	    private TextField price;
	    
	    @FXML
	    private ComboBox<String> categorie = new ComboBox<String>();

	    @FXML
	    private ComboBox<?> agence;

	    @FXML
	    private ComboBox<String> marque = new ComboBox<String>();

	    @FXML
	    private TextArea desc;

	    @FXML
	    private Label uplodedImage;
	    private String image;
	    @Lazy
	    @Autowired
	    private VoitureService voitureService;
	   
	    @FXML
	    void handleAdd(ActionEvent event) {
	    	Voiture voiture = new Voiture();
	    	voiture.setLibelle(this.car_name.getText());
	    	voiture.setMatricule(this.matricule.getText());
	    	voiture.setColor(this.color.getText());
	    	voiture.setMarque(marqueService.findByBrand(this.marque.getSelectionModel().getSelectedItem()));
	    	voiture.setCategorie(categorieService.findByName(this.categorie.getSelectionModel().getSelectedItem()));
	    	voiture.setCoutParJour(Double.parseDouble(this.price.getText()));
	    	voiture.setDiscreption(this.desc.getText());
	    	voiture.setImage(this.image);
	    	voitureService.save(voiture);
	    	
	    }

	    @FXML
	    void handleAddCar(ActionEvent event) {
	    	this.parent.getChildren().setAll(this.addCar);
	    	
	    }

	    @FXML
	    void handleClose(MouseEvent event) {
	    	 Platform.exit();
	         System.exit(0);
	    }

	    @FXML
	    void handleHome(ActionEvent event) {
	    	this.parent.getChildren().setAll(this.home);
	    }

	    @FXML
	    void handleNext(ActionEvent event) {

	    }

	    @FXML
	    void handleSearchCars(ActionEvent event) {
	    	this.parent.getChildren().setAll(this.searchCar);
	    	
	    	  table_libelle.setCellValueFactory(new PropertyValueFactory<Voiture,String>("libelle"));
	          table_matricule.setCellValueFactory(new PropertyValueFactory<Voiture,String>("matricule"));
	          table_prix.setCellValueFactory(new PropertyValueFactory<Voiture,Double>("coutParJour"));
	          table_couleur.setCellValueFactory(new PropertyValueFactory<Voiture,String>("color"));
	          table_marque.setCellValueFactory(new PropertyValueFactory<Voiture,String>("marqueBrand"));
	          table_categorie.setCellValueFactory(new PropertyValueFactory<Voiture,String>("categorieName"));
	          table_agence.setCellValueFactory(new PropertyValueFactory<Voiture,String>("agence"));
	          delete.setCellValueFactory(new PropertyValueFactory<Button,Button>("buttonDelete"));
	          modify.setCellValueFactory(new PropertyValueFactory<Button,Button>("buttonEdit"));
	          table_id.setCellValueFactory(new PropertyValueFactory<Voiture,Long>("id"));
	          table_couleur.setCellValueFactory(new PropertyValueFactory<Voiture,String>("color"));
	          table_car.setItems(getVoitures());
	    }
	    private ObservableList<Voiture> getVoitures(){
	    	ObservableList<Voiture> voitures=FXCollections.observableArrayList(voitureService.findAll());
	    	System.out.println(voitures);
	    	return voitures; 
	    }
	    @FXML
	    void handleSearchUsers(ActionEvent event) {
	    	this.parent.getChildren().setAll(this.searchUser);
	    } 
	    @FXML
	    void handleMarque(ActionEvent event) {

	    }
	    @FXML
	    void handleAgence(ActionEvent event) {

	    }

	    @FXML
	    void handleCategorie(ActionEvent event) {

	    }
	    @Lazy
	    @Autowired
	    private File selectedFile;
	    
	   
	    @FXML
	    void handleUpload(ActionEvent event) {
//	    	try {
//	    		  FileChooser fileChooser = new FileChooser();
//		    	  selectedFile=fileChooser.showOpenDialog(null);
//		    	  if(selectedFile.exists()) {
//		    		FileUtil.copyFile(selectedFile.getAbsolutePath(), "\\templates");  
//		    	  }
//		    	  
//		    		  
//	    	}catch(IOException e) {
//	    		e.printStackTrace();
//	    	}
	    	 FileChooser fileChooser = new FileChooser();
	    	  selectedFile=fileChooser.showOpenDialog(null);
	    	  if(selectedFile.exists()) {
	    		  this.image=selectedFile.getName();
	    		  this.uplodedImage.setText(this.image);
    	  }
	                
	               
	    	
	    }
	    @Lazy
	    @Autowired
	    private CategorieService categorieService;
	    @Lazy
	    @Autowired
	    private MarqueService marqueService;
		@Override
		public void initialize(URL url, ResourceBundle resourceBundle){
			categorieService.findAll().forEach(categorie->this.categorie.getItems().add(categorie.getName()));
			this.categorie.getSelectionModel().select(0);
			marqueService.findAll().forEach(marque->this.marque.getItems().add(marque.getBrand()));
			this.marque.getSelectionModel().select(0);
			this.marque.setVisibleRowCount(6);
		}
		@FXML
	    void handleResetCar(ActionEvent event) {
			this.car_name.setText("");
			this.desc.setText("");
			this.color.setText("");
			this.matricule.setText("");
			this.uplodedImage.setText("");
			this.price.setText("");
	    }
	
}
