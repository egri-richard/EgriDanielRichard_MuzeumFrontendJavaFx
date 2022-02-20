package com.example.museumrest;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class AddPaintingController extends Controller {
    @javafx.fxml.FXML
    private TextField tfTitle;
    @javafx.fxml.FXML
    private Button btnAddPainting;
    @javafx.fxml.FXML
    private Spinner<Integer> spYear;
    @javafx.fxml.FXML
    private CheckBox cbOnDisplay;

    @javafx.fxml.FXML
    public void addPaintingClick(ActionEvent actionEvent) {
        try {
            Painting p = new Painting(
                    0,
                    tfTitle.getText(),
                    spYear.getValue(),
                    cbOnDisplay.isSelected());
            Painting created = MuseumApi.addPainting(p);
            if (created != null){
                alert("Festmény hozzáadása sikeres");
            } else {
                alert("Festmény hozzáadása sikeretelen");
            }
        } catch (Exception e) {
            writeError(e);
        }
    }
}
