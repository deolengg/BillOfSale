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
public class CustomerController {
    private String cName;
    private int cCustomerNumber;
    private int cPhoneNumber;
    private String cEmailId;
    private static int CCounter = 01;
    
    public void CustomerController(){
        
    }
    
    public void CustomerController(String name,int number,int phone,String email){
        cName=name;
        cCustomerNumber =number;
        cPhoneNumber=phone;
        cEmailId=email;
    }
    
    
    public String getCustomerName(){
        return cName;
    }
    public void setCustomerName(String name){
        cName=name;
    }
    
    public int getCustomerNumber(){
        return cCustomerNumber=CCounter++;
    }
    public void setCustomerNumber(int Cnumber){
        cCustomerNumber=Cnumber;
    }
    
    public String getCustomerEmailId(){
        return cEmailId;
    }
    public void setCustomerEmailId(String cEmailId){
        cEmailId=cEmailId;
    }
    
    public int getCustomerPhoneNumber(){
        return cPhoneNumber;
    }
    public void setCustomerPhoneNumber(int cPhoneNumber){
        this.cPhoneNumber=cPhoneNumber;
    }
}
