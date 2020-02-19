package locations;

public class Location {
    private String district;
    private String city;
    private String streetName;
    private int streetAddress;

    public Location(String district, String city, String streetName, int streetAddress) {
        this.district = district;
        this.city = city;
        this.streetName = streetName;
        this.streetAddress = streetAddress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(int streetAddress) {
        this.streetAddress = streetAddress;
    }
}
