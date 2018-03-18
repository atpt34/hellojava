package ua.training.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author atpt34
 *
 */
public class PersonBook {

    private List<Person> persons; 
    
    public PersonBook() {
        persons = new ArrayList<Person>();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public String toString() {
        return "PersonBook [persons=" + persons + "]";
    }
    
    
}
