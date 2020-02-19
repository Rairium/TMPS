package locations;

public class ATM {
    private Location location;
    private int uniqueCurrencyAmount;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getUniqueCurrencyAmount() {
        return uniqueCurrencyAmount;
    }

    public void setUniqueCurrencyAmount(int uniqueCurrencyAmount) {
        this.uniqueCurrencyAmount = uniqueCurrencyAmount;
    }

    public ATM(Location location, int uniqueCurrencyAmount) {
        this.location = location;
        this.uniqueCurrencyAmount = uniqueCurrencyAmount;
    }
}
