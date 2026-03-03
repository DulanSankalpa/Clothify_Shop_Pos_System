package Edu.Icet.Clothify_Store.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {
    private int id;
    private String name;
    private String number;
    private String email;
    private String address;

    public Supplier(String name, String number, String email, String address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }
}
