import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APITest {
    
    private static final String BASE_URL = "https://your-api-endpoint.com"; // Replace with actual API endpoint

    @Test
    public void validateCourseTitleContainsMeaningfulData() {
        RestAssured.baseURI = BASE_URL;
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/courses")
                .then()
                .extract().response();
        
        JsonPath jsonPath = response.jsonPath();
        List<String> apiCourses = jsonPath.getList("api.courseTitle");
        List<String> mobileCourses = jsonPath.getList("mobile.courseTitle");
        
        for (String title : apiCourses) {
            Assert.assertNotNull(title, "Course title should not be null");
            Assert.assertFalse(title.trim().isEmpty(), "Course title should contain meaningful data");
        }
        
        for (String title : mobileCourses) {
            Assert.assertNotNull(title, "Course title should not be null");
            Assert.assertFalse(title.trim().isEmpty(), "Course title should contain meaningful data");
        }
    }

    @Test
    public void validateApiAndMobileArraysAreNotEmpty() {
        RestAssured.baseURI = BASE_URL;
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/courses")
                .then()
                .extract().response();
        
        JsonPath jsonPath = response.jsonPath();
        List<?> apiCourses = jsonPath.getList("api");
        List<?> mobileCourses = jsonPath.getList("mobile");
        
        Assert.assertFalse(apiCourses.isEmpty(), "API courses array should not be empty");
        Assert.assertFalse(mobileCourses.isEmpty(), "Mobile courses array should not be empty");
    }
}
