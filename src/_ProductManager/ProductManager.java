package _ProductManager;

import _Data.Accessories.DataOfAccessories;
import _Data.Bottom.DataOfBottom;
import _Data.Tops.DataOfTops;
import _Product.Accessories;
import _Product.Bottom;
import _Product.Product;
import _Product.Tops;
import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class ProductManager {

    public Scanner scanner = new Scanner(System.in);
    private ArrayList<Product> products;
    private final IOFile<Product> ioFile = new IOFile<>();
    private final String PATH_PRODUCT = "src/_File/ProductFile/product";
    private final String PATH_TOPS = "src/_File/ProductFile/tops";
    private final String PATH_BOTTOM = "src/_File/ProductFile/bottoms";
    private final String PATH_ACCESSORIES = "src/_File/ProductFile/accessories";
    // đọc ghi file
    public void writeToFile(ArrayList<Product> productData){
        ioFile.writerFileData(productData, PATH_PRODUCT);
    }
    public ArrayList<Product> readFromFile(){
        if (new File(PATH_PRODUCT).length() == 0){
            products = new ArrayList<>();
        } else {
            products = ioFile.readFileData(PATH_PRODUCT);
        }
        return products;
    }
    public ArrayList<Product> getProduct(){
        return readFromFile();
    }
    // thêm sản phẩm
    public void addByChoice(int id, String name, int price, int choice){
        if (choice == 1){
            addTops(id, name, price);
        } else if (choice == 2){
            addBottom(id, name, price);
        } else if (choice == 3){
            addAccessories(id, name, price);
        } else {
            ArrayList<Product> list = readFromFile();
            Product product = new Product(id, name, price);
            list.add(product);
            writeToFile(list);
        }
    }
    public void addTops(int id, String name, int price){
        ArrayList<Product> list = readFromFile();
        ArrayList<Tops> tops = new ArrayList<>();
        Product product = new Product(id, name, price);
        list.add(product);
        tops.add(new Tops(id, name, price));
        writeToFile(list);
        ((new IOFile<Tops>())).writerFileData(tops, PATH_TOPS);
    }
    public void addBottom(int id, String name, int price){
        ArrayList<Product> list = readFromFile();
        ArrayList<Bottom> bottoms = new ArrayList<>();
        Product product = new Product(id, name, price);
        list.add(product);
        bottoms.add(new Bottom(id, name, price));
        writeToFile(list);
        ((new IOFile<Bottom>())).writerFileData(bottoms, PATH_BOTTOM);
    }
    public void addAccessories(int id, String name, int price){
        ArrayList<Product> list = readFromFile();
        ArrayList<Accessories> accessories = new ArrayList<>();
        Product product = new Product(id, name, price);
        list.add(product);
        accessories.add(new Accessories(id, name, price));
        writeToFile(list);
        ((new IOFile<Accessories>())).writerFileData(accessories, PATH_ACCESSORIES);
    }
    // xóa sản phẩm
    public void deleteProduct() {
        System.out.println("Nhập id sản phẩm muốn xóa");
        int id = scanner.nextInt();
        boolean check = true;
        ArrayList<Product> list = readFromFile();
        for (Product product : list) {
            if (product.getId() == id){
                list.removeIf(Product -> Product.getId() == id);
                writeToFile(list);
                deleteTops(id);
                deleteBottom(id);
                deleteAccessories(id);
                check = false;
                break;
            }
        }
        if (check){
            System.err.println("Lỗi nhập dữ liệu");
            deleteProduct();
        }
    }
    public void deleteTops(int id){
        ArrayList<Tops> tops = new DataOfTops().topsList();
        for (int i = 0; i < tops.size(); i++) {
            if (tops.get(i).getId() == id){
                tops.remove(tops.get(i));
                (new IOFile<Tops>()).writerFileData(tops, PATH_TOPS);
            }
        }
    }
    public void deleteBottom(int id){
        ArrayList<Bottom> bottoms = new DataOfBottom().bottomList();
        for (int i = 0; i < bottoms.size(); i++) {
            if (bottoms.get(i).getId() == id){
                bottoms.remove(bottoms.get(i));
                (new IOFile<Bottom>()).writerFileData(bottoms, PATH_BOTTOM);
            }
        }
    }
    public void deleteAccessories(int id){
        ArrayList<Accessories> accessories = new DataOfAccessories().accessoriesList();
        for (int i = 0; i < accessories.size(); i++) {
            if (accessories.get(i).getId() == id){
                accessories.remove(accessories.get(i));
                (new IOFile<Accessories>()).writerFileData(accessories, PATH_ACCESSORIES);
            }
        }
    }
    public void deleteAll(){
        ArrayList<Product> products = readFromFile();
        ArrayList<Tops> tops = new DataOfTops().topsList();
        ArrayList<Bottom> bottoms = new DataOfBottom().bottomList();
        ArrayList<Accessories> accessories = new DataOfAccessories().accessoriesList();
        products.clear();
        tops.clear();
        bottoms.clear();
        accessories.clear();
        writeToFile(products);
        (new IOFile<Tops>()).writerFileData(tops, PATH_TOPS);
        (new IOFile<Bottom>()).writerFileData(bottoms, PATH_BOTTOM);
        (new IOFile<Accessories>()).writerFileData(accessories, PATH_ACCESSORIES);
    }

    // sửa sản phẩm
    public void editName(int id, String editName){
        ArrayList<Product> products = readFromFile();
        ArrayList<Tops> tops = new DataOfTops().topsList();
        ArrayList<Bottom> bottoms = new DataOfBottom().bottomList();
        ArrayList<Accessories> accessories = new DataOfAccessories().accessoriesList();
        for (Product product : products) {
            if (product.getId() == id){
                product.setName(editName);
            }
        }
        for (Tops top : tops) {
            if (top.getId() == id){
                top.setName(editName);
            }
        }
        for (Bottom bottom : bottoms) {
            if (bottom.getId() == id){
                bottom.setName(editName);
            }
        }
        for (Accessories accessories1 : accessories) {
            if (accessories1.getId() == id){
                accessories1.setName(editName);
            }
        }
        writeToFile(products);
        ((new IOFile<Tops>())).writerFileData(tops, PATH_TOPS);
        ((new IOFile<Bottom>())).writerFileData(bottoms, PATH_BOTTOM);
        ((new IOFile<Accessories>())).writerFileData(accessories, PATH_ACCESSORIES);
    }
    public void editPrice(int id, int editPrice){
        ArrayList<Product> products = readFromFile();
        ArrayList<Tops> tops = new DataOfTops().topsList();
        ArrayList<Bottom> bottoms = new DataOfBottom().bottomList();
        ArrayList<Accessories> accessories = new DataOfAccessories().accessoriesList();

        for (Product product : products) {
            if (product.getId() == id){
                product.setPrice(editPrice);
            }
        }
        for (Tops top : tops) {
            if (top.getId() == id){
                top.setPrice(editPrice);
            }
        }
        for (Bottom bottom : bottoms) {
            if (bottom.getId() == id){
                bottom.setPrice(editPrice);
            }
        }
        for (Accessories accessories1 : accessories) {
            if (accessories1.getId() == id){
                accessories1.setPrice(editPrice);
            }
        }
        writeToFile(products);
        ((new IOFile<Tops>())).writerFileData(tops, PATH_TOPS);
        ((new IOFile<Bottom>())).writerFileData(bottoms, PATH_BOTTOM);
        ((new IOFile<Accessories>())).writerFileData(accessories, PATH_ACCESSORIES);
    }

    // check file null - check id trùng
    public boolean checkFile(){
        if (readFromFile() == null){
            return true;
        }
        return false;
    }
    public boolean checkId(int id){
        ArrayList<Product> products = readFromFile();
        for (Product product : products) {
            if (product.getId() == id){
                return false;
            }
        }
        return true;
    }
}
