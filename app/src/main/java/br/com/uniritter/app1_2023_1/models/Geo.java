package br.com.uniritter.app1_2023_1.models;

public class Geo {
    private Double lat;
    private Double lng;

    public Geo(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}
