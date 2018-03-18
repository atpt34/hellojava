package ua.training.controller;

public interface RegexConstants {

    String FIRST_NAME = "([A-Z][a-z]{2,32})(-[A-Z][a-z]{2,32})?|(oleksii)";
    String SECOND_NAME = "([A-Z][a-z]{2,32})(-[A-Z][a-z]{2,32})?";
    String THIRD_NAME = "([A-Z][a-z]{2,32})(-[A-Z][a-z]{2,32})?";
    String SHORT_NAME = "([A-Z][a-z]{2,32})(-[A-Z][a-z]{2,32})? ([A-Z]\\.)";
    String NICK_NAME = "[A-Za-z][0-9A-Za-z]{6,32}";
    String COMMENT = ".*";
    String HOME_PHONE = "[0-9]{2}-[0-9]{2}-[0-9]{2}";
    String MOBILE_PHONE = "(\\+38)?\\(?((093)|(063))\\)?([0-9]{2}-?[0-9]{2}-?[0-9]{3})";
    String EMAIL = "[A-Za-z][0-9A-Za-z]{4,16}@([A-Za-z]{1,16}\\.){1,4}[a-z]{1,15}";
    String SKYPE = "[A-Za-z][0-9A-Za-z]{6,32}";
    String INDEX = "[1-9][0-9]{4,}";
    String CITY = "[A-Z][a-z]{2,32}";
    String STREET = "[A-Z][a-z]{2,32}";
    String BUILDING = "[1-9][0-9]{0,32}(( |\\\\|\\/)[a-z])?";
    String ROOM = "[1-9][0-9]{0,32}";
    String GROUP = "[A-Z]*";
    String DATE = "(1[0-9]|2[0-4]|[1-9]):[1-5]?[0-9] [1-3]?[0-9](-| |\\\\|\\/|\\.)[1]?[0-9](-| |\\\\|\\/|\\.)(19|20)[0-9]{2}";
    String YES = "yes";

}
