package restapi.adequateshop.models;

import lombok.Data;

@Data
public class ResponseBody {
    private int code;
    private String message;
    private AccountData data;
}
