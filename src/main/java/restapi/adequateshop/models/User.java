package restapi.adequateshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String email;
    @JsonProperty(value = "profilepicture")
    private String profilePicture;
    private String location;
    @JsonProperty(value = "createdat")
    private String createdAt;
}
