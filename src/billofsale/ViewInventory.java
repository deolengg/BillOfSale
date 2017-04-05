/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewInventory extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Current Inventory");
        primaryStage.setWidth(410);
        primaryStage.setHeight(500);
        
        
        TableView<ProductClass> productTable = new TableView<>();
        //productTable.setItems(data);
        //productTable.setEditable(true);

        final Label label = new Label("Inventory For Products (Read Only)");
        label.setFont(new Font("Arial", 20));
        
        final ObservableList<ProductClass> data;
        data = FXCollections.observableArrayList();

        
        TableColumn productId = new TableColumn("ID");
        productId.setMinWidth(50);
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn productName = new TableColumn("Name");
        productName.setMinWidth(150);
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn productCost = new TableColumn("Cost $");
        productCost.setMinWidth(50);
        productCost.setCellValueFactory(new PropertyValueFactory<>("productPrize"));

        TableColumn productQuality = new TableColumn("Quantity");
        productQuality.setMinWidth(50);
        productQuality.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));

        productTable.getColumns().addAll(productId, productName, productCost, productQuality);
        
     
        BorderPane.setMargin(productTable,new Insets(0,10,10,10));
        Button getValues = new Button("Get Values");
        
        getValues.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                   
                    System.out.println("Driver Found");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@dilbert.humber.ca:1521:grok", "n01168570", "oracle");
                    System.out.println("Connection Done");
                    String query = "select * from product";
                    PreparedStatement st = con.prepareStatement(query);
                    ResultSet RS = st.executeQuery();
                    while (RS.next()) {
                       data.add(new ProductClass(
                                RS.getString("product_id"),
                                RS.getString("product_name"),
                                RS.getString("product_prize"),
                                RS.getString("product_quantity")       
                        ));
                        productTable.setItems(data);

                    }

                    con.close();
                    RS.close();
                    System.out.println("Connection Closed");

                } catch (SQLException | ClassNotFoundException obj) {
                    System.err.print("Either Drive OR Class Not Found " + obj);
                }
            }

        });
        Button back = new Button("Back");
        
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
        hbox.setPadding(new Insets(5,0,0,5));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(getValues,back);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, productTable,hbox);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        

        primaryStage.setScene(scene);
        primaryStage.show();
   
    }
}