/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

import java.util.Date;

/**
 *
 * @author karan
 */
public class BillController {
    private int bBillNumber; 
    private String bBillDate ;
    private String bDescription;
    private double bHST; //13%
    private String bWarrantyExpires;
    private String bPaymentType;
    private static int counter = 1001;
    
    public BillController(int bnumber,String bdate,String dbesc,double bHst,String wdate,String bpayment){
        bBillNumber=bnumber;
        bBillDate=bdate;
        bDescription=dbesc;
        bHST=bHst;
        bWarrantyExpires=wdate;
        bPaymentType=bpayment;
    }
    
    public BillController(){
        
    }
    public int getBillNumber(){
        return bBillNumber=counter++;
    }
    public void setBillNumber(int bnumber){
        bBillNumber = bnumber;
    }
    public String getBillDate(){
        return bBillDate;
    }
    public void setBillDate(String billDate){
        bBillDate=billDate;
    }
    public String getBillDesc(){
        return bDescription;
    }
    public double getHst(){
        return bHST;
    }
    public void setHst(double bhst){
        bhst = 0.13;
        bHST=bhst;
    }
    public void setBillDesc(String desc){
        bDescription=desc;
    }
    public String getWarrantyDate(){
        return bWarrantyExpires;
    }
    public void getWarrantyDate(String wdate){
        bWarrantyExpires=wdate;
    }
    public String getPaymentType(){
        return bPaymentType;
    }
    public void setPaymentType(String bpayment){
        bPaymentType=bpayment;
    }

    
    public String toStringBill(){
        return "Bill Number :"+bBillNumber
               +" BillDate: "+bBillDate
               +" Description: "+bDescription
               +" HST: "+bHST
               +" bWarrantyExpires: "+bWarrantyExpires
               +" PaymentType: "+bPaymentType;
                
    }
    
   
}
