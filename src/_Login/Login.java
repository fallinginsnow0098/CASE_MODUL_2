package _Login;

import Validate.Validate;
import _Account.Admin.AccountAdmin;
import _Account.User.AccountUser;
import _Account.User.AccountUserManager;
import _Account.User.User;
import _Account.User.UserManager;
import _MenuSystem.RunByAdmin;
import _MenuSystem.RunByUser;

import javax.xml.validation.Validator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private final Scanner scanner = new Scanner(System.in);
    private final RunByAdmin runByAdmin = new RunByAdmin();
    private final RunByUser runByUser = new RunByUser();
    private final AccountAdmin accountAdmin = new AccountAdmin();
    private final AccountUserManager accountUserManager = new AccountUserManager();
    private final UserManager userManager = new UserManager();
    private final Validate validate = new Validate();
    public static String userAccount;

    public Login() {
    }

    public void login() {
        try {
            menuLogin();
        } catch (InputMismatchException e) {
            System.out.println("Nhập sai dữ liệu! ⛔ \n " +
                    "Vui lòng nhập lại!");

            login();
        } catch (NullPointerException e) {
            System.out.println("Dữ liệu không tồn tại! ⛔ \n" +
                    "Vui lòng nhập lại!");
            login();
        }
    }

    private void menuLogin() {
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng kí");
        System.out.println("0. Thoát");
        System.out.println("Nhập 0 1 2 từ bàn phím !⛔");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                loginManager();
                break;
            case 2:
                registrationManager();
                break;
        }
    }

    // đăng kí
    private void registrationManager() {
        scanner.nextLine();
        System.out.println("Mời bạn nhập thông tin cá nhân \n" +
                "Yêu cầu nhập hết - vì nó là cần thiết\n");
        System.out.println("-------------------------------------");
        System.out.println("Vui lòng nhập họ và tên ⛔");
        String name = scanner.nextLine();
        System.out.println("Vui lòng nhập số điện thoại đăng kí ⛔");
        String phoneNumber = registerPhoneNumber();
        System.out.println("Vui lòng nhập địa chỉ của bạn ⛔");
        String address = scanner.nextLine();
        System.out.println("Vui lòng nhập vào Tài Khoản đăng kí ⛔");
        String accountUser = registerAccount();
        System.out.println("Vui lòng nhập Mật Khẩu đăng kí ⛔");
        String passwordUser = registerPassword();
        checkAccountUser(name, phoneNumber, address, accountUser, passwordUser);
    }
    public String registerAccount(){
        String account ;
        while (true){
            String inputAccount = scanner.nextLine();
            if (!validate.validateAccount(inputAccount)){
                System.out.println("Tài khoản đăng kí không hợp lệ!");
                System.out.println(">[Chú ý]: Tài khoản phải từ 8 - 12 ký tự (a,1,...)\n");
            } else {
                account = inputAccount;
                break;
            }
        }
        return account;
    }
    public String registerPassword(){
        String password ;
        while (true){
            String inputPassword = scanner.nextLine();
            if (!validate.validatePassword(inputPassword)){
                System.out.println("Mật khẩu đăng kí không hợp lệ!");
                System.out.println(">[Chú ý]: Mật khẩu phải từ 8 - 16 ký tự (a,A,1,...)hoặc ký tự đặc biệt\n");
            } else {
                password = inputPassword;
                break;
            }
        }
        return password;
    }
    public String registerPhoneNumber(){
        String phoneNumber;
        while (true){
            String inputPhoneNumber = scanner.nextLine();
            if (!validate.validatePhone(inputPhoneNumber)){
                System.out.println("Số điện thoại đăng kí không hợp lệ !");
                System.out.println(">[Chú ý]: Số điện thoại phải có 10 số (0 - 9) định dạng: (+84)-911112222\n");
            } else {
                phoneNumber = inputPhoneNumber;
                break;
            }
        }
        return phoneNumber;
    }

    private void checkAccountUser(String name, String phoneNumber, String address, String accountUser, String passwordUser) {
        if (accountUserManager.checkFile()) {
            writeUserAccountInformation(name, phoneNumber, address, accountUser, passwordUser);
            System.out.println("Đăng kí thành công! ⛔\n" +
                    "Vui lòng đăng nhập để tiếp tục!\n");
        } else if (checkAccountExists(accountUser)) {
            System.out.println("Tài khoản đã tồn tại! ⛔\n" +
                    "Vui lòng đăng kí tài khoản mới\n");
        } else {
            writeUserAccountInformation(name, phoneNumber, address, accountUser, passwordUser);
            System.out.println("Đăng kí thành công! ⛔ \n" +
                    "Bây giờ bạn đã có thể đăng nhập với tài khoản này.\n");
        }
        login();
    }

    private void writeUserAccountInformation(String name, String phoneNumber, String address, String accountUser, String passwordUser) {
        userManager.setUserList(name, phoneNumber, address);
        accountUserManager.setAccountUserList(accountUser, passwordUser);
    }

    private boolean checkAccountExists(String accountUser) {
        for (AccountUser account : accountUserManager.getAccountUserList()) {
            boolean check = accountUser.equals(account.getUserAccount());
            if (check) {
                return true;
            }
        }
        return false;
    }

    // đăng nhập
    private void loginManager() {
        scanner.nextLine();
        System.out.println("Nhập vào tên đăng nhập");
        String account = scanner.nextLine();
        System.out.println("Nhập vào mật khẩu");
        String password = scanner.nextLine();
        checkLogin(account, password);
        userAccount = account;
    }

    private void checkLogin(String account, String password) {
        try {
            if (checkAdminLogin(account, password)) {
                System.out.println("Đăng nhập vào hệ thống thành công ⛔\n" +
                        "Xin chào - admin " + account + "\n");
                runByAdmin.runByAdmin();
            } else if (accountUserManager.checkFile()) {
                System.out.println("Tài khoản chưa tồn tại! ⛔\n" +
                        "Vui lòng đăng kí để đăng nhập!");
            } else if (checkUserLogin(account, password)) {
                System.out.println("Đăng nhập người dùng thành công ⛔\n" +
                        "Xin chào - "+ account);
                System.out.println("Chúc bạn mua sắm vui vẻ!\n");
                runByUser.runByUser();
            } else {
                System.out.println("Tài khoản vừa nhập vào không tồn tại! ⛔\n" +
                        "Vui lòng nhập lại tài khoản khác!\n");
                login();
            }
        } catch (InputMismatchException e) {
            System.out.println("Dữ liệu nhập vào không đúng! ⛔ \n" +
                    "Vui lòng nhập lại!");
        }
    }

    private boolean checkUserLogin(String account, String password) {
        for (AccountUser userAccount : accountUserManager.getAccountUserList()) {
            boolean check = account.equals(userAccount.getUserAccount()) && password.equals(userAccount.getUserPassword());
            if (check) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAdminLogin(String account, String password) {
        for (AccountAdmin adminAccount : accountAdmin.getAccountAdminList()) {
            boolean check = account.equals(adminAccount.getAdminAccount()) && password.equals(adminAccount.getAdminPassword());
            if (check) {
                return true;
            }
        }
        return false;
    }
}
