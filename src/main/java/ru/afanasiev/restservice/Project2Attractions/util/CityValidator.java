package ru.afanasiev.restservice.Project2Attractions.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.afanasiev.restservice.Project2Attractions.models.City;
import ru.afanasiev.restservice.Project2Attractions.services.CitiesService;


//Валидация на существование города в БД
@Component
public class CityValidator implements Validator {

    private final CitiesService citiesService;

    @Autowired
    public CityValidator(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return City.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        City city = (City) o;

        if (citiesService.getCityByCityName(city.getCityName()).isPresent())
            errors.rejectValue("cityName", "", "Такой город уже существует и добавлен в базу данных");
    }
}
