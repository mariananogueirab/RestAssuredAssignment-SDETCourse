package restapi.adequateshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Users {
    private int page;
    private int per_page;
    @JsonProperty(value = "totalrecord")
    private int totalRecord;
    @JsonProperty(value = "total_pages")
    private int totalPages;
    private List<User> data;
}
