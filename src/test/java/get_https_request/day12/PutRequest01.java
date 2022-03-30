package get_https_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequest01 extends JsonPlaceHolderBaseUrl {


       /*
           https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body 'i Put olarak gönderdigimizde

       {
           "userId": 21,
           "title": "Wash the dishes",
           "completed": false
          }
            Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
        {
         "userId": 21,
         "title": "Wash the dishes",
        "completed": false,
          "id": 198
                }
         */

    @Test
    public void test01(){



        // 1) URL OLUSTUR
        //https://jsonplaceholder.typicode.com/todos/198
        spec04.pathParams("bir","todos","iki","198");

        //2) EXPECTED DATA
        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();// verilerimizi kayıt ettıgımız  classtan buraya cagırmak ıcın
                                                                       // obje olusturduk class ismiyle aynı
        JSONObject expectedRequest=testObje.setUpPutData(); //
        System.out.println("expecedRetquest = " + expectedRequest);

        //3) REQUEST VE RESPONSE OLUSTUR
        Response response=given().contentType(ContentType.JSON).spec(spec04)
                .body(expectedRequest.toString()).when().patch("/{bir}/{iki}");

        response.prettyPrint();

        //4) DOGRULAMA

        //1)MATCHERS CLASS

     response.then().assertThat().body("userId", equalTo(expectedRequest.get("userId")));
     response.then().assertThat().body("title", equalTo(expectedRequest.get("title")));
     response.then().assertThat().body("completed", equalTo(expectedRequest.get("completed")));



        //2)JSON PATH

        JsonPath json=response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedRequest.get("userId"),json.get("userId"));
        Assert.assertEquals(expectedRequest.get("title"),json.get("title"));
        Assert.assertEquals(expectedRequest.get("completed"),json.get("completed"));


        //3)De-Serialization
        HashMap<String,Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedRequest.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedRequest.get("completed"),actualData.get("completed"));


    }
}
