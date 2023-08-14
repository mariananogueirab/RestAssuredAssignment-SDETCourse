package restapi.adequateshop.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restapi.adequateshop.helpers.AdequateShopHelper;
import restapi.adequateshop.models.*;
import restapi.adequateshop.utils.ConfigManager;
import static org.testng.Assert.assertEquals;
import static restapi.adequateshop.constants.Constants.MISSING_TOKEN;


import java.util.Random;

public class GetUsersTest {
    private AdequateShopHelper adequateShopHelper;
    public Random gerador = new Random();
    UserRegistration userRegistration = new UserRegistration();
    LoginInformation loginInformation = new LoginInformation();

    @BeforeClass
    public void init() {
        adequateShopHelper = new AdequateShopHelper();

        loginInformation.setEmail(ConfigManager.getInstance().getString("email"));
        loginInformation.setPassword(Integer.parseInt(ConfigManager.getInstance().getString("password")));
    }

    @Test
    public void getAllUsersTest() throws JsonProcessingException {
        ResponseBody responseBody = adequateShopHelper.postLogin(loginInformation);
        int page = 1;

        Users users = adequateShopHelper.getAllUsers(responseBody.getData().getToken(), Integer.toString(page));

        assertEquals(page, users.getPage());
        assertEquals(users.getPer_page(), users.getData().size());
    }

    @Test
    public void getAllUsers_WithoutAuthTest() {
        int page = 1;

        Response response = adequateShopHelper.getAllUsers_WithoutAuth(Integer.toString(page));
        assertEquals(MISSING_TOKEN, response.asString());
    }

    @Test
    public void getUserByIdTest() throws JsonProcessingException {
        ResponseBody responseBody = adequateShopHelper.postLogin(loginInformation);
        int id = 258817;

        User user = adequateShopHelper.getUserById(responseBody.getData().getToken(), Integer.toString(id));

        assertEquals(id, user.getId());
    }

}
