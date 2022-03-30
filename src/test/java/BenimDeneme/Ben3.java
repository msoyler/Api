package BenimDeneme;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.RectangularShape;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ben3 extends JsonPlaceHolderBaseUrl {


    //https://jsonplaceholder.typicode.com/todos/26
    //status code:200
    //"userId": 2,
    //"title": "aliquam aut quasi",
    //"completed": true

    // header bolumleri
    //Cache-Control degeri = max-age=43200
    //Pragma degeri = no-cache
    // Age degeri = 17178

    @Test
    public void test3(){

          //https://jsonplaceholder.typicode.com/todos/26

            // 1) URL OLUSTUR

      spec04.pathParams("bir","todos","iki",26);// url yı dinamık yaptık


          // 2) EXPECTED DATA OLUSTUR
        HashMap<String,Object> expectedData= new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("userId",2);
        expectedData.put("title","aliquam aut quasi");
        expectedData.put("completed",true);
        expectedData.put("Cache-Control","max-age=43200");
        expectedData.put("Pragma","no-cache");
        expectedData.put("Expires","-1");
        System.out.println("EXPECTED DATA : " +expectedData);

        // 3) REQUEST VE RESPONSE DATA OLUSTUR

        //https://jsonplaceholder.typicode.com // aşagidaki kodtan önce url bu şekilde
        Response response=given().spec(spec04).when().get("/{bir}/{iki}");

       // response.prettyPrint(); veriyi yazdırma


         // 4) DOGRULAMA

         // a.) MATCHERS CLASS
         response.then().assertThat().statusCode((Integer)expectedData.get("statusCode"))
                 .headers("Cache-Control",equalTo(expectedData.get("Cache-Control"))
                 ,"Pragma",equalTo(expectedData.get("Pragma")),"Expires",equalTo(expectedData.get("Expires")))
                        .body("userId",equalTo(expectedData.get("userId"))
                                         ,"title",equalTo(expectedData.get("title"))
                                         ,"completed",equalTo(expectedData.get("completed")));



         // b.) JSON PATH
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));

        //JSondan olmayan kısım icin dogrulamada benzer sekılde yapılır
        Assert.assertEquals(expectedData.get("statusCode"), response.statusCode());
        Assert.assertEquals(expectedData.get("Cache-Control"),response.getHeader("Cache-Control"));
        Assert.assertEquals(expectedData.get("Pragma"),response.getHeader("Pragma"));
        Assert.assertEquals(expectedData.get("Expires"),response.getHeader("Expires"));



         // c.) YOL DE-SERİALİZATİON
       // Bu kisimda sadece body deki veriler kullanılır.

        HashMap<String,Object> actualData=response.as(HashMap.class);
        System.out.println("ACTUAL DATA :" +actualData);
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));







    }
}
