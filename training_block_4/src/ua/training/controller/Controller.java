package ua.training.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ua.training.model.Address;
import ua.training.model.Group;
import ua.training.model.Person;
import ua.training.model.PersonBook;

import ua.training.view.View;

public class Controller {
    
    private Scanner scanner;
    private PersonBook personBook;
    private View view;

    public Controller(PersonBook personBook, View view) {
        this.personBook = personBook;
        this.view = view;
        this.scanner = new Scanner(System.in);
     
    }

    public void processUser() {

        Person person = new Person();
        Address address = new Address();
        
        person.setFirstName(inputStringValueWithScanner(View.INPUT_FIRST_NAME, RegexConstants.FIRST_NAME));
        person.setNickName(inputStringValueWithScanner(View.INPUT_NICK_NAME, RegexConstants.NICK_NAME));
        person.setComment(inputStringValueWithScanner(View.INPUT_COMMENT, RegexConstants.COMMENT));
        person.setSkype(inputStringValueWithScanner(View.INPUT_SKYPE, RegexConstants.SKYPE));
        person.setEmail(inputStringValueWithScanner(View.INPUT_EMAIL, RegexConstants.EMAIL));
        person.setHomePhone(inputStringValueWithScanner(View.INPUT_HOME_PHONE, RegexConstants.HOME_PHONE));
        person.setMobilePhone(inputStringValueWithScanner(View.INPUT_MOBILE_PHONE, RegexConstants.MOBILE_PHONE));
        person.setSinceDate(inputStringValueWithScanner(View.INPUT_SINCE_DATE, RegexConstants.DATE));
        
        address.setIndex(inputStringValueWithScanner(View.INPUT_INDEX, RegexConstants.INDEX));
        person.setAddress(address);
        
        List<Group> groups = inputListGroups(View.INPUT_GROUPS, RegexConstants.GROUP);
        person.setGroups(groups);
        
        personBook.addPerson(person);
        
        view.printMessage(personBook.toString());
    }


    private List<Group> inputListGroups(String inputGroups, String regex) {
        List<Group> groups = new ArrayList<Group>();
        while (isPresentOptionalInput(View.INPUT_OPTIONAL_GROUP)) {
            String groupString = inputStringValueWithScanner(inputGroups, regex);
            try {
                Group group = Group.mapStringToGroup(groupString);
                groups.add(group);
            } catch (IllegalArgumentException e) {
                view.printMessage(View.WRONG_INPUT_GROUP);
            }
        }
        return groups;
    }

    private boolean isPresentOptionalInput(String message) {
        String result = "";
        view.printMessage(message);
        if ((scanner.hasNextLine() && (result = scanner.nextLine()).matches(RegexConstants.YES))) {
            return true;
        }
        return false;
    }

    private String inputStringValueWithScanner(String message, String regex) {
        String result = "";
        view.printMessage(message);
        while(! (scanner.hasNextLine() && (result = scanner.nextLine()).matches(regex))) {
                view.printMessage(View.WRONG_INPUT);
        }
        return result;
    }
}
