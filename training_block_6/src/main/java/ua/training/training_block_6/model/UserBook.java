package ua.training.training_block_6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author atpt34
 *
 */
public class UserBook implements Cloneable {

    private List<User> users;

    public UserBook() {
        this.users = new ArrayList<User>();
    }
    
    public UserBook(List<User> users) {
        this.users = users;
    }
    
    public void addUser(User user) throws LoginException {
        Objects.requireNonNull(user, "user must not be null");
        if (!isUniqueNickName(user)) {
            throw new LoginException("User with such nickname is already exists");
        }
        safeAddUser(user);
    }
    
    private boolean isUniqueNickName(User newUser) {
        for (User user : users) {
            if (user.getNickName().equals(newUser.getNickName())) {
                return false;
            }
        }
        return true;
    }

    void safeAddUser(User user) {
        users.add(user);
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserBook other = (UserBook) obj;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        return true;
    }

    @Override
    public UserBook clone() {
        try {
            UserBook copy = (UserBook) super.clone();
            copy.users = new ArrayList<User>();
            for (User user : this.users) {
                copy.users.add(user.clone());
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public String toString() {
        return "UserBook [users=" + users + "]";
    }
    
}
