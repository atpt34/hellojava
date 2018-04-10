package com.oleksii.model.entity;

import java.time.LocalDate;

public class Travel extends AbstractEntity<Integer> {

    private String name;
    private TravelType type;
    private String transport;
    private LocalDate start;
    private int cost; // in USD

    public Travel(int id, String name, TravelType type, String transport, int cost, LocalDate start) {
        super(id);
        this.name = name;
        this.type = type;
        this.transport = transport;
        this.cost = cost;
        this.start = start;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TravelType getType() {
        return type;
    }

    public void setType(TravelType type) {
        this.type = type;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Travel [id=").append(id).append(", name=").append(name).append(", type=").append(type)
                .append(", transport=").append(transport).append(", start=").append(start).append(", cost=")
                .append(cost).append("]");
        return builder.toString();
    }

    
}
