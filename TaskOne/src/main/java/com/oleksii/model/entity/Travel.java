package com.oleksii.model.entity;

import java.time.LocalDate;
import java.util.Objects;

import com.oleksii.model.util.ArgumentChecker;

public class Travel extends AbstractEntity<Integer> {

    private String name;
    private TravelType type;
    private String transport;
    private LocalDate start;
    private int cost; // in USD

    public Travel(int id, String name, TravelType type, String transport, int cost, LocalDate start) {
        super(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(type);
        Objects.requireNonNull(transport);
        Objects.requireNonNull(start);
        ArgumentChecker.checkArgs(cost > 0);
        this.name = name;
        this.type = type;
        this.transport = transport;
        this.cost = cost;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    @Override
    public Integer getId() {
        return super.getId();
    }
    
    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public TravelType getType() {
        return type;
    }

    public void setType(TravelType type) {
        Objects.requireNonNull(type);
        this.type = type;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        Objects.requireNonNull(transport);
        this.transport = transport;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        Objects.requireNonNull(start);
        this.start = start;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        ArgumentChecker.checkArgs(cost > 0);
        this.cost = cost;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Travel [id=").append(super.getId()).append(", name=").append(name).append(", type=").append(type)
                .append(", transport=").append(transport).append(", start=").append(start).append(", cost=")
                .append(cost).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, type, transport, start, cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Travel) {
            Travel that = (Travel) obj;
            return super.equals(that) && 
                    Objects.equals(name, that.name) &&
                    Objects.equals(start, that.start) &&
                    Objects.equals(transport, that.transport) &&
                    Objects.equals(cost, that.cost) &&
                    Objects.equals(type, that.type);
        }
        return false;
    }

    
}
