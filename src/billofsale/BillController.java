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
public class BillController {

    private int bBillNumber;
    private String bBillDate;
    private String bDescription;
    private double bHST; //13%
    private String bPaymentType;
    private static int counter = 1001;

    public BillController(int bnumber, String bdate, String dbesc, double bHst, String bpayment) {
        bBillNumber = bnumber;
        bBillDate = bdate;
        bDescription = dbesc;
        bHST = bHst;
        bPaymentType = bpayment;
    }

    public BillController() {

    }

    public int getBillNumber() {
        return bBillNumber = counter++;
    }

    public void setBillNumber(int bnumber) {
        bBillNumber = bnumber;
    }

    public String getBillDate() {
        return bBillDate;
    }

    public void setBillDate(String billDate) {
        bBillDate = billDate;
    }

    public String getBillDesc() {
        return bDescription;
    }

    public double getHst() {
        return bHST;
    }

    public void setHst(double bhst) {
        bhst = 0.13;
        bHST = bhst;
    }

    public void setBillDesc(String desc) {
        bDescription = desc;
    }

    public String getPaymentType() {
        return bPaymentType;
    }

    public void setPaymentType(String bpayment) {
        bPaymentType = bpayment;
    }

    public String toStringBill() {
        return "Bill Number :" + bBillNumber
                + " BillDate: " + bBillDate
                + " Description: " + bDescription
                + " HST: " + bHST
                + " PaymentType: " + bPaymentType;

    }
    
    public double generateTotalBill(double subTotal){
        double totalBill = subTotal* bHST;
        return totalBill;
    }
    

}
