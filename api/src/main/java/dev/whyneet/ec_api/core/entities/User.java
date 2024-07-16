package dev.whyneet.ec_api.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends Entity {
    private String id;
    private String name;
    private String password;
    private String email;
    private String address;
}
