package ua.training.quickmaven.model;

import java.util.Objects;

public class User implements Cloneable {

    private String name;
    private String nickName;
    
    public User() {
    }
    
    public User(String name, String nickName) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(nickName);
        this.name = name;
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        Objects.requireNonNull(nickName);
        this.nickName = nickName;
    }

/*    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
        return result;
    }*/
    
    @Override
    public int hashCode() {
        return Objects.hash(name, nickName);
    }

    /*@Override
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
    }*/
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User that = (User) obj;
            return Objects.equals(this.name, that.name)
                    && Objects.equals(this.nickName, that.nickName);
        }
        return false;
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
    
/*    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [name=");
        builder.append(name);
        builder.append(", nickName=");
        builder.append(nickName);
        builder.append("]");
        return builder.toString();
    }*/
    
    @Override
    public String toString() {
        return Objects.toString(name) + " " + Objects.toString(nickName);
    }
}
