package com.example.location.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.example.location.bean.Reservation;
import com.example.location.bean.User;
import com.example.location.bean.Voiture;
import com.example.location.config.StageManager;
import com.example.location.service.facade.ReservationService;
import com.example.location.service.facade.UserService;
import com.example.location.service.facade.VoitureService;
import com.example.location.util.DateUtil;
import com.example.location.util.Notification;
import com.example.location.util.Session;
import com.example.location.views.FxmlView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

@Controller
public class DetailsController {
	@Lazy
	@Autowired
	private UserService userService;
	@Lazy
	@Autowired
	private ReservationService reservationService;
	@Lazy
	@Autowired
	private VoitureService voitureService;
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
	    private TextField res_full_name;

	    @FXML
	    private TextField res_email;

	    @FXML
	    private TextField res_tele;

	    @FXML
	    private TextField res_adress;

	    @FXML
	    private DatePicker res_date;
	    @FXML
	    private Button prix;
	    private Long id_v;
	    @FXML
	    void handlReservation(ActionEvent event) {
	    	User user = Session.getSessionUser("user");
	    	user.setEmail(this.res_email.getText());
	    	user.setAdress(this.res_adress.getText());
	    	user.setNumberPhone(this.res_tele.getText());
	    	String[] names = this.res_full_name.getText().split(" ");
	    	user.setNom(names[0]);
	    	user.setPrenom(names[1]);
	    	this.userService.modify(user);
	    	Reservation res = new Reservation();
	    	res.setClient(user);
	    	Voiture v = voitureService.findById(id_v);
	    	res.setVoiture(v);
	    	res.setReference("ACARS"+user.getId()+v.getId());
	    	res.setDateReserv(DateUtil.getDateFromDatePicker(this.res_date));
	    	if(reservationService.save(res) == 1) {
	    		Notification.successNotification("vous avez reservez "+v.getLibelle()+" avec succés!");
	    		this.stageManager.switchScene(FxmlView.WELCOME);
	    	}
	    	
	    	
	    }
	    @FXML
	    void hanldeClose(MouseEvent event) {
			Platform.exit();
			System.exit(0);
	    }
	@FXML
	public void initialize() {
		User user = Session.getSessionUser("user");
		this.res_adress.setText(user.getAdress());
		this.res_email.setText(user.getEmail());
		this.res_tele.setText(user.getNumberPhone());
		this.res_full_name.setText(user.getNom()+" "+user.getPrenom());
		Voiture voiture = Session.getSessionVoiture("voiture");
		this.id_v = voiture.getId();
		this.voiture_image.setImage(new Image("/storage/" + voiture.getImage()));
		this.voiture_nom.setText(voiture.getLibelle());
		this.voiture_marque.setText(voiture.getMarqueBrande());
		this.voiture_categorie.setText(voiture.getCategorieNom());
		this.prix.setText(Double.toString(voiture.getCoutParJour()) + "DH");
		this.voiture_description.setText( voiture.getDiscreption());
		this.voiture_couleur.setText( voiture.getColor());
		Session.removeSessionVoiture();
		
	}

	@FXML
	public void wel_back(ActionEvent event) {
		this.stageManager.switchScene(FxmlView.WELCOME);

	}
}
