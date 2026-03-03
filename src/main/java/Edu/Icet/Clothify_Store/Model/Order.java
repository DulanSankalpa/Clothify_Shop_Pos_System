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
}
