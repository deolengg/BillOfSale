/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

import java.util.Scanner;

/**
 *
 * @author karan
 */
public class BillOfSale {
    static Scanner SCN = new Scanner(System.in).useDelimiter("\\n");
    static ProductController PC = new ProductController();
    static int productCounter = 5;
    static InventoryController IC = new InventoryController();
    static ProductController[] stockList = new ProductController[productCounter];
    static CustomerController CC = new CustomerController();
    static BillController BC = new BillController();
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        
     System.out.println("************************************");
     System.out.println("*****  Welcome to Bill Of Sale *****");  
     System.out.println("******** Choose one Option  ********");
     System.out.println();
     System.out.println("1. Go To Inventory");
     System.out.println("2. Select Products for Purchase");
     System.out.println("2. Customer Ready to Bill");
     System.out.println("3. Exit");
 
       
        
        //Inventory Controller
        
        
        IC.setpQuantitySelected(10);// quantity selected by customer
        int q = IC.getNewpQuantityInHand(stockList[1].getpQuantity(),IC.getQuantitySelected()); // quantity selected - product stock list
        stockList[1].setpQuantity(q); 
        
        //Display Inventory
        
        IC.instantiatInventory(stockList);
        IC.defaultInventory(stockList);
        
        
      
        double subtotal = BC.genrateSubBill(stockList[0].getpPrice(), IC.getQuantitySelected()); // genrating sub bill from price * selected QTY
       
        
        //BillController
        BillController BC = new BillController();
        BC.getHst(); //HST 13% tax
        BC.generateTotalBill(subtotal); //Total bill
        
        
    }
    public static void goToInventory(){
        System.out.println("************************************");
        System.out.println("******** Current Inventory *********");  
        System.out.println("******** Choose one Option  ********");   
        System.out.println();
        System.out.println("1. View Inventory");
        System.out.println("2. Add Product to Invetory");
        System.out.println("3. Put Sale on Product");
        System.out.println("4. Search Item by Price");
        System.out.println("4. Back to Main Menu");
     
    }
    public static void viewCurrentInventory(){
        IC.displayInventory(stockList);
    }
    public static void addProductToInventory(){
    /*  Product added 
        stockList[5] = new ProductController("Apple iphone 7",01,"Smartphone",999.99,100);
        stockList[6] = new ProductController("Samsung Galaxy s7",02,"Smartphone",899.99,100);
    */
        String name,desc;
        int qty,code;
        double price;
        System.out.println("************************************");
        System.out.println("***** Add Product in Inventory *****"); 
        System.out.println();
        System.out.print("Enter Product Name: ");
        name = SCN.next();
        System.out.print("Enter Code Number: ");
        code = SCN.nextInt();
        System.out.print("Enter Desription: ");
        desc = SCN.next();
        System.out.print("Enter Quantity: ");
        qty = SCN.nextInt();
        System.out.print("Enter Quantity: ");
        price = SCN.nextDouble();
        stockList[productCounter++] = new ProductController(name,code,desc,price,qty);
        
    }
    public static void putSaleOnProduct(){
    /*  Put Sale on the product
        stockList[1].pSale = true;
        stockList[1].setpPrice(800.99);
    */
        System.out.println("************************************");
        System.out.println("***** Put Sale on Product **********"); 
        System.out.println();
        System.out.print("Enter the Product Code: ");
        int codeForSale = SCN.nextInt();
        int indexNumber = codeForSale-1 ;
        stockList[indexNumber].pSale=true;
        System.out.println("Enter the Price for "+stockList[indexNumber].getpName());
        double discounted = SCN.nextDouble();
        stockList[indexNumber].putSaleOnProduct(discounted);
        System.out.println();
        System.out.println("Discounted Price has been set");
        System.out.println(stockList[indexNumber].toStringProduct());
    }
    
    public static void customerReadyTobill(){
    /*
        CustomerController
        CustomerController CC = new CustomerController();
        CC.setCustomerName("jaskaran");
        CC.getCustomerNumber();
        CC.setCustomerPhoneNumber(123213123);
        CC.setCustomerEmailId("something@gmail.com");
    */  
        String name,email;
        int phone;
        System.out.println("************************************");
        System.out.println("***** Customer Ready for Bill ******"); 
        System.out.println();
        
        System.out.print("Enter Customer Name: ");
        name = SCN.next();
        System.out.print("Enter Customer Email: ");
        email = SCN.next();
        System.out.println("Enter Customer Phone Number: ");
        phone= SCN.nextInt();
        CC.CustomerController(name, CC.getCustomerNumber(), phone, email);
        System.out.println(CC.toString());
 
        System.out.println("************************************");
        System.out.println("***** Customer Purchase List *******");
        //loop for how many products customer want and its quanitity
        System.out.println("Enter the product code");
        //get product code
        int code= SCN.nextInt();
        stockList[code].getpCode();
        System.out.println("Enter Quantity for that product");
        //get quantity for that product
        stockList[1].getpQuantity();
        //loop ends here
        
        
        
        IC.setpQuantitySelected(stockList[1].getpQuantity()); //
        
        IC.setpQuantitySelected(10);// quantity selected by customer
        int q = IC.getNewpQuantityInHand(stockList[1].getpQuantity(),IC.getQuantitySelected()); // quantity selected - product stock list
        stockList[1].setpQuantity(q); 
        
       // IC.getNewpQuantityInHand(phone, q);
        
        double subtotal = BC.genrateSubBill(stockList[1].getpPrice(), IC.getQuantitySelected()); // genrating sub bill from price * selected QTY
        double totaltotal = BC.generateTotalBill(subtotal);
        
        //change product quantity
        //genrate purshase list
        stockList[1].getpQuantity(); // show current quantity
        int x = 100; // customer selects new quanity
        stockList[1].setpQuantity(x); // new quantity is set 
        
    }
}
