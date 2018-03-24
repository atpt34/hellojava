package ua.training.training_block_6.controller;

import java.util.Scanner;

import ua.training.training_block_6.model.LoginException;
import ua.training.training_block_6.model.User;
import ua.training.training_block_6.model.UserBook;
import ua.training.training_block_6.model.UserDetails;
import ua.training.training_block_6.view.View;


/**
 * 
 * @author atpt34
 *
 */
public class Controller {

    private Scanner scanner;
    private UserBook userBook;
    private View view;

    public Controller(UserBook userBook, View view) {
        this.userBook = userBook;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }



    public void processUser() {
        UserDetails user;
        do {
            String name = inputStringValueWithScanner(View.INPUT_NAME, RegexConstants.NAME);
            String nickName = inputStringValueWithScanner(View.INPUT_NICK_NAME, RegexConstants.NICK_NAME);
            String phone = inputStringValueWithScanner(View.INPUT_PHONE, RegexConstants.PHONE);
            String email = inputStringValueWithScanner(View.INPUT_EMAIL, RegexConstants.EMAIL);
            String icq = inputStringValueWithScanner(View.INPUT_ICQ, RegexConstants.ICQ);
            user = new UserDetails(name, nickName, phone, email, icq);
        } while(addUserToUserBook(user));
        view.printMessage(userBook.toString());
    }



    private boolean addUserToUserBook(User user){
        try {
            userBook.addUser(user);
            return true;
        } catch (LoginException e) {
            view.printMessage(View.EXISTING_LOGIN);
        }
        return false;
    }
    

    private String inputStringValueWithScanner(String message, String regex) {
        String result = null;
        view.printMessage(message);
        while(! (scanner.hasNextLine() && (result = scanner.nextLine()).matches(regex))) {
                view.printMessage(View.WRONG_INPUT);
        }
        return result;
    }

}
