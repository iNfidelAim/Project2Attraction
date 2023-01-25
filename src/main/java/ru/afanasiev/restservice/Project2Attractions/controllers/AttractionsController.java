package ru.afanasiev.restservice.Project2Attractions.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.afanasiev.restservice.Project2Attractions.dto.AttractionDTO;
import ru.afanasiev.restservice.Project2Attractions.models.Attraction;
import ru.afanasiev.restservice.Project2Attractions.models.City;
import ru.afanasiev.restservice.Project2Attractions.repositories.AttractionsRepository;
import ru.afanasiev.restservice.Project2Attractions.services.AttractionsService;
import ru.afanasiev.restservice.Project2Attractions.services.CitiesService;
import ru.afanasiev.restservice.Project2Attractions.util.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/attractions")
public class AttractionsController {

    private final AttractionsService attractionsService;
    private final CitiesService citiesService;
    private final ModelMapper modelMapper;



    @Autowired
    public AttractionsController(AttractionsService attractionsService, CitiesService citiesService,
                                 AttractionsRepository attractionsRepository, ModelMapper modelMapperAttraction) {
        this.attractionsService = attractionsService;
        this.citiesService = citiesService;
        this.modelMapper = modelMapperAttraction;
    }
    //все достопримечательности
    @GetMapping("/attractions")
    public List<Attraction> getAllAttractions() {
        return attractionsService.findAll();
    }

    //выдача отсортированных достопримечательностей
    @GetMapping("/sorted_attractions")
    public List<Attraction> getAllSortedAttractions() { // выдача всех достопримечат-ей
            return attractionsService.findAllSortedAttractions();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@ModelAttribute("attraction") @Valid AttractionDTO attractionDTO,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new AttractionNotCreatedException(errorMsg.toString());
        }
        citiesService.save(convertToAttraction(attractionDTO).getOwner());
        //отправляет HTTP пустой ответ со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<AttractionErrorResponse> handleException(AttractionNotCreatedException e) {
        AttractionErrorResponse response = new AttractionErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //400
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("attraction") @Valid Attraction attraction, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "attractions/edit";

        attractionsService.update(id, attraction);
        return "redirect:/attractions";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        attractionsService.delete(id);
        return "Удалено";
    }

    private Attraction convertToAttraction(AttractionDTO attractionDTO) {
        return modelMapper.map(attractionDTO, Attraction.class);
    }

    private AttractionDTO convertToAttractionDTO(Attraction attraction) {
        return modelMapper.map(attraction, AttractionDTO.class);
    }
}
