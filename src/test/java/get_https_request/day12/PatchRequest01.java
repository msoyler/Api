package get_https_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {

        /*
   https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde

     "title": "Batch44"

    }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"userId": 10,
"title": "Batch44"
"completed": true,
"id": 198
}
    */


    // PATCH yaparken sadece bir veri degiştiriyoruz
    //PUT ta ise bircok veri degistiriyoruz

    @Test
    public void test01() {

        // 1) URL OLUSTUR
        //https://jsonplaceholder.typicode.com/todos/198
        spec04.pathParams("bir", "todos", "iki", "198");

        //2) EXPECTED DATA
        JsonPlaceHolderTestData testData= new JsonPlaceHolderTestData();
        JSONObject  requestData = testData.setUpPAtchRequestData();
        System.out.println("requestData = " + requestData);

        JSONObject expectedData=testData.setUpPatchExpectedData();// dönen cevap icin olusturduk
        System.out.println("expectedData = " + expectedData);

        //3) REQUEST VE RESPONSE OLUSTUR

        Response response=given().contentType(ContentType.JSON).spec(spec04).body(requestData.toString()).when().patch("/{bir}/{iki}");
        response.prettyPrint();

        //4) DOGRULAMA

         // 1) JSonPath
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedData.get("userId"), json.get("userId"));
        Assert.assertEquals(expectedData.get("title"), json.get("title"));
        Assert.assertEquals(expectedData.get("completed"), json.get("completed"));
        Assert.assertEquals(expectedData.get("id"), json.get("id"));

        // 2) Matcher Class
        response.then().assertThat().statusCode(200).
                body("userId", equalTo(expectedData.get("userId")),
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")),
                        "id", equalTo(expectedData.get("id")));


    }

}
