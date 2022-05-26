package cl.cristian.rosales.test.models;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phones")
public class Phone implements Serializable {
    private static final long serialVersionUID = 4486904168401879193L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "city_code")
    private String city_code;
    @Column(name = "country_code")
    private String country_code;

    public Phone() {

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
