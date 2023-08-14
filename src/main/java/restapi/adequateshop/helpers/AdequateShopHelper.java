package restapi.adequateshop.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restapi.adequateshop.models.*;
import restapi.adequateshop.utils.ConfigManager;

import static io.restassured.RestAssured.*;
import static restapi.adequateshop.constants.Endpoints.*;

public class AdequateShopHelper {
    private static final String BASE_URI = ConfigManager.getInstance().getString("baseURI");
    final ObjectMapper objectMapper = new ObjectMapper();

    public AdequateShopHelper() {
        baseURI = BASE_URI;
    }

    public ResponseBody postUserRegistration(UserRegistration userRegistration) throws JsonProcessingException {
        String userRegistrationString = objectMapper.writeValueAsString(userRegistration);
        return given().log().all()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .body(userRegistrationString).log().all()
               .when()
                .post(REGISTRATION_ENDPOINT)
                .getBody().as(ResponseBody.class);
    }

    public ResponseBody postLogin(LoginInformation loginInformation) throws JsonProcessingException {
        String loginInformationString = objectMapper.writeValueAsString(loginInformation);
        return given().log().all()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .body(loginInformationString).log().all()
                .when()
                .post(LOGIN_ENDPOINT)
                .getBody().as(ResponseBody.class);
    }

    public Users getAllUsers(String token, String page) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .queryParam("page", page)
                .get(GET_ALL_USERS_ENDPOINT)
                .getBody().as(Users.class);
    }

    public Response getAllUsers_WithoutAuth(String page) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .queryParam("page", page)
                .get(GET_ALL_USERS_ENDPOINT);
    }

    public User getUserById(String token, String id) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .pathParam("id", id)
                .get(GET_USER_BY_ID_ENDPOINT)
                .getBody().as(User.class);
    }

}
