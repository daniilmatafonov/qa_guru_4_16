package tests;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import model.UserModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class DemoWebShopTest extends BaseTest {

    final Faker faker = new Faker();
    final Gson gson = new Gson();

    @Test
    public void subscribeToNews() {
        final String emailAddress = faker.internet().emailAddress();
        UserModel userModel = new UserModel(emailAddress);
        final String userSubscribeJson = gson.toJson(userModel);
        given().spec(requestSpecification)
                .when().body(userSubscribeJson)
                .post("/subscribenewsletter")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("Success", equalTo(Boolean.TRUE)).body("Result", equalTo("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }
}
