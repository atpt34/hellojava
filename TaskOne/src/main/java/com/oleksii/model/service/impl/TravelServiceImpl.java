package com.oleksii.model.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.oleksii.model.dao.CrudDAO;
import com.oleksii.model.dao.DaoFactory;
import com.oleksii.model.entity.Travel;
import com.oleksii.model.entity.TravelType;
import com.oleksii.model.service.TravelService;

public class TravelServiceImpl implements TravelService {

    private final CrudDAO<Travel, Integer> crud = DaoFactory.getInstance().getTravelDao();

    @Override
    public List<Travel> findAll() {
        return crud.findAll();
    }

    @Override
    public List<Travel> sortAllByCost() {
        List<Travel> all = crud.findAll();
        Collections.sort(all, new Comparator<Travel>() {
            @Override
            public int compare(Travel t1, Travel t2) {
                return t1.getCost() - t2.getCost();
            }
        });
        return all;
    }

    @Override
    public List<Travel> findByTravelType(TravelType type) {
        List<Travel> all = crud.findAll();
        List<Travel> result = new ArrayList<Travel>();
        for (Travel travel : all) {
            if (travel.getType() == type) {
                result.add(travel);
            }
        }
        return result;
    }

    @Override
    public Travel findTravelById(int id) {
        return crud.findOne(id);
    }

    @Override
    public void deleteTravelById(int id) {
        crud.delete(id);        
    }

    @Override
    public void editTravel(Travel t) {
        crud.update(t);       
    }

    @Override
    public void createTravel(Travel t) {
        crud.create(t);
    }

    @Override
    public boolean existsTravelById(int id) {
        return crud.exists(id);
    }

}
