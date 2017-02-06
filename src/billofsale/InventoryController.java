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
public class InventoryController {

    private int iQuantityInHand;
    private int iQuantitySelected;

    public InventoryController() {

    }

    public InventoryController(int qtyInHand, int qtySelected) {
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
    

    public void instantiatInventory(ProductController[] stockList) {
        for (int i = 0; i < stockList.length; i++) {
            stockList[i] = new ProductController();
        }
    }

    public void defaultInventory(ProductController[] stockList) {

        stockList[0] = new ProductController("Samsung", 01, "Smartphone", 950.99, 100);
        stockList[1] = new ProductController("Apple", 02, "Smartphone", 1199.99, 100);
        stockList[2] = new ProductController("HTC", 03, "Smartphone", 999.99, 100);
        stockList[3] = new ProductController("LG", 04, "Smartphone", 559.99, 100);
        stockList[4] = new ProductController("Motorolla", 05, "Smartphone", 499.99, 100);
    }

    public void displayInventory(ProductController[] stockList) {
        //instantiatInventory(stockList);
        System.out.println();
        System.out.println("*************  Current Inventory ****************");
        for (int i = 0; i < stockList.length; i++) {
            System.out.println(stockList[i].toStringProduct());
            System.out.println();
        }
    }

    

    public double serachByPrice(double price) {

        return price;
    }

}
