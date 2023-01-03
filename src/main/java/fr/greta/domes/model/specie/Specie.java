package fr.greta.domes.model.specie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.greta.domes.model.category.Category;

import java.util.Objects;
import java.util.UUID;

public class Specie {
    private UUID id;
    private String name;
    private Category category;

    public Specie(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Specie() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specie specie = (Specie) o;
        return Objects.equals(id, specie.id) && Objects.equals(name, specie.name) && Objects.equals(category, specie.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category);
    }

    @Override
    public String toString() {
        return "Specie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
