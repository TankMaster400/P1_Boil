package com.example.p1_boil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class ControllerD {

    // F_dodawania
    @FXML
    private TextField Nazwa;
    @FXML
    private TextField Czas;
    @FXML
    private TextField Po;
    @FXML
    private TextField Przed;

    @FXML
    private void Dodaj(ActionEvent event) throws IOException
    {
        DBConnect connection = new DBConnect();
        Connection connectDB = connection.getDB();
        try {
            String sqlD = "INSERT INTO Records (Nazwa, Czas, Po , Przed) VALUES ('"+Nazwa.getText()+"','"+Integer.parseInt(Czas.getText())+"','"+ Po.getText()+"',"+ Przed.getText()+")";

            Statement statement = connectDB.createStatement();
            statement.executeUpdate(sqlD);



            Nazwa.clear();
            Czas.clear();
            Po.clear();
            Przed.clear();

        } catch(Exception e)
        {
            System.out.println(e);
        }

    }
}