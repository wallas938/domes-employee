package fr.greta.domes.controller.client;

public class ClientSearchQuery {
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private String email;
    private int pageNumber;
    private int pageSize;

    public ClientSearchQuery() {
    }

    public ClientSearchQuery(String lastname, String firstname, String phoneNumber, String email, int pageNumber, int pageSize) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public String getLastname() {
        if (lastname != null) {
            return !lastname.trim().equals("") ? lastname : "";
        }
        return "";
    }

    public void setLastname(String lastname) {
        this.lastname = !lastname.trim().equals("") ? lastname : "";
        ;
    }

    public String getFirstname() {

        if (firstname != null) {
            return !firstname.trim().equals("") ? firstname : "";
        }
        return "";
    }

    public void setFirstname(String firstname) {
        this.firstname = !firstname.trim().equals("") ? firstname : "";
        ;
    }

    public String getPhoneNumber() {
        if (phoneNumber != null) {
            return !phoneNumber.trim().equals("") ? phoneNumber : "";
        }
        return "";
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = !phoneNumber.trim().equals("") ? phoneNumber : "";
    }

    public String getEmail() {
        if (email != null) {
            return !email.trim().equals("") ? email : "";
        }
        return "";
    }

    public void setEmail(String email) {
        this.email = !email.trim().equals("") ? email : "";
        ;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
