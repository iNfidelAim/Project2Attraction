package ru.afanasiev.restservice.Project2Attractions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.afanasiev.restservice.Project2Attractions.models.Attraction;


import java.util.List;
import java.util.Optional;


@Repository
public interface AttractionsRepository extends JpaRepository<Attraction, Integer> {

    //поиск по начальным буквам названия достопримечательностей (это делает hibernate)
    List<Attraction> findByNameOfAttractionStartingWith(String nameOfAttraction);

    //тут этот метод сделал для валидатора если вдруг будут добавлять уже существующую достопримечательность.
//тут hibernate ищет города по названиЮ
    Optional<Attraction> findByNameOfAttraction(String attractionName);
}
