package restapi.adequateshop.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restapi.adequateshop.helpers.AdequateShopHelper;
import restapi.adequateshop.models.LoginInformation;
import restapi.adequateshop.models.ResponseBody;
import restapi.adequateshop.utils.ConfigManager;

import static org.testng.Assert.assertEquals;
import static restapi.adequateshop.constants.Constants.INVALID_LOGIN_MESSAGE;
import static restapi.adequateshop.constants.Constants.SUCCESS_MESSAGE;

public class LoginTest {
    private AdequateShopHelper adequateShopHelper;
    LoginInformation loginInformation = new LoginInformation();

    @BeforeClass
    public void init() {
        adequateShopHelper = new AdequateShopHelper();
    }


    @Test
    public void loginTest() throws JsonProcessingException {
        loginInformation.setEmail(ConfigManager.getInstance().getString("email"));
        loginInformation.setPassword(Integer.parseInt(ConfigManager.getInstance().getString("password")));

        ResponseBody responseBody = adequateShopHelper.login(loginInformation);

        assertEquals(0, responseBody.getCode());
        assertEquals(SUCCESS_MESSAGE, responseBody.getMessage());
        assertEquals(ConfigManager.getInstance().getString("name"), responseBody.getData().getName());
        assertEquals(ConfigManager.getInstance().getString("email"), responseBody.getData().getEmail());
    }

    @Test
    public void invalidLoginTest() throws JsonProcessingException {
        LoginInformation invalidLoginInformation = new LoginInformation();
        invalidLoginInformation.setEmail(ConfigManager.getInstance().getString("invalidEmail"));
        invalidLoginInformation.setPassword(Integer.parseInt(ConfigManager.getInstance().getString("password")));

        ResponseBody responseBody = adequateShopHelper.login(invalidLoginInformation);
        System.out.println(responseBody.toString());

        assertEquals(1, responseBody.getCode());
        assertEquals(INVALID_LOGIN_MESSAGE, responseBody.getMessage());
        assertEquals(null, responseBody.getData());
    }
}
