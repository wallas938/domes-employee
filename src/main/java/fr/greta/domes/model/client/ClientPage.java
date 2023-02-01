package fr.greta.domes.model.client;

import java.util.List;

public class ClientPage {
    private List<Client> clients;
    private Integer totalPages;
    private Integer totalElements;

    public ClientPage() {
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return "ClientPage{" +
                "clients=" + clients +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }
}
