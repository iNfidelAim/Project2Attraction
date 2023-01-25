package ru.afanasiev.restservice.Project2Attractions.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.afanasiev.restservice.Project2Attractions.dto.CityDTO;
import ru.afanasiev.restservice.Project2Attractions.models.Attraction;
import ru.afanasiev.restservice.Project2Attractions.models.City;
import ru.afanasiev.restservice.Project2Attractions.services.CitiesService;
import ru.afanasiev.restservice.Project2Attractions.util.CityErrorResponse;
import ru.afanasiev.restservice.Project2Attractions.util.CityNotCreatedException;
import ru.afanasiev.restservice.Project2Attractions.util.CityNotFoundException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cities")
public class CitiesController {

    private final CitiesService citiesService;
    private final ModelMapper modelMapper;

    @Autowired
    public CitiesController(CitiesService citiesService, ModelMapper modelMapperCity) {
        this.citiesService = citiesService;
        this.modelMapper = modelMapperCity;
    }

    @GetMapping()
    public String index(Model model) {
       return model.addAttribute("cities", citiesService.findAll()).toString();
    }

    //Получение достопримечательностей по конкретному городу
    @GetMapping("/{id}")
    public List<Attraction> getAttractionsByCityId(@PathVariable("id") int id, Model model) {
        model.addAttribute("city", citiesService.findOne(id));
        model.addAttribute("attractions", citiesService.getAttractionsByCityId(id));

        return citiesService.getAttractionsByCityId(id);
    }
    //получение конкретного города
    @GetMapping("/city/{id}")
    public CityDTO getCity(@PathVariable("id") int id) {
        return convertToCityDTO(citiesService.findOne(id));
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CityDTO cityDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new CityNotCreatedException(errorMsg.toString());
        }
        citiesService.save(convertToCity(cityDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<CityErrorResponse> handleException(CityNotFoundException e) {
        CityErrorResponse response = new CityErrorResponse(
                "Город с таким ID не найден!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //будет ответ 404 и тело response
    }

    @ExceptionHandler
    private ResponseEntity<CityErrorResponse> handleException(CityNotCreatedException e) {
        CityErrorResponse response = new CityErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //400
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("city") @Valid City city, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "cities/edit";

        citiesService.update(id, city);
        return "redirect:/cities";
    }

    private City convertToCity(CityDTO cityDTO) {
        return modelMapper.map(cityDTO, City.class);
    }

    private CityDTO convertToCityDTO(City city) {
        return modelMapper.map(city, CityDTO.class);
    }

}
