/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author karan
 */
public class BillOfSale {

    static Scanner SCN = new Scanner(System.in);
    static ProductController[] STOCK = new ProductController[5];
    static BillController BILL = new BillController();
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    static Date date = new Date();

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("**************************************");
        System.out.println("************ BILL OF SALE ************");
        System.out.println("**************************************");
        System.out.println("******  Choose one option   **********");
        System.out.println("***** 0. Load Inventory  *************");
        System.out.println("***** 1. Display Current Inventory  **");
        System.out.println("***** 2. Put Sale on a Product  ******");
        System.out.println("***** 3. Search a Product ************");
        System.out.println("***** 4. Add Products to Cart  *******");
        System.out.println("***** 5. Display Customer Cart *******");
        System.out.println("***** 6. Genrate Bill of Sale ********");
        System.out.println("***** 7. Shut down / Exit ************");
        System.out.println();
        System.out.print("Enter a option: ");

        int input = SCN.nextInt();
        switch (input) {
            case 0:
                instantiatInventory(STOCK);
                storeInventory(STOCK);
                BillOfSale.main(null);
                break;
            case 1:
                displayInventory(STOCK);
                break;
            case 2:
                putSaleOnProduct(STOCK);
                break;
            case 3:
                searchProductBasedOnPriceOrQuantity();
                break;
            case 4:
                addProductsToCart();
                break;
            case 5:
                displayProductsOnCart();
                break;
            case 6:
                genrateBillOfSale();
                break;
            case 7:
                System.out.println("You choosed to Leave, Bye");
                System.exit(0);
            default:
                break;
        }

    }

    public static void searchProductBasedOnPriceOrQuantity() {

    }

    public static void addProductsToCart() {
        System.out.println();
        System.out.println("*************  Add to Cart ****************");
        System.out.println();
        System.out.print("Enter How many Products you buying: ");
        int input = SCN.nextInt();
        int arrayOfCodes[] = new int[input];
        int arrayOfQty[] = new int[input];
        for (int i = 0; i < arrayOfCodes.length; i++) {
            System.out.print("Enter Product Code: ");
            arrayOfCodes[i] = SCN.nextInt();
            for (int j = 0; j < arrayOfQty.length; j++) {
                System.out.print("Enter Quantity: ");
                arrayOfQty[j] = SCN.nextInt();
            }
        }
        System.out.println();
        for (int i = 0; i < arrayOfCodes.length; i++) {
            for (int j = 0; j < arrayOfQty.length; j++) {
                System.out.println("Product Code: " + arrayOfCodes[i] + " Quantity: " + arrayOfQty[j]);
            }

        }

    }

    public static void displayProductsOnCart() {

    }

    public static void genrateBillOfSale() {

    }

    public static void instantiatInventory(ProductController[] stockList) {

        for (int i = 0; i < stockList.length; i++) {
            stockList[i] = new ProductController();

        }
    } //instantait array objects for inventory

    public static void storeInventory(ProductController[] stockList) {

        stockList[0] = new ProductController("Samsung", 01, "Smartphone", 950.99, 100);
        stockList[1] = new ProductController("Apple", 02, "Smartphone", 1199.99, 100);
        stockList[2] = new ProductController("HTC", 03, "Smartphone", 999.99, 75);
        stockList[3] = new ProductController("LG", 04, "Smartphone", 559.99, 25);
        stockList[4] = new ProductController("Motorolla", 05, "Smartphone", 499.99, 100);
    }//Storing data in those array objects

    public static void displayInventory(ProductController[] stockList) {
        System.out.println();
        System.out.println("*************  Current Inventory ****************");
        for (int i = 0; i < stockList.length; i++) {

            System.out.println(stockList[i].toStringStock());
            System.out.println();
            /*System.out.println("Name  " + stocklist[i].getpName() + "\n"
                    + "Code  " + stocklist[i].getpCode() + "\n"
                    + "Description   " + stocklist[i].getpDescription() + "\n"
                    + "Price  " + stocklist[i].getpPrice() + "\n"
                    + "Quantity  " + stocklist[i].getQuantity());
            System.out.println("********************************************");
             */

        }
        System.out.println();
        System.out.println("Press 0 For Main Menu");
        int input = SCN.nextInt();
        if (input == 0) {
            BillOfSale.main(null);
        } else {
            System.exit(0);
        }
    }

    public static void putSaleOnProduct(ProductController[] stockList) {

        System.out.println();
        System.out.println("*****************************************");
        System.out.println("************ Product On Sale ************");
        System.out.println("*****************************************");
        System.out.print("Enter the Product Code:  ");
        int code = SCN.nextInt();
        System.out.println();
        int i = code - 1;
        System.out.println("You have Choosed Product < " + stockList[i].getpName() + " > For Sale");
        System.out.println();
        System.out.print("Enter the new Price :");
        double price = SCN.nextDouble();
        stockList[i].setpPrice(price);
        System.out.println();
        System.out.println("Sale has been Set");
        System.out.println();
        System.out.println(stockList[i].toStringStock());
        System.out.println();
        System.out.println("Press 0 For Main Menu");
        int input = SCN.nextInt();
        if (input == 0) {
            BillOfSale.main(null);
        } else {
            System.exit(0);
        }
    }//Price of any product can be cahnged

}
