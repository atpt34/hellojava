package ua.training.model;

/**
 * 
 * @author atpt34
 *
 */
public class Address {

    private String index;
    private String city;
    private String street;
    private String building;
    private String room;
    
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String toFullAddress() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Address [index=").append(index).append(", city=").append(city).append(", street=")
                .append(street).append(", building=").append(building).append(", room=").append(room).append("]");
        return builder.toString();
    }


}
