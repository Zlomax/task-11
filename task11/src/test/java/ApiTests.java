import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiTests {
    @Test
    public void testGetRequest() {

        String baseUri = "https://reqres.in/";
        String postUser = "api/users";
        String testBody = "{\n" +
                "    \"name\": \"Peter\",\n" +
                "    \"job\":  \" qa automation\"\n" +
                "}";
        given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(testBody)
                .log().all()
                .post(postUser)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED);

        Response response = when().get("https://reqres.in/api/user/23");
        response.then().log().all();

        Response response1 = when().get("https://reqres.in/api/user/2");
        response1.then().statusCode(200).log().all();

        Response response2 = when().get("https://reqres.in/api/users?page=2");
        response2.then().statusCode(200).log().all();
    }
    }
