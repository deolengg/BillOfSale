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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
public class GenrateBill {
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    PreparedStatement pst4 = null;
    PreparedStatement pst5 = null;
    PreparedStatement pst6 = null;
    
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs4 = null;
    ResultSet rs5 = null;
    ResultSet rs6 = null;
    Connection conn = null;
    static int estimation;
    static double finalBill;
    static int costOverall;
    static String cost;
    
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(25, 25, 25, 25));

        root.getChildren().addAll();

        Scene scene = new Scene(root, 800, 1100);

        Text scenetitle = new Text("Bill Of Sale");
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.add(scenetitle, 0, 0, 1, 1);
        
        Label customerName = new Label("Customer Name");
        root.add(customerName, 0, 1);

        TextField txtCustomerName = new TextField();
        root.add(txtCustomerName, 1, 1);

        Label customerNumber = new Label("Customer Number");
        root.add(customerNumber, 0, 2);
        
        TextField txtCustomerNumber = new TextField();
        root.add(txtCustomerNumber, 1, 2);
        
        Label paymentType = new Label("Payment Type");
        root.add(paymentType, 0, 3);
        final ComboBox paymentTypeList = new ComboBox();
        paymentTypeList.setEditable(true);
        paymentTypeList.getItems().addAll("Visa","Master Card","Cash","Cheque");
        root.add(paymentTypeList, 1, 3);
        
        Label productList = new Label("Product");
        root.add(productList, 0, 4);
        final ComboBox productNames = new ComboBox();
        productNames.setEditable(false);
        root.add(productNames, 1, 4);

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
        
        Label newqtylabel = new Label("Quantity");
        root.add(newqtylabel, 0, 5);

        TextField nqtytextbox = new TextField();
        root.add(nqtytextbox, 1, 5);
        HBox hbBtn = new HBox(10);
        Button add = new Button("Add");
        add.setAlignment(Pos.BOTTOM_LEFT);
        
        
        Button back = new Button("Back");
        back.setAlignment(Pos.BASELINE_RIGHT);
        
        Button clear = new Button("Clear");
        back.setAlignment(Pos.BASELINE_RIGHT);
        
        Button estimate = new Button("Get Estimate");
        back.setAlignment(Pos.BASELINE_RIGHT);
        
        hbBtn.getChildren().addAll(add, back,clear,estimate);
        root.add(hbBtn, 1, 6);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                EmployeeMenu emu = new EmployeeMenu();
                emu.start(primaryStage);
            }
        });
        
        
        
        
        TableView<BillClass> productListTable = new TableView<>();
        productListTable.setEditable(true);
        final ObservableList<BillClass> data;
        data = FXCollections.observableArrayList();

        TableColumn productName = new TableColumn("Name");
        productName.setMinWidth(150);
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn productCost = new TableColumn("Cost $");
        productCost.setMinWidth(50);
        productCost.setCellValueFactory(new PropertyValueFactory<>("productPrize"));

        TableColumn productQuality = new TableColumn("Quantity");
        productQuality.setMinWidth(50);
        productQuality.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));

        productListTable.getColumns().addAll(productName, productCost, productQuality);
        BorderPane.setMargin(productListTable, new Insets(0, 10, 10, 10));
   
        root.add(productListTable, 1, 7);
        
        HBox hbox2 = new HBox(10);
        Button genrateBill = new Button("Genrate Bill");
        back.setAlignment(Pos.BASELINE_CENTER);
        
        hbox2.getChildren().addAll(genrateBill);
        root.add(hbox2, 1, 8);
        
        add.setOnAction((ActionEvent e) -> {
            System.out.println(productNames.getValue());
            try {
            String query = "select product_prize from product where product_name ='"+productNames.getValue()+"' ";
                    conn = SqlConnection.DbConnector();
                    pst = conn.prepareStatement(query);
                    rs = pst.executeQuery();
                    while(rs.next()){
                    //System.out.println(rs.getString("product_prize")); // product prize from database
                    cost =rs.getString("product_prize");
                    Integer x = Integer.valueOf(nqtytextbox.getText());
                    }
                    
                    rs.close();
                    int x = Integer.parseInt(nqtytextbox.getText());
                    costOverall =Integer.parseInt(cost) * x;
            String query2 = "insert into productlist values('"+productNames.getValue()+"','"+costOverall+"','"+nqtytextbox.getText()+"')"; 
                    
                    pst2 = conn.prepareStatement(query2);
                    rs2 = pst2.executeQuery();
                    conn.commit();
                    rs2.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setContentText("Product "+productNames.getValue()+" is added to the List");
                            alert.showAndWait();
                    String query3 = "select * from productlist";
                    
                    pst3 = conn.prepareStatement(query3);
                    rs3 = pst3.executeQuery();
                    productListTable.getItems().clear();
                    while(rs3.next()){
                        
                        data.add(new BillClass(
                                rs3.getString("product_name"),
                                rs3.getString("product_prize"),
                                rs3.getString("product_qty")
                                
                        ));
                        productListTable.setItems(data);
                    }
                    rs3.close();
                    nqtytextbox.setText("");
                    conn.close();
                    
                    
            }
            catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
            //System.out.println(nqtytextbox.getText()); // qty selected by customer
            
        });
        clear.setOnAction(e->{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Request For Reset");
                            alert.setContentText("Are you Sure, You want to reset the list ?");
                            alert.showAndWait();
                    String queryForClear = "delete from productlist"; 
                    try {
                    conn = SqlConnection.DbConnector();
                    pst4 = conn.prepareStatement(queryForClear);
                    rs4 = pst4.executeQuery();
                    conn.commit();
                    conn.close();
                    }
                    catch (SQLException ex) {
                    ex.printStackTrace();
                }                    
        productListTable.getItems().clear();
        
        });
        
        estimate.setOnAction(e->{
        String queryForEstimate = "select product_prize from productlist";
        try {
        conn = SqlConnection.DbConnector();
        pst5 = conn.prepareStatement(queryForEstimate);
        rs5 = pst5.executeQuery();
        while(rs5.next()){
            estimation+=rs5.getInt("product_prize");
            }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Estimation");
                            alert.setContentText("Your Estimated Bill is "+estimation);
                            alert.showAndWait();
                            conn.close();
        }
        catch (SQLException ex) {
                    ex.printStackTrace();
                }
        });
        genrateBill.setOnAction(e->{
          String customerEntry = "insert into customertable values ('"+txtCustomerName.getText()+"','"+txtCustomerNumber.getText()+"','"+paymentTypeList.getValue()+"')"; 
          try {  
            conn = SqlConnection.DbConnector();
            pst6 = conn.prepareStatement(customerEntry);
            rs6 = pst6.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Customer Entry Done");
                            alert.setContentText("Customer Name: "+txtCustomerName.getText()+"\n"
                            +"Customer Phone: "+txtCustomerNumber.getText()+"\n"
                            +"Payment Type: "+paymentTypeList.getValue()+"\n"
                            );
                            
                            alert.showAndWait();
            conn.commit();
            conn.close();
          }
          catch (SQLException ex) {
                    ex.printStackTrace();
                }
          GenratedBillOfSale gbos = new GenratedBillOfSale();
          gbos.start(primaryStage);
        
        });

        primaryStage.setTitle("Bill Of Sale");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    
}
