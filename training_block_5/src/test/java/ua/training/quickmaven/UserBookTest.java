package ua.training.quickmaven;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ua.training.quickmaven.model.User;
import ua.training.quickmaven.model.UserBook;

public class UserBookTest extends TestCase {

    public static final User USER = new User("oleksii", "oleksa");
    public static final User SAME_USER = new User("oleksii", "oleksa");
    public static final User OTHER_USER = new User("oleksii", "popov");
    public static final List<User> USERS = new ArrayList<User>();
    public static final List<User> OTHER_USERS = new ArrayList<User>();
    static {
        USERS.add(USER);
        USERS.add(SAME_USER);
        USERS.add(OTHER_USER);
        OTHER_USERS.add(OTHER_USER);
        OTHER_USERS.add(SAME_USER);
        OTHER_USERS.add(USER);
    }
    public static final UserBook USERBOOK = new UserBook(USERS);
    public static final UserBook SAME_USERBOOK = new UserBook(USERS);
    public static final UserBook OTHER_USERBOOK = new UserBook(OTHER_USERS);
    public static final UserBook THIRD_SAME_USERBOOK = new UserBook(USERS);

    public void testJavaContract() {
        assertTrue(USERBOOK.equals(SAME_USERBOOK));
        assertTrue(USERBOOK.hashCode() == SAME_USERBOOK.hashCode());
    }
    
    public void testJavaContractConsistency() {
        int hashCode = USERBOOK.hashCode();
        List<User> get = USERBOOK.getUsers();
        int newHashCode = USERBOOK.hashCode();
        assertTrue(hashCode == newHashCode);
    }

    public void testHashCodeSameUsers() {
        assertTrue(USERBOOK.hashCode() == SAME_USERBOOK.hashCode());
    }
    
    // Not required condition
    public void testHashCodeNotSameUsers() {
        assertFalse(USERBOOK.hashCode() == OTHER_USERBOOK.hashCode());
    }

    public void testEqualsReflexive() {
        assertTrue(USERBOOK.equals(USERBOOK));
        assertTrue(SAME_USERBOOK.equals(SAME_USERBOOK));
        assertTrue(OTHER_USERBOOK.equals(OTHER_USERBOOK));
    }
    
    public void testEqualsSymmetry() {
        assertTrue(USERBOOK.equals(SAME_USERBOOK));
        assertTrue(SAME_USERBOOK.equals(USERBOOK));
    }
    
    public void testEqualsTransitive() {
        assertTrue(USERBOOK.equals(SAME_USERBOOK));
        assertTrue(SAME_USERBOOK.equals(THIRD_SAME_USERBOOK));
        assertTrue(USERBOOK.equals(THIRD_SAME_USERBOOK));
    }
    
    public void testEqualsConsistencyTrue() {
        assertTrue(USERBOOK.equals(SAME_USERBOOK));
        List<User> get = USERBOOK.getUsers();
        assertTrue(USERBOOK.equals(SAME_USERBOOK));
    }
    
    public void testEqualsConsistencyFalse() {
        assertFalse(USERBOOK.equals(OTHER_USERBOOK));
        List<User> get = USERBOOK.getUsers();
        assertFalse(USERBOOK.equals(OTHER_USERBOOK));
    }
    
    public void testEqualsNull() {
        assertFalse(USERBOOK.equals(null));
    }
    
    public void testEqualsSameUsers() {
        assertTrue(USERBOOK.equals(SAME_USERBOOK));
    }
    
    public void testEqualsNotSameUsers() {
        assertFalse(USERBOOK.equals(OTHER_USERBOOK));
    }

    public void testClone() {
        UserBook clone = USERBOOK.clone();
        assertTrue(USERBOOK.equals(clone));
    }
    
    public void testCloneUsers() {
        UserBook clone = USERBOOK.clone();
        assertFalse(USERBOOK.getUsers() == clone.getUsers());
    }
    
    public void testCloneUsersInternal() {
        UserBook clone = USERBOOK.clone();
        List<User> userBookUsers = USERBOOK.getUsers();
        List<User> cloneBookUsers = clone.getUsers();
        assertTrue(userBookUsers.size() == cloneBookUsers.size());
        for (int i = 0; i < userBookUsers.size(); i++) {
            assertFalse(userBookUsers.get(i) == cloneBookUsers.get(i));
        }
    }

}
