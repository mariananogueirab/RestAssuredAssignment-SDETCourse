package restapi.adequateshop.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restapi.adequateshop.helpers.AdequateShopHelper;
import restapi.adequateshop.models.LoginInformation;
import restapi.adequateshop.models.ResponseBody;
import restapi.adequateshop.utils.ConfigManager;

import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.testng.Assert.assertEquals;
import static restapi.adequateshop.constants.Constants.HTTP_VERB_NOT_ALLOWED;

public class UpdateUserTest {
    private AdequateShopHelper adequateShopHelper;
    LoginInformation loginInformation = new LoginInformation();

    @BeforeClass
    public void init() {
        adequateShopHelper = new AdequateShopHelper();

        loginInformation.setEmail(ConfigManager.getInstance().getString("email"));
        loginInformation.setPassword(Integer.parseInt(ConfigManager.getInstance().getString("password")));
    }

    @Test
    public void updateUserByIdTest() throws JsonProcessingException {
        ResponseBody responseBody = adequateShopHelper.login(loginInformation);
        String id = ConfigManager.getInstance().getString("userId");

        Response response = adequateShopHelper.updateUserById(responseBody.getData().getToken(), id);

        XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
        assertEquals(htmlPath.getString("html.head.title"), HTTP_VERB_NOT_ALLOWED);
    }
}
