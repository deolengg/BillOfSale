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
public class InventoryClass {

    private int iQuantityInHand;
    private int iQuantitySelected;

    public InventoryClass() {

    }

    public InventoryClass(int qtyInHand, int qtySelected) {
        iQuantityInHand = qtyInHand;
        iQuantitySelected = qtySelected;
    }

    public void setpQuantityInHand(int quantity) {
        iQuantityInHand = quantity;
    }
    public int getpQuantityInHand(){
        return iQuantityInHand;
    }
    
    public int getQuantitySelected() {
        return iQuantitySelected;
    }

    public void setpQuantitySelected(int pQtySelected) {
        iQuantitySelected = pQtySelected;
    }

    public int getNewpQuantityInHand(int qtyInHand, int qtySelected) {
        int qtyRemaining;
        qtyRemaining = qtyInHand - qtySelected;

        if (qtyRemaining < 10) {
            genratePurhchaseList(qtyRemaining);
            return qtyRemaining;
        }
        return qtyRemaining;
    }

    public void genratePurhchaseList(int qty) {
        System.out.println("Purchase List, Quantity for this product is  "+qty+"  which is less then 10");
        System.out.println("Please Order a new Shipment");
    }


}
