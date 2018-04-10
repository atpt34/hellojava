package com.oleksii.model.entity;

public enum TravelType {

    RELAX, EXCURSION, HEALTH, SHOPPING;
    
    public static TravelType convertFromString(String type) {
        String typeInUpperCase = type.toUpperCase();
        for (TravelType travelType : TravelType.values()) {
            if (travelType.toString().equals(typeInUpperCase)) {
                return travelType;
            }
        }
        return null;
    }
}
