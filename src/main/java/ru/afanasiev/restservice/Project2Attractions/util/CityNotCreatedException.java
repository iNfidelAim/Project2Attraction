package ru.afanasiev.restservice.Project2Attractions.util;

public class CityNotCreatedException extends RuntimeException {
    public CityNotCreatedException(String msg) {
        super(msg); //передали в RunTimeException
    }
}
