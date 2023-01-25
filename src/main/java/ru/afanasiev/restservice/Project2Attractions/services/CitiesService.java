package ru.afanasiev.restservice.Project2Attractions.services;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.afanasiev.restservice.Project2Attractions.models.Attraction;
import ru.afanasiev.restservice.Project2Attractions.models.City;
import ru.afanasiev.restservice.Project2Attractions.repositories.CitiesRepository;

import ru.afanasiev.restservice.Project2Attractions.util.CityNotFoundException;


import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class CitiesService {

    //что тут надо доделать
    // - Получить все достопримечательности конкретного города -
    //- Добавить город - это когда рест буду пилить
    //- Изменение данных по городу (возможно изменение только полей: Численность населения, Наличие метро)

    private final CitiesRepository citiesRepository;

    @Autowired
    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    //возвращаем список всех городов
    public List<City> findAll() {
        return citiesRepository.findAll();
    }

    //если город есть, то возвращается город, если такого города нет, то возрв. null
    //по сути поиск городов не нужен по заданию, но решил добавить, т.к. могут возникнуть ситуцации когда это необходимо
    //и обработать кастомно исключение
    public City findOne(int id) {
        Optional<City> foundCity = citiesRepository.findById(id);
        //если города нет с таким id, то возвращаем наше кастомное исключение
        // и скрипты по запросам  не будут крашиться
        return foundCity.orElseThrow(CityNotFoundException::new);
    }

    @Transactional
    public void save(City city) {
        enrichCity(city);
        citiesRepository.save(city);
    }

    private void enrichCity(City city) {
    }

    @Transactional
    public void update(int id, City updatedCity) {
        updatedCity.setId(id);
        citiesRepository.save(updatedCity);
    }

    @Transactional
    public void delete(int id) {
        citiesRepository.deleteById(id);
    }


    public Optional<City> getCityByCityName(String cityName) {
        return citiesRepository.findByCityName(cityName);
    }

    //получаем город по id, если  такой город есть, то видим его достопримечательности, если нет
    //то возвращается пустой список emptyList()
    //т.е. тут получаем достопримечательности конкретного города
    public List<Attraction> getAttractionsByCityId(int id) {
        Optional<City> city = citiesRepository.findById(id);

        if (city.isPresent()) {
            Hibernate.initialize(city.get().getAttractions());
            // Мы внизу итерируемся по достопримечательностям, поэтому они точно будут загружены, но для подстраховки
            // не помешает вызвать Hibernate.initialize() (от ошибки ленивой инициализации)
            // (на случай, например, если код в дальнейшем поменяется и итерация по достопримечательностям удалится)
            return city.get().getAttractions();
        }
        else {
            return null;
            //по хорошему сюда нужно добавить исключение как у findOne
            //return foundCity.orElseThrow(CityNotFoundException::new), вопрос времени.


        }
    }
}
