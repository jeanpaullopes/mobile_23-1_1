package br.com.uniritter.app1_2023_1.models;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zip;
    private Geo geo;

    public Address(String street, String suite, String city, String zip, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zip = zip;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
