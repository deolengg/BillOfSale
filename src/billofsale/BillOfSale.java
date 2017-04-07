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
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BillOfSale extends Application {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;
   
   public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
   
    @Override
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
        root.add(scenetitle, 0, 0, 2, 1);

        Label product = new Label("Product");
        root.add(product, 0, 1);

        TextField productTextField = new TextField();
        root.add(productTextField, 1, 1);
        productTextField.setEditable(false);

        Label qty = new Label("Quantity:");
        root.add(qty, 0, 2);

        TextField quantityTextField = new TextField();
        root.add(quantityTextField, 1, 2);

        Button btn = new Button("Add");
        HBox hbBtn = new HBox(10);

        Button Rbtn = new Button("Back");
        HBox RhbBtn = new HBox(10);

        final Label message = new Label("");
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        RhbBtn.setAlignment(Pos.BOTTOM_LEFT);
        

        hbBtn.getChildren().addAll(btn, Rbtn, message);
        root.add(hbBtn, 1, 4);
        

        final Text actiontarget = new Text();
        root.add(actiontarget, 1, 6);

        primaryStage.setTitle("Update Inventory");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}
