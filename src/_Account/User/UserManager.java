package _Account.User;

import _ReadWriteFile.IOFile;

import java.util.ArrayList;

public class UserManager {
    private final ArrayList<User> userList = new ArrayList<>();
    private final IOFile<User> ioFile = new IOFile<>();
    private final String PATH_USER_INFORMATION = "src/_File/UserInformation/user_information";

    public UserManager() {
    }

    public ArrayList<User> getUserList() throws NullPointerException{
        return ioFile.readFileData(PATH_USER_INFORMATION);
    }
    public void setUserList(String name, String phoneNumber, String address) {
        ArrayList<User> users ;
        if (checkFileUser()){
            users = userList;
        } else {
            users = getUserList();
        }
        users.add(new User(name, phoneNumber, address));
        ioFile.writerFileData(users, PATH_USER_INFORMATION);
        System.out.println("Thêm thành công!");
    }
    public void deleteUser(int index){
        for (int i = 0; i < userList.size(); i++) {
            userList.remove(index);
        }
        System.out.println("Xóa thông tin người dùng thành công!");
    }
    public boolean checkFileUser(){
        ArrayList<User> users = getUserList();
        return users == null;
    }
}
