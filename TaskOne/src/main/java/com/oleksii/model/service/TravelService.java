package com.oleksii.model.service;

import java.util.List;

import com.oleksii.model.entity.Travel;
import com.oleksii.model.entity.TravelType;

public interface TravelService {

    List<Travel> findAll();
    List<Travel> sortAllByCost();
    List<Travel> findByTravelType(TravelType type);
    Travel findTravelById(int id);
}
