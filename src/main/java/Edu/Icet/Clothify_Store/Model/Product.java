package Edu.Icet.Clothify_Store.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private int id;
    private String name;
    private String size;
    private String cmb;
    private int price;
    private int qty;

    public Product(String name, String size, String cmb, int price, int qty) {
        this.name = name;
        this.size = size;
        this.cmb = cmb;
        this.price = price;
        this.qty = qty;
    }
}
