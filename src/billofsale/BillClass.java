/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * 
 * @author karan
 */
public class BillClass {

    private  final SimpleStringProperty productName;
    private  final SimpleStringProperty productPrize;
    private final SimpleStringProperty productQuantity;
    
    

    public BillClass(String productname, String cost, String  qty) {
        this.productName = new SimpleStringProperty(productname);
        this.productPrize =new SimpleStringProperty(cost);
        this.productQuantity = new SimpleStringProperty(qty);
    }
    
    public String getpName() {
        return productName.get();
    }

    public void setpName(String name) {
        productName.set(name);
    }

    public StringProperty productNameProperty() {
        return productName;
    }
    
    public String getpPrize() {
        return productPrize.get();
    }

    public void setpPrize(String prize) {
        productPrize.set(prize);
    }

    public StringProperty productPrizeProperty() {
        return productPrize;
    }
    
    public String getpQuantity() {
        return productQuantity.get();
    }

    public void setpQuantity(String setQty) {
        productQuantity.set(setQty);
    }

    public StringProperty productQuantityProperty() {
        return productQuantity;
    }

}
