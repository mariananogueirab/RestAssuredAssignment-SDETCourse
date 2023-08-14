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
        System.out.println(users);

        assertEquals(page, users.getPage());
        assertEquals(users.getPer_page(), users.getData().size());
    }

    @Test
    public void getAllUsers_WithoutAuthTest() {
        String page = ConfigManager.getInstance().getString("page");

        Response response = adequateShopHelper.getAllUsers_WithoutAuth(page);
        assertEquals(MISSING_TOKEN, response.asString());
    }

    @Test
    public void getUserByIdTest() throws JsonProcessingException {
        ResponseBody responseBody = adequateShopHelper.postLogin(loginInformation);
        String id = ConfigManager.getInstance().getString("userId");

        User user = adequateShopHelper.getUserById(responseBody.getData().getToken(), id);
        assertEquals(id, Integer.toString(user.getId()));
    }

}
