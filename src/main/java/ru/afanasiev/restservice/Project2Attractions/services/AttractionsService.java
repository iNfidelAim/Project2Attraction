package ru.afanasiev.restservice.Project2Attractions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.afanasiev.restservice.Project2Attractions.models.Attraction;
import ru.afanasiev.restservice.Project2Attractions.models.City;
import ru.afanasiev.restservice.Project2Attractions.repositories.AttractionsRepository;


import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class AttractionsService {

    private final AttractionsRepository attractionsRepository;

    @Autowired
    public AttractionsService(AttractionsRepository attractionsRepository) {
        this.attractionsRepository = attractionsRepository;
    }

    //тут сортировка по наименованию достопримечательности
    public List<Attraction> findAllSortedAttractions() {
            return attractionsRepository.findAll(Sort.by("nameOfAttraction"));
    }
    public List<Attraction> findAll() {
        return attractionsRepository.findAll();
    }

/*
- Добавить достопримечательность
- Изменение данных по достопримечательности (возможно изменение только полей: Краткое описание)
- Удаление достопримечательности из справочника - точно не каскадом)) но это в бд реализую, но это не точно пока
*/

   //метод ниже поиск одной дост-и по сути не нужен наверно удалю
    public Attraction findOne(int id) {
        Optional<Attraction> foundAttraction = attractionsRepository.findById(id);
        return foundAttraction.orElse(null);
    }
    //метод ниже изменю на поиск достопримечательностей по конретному городу
    public List<Attraction> searchByCity(String query) {
        return attractionsRepository.findByNameOfAttractionStartingWith(query);
    }

    @Transactional
    public void save(Attraction attraction) {
        attractionsRepository.save(attraction);
    }

    @Transactional
    public void update(int id, Attraction updatedAttraction) {
        Attraction attractionToBeUpdated = attractionsRepository.findById(id).get();

        // добавляем по сути новую достопримечательность(которая не находится в Persistence context), поэтому нужен save()
        updatedAttraction.setId(id);
        updatedAttraction.setOwner(attractionToBeUpdated.getOwner()); // чтобы не терялась связь при обновлении

        attractionsRepository.save(updatedAttraction);
    }

    @Transactional
    public void delete(int id) {
        attractionsRepository.deleteById(id);
    }

    public Optional<Attraction> getAttractionByAttractionName(String attractionName) {
        return attractionsRepository.findByNameOfAttraction(attractionName);
    }
    // Возвращается null если у достостопримечательности нет города. Похоже лишний метод.
    public City getAttractionOwner(int id) {
        // Здесь Hibernate.initialize() не нужен, так как владелец (сторона One) загружается не лениво
        return attractionsRepository.findById(id).map(Attraction::getOwner).orElse(null);
    }


}

