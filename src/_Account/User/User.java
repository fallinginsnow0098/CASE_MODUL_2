package _Account.User;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String userPhoneNumber;
    private String userAddress;

    public User(String username, String userPhoneNumber, String userAddress) {
        this.username = username;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "[User " + "Name = " + username + " Phone Number = " + userPhoneNumber + " Address= " + userAddress +"]";
    }
}
