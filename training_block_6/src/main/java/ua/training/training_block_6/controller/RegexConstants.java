package ua.training.training_block_6.controller;

public interface RegexConstants {

    String NICK_NAME = "[A-Za-z][0-9A-Za-z]{5,32}";
    String NAME = "[A-Za-z][A-Za-z]{2,32}(-[A-Za-z][A-Za-z]{2,32})?";
    String PHONE = "(\\+38)?0[0-9]{2} ?([0-9]{2}-?[0-9]{2}-?[0-9]{3})";
    String EMAIL = "^[A-Za-z][A-Za-z0-9._%+-]{4,16}@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    String ICQ = "[A-Za-z][0-9A-Za-z._%+-/]{5,32}";
}
