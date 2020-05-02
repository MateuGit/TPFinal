package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.CityExceptions;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Repositories.ICityRepository;
import com.utn.UTNphones.Services.interfaces.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService implements ICityService {

    private final ICityRepository cityRepository;

    @Autowired
    public CityService(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City getById(Integer id) throws CityExceptions {
        Optional<City> cityOptional = cityRepository.findById(id);
        return Optional.ofNullable(cityOptional.get()).orElseThrow(()->new CityExceptions("The city doesn´t exist"));
    }
}