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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Karan
 */
public class UpdateStock {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;
    
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        root.getChildren().addAll();

        Scene scene = new Scene(root, 500, 350);

        Text scenetitle = new Text("Update Inventory");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.add(scenetitle, 0, 0, 1, 1);
        Label userName = new Label("Product");
        root.add(userName, 0, 1);
        final ComboBox productNames = new ComboBox();
        productNames.setEditable(false);
        root.add(productNames, 1, 1);

        Label currentqtylabel = new Label("Current Quantity");
        root.add(currentqtylabel, 0, 2);

        Label cqtytextbox = new Label();
        root.add(cqtytextbox, 1, 2);

        ObservableList<String> productOptions = FXCollections.observableArrayList();
        try {
            String query = "select product_name from product";

            conn = SqlConnection.DbConnector();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("product_name"));
                productOptions.addAll(rs.getString("product_name"));
                productNames.setItems(productOptions);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillOfSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        productNames.setOnAction(e->{
            try {
                String query2 = "select product_quantity from product where product_name ='"+productNames.getValue()+"' ";
                conn = SqlConnection.DbConnector();
                pst = conn.prepareStatement(query2);
                rs = pst.executeQuery();
                while (rs.next()){
                    //System.out.println("qty  " +rs.getString("product_quantity"));
                    cqtytextbox.setText(rs.getString("product_quantity"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(BillOfSale.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BillOfSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connection Closed");
        
        });
        
        

        Label newqtylabel = new Label("Add Quantity");
        root.add(newqtylabel, 0, 3);

        TextField nqtytextbox = new TextField();
        root.add(nqtytextbox, 1, 3);
        HBox hbBtn = new HBox(10);
        Button add = new Button("Add");
        add.setAlignment(Pos.BOTTOM_LEFT);
        
        add.setOnAction(e->{
            try {
                 
                int currentQty = Integer.parseInt(cqtytextbox.getText());
                int newQty = Integer.parseInt(nqtytextbox.getText());
                int finalValue = currentQty+ newQty;
                String query3 = "update product set product_quantity='"+finalValue+"' where product_name ='"+productNames.getValue()+"' ";
                
                conn = SqlConnection.DbConnector();
                pst = conn.prepareStatement(query3);
                rs = pst.executeQuery();
                conn.commit();
                conn.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Update");
                            alert.setContentText("Stock added");
                            alert.showAndWait();
                            //productNames.getItems().clear();
                            cqtytextbox.setText("");
                            nqtytextbox.setText("");
                            
                
            } catch (SQLException ex) {
                Logger.getLogger(BillOfSale.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
        

        Button back = new Button("Back");
        back.setAlignment(Pos.BASELINE_RIGHT);
        hbBtn.getChildren().addAll(add, back);
        root.add(hbBtn, 1, 5);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                EmployeeMenu emu = new EmployeeMenu();
                emu.start(primaryStage);
            }
        });

        primaryStage.setTitle("Update Inventory");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    
}
