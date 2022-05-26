package cl.cristian.rosales.test.dto;

import java.io.Serializable;

public class PhoneDTO implements Serializable {

    private static final long serialVersionUID = -965690957329944184L;

    private Long id;
    private String number;
    private String city_code;
    private String country_code;

    public PhoneDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

}
