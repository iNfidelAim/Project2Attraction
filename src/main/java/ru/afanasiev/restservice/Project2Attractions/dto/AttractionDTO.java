package ru.afanasiev.restservice.Project2Attractions.dto;

import org.springframework.format.annotation.DateTimeFormat;
import ru.afanasiev.restservice.Project2Attractions.models.TypeOfAttraction;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class AttractionDTO {

    @NotEmpty(message = "Название достопримечательности не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название достопримечательности должно быть от 2 до 100 символов длиной")
    private String nameOfAttraction;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotEmpty(message = "Необходимо ввести дату в формате 2000-01-01 (Год-месяц-число)")
    private Date dateOfBuild;

    @NotEmpty(message = "Краткое описание достопримечательности не должно быть пустым")
    @Size(min = 2, max = 1000, message = "Краткое описание должно быть от 2 до 1000 символов длиной")
    private String shortDescription;

    @Enumerated(EnumType.STRING)
    private TypeOfAttraction typeOfAttraction;

    public String getNameOfAttraction() {
        return nameOfAttraction;
    }

    public void setNameOfAttraction(String nameOfAttraction) {
        this.nameOfAttraction = nameOfAttraction;
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
}
