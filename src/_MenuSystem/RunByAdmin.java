package _MenuSystem;

import _Account.User.AccountUser;
import _Account.User.AccountUserManager;
import _Account.User.User;
import _Account.User.UserManager;
import _Product.Product;

import _ProductManager.ProductManager;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunByAdmin {
    private final Scanner scan = new Scanner(System.in);
    private final ProductManager productManager = new ProductManager();
    private final AccountUserManager accountUserManager = new AccountUserManager();
    public RunByAdmin() {
    }

    public void runByAdmin() {
        int choice;
        do {
            System.out.println("Menu Admin");
            System.out.println("1. Quản lí người dùng");
            System.out.println("2. Quản lí sản phẩm");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    menuAccountUserManager();
                    break;
                case 2:
                    menuProductManager();
                    break;
            }
        } while (choice != 0);
    }

    public void menuAccountUserManager() {
        try {
            int choice;
            do {
                System.out.println("Quản lí người dùng");
                System.out.println("1. Hiển thị tài khoản người dùng hiện có");
                System.out.println("2. Xóa tài khoản người dùng");
                System.out.println("0. Quay lại Menu chính");
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        checkDisplayUser();
                        break;
                    case 2:
                        scan.nextLine();
                        System.out.println("Nhập vào tên tài khoản người dùng muốn xóa!");
                        String deleteAccount = scan.nextLine();
                        accountUserManager.deleteAccountUser(deleteAccount);
                        int index = (new AccountUser()).getUserAccount().indexOf(deleteAccount);
                        (new UserManager()).deleteUser(index);
                        break;
                }
            } while (choice != 0);
        }  catch (InputMismatchException e) {
            System.out.println("Nhập sai dữ liệu, mời nhập lại!\n");
            scan.nextLine();
            menuAccountUserManager();
        } catch (NullPointerException e) {
            System.out.println("Dữ liệu không tồn tại!\n");
        }
    }

    private void checkDisplayUser() {
        ArrayList<AccountUser> accountUsers = (new AccountUserManager()).getAccountUserList();
        ArrayList<User> users = (new UserManager()).getUserList();
        if (accountUsers.size() == 0){
            System.out.println("Hiện chưa có tài khoản người dùng nào trong hệ thống! ");
        } else {
            for (int i = 0; i < accountUsers.size(); i++) {
                System.out.println(accountUsers.get(i));
                System.out.println(users.get(i));
            }
            System.out.println();
        }
    }

    public void menuProductManager() {
        try {
            int choice;
            do {
                System.out.println("Quản lí sản phẩm");
                System.out.println("1. Thêm thông tin sản phẩm mới");
                System.out.println("2. Sửa thông tin sản phẩm");
                System.out.println("3. Xóa thông sản phẩm");
                System.out.println("4. Hiển thị sản phẩm hiện có");
                System.out.println("5. Xóa hết thông tin sản phẩm");
                System.out.println("0. Quay lại Menu chính");
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        menuAddProduct();
                        break;
                    case 2:
                        menuEditProduct();
                        break;
                    case 3:
                        checkDeleteProduct();
                        break;
                    case 4:
                        checkDisplayProduct();
                        break;
                    case 5:
                        checkDeleteAllProduct();
                        break;
                }
            } while (choice != 0);
        } catch (InputMismatchException e) {
            System.out.println("Nhập sai dữ liệu, mời nhập lại!");
            scan.nextLine();
            menuProductManager();
        } catch (NullPointerException e) {
            System.out.println("Dữ liệu không tồn tại");
            scan.nextLine();
            menuProductManager();
        }
    }

    private void checkDeleteAllProduct() {
        if (productManager.checkFile()) {
            System.out.println("Hiện không có sản phẩm trong hệ thống");
        } else {
            productManager.deleteAll();
        }
    }

    private void checkDisplayProduct() {
        if (productManager.checkFile()) {
            System.out.println("Hiện không có sản phẩm trong hệ thống");
        } else {
            for (Product product : productManager.getProduct()) {
                product.display();
            }
        }
    }

    private void checkDeleteProduct() {
        if (productManager.checkFile()) {
            System.out.println("Hiện không có sản phẩm trong hệ thống");
        } else {
            productManager.deleteProduct();
        }
    }

    private void menuEditProduct() {
        System.out.println("Nhập vào id sản phẩm");
        int editId = scan.nextInt();
        System.out.println("1. Sửa tên sản phẩm");
        System.out.println("2. Sửa giá sản phẩm");
        int choiceToEdit = scan.nextInt();
        scan.nextLine();
        switch (choiceToEdit) {
            case 1:
                System.out.println("Nhập tên mới cho sản phẩm");
                String editName = scan.nextLine();
                productManager.editName(editId, editName);
                break;
            case 2:
                System.out.println("Nhập giá mới cho sản phẩm ");
                int editPrice = scan.nextInt();
                productManager.editPrice(editId, editPrice);
                break;
        }
    }

    private void menuAddProduct() {
        System.out.println("1. Thêm áo");
        System.out.println("2. Thêm quần");
        System.out.println("3. Thêm phụ kiện");
        int choiceToAdd = scan.nextInt();
        scan.nextLine();
        System.out.println("Nhập tên sản phẩm");
        String name = scan.nextLine();
        System.out.println("Nhập giá sản phẩm");
        int price = scan.nextInt();
        System.out.println("Nhập id sản phẩm");
        int id = scan.nextInt();
        if (productManager.checkId(id)) {
            productManager.addByChoice(id, name, price, choiceToAdd);
        } else {
            System.out.println("ID vừa nhập đã tồn tại, mời nhập ID khác!");
        }
    }
}
