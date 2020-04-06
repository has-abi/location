package com.example.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.Cart;
import com.example.location.bean.CartItem;
import com.example.location.bean.User;
import com.example.location.bean.Voiture;
import com.example.location.config.StageManager;
import com.example.location.service.facade.CartItemService;
import com.example.location.service.facade.CartService;
import com.example.location.service.facade.UserService;
import com.example.location.service.facade.VoitureService;
import com.example.location.util.ComposantUtil;
import com.example.location.util.Notification;
import com.example.location.util.Session;
import com.example.location.views.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

@Controller
public class WelcomeController {
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Lazy
	@Autowired
	private CartService cartService;
	@Lazy
	@Autowired
	private CartItemService cartItemService;
	@Lazy
	@Autowired
	private UserService userService;
	@FXML
	private AnchorPane parent;
	@FXML
	private Pane home;
	private Button ajouter;
	private Button plus;
	private ImageView image;
	private Label car_name;
	private Label car_price;
	private Label car_location;
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
	@FXML
	private Label voiture_counter;
	@FXML
	private Pane cartPane;
	// profile
	@FXML
	private Pane profile;

	@FXML
	private TextField user_nom;

	@FXML
	private TextField user_prenom;

	@FXML
	private TextField user_email;

	@FXML
	private TextField user_password;

	@FXML
	private TextField user_phone;

	@FXML
	private TextField user_adress;

	@FXML
	private ComboBox<String> user_gender;
	@FXML
	private Button enregistrer;
	//side search
	@FXML
    private TextField filter_marque;

    @FXML
    private TextField filter_categorie;

    @FXML
    private TextField filter_adress;

    @FXML
    private TextField filter_couleur;
	@FXML
	private Pane home_pane;

	private Button next;
	private Button prev;

	@FXML
	void handleProfile(ActionEvent event) {
		this.parent.getChildren().setAll(this.profile);
		User user = Session.getSessionUser("user");
		this.user_adress.setText(user.getAdress());
		this.user_email.setText(user.getEmail());
		this.user_nom.setText(user.getNom());
		this.user_prenom.setText(user.getPrenom());
		this.user_password.setText(user.getPassword());
		this.user_phone.setText(user.getNumberPhone());
		this.user_gender.getItems().add("Femme");
		this.user_gender.getItems().add("Homme");
		this.user_gender.getSelectionModel().select(user.getGender());
		desactiver();
	}
	public void desactiver() {
		this.user_adress.setDisable(true);
		this.user_email.setDisable(true);
		this.user_gender.setDisable(true);
		this.user_nom.setDisable(true);
		this.user_prenom.setDisable(true);
		this.user_phone.setDisable(true);
		this.user_password.setDisable(true);
		this.enregistrer.setDisable(true);
	}

	@FXML
	void UserModify(ActionEvent event) {
		this.user_adress.setDisable(false);
		this.user_gender.setDisable(false);
		this.user_nom.setDisable(false);
		this.user_prenom.setDisable(false);
		this.user_phone.setDisable(false);
		this.user_password.setDisable(false);
		this.enregistrer.setDisable(false);
	}

	@FXML
	void UserUpdate(ActionEvent event) {
		User user = new User();
		
		user.setAdress(this.user_adress.getText());
		user.setEmail(this.user_email.getText());
		user.setGender(this.user_gender.getSelectionModel().getSelectedItem());
		user.setPrenom(this.user_prenom.getText());
		user.setNom(this.user_nom.getText());
		user.setPassword(this.user_password.getText());
		user.setNumberPhone(this.user_phone.getText());
		if(userService.modify(user) == 1) {
			Notification.successNotification("votre profile est modifier avec succée!");
		}else {
			Notification.errorNotification("quelque chose incorrect!");
		}
	}

	@FXML
	void handleAbout(ActionEvent event) {
		this.parent.getChildren().setAll(this._about);
	}

