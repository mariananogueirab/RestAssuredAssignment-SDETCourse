package restapi.adequateshop.models;

import lombok.Data;

@Data
public class UserRegistration {
    private String name;
    private String email;
    private int password;

}
