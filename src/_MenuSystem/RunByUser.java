package _MenuSystem;

import _Product.Product;
import _ProductManager.ProductManager;
import java.util.*;

public class RunByUser {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductManager productManager = new ProductManager();
    private final ArrayList<Product> cartItems = new ArrayList<>();

    public RunByUser() {
    }

    public void runByUser(){
        try {
            int choice;
            do {
                System.out.println("Quản lí mua-bán-tìm kiếm sản phẩm");
                System.out.println("1. Hiển thị sản phẩm ");
                System.out.println("2. Tìm kiếm sản phẩm ");
                System.out.println("3. Thêm sản phẩm vào giỏ hàng ");
                System.out.println("4. Xóa sản phẩm khỏi giỏ hàng ");
                System.out.println("5. Mua sản phẩm ");
                System.out.println("6. Hiển thị giỏ hàng ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        displayProduct();
                        break;
                    case 2:
                        searchProductByPrice();
                        break;
                    case 3:
                        addProductToCartById();
                        break;
                    case 4:
                        removeProductById();
                        break;
                    case 5:
                        buyProduct();
                        break;
                    case 6:
                        displayCart();
                        break;
                }
            } while (choice != 0);
        } catch (InputMismatchException e) {
            System.err.println("Bạn đã nhập sai");
            System.out.println("Mời nhập lại ");
        } catch (NullPointerException e) {
            System.out.println("Lỗi");
            System.out.println("Nhập lại ");
        }
    }
    // search product
    private void searchProductByPrice() {
        int choice1;
        do {
            System.out.println("Lựa chọn \uD83D\uDD0E");
            System.out.println("1. Tìm kiếm theo giá tăng dần ");
            System.out.println("2. Tìm kiếm theo giá giảm dần ");
            System.out.println("3. Tìm kiếm theo khoảng giá ");
            System.out.println("4. Quay lại menu chính ☝");
            System.out.println("5. Tìm kiếm sản phẩm theo từ khóa");
            choice1 = scanner.nextInt();
            switch (choice1) {
                case 1:
                    sortPriceUpper();
                    break;
                case 2:
                    sortPriceLower();
                    break;
                case 3:
                    System.out.println("Nhập vào giá trên ");
                    int highPrice = scanner.nextInt();
                    System.out.println("Nhập vào giá dưới ");
                    int lowPrice = scanner.nextInt();
                    searchByPrice(highPrice, lowPrice);
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    runByUser();
                    break;
            }
        } while (choice1 != 0);
    }

    private void searchByName() {
        System.out.println("Nhập tên sản phẩm muốn tìm");
        String searchName = scanner.nextLine();
        for (Product product : productManager.getProduct()) {
            if (product.getName().equals(searchName)){
                System.out.println(product);
            }
        }
    }

    private void searchByPrice(int highPrice, int lowPrice) {
        System.out.println("Các sản phẩm trong khoảng giá trên: \n");
        ArrayList<Product> listSearchProduct = new ArrayList<>();
        Product searchProduct ;
        for (Product product : productManager.getProduct()) {
            if (product.getPrice() >= lowPrice && product.getPrice() <= highPrice) {
                searchProduct = product;
                listSearchProduct.add(searchProduct);
            }
        }
        for (Product product : listSearchProduct) {
            product.display();
        }
    }

    private void sortPriceLower() {
        ArrayList<Product> products = productManager.getProduct();
        products.sort((o1, o2) -> o2.getPrice() - o1.getPrice());
        System.out.println("Sản phẩm theo giá giảm dần: \uD83D\uDC47\n");
        for (Product product : products) {
            product.display();
        }
    }

    private void sortPriceUpper() {
        ArrayList<Product> products = productManager.getProduct();
        products.sort((o1, o2) -> o1.getPrice() - o2.getPrice());
        System.out.println("Sản phẩm theo giá tăng dần: \uD83D\uDC46\n");
        for (Product product : products) {
            product.display();
        }
    }

    private void addProductToCartById() {
        System.out.println("Nhập vào id sản phẩm cần thêm");
        int id = scanner.nextInt();
        Product product = null;
        for (Product p : productManager.getProduct()) {
            if (p.getId() == id) {
                product = p;
            }
        }
        cartItems.add(product);
    }

    private void removeProductById() {
        System.out.println("Nhập id sản phẩm muốn loại bỏ ✂");
        int removeId = scanner.nextInt();
        Product removeProduct = null;
        for (Product product1 : cartItems) {
            if (product1.getId() == removeId) {
                removeProduct = product1;
            }
        }
        if (removeProduct != null) {
            removeProduct.display();
        }
        System.out.println("Giỏ hàng của bạn: \n");
        for (Product itiem : cartItems) {
            itiem.display();
        }
    }

    private void displayCart() {
        if (cartItems.size() == 0){
            System.out.println("Giỏ hàng đang trống !\n Pick something up please!\n");
        } else {
            System.out.println("Giỏ hàng của bạn hiện có: \n");
            for (Product item : cartItems) {
                item.display();
            }
        }
    }

    private void displayProduct() {
        if (productManager.checkFile()) {
            System.out.println("Hiện không có sản phẩm trong hệ thống!\n");
        } else {
            System.out.println("Sản phẩm hiện có trong cửa hàng: \n");
            for (Product product : productManager.getProduct()) {
                product.display();
            }
        }
    }
    // bills
    private void buyProduct(){
        int sumPrice = getTotalPrice();
        System.out.println("Số tiền phải thanh toán là: " + sumPrice +"\n");
        cartItems.clear();
        try {
            Thread.sleep(1500);
            System.out.println("Đang in bill..");
            Thread.sleep(1500);
            System.out.println("Đang trả tiền thừa lại cho khách..");
            Thread.sleep(1500);
            System.out.println("Thanh toán thành công!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int getTotalPrice() {
        int sumPrice = 0;
        if (cartItems.size() == 0) {
            System.out.println("Giỏ hàng đang trống !\n");
        } else {
            for (Product p : cartItems) {
                sumPrice += p.getPrice();
            }
        }
        return sumPrice;
    }

}
