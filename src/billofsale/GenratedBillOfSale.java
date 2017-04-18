/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class GenratedBillOfSale {

    Connection conn = null;
    PreparedStatement pst = null, pst2 = null, pst3 = null, pst4 = null, pst5 = null, pst6 = null;
    ResultSet rs = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null, rs6 = null;

    int estimation;
    double finalTotal;
    GenrateBillClass gbs = new GenrateBillClass();
    Writer writer = null;

    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(25, 25, 25, 25));

        root.getChildren().addAll();

        Scene scene = new Scene(root, 800, 1100);

        Text scenetitle = new Text("Receipt");

        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.add(scenetitle, 0, 0, 1, 1);

        Label customerName = new Label("Customer Name");
        root.add(customerName, 0, 1);

        Label txtCustomerName = new Label();
        root.add(txtCustomerName, 1, 1);

        Label customerNumber = new Label("Customer Number");
        root.add(customerNumber, 0, 2);

        Label txtCustomerNumber = new Label();
        root.add(txtCustomerNumber, 1, 2);

        Label paymentType = new Label("Payment Type");
        root.add(paymentType, 0, 3);

        Label paymentTypeList = new Label();
        root.add(paymentTypeList, 1, 3);

        TableView<BillClass> productListTable = new TableView<>();
        productListTable.setEditable(false);
        productListTable.setFixedCellSize(25);
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

        Label estimationBill = new Label("Sub-Total: $");
        root.add(estimationBill, 2, 8);

        Label estimationTxtBill = new Label();
        root.add(estimationTxtBill, 3, 8);

        Label lblhst = new Label("HST: $");
        root.add(lblhst, 2, 9);

        Label txthst = new Label();
        root.add(txthst, 3, 9);

        Label totalBill = new Label("Total-Amount: $");
        root.add(totalBill, 2, 10);

        Label totalTxtBill = new Label();
        root.add(totalTxtBill, 3, 10);

        HBox hbox = new HBox();
        Button punchBill = new Button("Punch Bill");
        Button mainMenu = new Button("Main Menu");
        hbox.getChildren().addAll(punchBill, mainMenu);
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        root.add(hbox, 1, 10);

        mainMenu.setOnAction(e -> {
            EmployeeMenu emu = new EmployeeMenu();
            emu.start(primaryStage);
        });

        punchBill.setOnAction(e -> {
            ArrayList allNames = new ArrayList();
            ArrayList allQtys = new ArrayList();
            ArrayList allCosts = new ArrayList();
            for (int i = 0; i < productListTable.getItems().size(); i++) {
                //System.out.println("count " + productListTable.getItems().size());
                
                allNames.add(productListTable.getItems().get(i).getpName());
                allQtys.add(productListTable.getItems().get(i).getpQuantity());
                allCosts.add(productListTable.getItems().get(i).getpPrize());
            }
            
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("BillOfSale.txt"), "utf-8"));
                writer.write("Bill Of Sale     "
                        + "Customer Name       "+txtCustomerName.getText()+"\n"
                        + "Customer Number     "+txtCustomerNumber.getText()+"\n"
                        + "Payment Type        "+paymentTypeList.getText()+"\n"
                        + "Product List        "+"\n"
                        +"-----------------------------------------"+"\n"
                        +"Product Name   "+"Quantity      "+"Cost  "+"\n"      
                        +allNames+"      "+allQtys+"      "+allCosts+"\n"
                        +"-----------------------------------------"+"\n"        
                        + "Sub-Ammount         "+estimationTxtBill.getText()+"\n"
                        + "HST (taxes)         "+txthst.getText()+"\n"
                        +"                    "+"------------------"+"\n"
                        +"Total-Ammount        "+totalTxtBill.getText()+"\n"
     
                );
            } catch (IOException ex) {
                // report
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {/*ignore*/
                }
            }
            int c = productListTable.getItems().size();
            //System.out.println("count " + c);

            for (int i = 0; i < productListTable.getItems().size(); i++) {
                //System.out.println("count " + productListTable.getItems().size());
                int totalCount = 0;
                //System.out.println(productListTable.getItems().get(i).getpName());
                //System.out.println(productListTable.getItems().get(i).getpQuantity());

                String queryForQtyUpdate = "select product_quantity from product where product_name='" + productListTable.getItems().get(i).getpName() + "'";
                try {
                    conn = SqlConnection.DbConnector();
                    pst4 = conn.prepareStatement(queryForQtyUpdate);
                    rs4 = pst4.executeQuery();
                    while (rs4.next()) {
                        totalCount = rs4.getInt("product_quantity");
                    }
                    rs4.close();
                    pst4.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("1 : " + ex.getMessage());
                }

                int qtyToUpdate = totalCount - Integer.parseInt(productListTable.getItems().get(i).getpQuantity());
                String updateQtyTable = "update product set product_quantity =" + qtyToUpdate + " where product_name='" + productListTable.getItems().get(i).getpName() + "'";
                try {
                    conn = SqlConnection.DbConnector();
                    pst5 = conn.prepareStatement(updateQtyTable);
                    pst5.executeQuery();
                    conn.commit();
                    pst5.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("2 : " + ex.getMessage());
                }
            }
            String deleteTempTable = "delete from productlist";
            try {
                conn = SqlConnection.DbConnector();
                pst6 = conn.prepareStatement(deleteTempTable);
                pst6.executeQuery();
                conn.commit();
                pst6.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("2 : " + ex.getMessage());
            }
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Bill Punched");
                            alert.setContentText("Bill of Sale is Genrated, Please Print the Bill and Go back to Main Menu");
                            alert.showAndWait();

        });

        primaryStage.setTitle("Bill Of Sale");
        primaryStage.setScene(scene);

        primaryStage.show();

        String queryForEstimate = "select * from productlist";
        String queryForCustomer = "select * from customertable";
        String queryForSubBill = "select product_prize from productlist";
        try {
            conn = SqlConnection.DbConnector();
            pst2 = conn.prepareStatement(queryForCustomer);
            rs2 = pst2.executeQuery();
            while (rs2.next()) {
                txtCustomerName.setText(rs2.getString("customers_name"));
                txtCustomerNumber.setText(rs2.getString("customers_phone"));
                paymentTypeList.setText(rs2.getString("payment_type"));
            }
            rs2.close();
            pst = conn.prepareStatement(queryForEstimate);
            rs = pst.executeQuery();
            while (rs.next()) {

                data.add(new BillClass(
                        rs.getString("product_name"),
                        rs.getString("product_prize"),
                        rs.getString("product_qty")
                ));
                productListTable.setItems(data);

            }

            pst3 = conn.prepareCall(queryForSubBill);
            rs3 = pst3.executeQuery();
            while (rs3.next()) {
                estimation += rs3.getInt("product_prize");
            }
            estimationTxtBill.setText(Integer.toString(estimation));

            String getBill = String.valueOf(gbs.generateTotalBill(estimation));
            String getHstAmt = String.valueOf(gbs.hst(estimation));
            txthst.setText(getHstAmt);
            totalTxtBill.setText(getBill);
            rs3.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
