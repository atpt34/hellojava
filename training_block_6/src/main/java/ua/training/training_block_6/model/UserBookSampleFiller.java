/**
 * 
 */
package ua.training.training_block_6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author atpt34
 *
 */
public class UserBookSampleFiller {

    
    private static List<UserDetails> sample;
    static {
        sample = new ArrayList<UserDetails>();
        sample.add(new UserDetails("Oleksii", "oleksa", "0932938923", "atpt34@gmail.com", "helloatpt"));
        sample.add(new UserDetails("Vadim", "vadimka", "0638282000", "vadim2016@gmail.com", "vadim2016"));
    }
    
    private UserBook userBook;

    public UserBookSampleFiller(UserBook userBook) {
        this.userBook = Objects.requireNonNull(userBook, "userBook must not be null");
    }

    public void fill() {
        for (User user : sample) {
            userBook.safeAddUser(user);
        }
    }

}
