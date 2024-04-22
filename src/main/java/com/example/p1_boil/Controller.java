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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Math.sqrt;

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

            OD_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Po"));
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

            DO_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Przed"));
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

        ControllerD dialog = fxmlLoader.getController();
        dialog.setController(this);

    }
    public void odnowienietabeli()
    {

            DBConnect connection = new DBConnect();
            Connection connectDB = connection.getDB();
            try {
                String sqlS2 = "SELECT * FROM Records";

                ObservableList<Records> list = FXCollections.observableArrayList();

                Statement statement2 = connectDB.createStatement();
                ResultSet resultSet = statement2.executeQuery(sqlS2);

                while (resultSet.next()) {
                    list.add(new Records(resultSet.getString("Nazwa"), Integer.parseInt(resultSet.getString("Czas")), resultSet.getString("Po"), resultSet.getString("Przed"), Integer.parseInt(resultSet.getString("id"))));
                }
                Table1.setEditable(true);
                Table1.setItems(list);

                Nazwa_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Nazwa"));
                CZAS_t.setCellValueFactory(new PropertyValueFactory<Records, Integer>("Czas"));
                OD_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Po"));
                DO_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Przed"));
            } catch (Exception e) {
                System.out.println(e);
            }

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
            OD_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Po"));
            DO_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Przed"));

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
                OD_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Po"));
                DO_t.setCellValueFactory(new PropertyValueFactory<Records, String>("Przed"));

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
        Scene scene = new Scene(fxmlLoader.load(), 1200, 640, Color.LIGHTGREEN);
        Stage stage = new Stage();
        stage.setTitle("Graf");
        stage.setScene(scene);
        stage.show();

        DBConnect connection = new DBConnect();
        Connection connectDB = connection.getDB();

        String sqlS = "SELECT * FROM Records";

        //ObservableList<Records> list = FXCollections.observableArrayList();

        Data data = new Data();
        try
        {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlS);

            while (resultSet.next())
            {
                data.addRecord(resultSet.getString("Nazwa"),Integer.parseInt(resultSet.getString("Czas")),resultSet.getString("Po"),resultSet.getString("Przed"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        data.licz();



        //TODO: rewrite, one controller two fxml's - possibly wrong
        ScrollPane baseScrollPane = (ScrollPane)fxmlLoader.getNamespace().get("genPane");
        Pane basePane = new Pane();
        Integer sizeVariable = 90;
        //Iterate over nodes
        //TODO: rewrite, add classes for circles, do something with var i
        System.out.println("Generowanie grafiki: START");
        Integer i = 1;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        System.out.println("Generowanie: ilość node "+data.nodes.size());
        for(Node dataNode : data.nodes) {
            System.out.println("Generowanie: NODE "+dataNode.name);
            Circle circle = new Circle();
            circle.setStroke(Color.BLACK);

            System.out.println("dataNode.sk: "+ dataNode.sk);
            System.out.println("");
            if(dataNode.next.isEmpty()){
                circle.setFill(Color.RED);
            }else if(dataNode.prev.isEmpty()){
                circle.setFill(Color.GREEN);
            }else if(dataNode.sk != null){
                circle.setFill(Color.LIGHTBLUE);
            }else{
                circle.setFill(null);
            }

            circle.setRadius(sizeVariable/3);
            circle.setCenterX(sizeVariable*i);
            circle.setCenterY(sizeVariable*i);

            Text text = new Text();
            text.setFont(new Font(20));
            text.setWrappingWidth(200);
            text.setTextAlignment(TextAlignment.JUSTIFY);
            text.setText(dataNode.name);
            tmp.add(Integer.parseInt(dataNode.name));
            text.setX(sizeVariable*i-5); //node number location
            text.setY(-(sizeVariable/6)+4+sizeVariable*i); //offset to center

            Text textB = new Text();
            textB.setFont(new Font(15));
            textB.setWrappingWidth(200);
            textB.setTextAlignment(TextAlignment.JUSTIFY);
            textB.setText(String.valueOf(dataNode.time_pk));
            textB.setX(sizeVariable*i-sizeVariable/3.5); //node number location
            textB.setY(sizeVariable*i+5); //offset to center

            Text textC = new Text();
            textC.setFont(new Font(15));
            textC.setWrappingWidth(200);
            textC.setTextAlignment(TextAlignment.JUSTIFY);
            textC.setText(String.valueOf(dataNode.time_kp));
            textC.setX(sizeVariable*i+sizeVariable/8); //node number location
            textC.setY(sizeVariable*i+5); //offset to center

            Text textD = new Text();
            textD.setFont(new Font(15));
            textD.setWrappingWidth(200);
            textD.setTextAlignment(TextAlignment.JUSTIFY);
            textD.setText(String.valueOf(dataNode.time_l));
            textD.setX(sizeVariable*i-sizeVariable/18); //node number location
            textD.setY(+(sizeVariable/6)+4+sizeVariable*i); //offset to center

            Text textE = new Text();
            textE.setFont(new Font(15));
            textE.setWrappingWidth(2000);
            textE.setTextAlignment(TextAlignment.JUSTIFY);
            String finalString = "";
            for(Czyn nextCzyn : dataNode.next){
                if(nextCzyn.next == dataNode.sk){
                    finalString += "KRYT CZYN: "+nextCzyn.name+ " - to: " + nextCzyn.next.name + " - time: " + nextCzyn.time + " | ";
                }else{
                    finalString += "czyn: "+nextCzyn.name+ " - to: " + nextCzyn.next.name + " - time: " + nextCzyn.time + " | ";
                }

            }
            textE.setText(finalString);
            textE.setX(sizeVariable*i+sizeVariable/2.5); //node number location
            textE.setY(sizeVariable*i+5); //offset to center

            Line crossA = new Line();
            Line crossB = new Line();

            crossA.setStartX((sqrt(2)/2)*(sizeVariable/3)+sizeVariable*i);
            crossA.setStartY((sqrt(2)/2)*(sizeVariable/3)+sizeVariable*i);
            crossA.setEndX((-(sqrt(2)/2)*(sizeVariable/3)+sizeVariable*i));
            crossA.setEndY((-(sqrt(2)/2)*(sizeVariable/3)+sizeVariable*i));

            crossB.setStartX(-(sqrt(2)/2)*(sizeVariable/3)+sizeVariable*i);
            crossB.setStartY((sqrt(2)/2)*(sizeVariable/3)+sizeVariable*i);
            crossB.setEndX(((sqrt(2)/2)*(sizeVariable/3)+sizeVariable*i));
            crossB.setEndY((-(sqrt(2)/2)*(sizeVariable/3)+sizeVariable*i));

            basePane.getChildren().add(circle);
            basePane.getChildren().add(text);
            basePane.getChildren().add(textB);
            basePane.getChildren().add(textC);
            basePane.getChildren().add(textD);
            basePane.getChildren().add(textE);
            basePane.getChildren().add(crossA);
            basePane.getChildren().add(crossB);
            i++;
        }
        //Iterate over links
        //TODO: rename 'czyns' to 'links' or literally anything else
        //TODO: rewrite, add classes for lines
        Boolean switchOn = true;
        for(Czyn dataLink : data.czyns){
            CubicCurve cubicCurve = new CubicCurve();
            Line arrowPartA = new Line();
            Line arrowPartB = new Line();

            Integer prevNumberPosition = tmp.indexOf(Integer.parseInt(dataLink.prev.name))+1;
            Integer nextNumberPosition = tmp.indexOf(Integer.parseInt(dataLink.next.name))+1;

            if(switchOn){
                cubicCurve.setControlX1(sizeVariable/8+sizeVariable*prevNumberPosition);
                cubicCurve.setControlY1(sizeVariable+sizeVariable*prevNumberPosition);
                cubicCurve.setControlX2(-sizeVariable+sizeVariable*nextNumberPosition);
                cubicCurve.setControlY2(-sizeVariable/8+sizeVariable*nextNumberPosition);
                switchOn = false;
            }else{
                cubicCurve.setControlX1(sizeVariable+sizeVariable*prevNumberPosition);
                cubicCurve.setControlY1(sizeVariable/8+sizeVariable*prevNumberPosition);
                cubicCurve.setControlX2(-sizeVariable/8+sizeVariable*nextNumberPosition);
                cubicCurve.setControlY2(-sizeVariable+sizeVariable*nextNumberPosition);
                switchOn = true;
            }

            cubicCurve.setStartX(sizeVariable/3+sizeVariable*prevNumberPosition);
            cubicCurve.setStartY(sizeVariable*prevNumberPosition);

            cubicCurve.setEndX(-sizeVariable/3+sizeVariable*nextNumberPosition);
            cubicCurve.setEndY(sizeVariable*nextNumberPosition);

            Integer arrowOffset = -sizeVariable/6;
            arrowPartA.setStartX(arrowOffset+(-sizeVariable/3)+sizeVariable*nextNumberPosition);
            arrowPartA.setStartY(-arrowOffset+sizeVariable*nextNumberPosition);
            arrowPartB.setStartX(arrowOffset+(-sizeVariable/3)+sizeVariable*nextNumberPosition);
            arrowPartB.setStartY(arrowOffset+sizeVariable*nextNumberPosition);

            arrowPartA.setEndX(-sizeVariable/3+sizeVariable*nextNumberPosition);
            arrowPartA.setEndY(sizeVariable*nextNumberPosition);
            arrowPartB.setEndX(-sizeVariable/3+sizeVariable*nextNumberPosition);
            arrowPartB.setEndY(sizeVariable*nextNumberPosition);

            cubicCurve.setStroke(Color.FORESTGREEN);
            cubicCurve.setStrokeWidth(1);

            arrowPartA.setStroke(Color.BLACK);
            arrowPartA.setStrokeWidth(1);

            arrowPartB.setStroke(Color.BLACK);
            arrowPartB.setStrokeWidth(1);

            cubicCurve.setFill(null);
            basePane.getChildren().add(cubicCurve);
            basePane.getChildren().add(arrowPartA);
            basePane.getChildren().add(arrowPartB);
        }
        baseScrollPane.setContent(basePane);
    }



}