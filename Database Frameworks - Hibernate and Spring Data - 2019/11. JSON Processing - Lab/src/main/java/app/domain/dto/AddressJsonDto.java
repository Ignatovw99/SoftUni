package app.domain.dto;

import com.google.gson.annotations.Expose;

public class AddressJsonDto {

    @Expose
    private String city;

    @Expose
    private String country;

    private String street;

    public AddressJsonDto() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
