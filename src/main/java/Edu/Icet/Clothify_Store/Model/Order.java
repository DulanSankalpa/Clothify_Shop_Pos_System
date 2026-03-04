package Edu.Icet.Clothify_Store.Model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;
    private String name;
    private String size;
    private int qty;
    private double price;
    private double totalPrice;
    private int counterid;
    private String datetime;

    public Order(String name, String size, int qty, double price, int counterid) {
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.totalPrice = qty * price; // auto calculate
        this.counterid = counterid;
    }


    public Order(String name, String size, int qty, double price, double totalPrice, int counterid) {
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.totalPrice = totalPrice;
        this.counterid = counterid;
    }



    public Order(String name, String size, int qty, int counterid) {
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.counterid = counterid;
    }

    public Order(int id, String name, String size, int qty, double price, String datetime) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.datetime = datetime;
    }

    public Order(int id, String name, String size, int qty, double price, double totalPrice, int counterid) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.totalPrice = totalPrice;
        this.counterid = counterid;
    }

    public Order(int id, String name, String size, int qty, double price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.price = price;
    }



    public Order(int id, String productname, String size, int qty, String orderDateTime) {


    }

    public Order(int id, String name, String size, int qty, int counterid, double totalPrice, String datetime) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.counterid = counterid;
        this.totalPrice = totalPrice;
        this.datetime = datetime;
    }

    public Order(int id, String name, String size, double totalPrice, int counterid, String datetime) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.totalPrice = totalPrice;
        this.counterid = counterid;
        this.datetime = datetime;
    }
}
