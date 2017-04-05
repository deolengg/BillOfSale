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
public class CustomerClass {
    private String cName;
    private int cCustomer_Id;
    private int cPhoneNumber;
    private int CCounter = 01;
    
    public void CustomerController(){
        
    }
    
    public void CustomerController(String name,int number,int phone){
        cName=name;
        cCustomer_Id =number;
        cPhoneNumber=phone;
        
    }
    
    
    public String getCustomerName(){
        return cName;
    }
    public void setCustomerName(String name){
        cName=name;
    }
    
    public int getCustomerNumber(){
        return cCustomer_Id;
    }
    public void setCustomerNumber(int Cnumber){
        Cnumber=CCounter++;
        cCustomer_Id=Cnumber;
    }
    
    public int getCustomerPhoneNumber(){
        return cPhoneNumber;
    }
    public void setCustomerPhoneNumber(int cPhoneNumber){
        this.cPhoneNumber=cPhoneNumber;
    }
}
