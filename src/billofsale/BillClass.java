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
public class BillClass {

    private int bBill_Id;
    private String bBillDate;
    private double bHST; //13%
    private String bPaymentType;
    private static int counter = 001;

    public BillClass(int bnumber, String bdate, double bHst, String bpayment) {
        bBill_Id = bnumber;
        bBillDate = bdate;
        bHST = bHst;
        bPaymentType = bpayment;
    }

    public BillClass() {

    }

    public int getBillNumber() {
        return bBill_Id = counter++;
    }

    public void setBillNumber(int bnumber) {
        bBill_Id = bnumber;
    }

    public String getBillDate() {
        return bBillDate;
    }

    public void setBillDate(String billDate) {
        bBillDate = billDate;
    }

    public double getHst() {
        return bHST;
    }

    public void setHst(double bhst) {
        bhst = 0.13;
        bHST = bhst;
    }

    public String getPaymentType() {
        return bPaymentType;
    }

    public void setPaymentType(String bpayment) {
        bPaymentType = bpayment;
    }

    public String toStringBill() {
        return "Bill Number :" + bBill_Id
                + " BillDate: " + bBillDate
                + " HST: " + bHST
                + " PaymentType: " + bPaymentType;

    }

    public double genrateSubBill(double price, int sQty) {
        double subBill;
        subBill = price * sQty;
        return subBill;
    }

    public double generateTotalBill(double subTotal) {
        double totalBill = subTotal * bHST;
        return totalBill;
    }

}
