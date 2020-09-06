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
import com.example.location.service.facade.AgenceService;
import com.example.location.service.facade.CartItemService;
import com.example.location.service.facade.CartService;
import com.example.location.service.facade.CategorieService;
import com.example.location.service.facade.MarqueService;
import com.example.location.service.facade.UserService;
import com.example.location.service.facade.VoitureService;
import com.example.location.util.ComposantUtil;
import com.example.location.util.Notification;
import com.example.location.util.Session;
import com.example.location.views.FxmlView;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	@Lazy
	@Autowired
	private CartService cartService;
	@Lazy
	@Autowired
	private CartItemService cartItemService;
	@Lazy
	@Autowired
	private UserService userService;
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
	private AnchorPane parent;
	@FXML
	private Pane home;

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
	// side search
	@FXML
	private TextField search_field;

	@FXML
	private ImageView search_btn;

	@FXML
	private ComboBox<String> marque_chooser;

	@FXML
	private ComboBox<String> categorie_chooser;

	@FXML
	private ComboBox<String> couleur_chooser;

	@FXML
	private TextField prix_max;

	@FXML
	private TextField prix_min;

    

	@FXML
	private ComboBox<String> ville_chooser;
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
		if (userService.modify(user) == 1) {
			Notification.successNotification("votre profile est modifier avec succée!");
		} else {
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
		Notification.infoNotification("Vous avec déconnecté!");
		;
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
		this.search_btn.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
			System.out.println("clicked");
			if(this.search_field.getText() != "") {
				this.home_pane.getChildren().clear();
				chargerLesVoitures(voitureService.searchForVoitures(this.search_field.getText(), 0, 4).getContent());
			}
		});
		initChoosers();
		this.voiture_counter.setText(Integer.toString(
				cartItemService.countByCart(cartService.findByUserId(Session.getSessionUser("user").getId()))));
		this.listVoitures = voitureService.findAllWithPagination("first");
		this.next = new Button(">");
		this.next.setLayoutX(983);
		this.next.setLayoutY(518);
		this.next.setPrefHeight(31);
		this.next.setPrefWidth(27);
		this.next.setStyle("-fx-background-color: black;-fx-font-size:16px;-fx-text-fill:white;-fx-font-weight:bold");
		this.home.getChildren().add(this.next);
		this.prev = new Button("<");
		this.prev.setStyle("-fx-background-color: black;-fx-font-size:16px;-fx-text-fill:white;-fx-font-weight:bold");
		this.prev.setLayoutX(947);
		this.prev.setLayoutY(518);
		this.prev.setPrefHeight(31);
		this.prev.setPrefWidth(27);
		this.next.addEventHandler(ActionEvent.ACTION, e -> {
			this.home_pane.getChildren().clear();
			chargerLesVoitures(voitureService.findAllWithPagination("next"));
			this.home.getChildren().add(this.prev);
		});
		this.prev.addEventHandler(ActionEvent.ACTION, e -> {
			this.home_pane.getChildren().clear();
			chargerLesVoitures(voitureService.findAllWithPagination("prev"));
		});
		chargerLesVoitures(this.listVoitures);
		// NumberAxis xAxis = new NumberAxis();
		// xAxis.setLabel("Names");
		// NumberAxis yAxis = new NumberAxis();
		// yAxis.setLabel("values");

		// this.home_pane.getChildren().add(lineChart);
	}

	public void chargerLesVoitures(List<Voiture> voitures) {
		float addx = 0;
		float addy = 0;
		int counter = 0;

		for (Voiture voiture : voitures) {
			if (counter > 0 && counter % 2 == 0) {
				addy += 246;
				addx = 0;
			}

			Label car_price;
			ImageView overflow;
			Button ajouter;
			Button plus;
			ImageView image;

			overflow = new ImageView();
			overflow.setFitHeight(50.0);
			overflow.setFitWidth(389.0);
			overflow.setLayoutX(14.0 + addx);
			overflow.setLayoutY(197.0 + addy);
			overflow.setImage(new Image("/icons/overflow.png"));

			car_price = new Label(Double.toString(voiture.getCoutParJour()) + " DH");
			car_price.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:24px");
			car_price.setPrefHeight(41.0);
			car_price.setPrefWidth(127.0);
			car_price.setLayoutX(121.0 + addx);
			car_price.setLayoutY(205.0 + addy);

			image = new ImageView();
			image.setFitHeight(233.0);
			image.setFitWidth(389.0);
			image.setLayoutX(14.0 + addx);
			image.setLayoutY(14.0 + addy);
			image.setStyle("-fx-effect: dropshadow(three-pass-box, #e3e6e4, 10, 0, 0, 5)");
			// image.setPickOnBounds(true);
//			image.setPreserveRatio(true);
			image.setImage(new Image("/storage/" + voiture.getImage()));

			ajouter = new Button(null, GlyphsDude.createIcon(FontAwesomeIcon.SHOPPING_CART, "16px"));
			ajouter.setLayoutX(364.0 + addx);
			ajouter.setLayoutY(20.0 + addy);

			// this.ajouter.setMnemonicParsing(false);
			ajouter.setStyle("-fx-background-color: white;");
			ajouter.setCursor(Cursor.HAND);

			plus = new Button(null, GlyphsDude.createIcon(FontAwesomeIcon.BARS, "16px"));
			plus.setLayoutX(364.0 + addx);
			plus.setLayoutY(70.0 + addy);

			// this.plus.setMnemonicParsing(false);
			plus.setStyle("-fx-background-color: white");
			plus.setCursor(Cursor.HAND);
			plus.addEventHandler(ActionEvent.ACTION, e -> {
				System.out.println("plus");
				Session.SetSessionVoiture(voiture);
				this.stageManager.switchScene(FxmlView.MORE);

			});
			ajouter.addEventHandler(ActionEvent.ACTION, e -> {
				System.out.println("ajouter");
				Cart cart = new Cart();
				CartItem ci = new CartItem();
				cart.setClient(Session.getSessionUser("user"));
				ci.setVoiture(voiture);
				ci.setCart(cart);
				cartItemService.save(ci);
				Notification.successNotification(voiture.getLibelle() + " est ajouté a votre cart!");
				this.voiture_counter.setText(Integer.toString(Integer.parseInt(this.voiture_counter.getText()) + 1));

			});
			this.home_pane.getChildren().addAll(image, overflow, car_price, ajouter, plus);

			addx += 425;
			counter++;

		}
	}

	private List<CartItem> cartitems;

	public void chargerUserCart() {
		Button next = ComposantUtil.createButton(674, 36, ">", "-fx-text-fill:white;-fx-background-color: #1c1c1c;");
		Button prev = ComposantUtil.createButton(341, 36, "<", "-fx-text-fill:white;-fx-background-color: #1c1c1c;");
		Label pasV = ComposantUtil.createLabel(333, 233, "Pas De Voiture ! retourner en arrière", "");
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
			ImageView imageView = ComposantUtil.createImageView(14, 13, 137, 200, "/storage/" + voiture.getImage());

			ImageView pngImage = ComposantUtil.createImageView(450, -5, 167, 200, "/icons/prix.png");

			Label libelle = GlyphsDude.createIconLabel(FontAwesomeIcon.CAR, voiture.getLibelle(), "24px", "24px",
					ContentDisplay.LEFT);
			libelle.setLayoutX(255);
			libelle.setLayoutY(21);

			// Label marque = ComposantUtil.createLabel(54, 96, voiture.getMarqueBrande(),
			// "-fx-text-fill:white");

			Label categorie = GlyphsDude.createIconLabel(FontAwesomeIcon.DASHBOARD, voiture.getCategorieNom(), "20px",
					"20px", ContentDisplay.LEFT);
			categorie.setLayoutX(225);
			categorie.setLayoutY(56);

			Label agence = GlyphsDude.createIconLabel(FontAwesomeIcon.LOCATION_ARROW, voiture.getAgenceAddress(),
					"22px", "22px", ContentDisplay.LEFT);
			agence.setLayoutX(225);
			agence.setLayoutY(86);

			Label prix = ComposantUtil.createLabel(490, 54, Double.toString(voiture.getCoutParJour()) + " DH",
					"-fx-font-size:29px;-fx-font-family:Bell MT;-fx-text-fill:#ffcb00");
			Label parJour = ComposantUtil.createLabel(571, 102, "Par Jour", "-fx-font-size:15px;-fx-text-fill:white");
			// Label couleur = ComposantUtil.createLabel(54, 76, voiture.getColor(),
			// "-fx-text-fill:white");
			// Text descreption = ComposantUtil.createText(143, 80,
			// voiture.getDiscreption(), 108, "-fx-text-fill:white");
			Button reserver = ComposantUtil.createButton(325, 123, "Réserver", "-fx-background-color:  #ffcb00");

			Button details = ComposantUtil.createButton(246, 123, "Détails",
					"-fx-background-color: #212121;-fx-text-fill: #ffcb00");
			details.setCursor(Cursor.HAND);
			Button suprimer = ComposantUtil.createButton(601, 13, "X",
					"-fx-background-color: transparent;-fx-text-fill:white;-fx-font-size:23px");
			reserver.setCursor(Cursor.HAND);
			suprimer.setCursor(Cursor.HAND);
			Pane pane = ComposantUtil.createPane(196, 39 + y, 158, 648,
					"-fx-border-width: 2px;-fx-border-color: #212121");
			pane.getChildren().addAll(imageView, libelle, categorie, agence, pngImage, parJour, prix, details, reserver,
					suprimer);
			this.cartPane.getChildren().addAll(pane);
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
			y += 217;
		}
	}

	private List<Voiture> searchVs;

	// search method
	@FXML
	void handelSearch(ActionEvent event) {
		if (this.searchVs != null) {
			this.searchVs.clear();
		}

		if (this.searchVs != null) {
			this.home_pane.getChildren().clear();
			chargerLesVoitures(this.searchVs);
		}

	}

//reset serch fields method
	@FXML
	void reset(ActionEvent event) {
		this.home_pane.getChildren().clear();
		initialize();
	}

	public void initChoosers() {
		// setup color chooser
				this.couleur_chooser.getItems().add("-SELECT-");
				this.voitureService.findAllColors().forEach(c -> this.couleur_chooser.getItems().add(c));
				this.couleur_chooser.getSelectionModel().selectFirst();

				// setup categorie chooser
				this.categorie_chooser.getItems().add("-SELECT-");
				this.categorieService.findAll().forEach(c->this.categorie_chooser.getItems().add(c.getName()));
				this.categorie_chooser.getSelectionModel().selectFirst();
				
				//setup marque chooser
				this.marque_chooser.getItems().add("-SELECT-");
				this.marqueService.findAll().forEach(m->this.marque_chooser.getItems().add(m.getBrand()));
				this.marque_chooser.getSelectionModel().selectFirst();
				
				//setup villes 
				this.ville_chooser.getItems().add("-SELECT-");
				this.agenceService.findAll().forEach(a->this.ville_chooser.getItems().add(a.getAdress()));
				this.ville_chooser.getSelectionModel().selectFirst();
	}
	
	
}
