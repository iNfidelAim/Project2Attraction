package ru.afanasiev.restservice.Project2Attractions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.afanasiev.restservice.Project2Attractions.models.City;

import java.util.Optional;


@Repository
public interface CitiesRepository extends JpaRepository<City, Integer> {

//тут этот метод сделал для валидатора если вдруг будут добавлять уже существующий город.
//тут hibernate ищет города по названиЮ
    Optional<City> findByCityName(String cityName);
}
