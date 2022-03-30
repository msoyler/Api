package get_https_request;

import base_url.GMIBankBasUrl;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends JsonPlaceHolderBaseUrl {

       /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */

    @Test
    public void test16(){

        // 1) URL OLUSTURMA

       spec04.pathParams("bir","todos","iki","7");

        // 2) EXPECTED(BEKLENEN) DATA OLUSTUR

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed",false);
        System.out.println("Expected Data :" + expectedData);


        //3)  REQUEST ve RESPONSE

        // https://jsonplaceholder.typicode.com
       Response response= given().spec(spec04).when().get("/{bir}/{iki}");
       //"/{bir}/{iki}"-->adrese bunu ekle --</todos/7

      // response.prettyPrint();

        // DATAYI JSONDAN --> JAVAYA  DONUSTURMEYE = De-Serialization denır // yani formata donusturmek
        // DATAYI  JAVADAN --> JSON'A DONUSTURMEYE = Serialization
        // Expected datalarımızla karsılastırdıgımız yada dogrulama yapacagımız Actual datalarımızın aynı formatta olması lazım.
        // Biz bu aralarındaki degistirme yada formatını farklı bir formata donuşturmeye  denir.

        Map<String,Object> actualData=response.as(HashMap.class); // De-Serialization

        System.out.println("ACTUAL DATA :" + actualData);

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }


}
