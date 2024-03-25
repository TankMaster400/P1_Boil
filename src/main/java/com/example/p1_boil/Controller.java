package com.example.p1_boil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    // main_view
    @FXML
    private void dodawanie(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("F_dodawania.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 560, 440);
        Stage stage = new Stage();
        stage.setTitle("Formularz Dodawania");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void Usun(ActionEvent event) throws IOException
    {

    }

    @FXML
    private void UsunW(ActionEvent event) throws IOException
    {

    }

    @FXML
    private void Generuj(ActionEvent event) throws IOException
    {

    }

    // F_dodawania
    @FXML
    private TextField Nazwa;
    @FXML
    private TextField Czas_pk;
    @FXML
    private TextField Czas_kp;
    @FXML
    private TextField Czas_l;
    @FXML
    private TextField Po;
    @FXML
    private TextField Przed;

    @FXML
    private void Dodaj(ActionEvent event) throws IOException
    {
        Nazwa.clear();
        Czas_kp.clear();
        Czas_pk.clear();
        Czas_l.clear();
        Po.clear();
        Przed.clear();
    }




}