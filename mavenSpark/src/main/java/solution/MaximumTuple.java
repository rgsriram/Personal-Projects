package solution;

import java.io.Serializable;

/*
Class holds all the values used for the calculation.
 */
public class MaximumTuple implements Serializable {
    private int count;
    private double closingPrice;
    private double openingPrice;
    public MaximumTuple(int count, double closingPrice, double openingPrice) {
        super();
        this.count = count;
        this.closingPrice = closingPrice;
        this.openingPrice = openingPrice;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public double getClosingPrice() {
        return closingPrice;
    }
    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }
    public double getOpeningPrice() {
        return openingPrice;
    }
    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }
    @Override
    public String toString() {
        return String.valueOf(((closingPrice/count)-(openingPrice/ count)));
    }
}
