package restapi.adequateshop.models;

import lombok.Data;

@Data
public class LoginInformation {
    private String email;
    private int password;
}
