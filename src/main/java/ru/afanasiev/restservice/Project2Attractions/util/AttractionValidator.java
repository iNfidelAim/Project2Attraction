package ru.afanasiev.restservice.Project2Attractions.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.afanasiev.restservice.Project2Attractions.models.Attraction;
import ru.afanasiev.restservice.Project2Attractions.services.AttractionsService;


//Валидация на существование достопримечательности (двух одинаковых быть не может) в БД

@Component
public class AttractionValidator implements Validator {

    private final AttractionsService attractionsService;

    @Autowired
    public AttractionValidator(AttractionsService attractionsService) {
        this.attractionsService = attractionsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
            return Attraction.class.equals(aClass);
        }

    @Override
    public void validate(Object o, Errors errors) {
        Attraction attraction = (Attraction) o;

            if (attractionsService.getAttractionByAttractionName(attraction.getNameOfAttraction()).isPresent())
                errors.rejectValue("cityName", "",
                        "Такая достопримечательность уже существует и добавлена в базу данных");
        }
}

