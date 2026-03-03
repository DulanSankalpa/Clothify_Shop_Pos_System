package Edu.Icet.Clothify_Store.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SObject {
    private int id;
    private String username;
    private String email;
    private String pw;
    private String cmd;

    public SObject(String username, String email, String pw, String cmd) {
        this.username = username;
        this.email = email;
        this.pw = pw;
        this.cmd = cmd;
    }
}
