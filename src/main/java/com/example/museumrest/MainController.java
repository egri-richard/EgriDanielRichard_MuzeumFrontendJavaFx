package com.example.museumrest;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import com.example.museumrest.Controller;

public class MainController extends Controller {

    @javafx.fxml.FXML
    private TableView<Painting> paintingTable;
    @javafx.fxml.FXML
    private TableColumn<Painting, Boolean> paintingOnDisplayCol;
    @javafx.fxml.FXML
    private TableView<Statue> statueTable;
    @javafx.fxml.FXML
    private TableColumn<Painting, Integer> paintingDateCol;
    @javafx.fxml.FXML
    private TableColumn<Painting, String> paintingNameCol;
    @javafx.fxml.FXML
    private TableColumn<Statue, String> statuePersonCol;
    @javafx.fxml.FXML
    private Tab paintingPane;
    @javafx.fxml.FXML
    private Tab statuePane;
    @javafx.fxml.FXML
    private TableColumn<Statue, Integer> statueHeightCol;
    @javafx.fxml.FXML
    private TableColumn<Statue, Integer> statuePriceCol;
    @javafx.fxml.FXML
    private TabPane tabPane;
    @javafx.fxml.FXML
    private Button btnEdit;
    @javafx.fxml.FXML
    private Button btnDelete;
    @javafx.fxml.FXML
    private Button btnAdd;

    public void initialize() {
        paintingNameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        paintingDateCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        paintingOnDisplayCol.setCellValueFactory(new PropertyValueFactory<>("on_display"));

        statuePersonCol.setCellValueFactory(new PropertyValueFactory<>("person"));
        statueHeightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        statuePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            fillTable();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void addItem(ActionEvent actionEvent) {
        int selectedTab = tabPane.getSelectionModel().getSelectedIndex();
        if (selectedTab == 0) {
            try {
                Controller add = newWindow("add-painting-view.fxml", "Festmény hozzáadása",
                        320, 400);
                add.getStage().setOnCloseRequest(event -> {
                    try {
                        fillTable();
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                });
                add.getStage().show();
            } catch (Exception e) {
                writeError(e);
            }
        } else {
            try {
                Controller add = newWindow("add-statue-view.fxml", "Szobor hozzáadása",
                        320, 400);
                add.getStage().setOnCloseRequest(event -> {
                    try {
                        fillTable();
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                });
                add.getStage().show();
            } catch (Exception e) {
                writeError(e);
            }
        }
    }

    @javafx.fxml.FXML
    public void editItem(ActionEvent actionEvent) {
        int selectedTab = tabPane.getSelectionModel().getSelectedIndex();
        if (selectedTab == 0) {
            int selectedIndex = paintingTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
                return;
            }
            Painting toEdit = paintingTable.getSelectionModel().getSelectedItem();
            try {
                EditPaintingController edit = (EditPaintingController) newWindow("edit-painting-view.fxml",
                        "Festmény módosítása", 320, 400);
                edit.setToEdit(toEdit);
                edit.getStage().setOnHiding(event -> paintingTable.refresh());
                edit.getStage().show();
            } catch (IOException e) {
                writeError(e);
            }
        } else {
            int selectedIndex = statueTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
                return;
            }
            Statue toEdit = statueTable.getSelectionModel().getSelectedItem();
            try {
                EditStatueController edit = (EditStatueController) newWindow("edit-statue-view.fxml",
                        "Szobor módosítása", 320, 400);
                edit.setToEdit(toEdit);
                edit.getStage().setOnHiding(event -> statueTable.refresh());
                edit.getStage().show();
            } catch (IOException e) {
                writeError(e);
            }
        }
    }

    @javafx.fxml.FXML
    public void deleteItem(ActionEvent actionEvent) {
        int selectedTab = tabPane.getSelectionModel().getSelectedIndex();
        if (selectedTab == 0) {
            int selectedIndex = paintingTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
                return;
            }
            Painting p = paintingTable.getSelectionModel().getSelectedItem();
            if (!confirm("Biztos hogy törölni szeretné az alábbi festményt: " + p.getTitle())) {
                return;
            }
            try {
                boolean sikeres = MuseumApi.deletePainting(p.getId());
                alert(sikeres ? "Sikeres törlés" : "Sikertelen törlés");
                fillTable();
            } catch (IOException e) {
                writeError(e);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            int selectedIndex = statueTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
                return;
            }
            Statue s = statueTable.getSelectionModel().getSelectedItem();
            if (!confirm("Biztos hogy törölni szeretné az alábbi festményt: " + s.getPerson())) {
                return;
            }
            try {
                boolean sikeres = MuseumApi.deleteStatue(s.getId());
                alert(sikeres ? "Sikeres törlés" : "Sikertelen törlés");
                fillTable();
            } catch (IOException e) {
                writeError(e);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillTable() throws IOException, ParseException {
        List<Painting> allP = MuseumApi.getPaintings();
        paintingTable.getItems().clear();
        for (Painting p: allP) {
            paintingTable.getItems().add(p);
        }

        List<Statue> allS = MuseumApi.getStatues();
        statueTable.getItems().clear();
        for (Statue s: allS) {
            statueTable.getItems().add(s);
        }
    }
}