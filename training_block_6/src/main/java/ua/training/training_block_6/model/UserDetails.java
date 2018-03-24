package ua.training.training_block_6.model;

/**
 * 
 * @author atpt34
 *
 */
public class UserDetails extends User implements Cloneable {

    private String phone;
    private String email;
    private String icq;
    
    public UserDetails() {
        
    }
    
    public UserDetails(String name, String nickName) {
        super(name, nickName);
    }

    public UserDetails(String name, String nickName, String phone, String email, String icq) {
        super(name, nickName);
        this.phone = phone;
        this.email = email;
        this.icq = icq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((icq == null) ? 0 : icq.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDetails other = (UserDetails) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (icq == null) {
            if (other.icq != null)
                return false;
        } else if (!icq.equals(other.icq))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        return true;
    }
    
    @Override
    public UserDetails clone() {
        UserDetails clone = (UserDetails) super.clone();
        clone.email = new String(email);
        clone.icq = new String(icq);
        clone.phone = new String(phone);
        return clone;
        
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserDetails [phone=");
        builder.append(phone);
        builder.append(", email=");
        builder.append(email);
        builder.append(", icq=");
        builder.append(icq);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }
    
}
