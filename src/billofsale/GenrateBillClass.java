/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billofsale;

/**
 *
 * @author Karan
 */
public class GenrateBillClass {
    private double bHST=0.13;
    
    public GenrateBillClass(){}

    public double generateTotalBill(double subTotal) {
        double subBill = subTotal * bHST;
        double totalBill = subBill+subTotal;
        return totalBill;
    }
    public double hst(double subTotal)
    {
    double subBill = subTotal * bHST;
    return subBill;
    }

}
