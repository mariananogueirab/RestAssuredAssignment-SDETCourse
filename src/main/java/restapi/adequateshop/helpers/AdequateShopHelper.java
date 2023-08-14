package restapi.adequateshop.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restapi.adequateshop.models.ResponseBody;
import restapi.adequateshop.models.UserRegistration;
import restapi.adequateshop.utils.ConfigManager;

import static io.restassured.RestAssured.*;
import static restapi.adequateshop.constants.Endpoints.REGISTRATION_ENDPOINT;

public class AdequateShopHelper {
    private static final String BASE_URI = ConfigManager.getInstance().getString("baseURI");
    final ObjectMapper objectMapper = new ObjectMapper();

    public AdequateShopHelper() {
        baseURI = BASE_URI;
    }

    public ResponseBody postUserRegistration(UserRegistration userRegistration) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(userRegistration);
        return given().log().all()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .body(json).log().all()
               .when()
                .post(REGISTRATION_ENDPOINT)
                .getBody().as(ResponseBody.class);
    }



}
