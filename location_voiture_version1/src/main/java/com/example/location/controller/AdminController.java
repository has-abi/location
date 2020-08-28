package com.example.location.controller;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.Agence;
import com.example.location.bean.Categorie;
import com.example.location.bean.Marque;
import com.example.location.bean.Reservation;
import com.example.location.bean.User;
import com.example.location.bean.Voiture;
import com.example.location.config.StageManager;
import com.example.location.service.facade.AgenceService;
import com.example.location.service.facade.CategorieService;
import com.example.location.service.facade.MarqueService;
import com.example.location.service.facade.ReservationService;
import com.example.location.service.facade.UserService;
import com.example.location.service.facade.VoitureService;
import com.example.location.util.FileUtil;
import com.example.location.util.HandMessages;
import com.example.location.util.Notification;
import com.example.location.util.Session;
import com.example.location.util.SmallViews;
import com.example.location.views.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

@Controller
public class AdminController implements Initializable {
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Lazy
	@Autowired
	private UserService userService;
	@Autowired
	@Lazy
	private ReservationService reservationService;
	
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
	private TableColumn<Voiture, String> table_categorie;

	@FXML
	private TableColumn<Voiture, String> table_libelle;

	@FXML
	private TableColumn<Voiture, String> table_matricule;

	@FXML
	private TableColumn<Voiture, Double> table_prix;

	@FXML
	private TableColumn<?, ?> table_price;

	@FXML
	private TableColumn<Voiture, String> table_couleur;

	@FXML
	private TableColumn<Voiture, String> table_marque;

	@FXML
	private TableColumn<Voiture, Long> table_id;
	@FXML
	private TableColumn<Voiture, String> table_agence;

	@FXML
	private Button next;

	@FXML
	private Pane searchUser;

	@FXML
	private TableView<User> table_user;

	@FXML
	private TableColumn<User, Long> col_id;

	@FXML
	private TableColumn<User, String> col_nom;

	@FXML
	private TableColumn<User, String> col_prenom;

	@FXML
	private TableColumn<User, String> col_email;

	@FXML
	private TableColumn<User, String> col_adress;

	@FXML
	private TableColumn<User, String> col_sexe;

	@FXML
	private TableColumn<User, String> col_phone;

	@FXML
	private TableColumn<User, String> col_type;
	@FXML
	private TableView<Agence> tabl_agence;
	@FXML
	private Pane reservation;

	@FXML
	private TableView<Reservation> table_reservation;

	@FXML
	private TableColumn<Reservation,Long> reservation_id;

	@FXML
	private TableColumn<Reservation,String> reservation_ref;

	@FXML
	private TableColumn<Reservation,Date> reservation_date;

	@FXML
	private TableColumn<Reservation,String> reservation_client;

	@FXML
	private TableColumn<Reservation,String> reservation_phone;

	@FXML
	private TableColumn<Reservation,String> reservation_sexe;

	@FXML
	private TableColumn<Reservation,String> reservation_voiture;

	@FXML
	private TableColumn<Reservation,String> reservation_agence;
	@FXML
	private Pane autres;
	@FXML
	private TextField marque_nom;

	@FXML
	private TextField agence_nom;

	@FXML
	private TextField agence_adress;

	@FXML
	private TextField categorie_nom;

	@FXML
	private TextField categorie_portes;

	@FXML
	private TextField categorie_places;

	@FXML
	private TableColumn<Agence,Long> ag_id;

	@FXML
	private TableColumn<Agence,String> ag_nom;

	@FXML
	private TableColumn<Agence,String> ag_adress;
	@FXML
	private Pane addCar;

	@FXML
	private Label msg;

	@FXML
	private TextField car_name;

	@FXML
	private TextField matricule;
	@FXML
	private TextField searchV;

	@FXML
	private TextField color;

	@FXML
	private TextField price;

	@FXML
	private ComboBox<String> categorie = new ComboBox<String>();

	@FXML
	private ComboBox<String> agence = new ComboBox<String>();

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
	@Lazy
	@Autowired
	private AgenceService agenceServcie;

	@FXML
	void ajouterAgence(ActionEvent event) {
		if(!this.ag_nom.getText().isEmpty() && !this.ag_adress.getText().isEmpty()) {
			Agence ag = new Agence();
			ag.setName(this.ag_nom.getText());
			ag.setAdress(this.ag_adress.getText());
			if(agenceServcie.save(ag) == 1) {
				Notification.successNotification("agence "+this.ag_nom.getText()+" ajouté avec succée!");
			}
		}else {
			Notification.warningNotification("remplir tout les champs!");
		}
		
	}

	@FXML
	void ajouterCategorie(ActionEvent event) {
		if(!this.categorie_nom.getText().isEmpty() && !this.categorie_places.getText().isEmpty() &&  !this.categorie_portes.getText().isEmpty()) {
			Categorie c = new Categorie();
			c.setName(this.categorie_nom.getText());
			c.setNbrePlaces(Integer.parseInt(this.categorie_places.getText()));
			c.setNbrePortes(Integer.parseInt(this.categorie_portes.getText()));
			if(categorieService.save(c) == 1) {
				Notification.successNotification("Catégorie "+this.categorie_nom.getText()+" ajouté avec succée!");
			}
		}else {
			Notification.warningNotification("remplir tout les champs!");
		}
	}

