package restapi.adequateshop.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restapi.adequateshop.helpers.AdequateShopHelper;
import restapi.adequateshop.models.ResponseBody;
import restapi.adequateshop.models.UserRegistration;
import restapi.adequateshop.utils.ConfigManager;

import java.util.Random;

import static org.testng.Assert.assertEquals;
import static restapi.adequateshop.constants.Constants.EMAIL_ALREADY_EXISTS_MESSAGE;
import static restapi.adequateshop.constants.Constants.SUCCESS_MESSAGE;

public class RegistrationTest {
    private AdequateShopHelper adequateShopHelper;
    public Random gerador = new Random();

    UserRegistration userRegistration = new UserRegistration();

    @BeforeClass
    public void init() {
        adequateShopHelper = new AdequateShopHelper();
    }

    @BeforeMethod
    public void setUpUserToRegistration() {
        String id = Integer.toString(gerador.nextInt(99));
        String name = ConfigManager.getInstance().getString("name");
        String email = ConfigManager.getInstance().getString("email");
        int password = Integer.parseInt(ConfigManager.getInstance().getString("password"));
        userRegistration.setName(name);
        userRegistration.setEmail(id + email);
        userRegistration.setPassword(password);
    }

    @Test
    public void userRegistrationTest() throws JsonProcessingException {

        ResponseBody responseBody = adequateShopHelper.postUserRegistration(userRegistration);
        System.out.println(responseBody.toString());

        assertEquals(0, responseBody.getCode());
        assertEquals(SUCCESS_MESSAGE, responseBody.getMessage());
        assertEquals(userRegistration.getName(), responseBody.getData().getName());
        assertEquals(userRegistration.getEmail(), responseBody.getData().getEmail());
    }

    @Test
    public void userRegistration_AlreadyExistsTest() throws JsonProcessingException {
        adequateShopHelper.postUserRegistration(userRegistration);

        ResponseBody responseBody = adequateShopHelper.postUserRegistration(userRegistration);
        System.out.println(responseBody.toString());

        assertEquals(1, responseBody.getCode());
        assertEquals(EMAIL_ALREADY_EXISTS_MESSAGE, responseBody.getMessage());
        assertEquals(null, responseBody.getData());
    }
}
