package ua.training.training_block_6.model;

import java.util.Objects;

/**
 * 
 * @author atpt34
 *
 */
public class User implements Cloneable {

    private String name;
    private String nickName;
    
    public User() {
    }
    
    public User(String name, String nickName) {
        this.name = name;
        this.nickName = Objects.requireNonNull(nickName, "nickName must not be null");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = Objects.requireNonNull(nickName, "nickName must not be null");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
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
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        return true;
    }

    @Override
    public User clone() {
        User copy = null;
        try {
            copy = (User) super.clone();
            copy.name = new String(name);
            copy.nickName = new String(nickName);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [name=");
        builder.append(name);
        builder.append(", nickName=");
        builder.append(nickName);
        builder.append("]");
        return builder.toString();
    }
}