	@FXML
	void ajouterMarque(ActionEvent event) {
		if(!this.marque_nom.getText().isEmpty()) {
			Marque m = new Marque();
			m.setBrand(this.marque_nom.getText());
			if(marqueService.save(m) == 1) {
				Notification.successNotification("la marque "+this.marque_nom.getText()+" ajouté avec succée!");
			}
		}else {
			Notification.warningNotification("remplir tout les champs!");
		}
	}

	@FXML
	void handleOthers(ActionEvent event) {
		this.parent.getChildren().setAll(this.autres);
		ag_id.setCellValueFactory(new PropertyValueFactory<Agence, Long>("id"));
		ag_nom.setCellValueFactory(new PropertyValueFactory<Agence, String>("name"));
		ag_adress.setCellValueFactory(new PropertyValueFactory<Agence, String>("adress"));
		this.tabl_agence.setItems(getAgences());
	}

	@FXML
	void handleReservation(ActionEvent event) {
		this.parent.getChildren().setAll(this.reservation);
		reservation_id.setCellValueFactory(new PropertyValueFactory<Reservation, Long>("id"));
		reservation_client.setCellValueFactory(new PropertyValueFactory<Reservation, String>("clientNom"));
		reservation_date.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("dateReserv"));
		reservation_agence.setCellValueFactory(new PropertyValueFactory<Reservation, String>("agenceAdress"));
		reservation_phone.setCellValueFactory(new PropertyValueFactory<Reservation, String>("clientPhone"));
		reservation_ref.setCellValueFactory(new PropertyValueFactory<Reservation, String>("reference"));
		reservation_sexe.setCellValueFactory(new PropertyValueFactory<Reservation, String>("clientSexe"));
		reservation_voiture.setCellValueFactory(new PropertyValueFactory<Reservation, String>("voitureLibelle"));
		this.table_reservation.setItems(getReservations());
	}
	@FXML
	void searchReservation(ActionEvent event) {
		FilteredList<Reservation> filteredData = new FilteredList<Reservation>(getReservations(), v -> true);

		filteredData.setPredicate(res -> {
			String newValue = this.searchV.getText();
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();

			if (res.getAgenceAdress().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (res.getClientNom().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (res.getReference().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (res.getDateReserv().toString().contains(lowerCaseFilter)) {
				return true;
			} else if (res.getVoitureLibelle().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (res.getClientSexe().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			}
			return false;
		});
		SortedList<Reservation> sortedData = new SortedList<Reservation>(filteredData);
		sortedData.comparatorProperty().bind(table_reservation.comparatorProperty());
		table_reservation.setItems(sortedData);
	}
	@FXML
	void handleAdd(ActionEvent event) {
		Voiture voiture = new Voiture();
		voiture.setLibelle(this.car_name.getText());
		voiture.setMatricule(this.matricule.getText());
		voiture.setColor(this.color.getText());
		voiture.setMarque(marqueService.findByBrand(this.marque.getSelectionModel().getSelectedItem()));
		voiture.setCategorie(categorieService.findByName(this.categorie.getSelectionModel().getSelectedItem()));
		voiture.setAgence(agenceServcie.findByAdress(this.agence.getSelectionModel().getSelectedItem()));
		voiture.setCoutParJour(Double.parseDouble(this.price.getText()));
		voiture.setDiscreption(this.desc.getText());
		voiture.setImage(this.image);
		if(voitureService.save(voiture) == 1) {
			Notification.successNotification(this.car_name.getText()+" est ajouter avec sucée!");
		}
		
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
	void handleDeconnexion(ActionEvent event) {
		Session.clear();
		Notification.infoNotification("Vous avec déconnecté!");
		;
		this.stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	void handleSearchCars(ActionEvent event) {
		this.parent.getChildren().setAll(this.searchCar);
		table_libelle.setCellValueFactory(new PropertyValueFactory<Voiture, String>("libelle"));
		table_matricule.setCellValueFactory(new PropertyValueFactory<Voiture, String>("matricule"));
		table_prix.setCellValueFactory(new PropertyValueFactory<Voiture, Double>("coutParJour"));
		table_couleur.setCellValueFactory(new PropertyValueFactory<Voiture, String>("color"));
		table_marque.setCellValueFactory(new PropertyValueFactory<Voiture, String>("marqueBrande"));
		table_categorie.setCellValueFactory(new PropertyValueFactory<Voiture, String>("categorieNom"));
		table_agence.setCellValueFactory(new PropertyValueFactory<Voiture, String>("agenceAddress"));
		table_id.setCellValueFactory(new PropertyValueFactory<Voiture, Long>("id"));
		table_couleur.setCellValueFactory(new PropertyValueFactory<Voiture, String>("color"));
		table_car.setItems(getVoitures());
	}

	private ObservableList<Voiture> getVoitures() {
		ObservableList<Voiture> voitures = FXCollections.observableArrayList(voitureService.findAll());
		return voitures;
	}

	private ObservableList<User> getUsers() {
		ObservableList<User> users = FXCollections.observableArrayList(userService.findAll());
		return users;
	}
	private ObservableList<Agence> getAgences() {
		ObservableList<Agence> agences = FXCollections.observableArrayList(agenceServcie.findAll());
		return agences;
	}
	
	private ObservableList<Reservation> getReservations() {
		ObservableList<Reservation> reser = FXCollections.observableArrayList(reservationService.findAll());
		return reser;
	}
	

	@FXML
	void handleSearchUsers(ActionEvent event) {
		this.parent.getChildren().setAll(this.searchUser);
		col_id.setCellValueFactory(new PropertyValueFactory<User, Long>("id"));
		col_nom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
		col_prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
		col_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		col_adress.setCellValueFactory(new PropertyValueFactory<User, String>("adress"));
		col_phone.setCellValueFactory(new PropertyValueFactory<User, String>("numberPhone"));
		col_sexe.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
		col_type.setCellValueFactory(new PropertyValueFactory<User, String>("type"));
		this.table_user.setItems(getUsers());
	}

	@FXML
	void handleMarque(ActionEvent event) {
		String marque = SmallViews.dialogView("ajouter une marque", "taper la marque");
		if (marque != null && marque != "") {
			Marque m = new Marque();
			m.setBrand(marque);
			if (marqueService.save(m) == 1) {
				Notification.successNotification("categrie ajouté avec succée!");
			} else {
				Notification.warningNotification("categorie existe dèja!");
			}
		}
	}

	@FXML
	void handleAgence(ActionEvent event) {
		String agence = SmallViews.dialogView("ajouter un agence", "taper le nom de l'agence");
		if (agence != null && agence != "") {
			Agence a = new Agence();
			a.setName(agence);
			if (agenceServcie.save(a) == 1) {
				Notification.successNotification("agence ajouté avec succée!");
			} else {
				Notification.warningNotification("agence dèja existe!");
			}
		}
	}

	@FXML
	void handleCategorie(ActionEvent event) {
		String categorie = SmallViews.dialogView("ajouter categorie", "taper le nom du categorie");
		if (categorie != null && categorie != "") {
			Categorie c = new Categorie();
			c.setName(categorie);
			if (categorieService.save(c) == 1) {
				Notification.successNotification("categorie ajouter avec succée!");
			} else {
				Notification.warningNotification("categorie dèja existe!");
			}
		}
	}

	@Lazy
	@Autowired
	private File selectedFile;

	@FXML
	void handleUpload(ActionEvent event) {
		this.image = FileUtil.getNameFromFileChooser(new FileChooser());
		this.uplodedImage.setText(this.image);
	}

	@Lazy
	@Autowired
	private CategorieService categorieService;
	@Lazy
	@Autowired
	private MarqueService marqueService;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		categorieService.findAll().forEach(categorie -> this.categorie.getItems().add(categorie.getName()));
		this.categorie.getSelectionModel().select(0);
		marqueService.findAll().forEach(marque -> this.marque.getItems().add(marque.getBrand()));
		this.marque.getSelectionModel().select(0);
		this.marque.setVisibleRowCount(6);
		agenceServcie.findAll().forEach(agence->this.agence.getItems().add(agence.getAdress()));
		this.agence.setVisibleRowCount(6);
		this.agence.getSelectionModel().select(0);
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

	@FXML
	void handlSelect(ActionEvent event) {
		Voiture v = table_car.getSelectionModel().getSelectedItem();
		System.out.println(v);
	}

	@FXML
	void search(ActionEvent event) {
		FilteredList<Voiture> filteredData = new FilteredList<Voiture>(getVoitures(), v -> true);

		filteredData.setPredicate(voiture -> {
			String newValue = this.searchV.getText();
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();

			if (voiture.getLibelle().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (voiture.getCategorieNom().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (voiture.getMarqueBrande().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (voiture.getColor().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			} else if (Double.toString(voiture.getCoutParJour()).contains(lowerCaseFilter)) {
				return true;
			} else if (voiture.getDiscreption().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			}
			return false;
		});
		SortedList<Voiture> sortedData = new SortedList<Voiture>(filteredData);
		sortedData.comparatorProperty().bind(table_car.comparatorProperty());
		table_car.setItems(sortedData);
	}

	@FXML
	void handlModify(ActionEvent event) {
		Voiture voiture = this.table_car.getSelectionModel().getSelectedItem();
		Session.SetSessionVoiture(voiture);
		System.out.println(Session.getSessionVoiture("voiture"));
		this.stageManager.switchScene(FxmlView.MODIFIERVOITURE);
	}

	@FXML
	void handleDelete(ActionEvent event) {
		HandMessages hm = new HandMessages();
		if (hm.warningMessage("message") == 1) {
			Voiture voiture = this.table_car.getSelectionModel().getSelectedItem();
			this.voitureService.remove(voiture);
			Notification.successNotification("voiture est suprimer avec succee!");
			this.table_car.setItems(getVoitures());

		}

	}

}
