/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Current Inventory");
        primaryStage.setWidth(410);
        primaryStage.setHeight(500);

        TableView<ProductClass> productTable = new TableView<>();

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

        BorderPane.setMargin(productTable, new Insets(0, 10, 10, 10));
        Button getValues = new Button("Get Values");

        getValues.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    String query = "select * from product";
                    conn = SqlConnection.DbConnector();
                    pst = conn.prepareStatement(query);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        data.add(new ProductClass(
                                rs.getString("product_id"),
                                rs.getString("product_name"),
                                rs.getString("product_prize"),
                                rs.getString("product_quantity")
                        ));
                        productTable.setItems(data);

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillOfSale.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Connection Closed");
            }

        });
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                EmployeeMenu emu = new EmployeeMenu();
                emu.start(primaryStage);
            }
        });

        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 0, 0, 5));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(getValues, back);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, productTable, hbox);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
