package dev.whyneet.ec_api.core.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Entity {
    private String id;
    private String name;
    private String password;
    private String email;
    private String address;
}
