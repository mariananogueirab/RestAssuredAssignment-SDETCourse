package restapi.adequateshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

@Data
public class AccountData {
    @JsonProperty(value = "Id")
    private int id;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Email")
    private String email;
    @JsonProperty(value = "Token")
    private String token;
}