	@FXML
	void handlClose(MouseEvent event) {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	void handleDeconnexion(ActionEvent event) {
		Session.clear();
		Notification.infoNotification("Vous avec déconnecté!");;
		this.stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	void handleHome(ActionEvent event) {
		this.parent.getChildren().setAll(this.home);
	}

	@FXML
	void handleMyCars(ActionEvent event) {
		this.parent.getChildren().setAll(this.myCars);
		chargerUserCart();
	}

	private List<Voiture> listVoitures;

	@FXML
	public void initialize() {
		this.voiture_counter.setText(Integer.toString(
				cartItemService.countByCart(cartService.findByUserId(Session.getSessionUser("user").getId()))));
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
		this.prev.setLayoutY(450);
		this.prev.setPrefHeight(27);
		this.prev.setPrefWidth(141);
		this.next.addEventHandler(ActionEvent.ACTION, e -> {
			this.home_pane.getChildren().clear();
			chargerLesVoitures(voitureService.findAllWithPagination("next"));
			this.home_pane.getChildren().add(this.prev);
		});
		this.prev.addEventHandler(ActionEvent.ACTION, e -> {
			this.home_pane.getChildren().clear();
			chargerLesVoitures(voitureService.findAllWithPagination("prev"));
		});
		chargerLesVoitures(this.listVoitures);
	}

	public void chargerLesVoitures(List<Voiture> voitures) {
		float addx = 0;
		float addy = 0;
		int counter = 0;

		for (Voiture voiture : voitures) {
			if (counter > 0 && counter % 3 == 0) {
				addy += 220;
				addx = 0;
			}
			car_name = new Label();
			car_price = new Label();
			car_location = new Label();
			this.image = new ImageView();
			this.image.setFitHeight(95.0);
			this.image.setFitWidth(141.0);
			this.image.setLayoutX(13.0 + addx);
			this.image.setLayoutY(10.0 + addy);
			this.image.setPickOnBounds(true);
			this.image.setPreserveRatio(true);
			this.image.setImage(new Image("/storage/" + voiture.getImage()));
			this.car_name.setLayoutX(25 + addx);
			this.car_name.setLayoutY(110 + addy);
			this.car_name.setPrefHeight(17);
			this.car_name.setPrefWidth(127);
			this.car_name.setText(voiture.getLibelle());
			this.car_name.setStyle("-fx-text-fill:black");
			this.car_price.setLayoutX(25 + addx);
			this.car_name.setAlignment(Pos.CENTER);
			this.car_price.setLayoutY(130 + addy);
			this.car_price.setPrefHeight(17);
			this.car_price.setPrefWidth(127);
			this.car_price.setAlignment(Pos.CENTER);
			this.car_price.setStyle("-fx-text-fill:black");
			this.car_price.setText(Double.toString(voiture.getCoutParJour())+" DH");
			
			this.car_location.setLayoutX(25 + addx);
			this.car_location.setLayoutY(150.0 + addy);
			this.car_location.setPrefHeight(17);
			this.car_location.setPrefWidth(127);
			this.car_location.setAlignment(Pos.CENTER);
			this.car_location.setText(voiture.getAgence().getAdress());
			this.car_location.setStyle("-fx-text-fill:black");
			System.out.println(voiture);
			
			this.ajouter = new Button("ajouter");
			this.ajouter.setStyle("-fx-background-color:#42f560;-fx-text-fill:white");
			this.ajouter.setLayoutX(90.0 + addx);
			this.ajouter.setLayoutY(180.0 + addy);
			this.ajouter.setPrefHeight(25.0);
			this.ajouter.setPrefWidth(65.0);
			this.ajouter.setMnemonicParsing(false);

			this.plus = new Button("plus");
			this.plus.setStyle("-fx-background-color:#0158FC;-fx-text-fill:white");
			this.plus.setLayoutX(16.0 + addx);
			this.plus.setLayoutY(180.0 + addy);
			this.plus.setPrefHeight(25.0);
			this.plus.setPrefWidth(65.0);
			this.plus.setMnemonicParsing(false);
			this.plus.addEventHandler(ActionEvent.ACTION, e -> {
				Session.SetSessionVoiture(voiture);
				this.stageManager.switchScene(FxmlView.MORE);

			});
			this.ajouter.addEventHandler(ActionEvent.ACTION, e -> {
				Cart cart = new Cart();
				CartItem ci = new CartItem();
				cart.setClient(Session.getSessionUser("user"));
				ci.setVoiture(voiture);
				ci.setCart(cart);
				cartItemService.save(ci);
				Notification.successNotification(voiture.getLibelle() + " est ajouté a votre cart!");
				this.voiture_counter.setText(Integer.toString(Integer.parseInt(this.voiture_counter.getText()) + 1));

			});
			this.home_pane.getChildren().addAll(ajouter, plus, image, car_name, car_price,car_location);
			
			addx += 160;
			counter++;

		}
	}

	private List<CartItem> cartitems;

	public void chargerUserCart() {
		Button next = ComposantUtil.createButton(557, 36, ">", "-fx-text-fill:white;-fx-background-color: #1c1c1c;");
		Button prev = ComposantUtil.createButton(100, 36, "<", "-fx-text-fill:white;-fx-background-color: #1c1c1c;");
		Label pasV = ComposantUtil.createLabel(233, 233, "Pas De Voiture ! retourner en arrière", "");
		next.addEventHandler(ActionEvent.ACTION, e -> {
			this.cartitems = cartItemService
					.findAllByCart(cartService.findByUserId(Session.getSessionUser("user").getId()), "next");
			this.cartPane.getChildren().clear();
			System.out.println("next :" + this.cartitems.size());
			if (this.cartitems.size() == 0) {
				this.cartPane.getChildren().add(pasV);
			}
			cartItems(this.cartitems);
		});
		prev.addEventHandler(ActionEvent.ACTION, e -> {
			this.cartitems = cartItemService
					.findAllByCart(cartService.findByUserId(Session.getSessionUser("user").getId()), "prev");
			this.cartPane.getChildren().clear();
			cartItems(this.cartitems);

		});
		this.cartitems = cartItemService.findAllByCart(cartService.findByUserId(Session.getSessionUser("user").getId()),
				"first");
		cartItems(this.cartitems);
		this.myCars.getChildren().addAll(next, prev);

	}

	public void cartItems(List<CartItem> cartItems) {
		int y = 0;
		for (CartItem cartitem : cartItems) {
			Voiture voiture = cartitem.getVoiture();
			ImageView imageView = ComposantUtil.createImageView(49, 10 + y, 150, 200, voiture.getImage());
			Label libelle = ComposantUtil.createLabel(54, 16, voiture.getLibelle(), "-fx-text-fill:white");
			Label marque = ComposantUtil.createLabel(54, 96, voiture.getMarqueBrande(), "-fx-text-fill:white");
			Label categorie = ComposantUtil.createLabel(54, 36, voiture.getCategorieNom(), "-fx-text-fill:white");
			Label agence = ComposantUtil.createLabel(54, 56, voiture.getAgenceName(), "-fx-text-fill:white");
			Label prix = ComposantUtil.createLabel(153, 110,
					"Prix : " + Double.toString(voiture.getCoutParJour()) + " DH", "-fx-text-fill:white");
			Label couleur = ComposantUtil.createLabel(54, 76, voiture.getColor(), "-fx-text-fill:white");
			Text descreption = ComposantUtil.createText(143, 80, voiture.getDiscreption(), 108, "-fx-text-fill:white");
			Button reserver = ComposantUtil.createButton(261, 31, "Reserver",
					"-fx-background-color: #26acff;-fx-text-fill:white");
			Button suprimer = ComposantUtil.createButton(261, 78, "Suprimer",
					"-fx-background-color: #ffad07;-fx-text-fill:white");
			Pane pane = ComposantUtil.createPane(253, 10 + y, 132.5, 357, "-fx-background-color: #1c1c1c;");
			pane.getChildren().addAll(libelle, marque, categorie, agence, prix, couleur, descreption, reserver,
					suprimer);
			this.cartPane.getChildren().addAll(imageView, pane);
			reserver.addEventHandler(ActionEvent.ACTION, e -> {
				Session.SetSessionVoiture(voiture);
				this.stageManager.switchScene(FxmlView.MORE);
			});
			suprimer.addEventHandler(ActionEvent.ACTION, e -> {
				if (this.cartItemService.delete(cartitem) == 1) {
					Notification.successNotification(voiture.getLibelle() + " est suprimée de votre Cart");
					this.cartitems.remove(cartitem);
					this.cartPane.getChildren().clear();
					cartItems(this.cartitems);
					this.voiture_counter
							.setText(Integer.toString(Integer.parseInt(this.voiture_counter.getText()) - 1));
				}
			});
			y += 163;
		}
	}
	private List<Voiture> searchVs;
	  @FXML
	    void handelSearch(ActionEvent event) {
		  if(this.searchVs!=null) {
			  this.searchVs.clear();
		  }
		  	if(!this.filter_adress.getText().isEmpty() && this.filter_adress.getText()!="") {
		  		this.searchVs = voitureService.findAllByAgenceAddress(this.filter_adress.getText());
		  	}
		  	if(!this.filter_categorie.getText().isEmpty() && this.filter_categorie.getText()!="") {
		  		if(this.searchVs !=null) {
		  			this.searchVs.addAll(voitureService.findAllByCategorieName(this.filter_categorie.getText()));
		  		}else{
		  			this.searchVs = voitureService.findAllByCategorieName(this.filter_categorie.getText());
		  		}
		  	}
		  	if(!this.filter_couleur.getText().isEmpty() && this.filter_couleur.getText()!=null) {
		  		if(this.searchVs !=null) {
		  			System.out.println("here1");
		  			this.searchVs.addAll(voitureService.findAllByColor(this.filter_couleur.getText()));
		  		}else {
		  			System.out.println("here2");
		  			this.searchVs = voitureService.findAllByColor(this.filter_couleur.getText());
		  			System.out.println(this.searchVs.size());
		  		}
		  		
		  	}
		  	if(!this.filter_marque.getText().isEmpty() && this.filter_marque.getText()!="") {
		  		if(this.searchVs!=null) {
		  			this.searchVs.addAll(voitureService.findAllByMarqueBrand(this.filter_marque.getText()));	
		  		}else {
		  			this.searchVs = voitureService.findAllByMarqueBrand(this.filter_marque.getText());
		  		}
		  		
		  	}if(this.searchVs != null) {
		  		this.searchVs.forEach(v->System.out.println(v));
		  		this.home_pane.getChildren().clear();
		  		chargerLesVoitures(this.searchVs);
		  	}
		  	
		 
	    }
	   @FXML
	    void reset(ActionEvent event) {
		   this.home_pane.getChildren().clear();
		   initialize();
	    }

}
