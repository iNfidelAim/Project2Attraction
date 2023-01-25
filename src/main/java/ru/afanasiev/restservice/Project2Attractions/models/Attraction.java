package ru.afanasiev.restservice.Project2Attractions.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "Attraction")
public class Attraction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название достопримечательности не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название достопримечательности должно быть от 2 до 100 символов длиной")
    @Column(name = "name_of_attraction")
    private String nameOfAttraction;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotEmpty(message = "Необходимо ввести дату в формате 2000-01-01 (Год-месяц-число)")
    @Column(name = "date_of_build")
    private Date dateOfBuild;

    @NotEmpty(message = "Краткое описание достопримечательности не должно быть пустым")
    @Size(min = 2, max = 1000, message = "Краткое описание должно быть от 2 до 1000 символов длиной")
    @Column(name = "short_description")
    private String shortDescription;

    @Enumerated(EnumType.STRING)
    //отказался от ORDINAL т.к. возможно раширение enum, а при ординале индексы будут сбиваться
    @Column(name = "type_of_attraction")
    private TypeOfAttraction typeOfAttraction;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City owner;


    public Attraction() {
    }

    public Attraction(String nameOfAttraction, Date dateOfBuild, String shortDescription,
                      TypeOfAttraction typeOfAttraction, City owner) {
        this.nameOfAttraction = nameOfAttraction;
        this.dateOfBuild = dateOfBuild;
        this.shortDescription = shortDescription;
        this.typeOfAttraction = typeOfAttraction;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfAttraction() {
        return nameOfAttraction;
    }

    public void setName_of_attraction(String name_of_attraction) {
        this.nameOfAttraction = name_of_attraction;
    }

    public Date getDateOfBuild() {
        return dateOfBuild;
    }

    public void setDateOfBuild(Date dateOfBuild) {
        this.dateOfBuild = dateOfBuild;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public TypeOfAttraction getTypeOfAttraction() {
        return typeOfAttraction;
    }

    public void setTypeOfAttraction(TypeOfAttraction typeOfAttraction) {
        this.typeOfAttraction = typeOfAttraction;
    }

    public City getOwner() {
        return owner;
    }

    public void setOwner(City owner) {
        this.owner = owner;
    }
}
