package com.example.location.frontEnd;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.example.location.bean.Address;
import com.example.location.config.StageManager;
import com.example.location.service.facade.AddressService;
import com.example.location.views.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;





@Controller
public class LocationController {
	@Lazy
	@Autowired
	private StageManager stageManager;
	@FXML
	private Button loadAdress;
	public void loadAddressView(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.ADDRESS);
	}
	@FXML
	private TextField paye;
	@FXML
	private TextField ville;
	@FXML
	private TextField nbr;
	@FXML
	private TextField region;
	@FXML
	private TextField province;
	@Lazy
	@Autowired
	private AddressService addressService;
	public void insertAddress(ActionEvent event) {
		Address address = new Address();
		address.setNumb(Integer.parseInt(this.nbr.getText()));
		address.setPaye(this.paye.getText());
		address.setProvince(this.province.getText());
		address.setRegion(this.region.getText());
		address.setVille(this.ville.getText());
		addressService.save(address);
	}
}
