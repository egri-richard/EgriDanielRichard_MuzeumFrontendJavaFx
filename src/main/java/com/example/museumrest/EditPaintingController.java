package com.example.museumrest;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditPaintingController extends Controller {
    @javafx.fxml.FXML
    private TextField tfTitle;
    @javafx.fxml.FXML
    private Button btnEditPainting;
    @javafx.fxml.FXML
    private Spinner<Integer> spYear;
    @javafx.fxml.FXML
    private CheckBox cbOnDisplay;
    private Painting toEdit;

    @javafx.fxml.FXML
    public void editPaintingClick(ActionEvent actionEvent) {
        toEdit.setTitle(tfTitle.getText());
        toEdit.setYear(spYear.getValue());
        toEdit.setOn_display(cbOnDisplay.isSelected());

        try {
            Painting edited = MuseumApi.editPainting(toEdit);
            if (edited != null){
                alertWait("Sikeres módosítás");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Painting getToEdit() {
        return toEdit;
    }

    public void setToEdit(Painting toEdit) {
        this.toEdit = toEdit;
        setInitialValues();
    }

    private void setInitialValues() {
        tfTitle.setText(toEdit.getTitle());
        spYear.getValueFactory().setValue(toEdit.getYear());
        cbOnDisplay.setSelected(toEdit.getOn_display());
    }
}
