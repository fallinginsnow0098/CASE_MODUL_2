package _Account.User;

import _ReadWriteFile.IOFile;

import java.util.ArrayList;

public class AccountUserManager {
    private final ArrayList<AccountUser> accountUserList = new ArrayList<>();
    private final IOFile<AccountUser> ioFile = new IOFile<>();
    private final String PATH_USER_ACC = "src/_File/AccountFile/account_user_file";

    public AccountUserManager() {
    }

    public ArrayList<AccountUser> getAccountUserList() {
        return ioFile.readFileData(PATH_USER_ACC);
    }

    public void setAccountUserList(String userAccount, String userPassword) {
        ArrayList<AccountUser> accounts;
        if (checkFile()) {
            accounts = accountUserList;
        } else {
            accounts = getAccountUserList();
        }
        accounts.add(new AccountUser(userAccount, userPassword));
        ioFile.writerFileData(accounts, PATH_USER_ACC);
    }

    public void deleteAccountUser(String account) {
        accountUserList.removeIf(AccountUser -> AccountUser.getUserAccount().equals(account));
        ioFile.writerFileData(accountUserList, PATH_USER_ACC);
        System.out.println("Xóa tài khoản " + account + " thành công! ");
    }
    public boolean checkFile() {
        ArrayList<AccountUser> accountUsers = getAccountUserList();
        return accountUsers == null ;
    }
}
