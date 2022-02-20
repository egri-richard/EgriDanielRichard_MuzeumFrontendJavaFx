package com.example.museumrest;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import com.example.museumrest.Controller;

public class AddStatueController extends Controller {
    @javafx.fxml.FXML
    private Button btnAddStatue;
    @javafx.fxml.FXML
    private Spinner<Integer> spPrice;
    @javafx.fxml.FXML
    private Spinner<Integer> spHeight;
    @javafx.fxml.FXML
    private TextField tfPerson;

    @javafx.fxml.FXML
    public void addStatueClick(ActionEvent actionEvent) {
        try {
            Statue s = new Statue(
                    0,
                    tfPerson.getText(),
                    spHeight.getValue(),
                    spPrice.getValue());
            Statue created = MuseumApi.addStatue(s);
            if (created != null){
                alert("Szobor hozzáadása sikeres");
            } else {
                alert("Szobor hozzáadása sikeretelen");
            }
        } catch (Exception e) {
            writeError(e);
        }
    }
}
