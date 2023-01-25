package ru.afanasiev.restservice.Project2Attractions.dto;


import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CityDTO {

    @NotEmpty(message = "Название города не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название города должно быть от 2 до 100 символов длиной")
    private String cityName;

    @Min(value = 1, message = "Численность населения должна быть больше, чем 1")
    private int populationSize;

    @NotEmpty(message = "Метро либо есть, либо нет.")
    private boolean metroAvailability;

    @NotEmpty(message = "Поле \"Страна\" не должно быть пустым")
    private String country;

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
}
