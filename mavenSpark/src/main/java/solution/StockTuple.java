package solution;

import java.io.Serializable;

public class StockTuple implements Serializable {
    private int count;
    private double volume;

    public StockTuple(int count, double volume) {
        super();
        this.count = count;
        this.volume = volume;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    @Override
    public String toString() {
        return String.valueOf(volume);
    }
}
