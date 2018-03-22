package ua.training.quickmaven;

import junit.framework.TestCase;
import ua.training.quickmaven.model.User;

public class UserTest extends TestCase {
    
    public static final User USER = new User("oleksii", "oleksa");
    public static final User SAME_USER = new User("oleksii", "oleksa");
    public static final User OTHER_USER = new User("oleksii", "popov");
    public static final User THIRD_SAME_USER = new User("oleksii", "oleksa");

    public void testJavaContract() {
        assertTrue(USER.equals(SAME_USER));
        assertTrue(USER.hashCode() == SAME_USER.hashCode());
    }
    
    public void testJavaContractConsistency() {
        int hashCode = USER.hashCode();
        String getName = USER.getName();
        String getNickName = USER.getNickName();
        int newHashCode = USER.hashCode();
        assertTrue(hashCode == newHashCode);
    }

    public void testHashCodeSameUsers() {
        assertTrue(USER.hashCode() == SAME_USER.hashCode());
    }
    
    // Not required condition
    public void testHashCodeNotSameUsers() {
        assertFalse(USER.hashCode() == OTHER_USER.hashCode());
    }

    public void testEqualsReflexive() {
        assertTrue(USER.equals(USER));
        assertTrue(SAME_USER.equals(SAME_USER));
        assertTrue(OTHER_USER.equals(OTHER_USER));
    }
    
    public void testEqualsSymmetry() {
        assertTrue(USER.equals(SAME_USER));
        assertTrue(SAME_USER.equals(USER));
    }
    
    public void testEqualsTransitive() {
        assertTrue(USER.equals(SAME_USER));
        assertTrue(SAME_USER.equals(THIRD_SAME_USER));
        assertTrue(USER.equals(THIRD_SAME_USER));
    }
    
    public void testEqualsConsistencyTrue() {
        assertTrue(USER.equals(SAME_USER));
        String getName = USER.getName();
        String getNickName = USER.getNickName();
        String getName2 = SAME_USER.getName();
        String getNickName2 = SAME_USER.getNickName();
        assertTrue(USER.equals(SAME_USER));
    }
    
    public void testEqualsConsistencyFalse() {
        assertFalse(USER.equals(OTHER_USER));
        String getName = USER.getName();
        String getNickName = USER.getNickName();
        String getName2 = OTHER_USER.getName();
        String getNickName2 = OTHER_USER.getNickName();
        assertFalse(USER.equals(OTHER_USER));
    }
    
    public void testEqualsNull() {
        assertFalse(USER.equals(null));
    }
    
    public void testEqualsSameUsers() {
        assertTrue(USER.equals(SAME_USER));
    }
    
    public void testEqualsNotSameUsers() {
        assertFalse(USER.equals(OTHER_USER));
    }

    public void testClone() {
        User clone = USER.clone();
        assertTrue(USER.equals(clone));
    }
    
    public void testCloneNameAndNickName() {
        User clone = USER.clone();
        assertFalse(USER.getName() == clone.getName());
        assertFalse(USER.getNickName() == clone.getNickName());
    }


}
