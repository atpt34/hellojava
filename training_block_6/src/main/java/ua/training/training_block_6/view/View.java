package ua.training.training_block_6.view;

/**
 * 
 * @author atpt34
 *
 */
public class View {

    public static final String WRONG_INPUT = "Wrong input!";
    public static final String INPUT_NICK_NAME = "Please, input nick name: ";
    public static final String INPUT_NAME = "Please, input name: ";
    public static final String INPUT_PHONE = "Please, input phone: ";
    public static final String INPUT_EMAIL = "Please, input email: ";
    public static final String INPUT_ICQ = "Please, input icq: ";
    public static final String EXISTING_LOGIN = "Nickname already in use!";

    public void printMessage(String message) {
        System.out.println(message);
    }
}
