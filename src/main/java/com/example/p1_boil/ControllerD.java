package com.example.p1_boil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class ControllerD {

    // F_dodawania
    private Controller controller;
    public void setController(Controller controller){
        this.controller = controller;
    }

    @FXML
    private Label tekst_b;
    @FXML
    private TextField Nazwa;
    @FXML
    private TextField Czas;
    @FXML
    private TextField Po;
    @FXML
    private TextField Przed;

    @FXML
    public void Dodaj(ActionEvent event ) throws IOException
    {
        DBConnect connection = new DBConnect();
        Connection connectDB = connection.getDB();
        if(Integer.parseInt(Czas.getText()) >= 0 && (Integer.parseInt(Po.getText()) > 0 && Integer.parseInt(Przed.getText()) > 0 )) {
            try {
                String sqlD = "INSERT INTO Records (Nazwa, Czas, Po , Przed) VALUES ('" + Nazwa.getText() + "','" + Integer.parseInt(Czas.getText()) + "','" + Po.getText() + "'," + Przed.getText() + ")";

                Statement statement = connectDB.createStatement();
                statement.executeUpdate(sqlD);
                controller.odnowienietabeli();
                Nazwa.clear();
                Czas.clear();
                Po.clear();
                Przed.clear();
                tekst_b.setText("Dodano");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else
        {
            tekst_b.setText("Błedne dane\nCzas oraz Zdarzenia nie mogą być ujemne");
        }
    }
}
