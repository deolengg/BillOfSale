package billofsale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author karan
 */
public class EmployeeMenu extends Application 
{
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;
    @Override
    public void start(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 500, 350);

    MenuBar menuBar = new MenuBar();
    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
    root.setTop(menuBar);

    
    Menu inventryMenu = new Menu("Inventory");
    MenuItem viewStock = new MenuItem("View Stock");
    MenuItem updateStock = new MenuItem("Update Stock");
    viewStock.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
            ViewInventory vin = new ViewInventory();
            vin.start(primaryStage);
        }
    });
    
    updateStock.setOnAction(e-> {
        UpdateStock ups = new UpdateStock();
        ups.start(primaryStage);
    });
    
    

    inventryMenu.getItems().addAll(viewStock, updateStock,
        new SeparatorMenuItem());
    
    Menu billMenu = new Menu("Bill");
    MenuItem genrateBill = new MenuItem("Genrate Bill");
    genrateBill.setOnAction(e->{
    GenrateBill gb = new GenrateBill();
    gb.start(primaryStage);
    });
    
    billMenu.getItems().addAll(genrateBill,
        new SeparatorMenuItem());
    
    Menu exitMenu = new Menu("Exit");
    MenuItem systemExit = new MenuItem("System Exit");
    
    exitMenu.getItems().addAll(systemExit,
        new SeparatorMenuItem());



    menuBar.getMenus().addAll(inventryMenu, billMenu,exitMenu);

    primaryStage.setScene(scene);
    primaryStage.show();
    
    systemExit.setOnAction((javafx.event.ActionEvent event)
                -> {

            System.out.println("Exit");
            System.exit(1);
        });
    
  }
}