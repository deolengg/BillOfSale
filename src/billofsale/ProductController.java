/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

/**
 *
 * @author karan
 */
public class ProductController {

    private String pName;
    private int pCode;
    private String pDescription;
    double pPrice;
    int pQuantity;
    boolean pSale;

    public ProductController(String name, int code, String desc, double price, int qty) {
        pName = name;
        pCode = code;
        pDescription = desc;
        pPrice = price;
        pQuantity = qty;
    }

    public ProductController() {
        
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String name) {
        pName = name;
    }

    public int getpCode() {
        return pCode;
    }

    public void setpCode(int code) {
        pCode = code;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String description) {
        pDescription = description;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double price) {
        pPrice = price;
    }

    public int getpQuantity() {
        return pQuantity;
    }
    public void setpQuantity(int setQty) {
        pQuantity=setQty;
    }

    public void putSaleOnProduct(double price) {
        if (pSale = true){
        setpPrice(price);
        }
    }

    public String toStringProduct() {
        return "Name: " + pName + " Code: " + pCode + " Description: " + pDescription + " Price: " + pPrice + " Quantity: " + pQuantity;
    }

}
