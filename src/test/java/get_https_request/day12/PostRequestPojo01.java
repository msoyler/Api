package get_https_request.day12;

import POJOS.JsonPlaceHolderPojo;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequestPojo01 extends JsonPlaceHolderBaseUrl {

/*
           https://jsonplaceholder.typicode.com/todos url 'ine bir request gönderildiğinde
           Request body{
         "userId": 21,
        "id": 201,
       "title": "Tidy your room",
       "completed": false
         }
     Status kodun 201, response body 'nin ise

      {
      "userId": 21,
     "id": 201,
    "title": "Tidy your room",
    "completed": false
      }
          */

    @Test
    public void test() {

         //1)URL OLUSTUR

        spec04.pathParam("first","todos");

        //2) EXPECTED DATA OLUSTUR
        JsonPlaceHolderPojo expected=new JsonPlaceHolderPojo(21,201,"Tidy your room",false);
        System.out.println("expected = " + expected);

        //3) REQUEST VE RESPONSE
        Response response=given().contentType(ContentType.JSON).spec(spec04).body(expected).when().post("/{first}");
        response.prettyPrint();

        //4) DOGRULAMA

        // a) DE-SERİALİZATİON

        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);

        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(expected.getId(),actualData.getId());
        Assert.assertEquals(expected.getTitle(),actualData.getTitle());
        Assert.assertEquals(expected.getUserId(),actualData.getUserId());
        Assert.assertEquals(expected.isCompleted(),actualData.isCompleted());





    }
}
