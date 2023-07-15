package fr.greta.domes.model.order;

import fr.greta.domes.model.animal.Animal;
import fr.greta.domes.model.article.Article;
import fr.greta.domes.model.client.Address;
import fr.greta.domes.model.client.Client;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public class Order {
    private UUID id;
    private Collection<Animal> animals;
    private Client client;
    private Address shippingAddress;
    private int numberOfArticles;
    private double total;
    private LocalDate purchaseDate;

    public Order() {
    }

    public Order(UUID reference, Collection<Animal> animals, Client client, Address shippingAddress, int numberOfArticles, double total, LocalDate purchaseDate) {
        this.id = reference;
        this.animals = animals;
        this.client = client;
        this.shippingAddress = shippingAddress;
        this.numberOfArticles = numberOfArticles;
        this.total = total;
        this.purchaseDate = purchaseDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Collection<Animal> getAnimals() {
        return animals;
    }

    public void setArticles(Collection<Animal> animals) {
        this.animals = animals;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
