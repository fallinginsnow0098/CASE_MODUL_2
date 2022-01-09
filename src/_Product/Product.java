package _Product;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int price;


    public Product() {
    }

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void display(){
        System.out.printf("%-15S%-15d", "mã sản phẩm: ", getId());
        System.out.printf("%-15S%-15s","tên sản phẩm: ", getName());
        System.out.printf("%-15S%-15d\n","giá sản phẩm: ", getPrice());
    }
}
