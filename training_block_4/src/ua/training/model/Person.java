package ua.training.model;

import java.time.LocalDateTime;
import java.util.List;
import ua.training.StringConstants;

/**
 * 
 * @author atpt34
 *
 */
public class Person {

    private String firstName;
    private String secondName;
    private String thirdName;
    private String shortName;
    private String nickName;
    private String comment;
    private List<Group> groups;
    private String homePhone;
    private String mobilePhone;
    private String mobilePhoneAdditional;
    private String email;
    private String skype;
    private Address address;
    private String fullAddress;
    private String sinceDate;
    private String changedDate;
    
    public void makeShortName() {
        shortName = secondName + StringConstants.SPACE + 
                firstName.charAt(0) + StringConstants.DOT;
    }
    
    public void makeFullAddress() {
        fullAddress = address.toFullAddress();
    }
    
    public String makeNowDateString() {
        return LocalDateTime.now().toString();        
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(String sinceDate) {
        this.sinceDate = sinceDate;
    }

    public String getNickName() {
        return nickName;
    }

    public String getComment() {
        return comment;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person [firstName=");
        builder.append(firstName);
        builder.append(", secondName=");
        builder.append(secondName);
        builder.append(", thirdName=");
        builder.append(thirdName);
        builder.append(", shortName=");
        builder.append(shortName);
        builder.append(", nickName=");
        builder.append(nickName);
        builder.append(", comment=");
        builder.append(comment);
        builder.append(", groups=");
        builder.append(groups);
        builder.append(", homePhone=");
        builder.append(homePhone);
        builder.append(", mobilePhone=");
        builder.append(mobilePhone);
        builder.append(", mobilePhoneAdditional=");
        builder.append(mobilePhoneAdditional);
        builder.append(", email=");
        builder.append(email);
        builder.append(", skype=");
        builder.append(skype);
        builder.append(", address=");
        builder.append(address);
        builder.append(", fullAddress=");
        builder.append(fullAddress);
        builder.append(", sinceDate=");
        builder.append(sinceDate);
        builder.append(", changedDate=");
        builder.append(changedDate);
        builder.append("]");
        return builder.toString();
    }



    
}
