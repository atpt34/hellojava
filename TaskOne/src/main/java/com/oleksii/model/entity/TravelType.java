package com.oleksii.model.entity;

import java.util.Objects;

public enum TravelType {

    RELAX, EXCURSION, HEALTH, SHOPPING;
    
    public static TravelType convertFromString(String type) {
        Objects.requireNonNull(type);
        String typeInUpperCase = type.toUpperCase();
        for (TravelType travelType : TravelType.values()) {
            if (travelType.toString().equals(typeInUpperCase)) {
                return travelType;
            }
        }
        return null;
    }
}
