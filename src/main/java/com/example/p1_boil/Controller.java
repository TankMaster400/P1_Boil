package com.example.p1_boil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // main_view

    @FXML
    private TableView<Records> Table1;
    @FXML
    private TableColumn<Records, String> Nazwa_t;
    @FXML
    private TableColumn<Records, Integer> CZAS_t;
    @FXML
    private TableColumn<Records, String> OD_t;
    @FXML
    private TableColumn<Records, String> DO_t;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


        DBConnect connection = new DBConnect();
        Connection connectDB = connection.getDB();

        String sqlS = "SELECT * FROM Records";

        ObservableList<Records> list = FXCollections.observableArrayList();
        
        try
        {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlS);

            while (resultSet.next())
            {
                list.add(new Records(resultSet.getString("Nazwa"),Integer.parseInt(resultSet.getString("Czas")),resultSet.getString("Po"),resultSet.getString("Przed"),Integer.parseInt(resultSet.getString("id"))));
            }

            Table1.setItems(list);

            Nazwa_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Nazwa"));
            Nazwa_t.setCellFactory(TextFieldTableCell.forTableColumn());
            Nazwa_t.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Records, String>>() {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Records, String> event) {
                                            Records re = event.getRowValue();
                                            re.setNazwa(event.getNewValue());
                                            String sqlU = "UPDATE Records SET Nazwa = '" + event.getNewValue() + "' WHERE id =" + re.getId();
                                            try {
                                                Statement statementUN = connectDB.createStatement();
                                                ResultSet resultSetUN = statement.executeQuery(sqlU);
                                            }
                                            catch (Exception e)
                                            {
                                                System.out.println(e);
                                            }
                                        }
                                    }
            );

            CZAS_t.setCellValueFactory(new PropertyValueFactory<Records, Integer>("Czas"));
            CZAS_t.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            CZAS_t.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Records, Integer>>() {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Records, Integer> event) {
                                            Records re = event.getRowValue();
                                            re.setCzas(event.getNewValue());
                                            String sqlU = "UPDATE Records SET Czas = '" + event.getNewValue() + "' WHERE id =" + re.getId();
                                            try {
                                                Statement statementUN = connectDB.createStatement();
                                                ResultSet resultSetUN = statement.executeQuery(sqlU);
                                            }
                                            catch (Exception e)
                                            {
                                                System.out.println(e);
                                            }
                                        }
                                    }
            );

            OD_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Przed"));
            OD_t.setCellFactory(TextFieldTableCell.forTableColumn());
            OD_t.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Records, String>>() {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Records, String> event) {
                                            Records re = event.getRowValue();
                                            re.setPrzed(event.getNewValue());
                                            String sqlU = "UPDATE Records SET Po = '" + event.getNewValue() + "' WHERE id =" + re.getId();
                                            try {
                                                Statement statementUN = connectDB.createStatement();
                                                ResultSet resultSetUN = statement.executeQuery(sqlU);
                                            }
                                            catch (Exception e)
                                            {
                                                System.out.println(e);
                                            }
                                        }
                                    }
            );

            DO_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Po"));
            DO_t.setCellFactory(TextFieldTableCell.forTableColumn());
            DO_t.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Records, String>>() {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<Records, String> event) {
                                            Records re = event.getRowValue();
                                            re.setPo(event.getNewValue());
                                            String sqlU = "UPDATE Records SET Przed = '" + event.getNewValue() + "' WHERE id =" + re.getId();
                                            try {
                                                Statement statementUN = connectDB.createStatement();
                                                ResultSet resultSetUN = statement.executeQuery(sqlU);
                                            }
                                            catch (Exception e)
                                            {
                                                System.out.println(e);
                                            }
                                        }
                                    }
            );


        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

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
        int pam = Table1.getSelectionModel().getSelectedIndex();

        DBConnect connection = new DBConnect();
        Connection connectDB = connection.getDB();
        Records re = Table1.getItems().get(pam);

        String sqlD = "DELETE FROM Records WHERE id =" + re.getId();


        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(sqlD);

            String sqlS2 = "SELECT * FROM Records";

            ObservableList<Records> list = FXCollections.observableArrayList();

            Statement statement2 = connectDB.createStatement();
            ResultSet resultSet = statement2.executeQuery(sqlS2);

            while (resultSet.next())
            {
                list.add(new Records(resultSet.getString("Nazwa"),Integer.parseInt(resultSet.getString("Czas")),resultSet.getString("Po"),resultSet.getString("Przed"),Integer.parseInt(resultSet.getString("id"))));
            }
            Table1.setEditable(true);
            Table1.setItems(list);

            Nazwa_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Nazwa"));
            CZAS_t.setCellValueFactory(new PropertyValueFactory<Records, Integer>("Czas"));
            OD_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Przed"));
            DO_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Po"));

        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }

    @FXML
    private void UsunW(ActionEvent event) throws IOException
    {
        DBConnect connection = new DBConnect();
        Connection connectDB = connection.getDB();

        String sqlS = "DELETE FROM Records";
        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(sqlS);

            String sqlS2 = "SELECT * FROM Records";

            ObservableList<Records> list = FXCollections.observableArrayList();

                Statement statement2 = connectDB.createStatement();
                ResultSet resultSet = statement2.executeQuery(sqlS2);

                while (resultSet.next())
                {
                    list.add(new Records(resultSet.getString("Nazwa"),Integer.parseInt(resultSet.getString("Czas")),resultSet.getString("Po"),resultSet.getString("Przed"),Integer.parseInt(resultSet.getString("id"))));
                }
                Table1.setEditable(true);
                Table1.setItems(list);

                Nazwa_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Nazwa"));
                CZAS_t.setCellValueFactory(new PropertyValueFactory<Records, Integer>("Czas"));
                OD_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Przed"));
                DO_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Po"));

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    @FXML
    private void Generuj(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Generuj_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 560, 440);
        Stage stage = new Stage();
        stage.setTitle("Graf");
        stage.setScene(scene);
        stage.show();

//        DBConnect connection = new DBConnect();
//        Connection connectDB = connection.getDB();
//
//        String sqlS = "SELECT * FROM Records";
//
//        ObservableList<Records> list = FXCollections.observableArrayList();
//
//        try
//        {
//            Statement statement = connectDB.createStatement();
//            ResultSet resultSet = statement.executeQuery(sqlS);
//
//            while (resultSet.next())
//            {
//                list.add(new Records(resultSet.getString("Nazwa"),Integer.parseInt(resultSet.getString("Czas")),resultSet.getString("Po"),resultSet.getString("Przed"),Integer.parseInt(resultSet.getString("id"))));
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//        }
        Data data = new Data();
        data.licz();

    }



}