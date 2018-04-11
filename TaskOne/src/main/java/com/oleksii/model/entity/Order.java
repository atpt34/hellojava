package com.oleksii.model.entity;

import java.util.Objects;

import com.oleksii.model.util.ArgumentChecker;

public class Order extends AbstractEntity<Integer> {
    
    private Travel travel;
    private String username;
    private String userphone;
    private int numOfDays;
    private int numOfPeople;
    
    public Order(int id, Travel travel, String username, String userphone, int numOfDays, int numOfPeople) {
        super(id);
        Objects.requireNonNull(travel);
        Objects.requireNonNull(username);
        Objects.requireNonNull(userphone);
        ArgumentChecker.checkArgs(numOfDays > 0);
        ArgumentChecker.checkArgs(numOfPeople > 0);
        this.travel = travel;
        this.username = username;
        this.userphone = userphone;
        this.numOfDays = numOfDays;
        this.numOfPeople = numOfPeople;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        Objects.requireNonNull(travel);
        this.travel = travel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Objects.requireNonNull(username);
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        Objects.requireNonNull(userphone);
        this.userphone = userphone;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        ArgumentChecker.checkArgs(numOfDays > 0);
        this.numOfDays = numOfDays;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        ArgumentChecker.checkArgs(numOfPeople > 0);
        this.numOfPeople = numOfPeople;
    }

    @Override
    public Integer getId() {
        return super.getId();
    }
    
    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order [id=").append(super.getId()).append(", travel=").append(travel).append(", username=")
                .append(username).append(", userphone=").append(userphone).append(", numOfDays=").append(numOfDays)
                .append(", numOfPeople=").append(numOfPeople).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numOfDays;
        result = prime * result + numOfPeople;
        result = prime * result + ((travel == null) ? 0 : travel.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((userphone == null) ? 0 : userphone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof Order))
            return false;
        Order other = (Order) obj;
        if (numOfDays != other.numOfDays)
            return false;
        if (numOfPeople != other.numOfPeople)
            return false;
        if (travel == null) {
            if (other.travel != null)
                return false;
        } else if (!travel.equals(other.travel))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (userphone == null) {
            if (other.userphone != null)
                return false;
        } else if (!userphone.equals(other.userphone))
            return false;
        return true;
    }
    
    

}
