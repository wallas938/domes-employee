package fr.greta.domes.model.client;

import fr.greta.domes.model.order.Order;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private UUID id;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private Address address;
    private String email;
    private LocalDate registrationDate = LocalDate.now();

    public Client() {}

    public Client(UUID id, String lastname, String firstname, String phoneNumber, Address address, String email, LocalDate registrationDate) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
