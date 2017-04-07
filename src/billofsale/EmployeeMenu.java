package billofsale;

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
public class EmployeeMenu extends Application {
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
    

    inventryMenu.getItems().addAll(viewStock, updateStock,
        new SeparatorMenuItem());
    
    Menu billMenu = new Menu("Bill");
    MenuItem genrateBill = new MenuItem("Genrate Bill");
    
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