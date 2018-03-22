package ua.training.quickmaven.controller;

import java.util.Scanner;

import ua.training.quickmaven.model.User;
import ua.training.quickmaven.model.UserBook;
import ua.training.quickmaven.model.UserDetails;
import ua.training.quickmaven.view.View;

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
        
        String name = inputStringValueWithScanner(View.INPUT_NAME, RegexConstants.NAME);
        String nickName = inputStringValueWithScanner(View.INPUT_NICK_NAME, RegexConstants.NICK_NAME);
        String phone = inputStringValueWithScanner(View.INPUT_PHONE, RegexConstants.PHONE);
        String email = inputStringValueWithScanner(View.INPUT_EMAIL, RegexConstants.EMAIL);
        String icq = inputStringValueWithScanner(View.INPUT_ICQ, RegexConstants.ICQ);
        
        User user = new UserDetails(name, nickName, phone, email, icq);
        userBook.addUser(user);
        view.printMessage(userBook.toString());
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
