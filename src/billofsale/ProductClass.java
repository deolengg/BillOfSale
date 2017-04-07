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
 * @author karan
 */
public class ProductClass {

    private final SimpleStringProperty productId;
    private final SimpleStringProperty productName;
    private final SimpleStringProperty productPrize;
    private final SimpleStringProperty productQuantity;

    public ProductClass(String code, String name, String price, String qty) {
        this.productId = new SimpleStringProperty(code);
        this.productName = new SimpleStringProperty(name);
        this.productPrize = new SimpleStringProperty(price);
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

    public String getp_Id() {
        return productId.get();
    }

    public void setp_Id(String code) {
        productId.set(code);
    }

    public StringProperty productIdProperty() {
        return productId;
    }

    public String getpPrice() {
        return productPrize.get();
    }

    public void setpPrice(String price) {
        productPrize.set(price);
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
