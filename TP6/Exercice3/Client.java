package Exercice3;

import java.io.Serializable;

public class Client implements Serializable {
    private String lastName;
    private String firstName;
    private String address;
    private String phone;
    private String email;

    public Client(String lastName, String firstName, String address, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Client() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                """
                        ----------------------------
                        Client Information:
                        Last Name: %s
                        First Name: %s
                        Address: %s
                        Phone: %s
                        Email: %s
                        ----------------------------""",
                lastName, firstName, address, phone, email
        );
    }

}
