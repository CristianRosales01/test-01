package cl.cristian.rosales.test.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 72760379987844709L;

    private Long id;
    private String name;

    private String email;
    private String  password;
    private List<PhoneDTO> phones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }
}
