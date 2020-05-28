package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class FXMLController {

	Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleSimula(ActionEvent event) {
    	this.txtResult.clear();
    	this.model.run();
    	this.txtResult.appendText(String.format("numero clienti soddisfatti %d \n", this.model.clientiSoddisfatti()));
    	this.txtResult.appendText(String.format("numero clienti insoddisfatti %d \n", this.model.clientiInsoddisfatti()));
    	this.txtResult.appendText(String.format("numero clienti  %d \n", this.model.clientiTotali()));
    	
    	

    }

    @FXML
    void initialize() {
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}

