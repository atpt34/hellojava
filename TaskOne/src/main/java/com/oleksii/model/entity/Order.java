package com.oleksii.model.entity;



public class Order extends AbstractEntity<Integer> {
    
    private Travel travel;
    private String username;
    private String userphone;
    private int numOfDays;
    private int numOfPeople;
    
    public Order(int id, Travel travel, String username, String userphone, int numOfDays, int numOfPeople) {
        super(id);
        this.travel = travel;
        this.username = username;
        this.userphone = userphone;
        this.numOfDays = numOfDays;
        this.numOfPeople = numOfPeople;
    }
   
    public int getId() {
        return id;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order [id=").append(id).append(", travel=").append(travel).append(", username=")
                .append(username).append(", userphone=").append(userphone).append(", numOfDays=").append(numOfDays)
                .append(", numOfPeople=").append(numOfPeople).append("]");
        return builder.toString();
    }
    
    

}
