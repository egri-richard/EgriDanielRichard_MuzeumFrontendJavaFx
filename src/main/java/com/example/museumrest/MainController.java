package com.example.museumrest;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class MainController {

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
            //Api.addPainting();
        } else {
            //Api.addStatue();
        }
    }

    @javafx.fxml.FXML
    public void editItem(ActionEvent actionEvent) {
        int selectedTab = tabPane.getSelectionModel().getSelectedIndex();
        if (selectedTab == 0) {

        } else {

        }
    }

    @javafx.fxml.FXML
    public void deleteItem(ActionEvent actionEvent) {
        int selectedTab = tabPane.getSelectionModel().getSelectedIndex();
        if (selectedTab == 0) {

        } else {

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