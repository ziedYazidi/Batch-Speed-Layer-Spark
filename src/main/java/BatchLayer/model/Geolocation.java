package BatchLayer.model;

public class Geolocation {
    private Double Latitude;
    private Double Longitude;
    private String City;

    public Geolocation() {
    }

    public Geolocation(Double latitude, Double longitude, String city) {
        Latitude = latitude;
        Longitude = longitude;
        City = city;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
