package siq.BestMealsAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore // não deixa setar ID via JSON
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "state")
    private String state;
    @Column(name = "zip_code")
    private String zipCode;

    public Restaurant() {}

    public Restaurant(Long id, String name, String address, String state, String zipCode) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.zipCode = zipCode;
    }

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Meal> meals = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // para prevenir recursão infinita na serialização JSON. (??)
    private List<RestaurantEvaluation> evaluations = new ArrayList<>();

    public void addEvaluation(RestaurantEvaluation evaluation) {
        evaluations.add(evaluation);
        evaluation.setRestaurant(this);
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public List<RestaurantEvaluation> getEvaluations() { return evaluations; }
}
