package ua.training.model;

/**
 * 
 * @author atpt34
 *
 */
public enum Group {

    USER, ADMIN, MANAGER, DEVELOPER;
    
    public static Group mapStringToGroup(String groupString) 
            throws IllegalArgumentException {
        return Group.valueOf(groupString.toUpperCase());
    }
    
}
