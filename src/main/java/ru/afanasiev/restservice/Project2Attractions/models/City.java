package ru.afanasiev.restservice.Project2Attractions.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "City")
public class City {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название города не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название города должно быть от 2 до 100 символов длиной")
    @Column(name = "city_name")
    private String cityName;

    @Min(value = 1, message = "Численность населения должна быть больше, чем 1")
    @Column(name = "population_size")
    private int populationSize;

    @Column(name = "metro_availability")
    @NotEmpty(message = "Метро либо есть, либо нет.")
    private boolean metroAvailability;

    @NotEmpty(message = "Поле \"Страна\" не должно быть пустым")
    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "owner")
    private List<Attraction> attractions;

    // Конструктор по умолчанию нужен для Spring
    public City() {
    }

    public City(String cityName, int populationSize, boolean metroAvailability, String country) {
        this.cityName = cityName;
        this.populationSize = populationSize;
        this.metroAvailability = metroAvailability;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public boolean isMetroAvailability() {
        return metroAvailability;
    }

    public void setMetroAvailability(boolean metroAvailability) {
        this.metroAvailability = metroAvailability;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
