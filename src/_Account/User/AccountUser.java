package _Account.User;

import java.io.Serializable;

public class AccountUser implements Serializable {
    private String userAccount;
    private String userPassword;

    public AccountUser() {
    }

    public AccountUser(String userAccount, String userPassword) {
        this.userAccount = userAccount;
        this.userPassword = userPassword;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "[USER " + "Account = " + userAccount + " Password = " + userPassword + "]";
    }
}
