package get_https_request;

import base_url.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest22 extends HerOkuAppBaseUrl {

               /*
         https://restful-booker.herokuapp.com/booking/41
       {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-02-01", //bu kisim ilk basta yapılır
               "checkout": "2022-02-11" //bu kisim ilk basta yapılır
          */
    @Test
    public void test22(){

        // 1) URL OLUSTUR
        spec05.pathParams("bir","booking","iki", 13);// 13 degısebılır  tekrar bak postmandan :)

        // 2) EXPECTED DATA OLUSTUR
        HerOkuAppTestData expectedObje = new HerOkuAppTestData();// Herokuapp dakı verılerı  buraya getırmek icin obje olusturuldu

        HashMap<String,Object> expectedTestDataMap=expectedObje.SetUpTestData(); //buraya getırılen verıler Map'e atadık tekrar.

        System.out.println("TEST DATA İCİNDEKİ EXPECTED DATA : "+expectedTestDataMap);

     // 3) REQUEST VE RESPONSE

        Response response=given().spec(spec05).when().get("/{bir}/{iki}");
        response.prettyPrint();

       // 4) DOGRULAMA (Dogrulamayı 3 adımda yapıyoruz.)

        // 1.) YOL De-Serialization ====// DATAYI JSONDAN --> JAVAYA  DONUSTURMEYE ===00

        HashMap<String,Object> actualData=response.as(HashMap.class);
        //JSON formatindaki datayı HashMap e donusturur
        System.out.println("ACTUAL DATA : "+actualData);

        Assert.assertEquals(expectedTestDataMap.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("otalprice"),actualData.get("otalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"),actualData.get("depositpaid"));

        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates"))
                .get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map) expectedTestDataMap.get("bookingdates"))
                .get("checkout"),((Map<?, ?>) actualData.get("bookingdates")).get("checkout"));

        // 2) YOL JSON PATH
        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedTestDataMap.get("firstname"),json.getString("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"),json.getString("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("otalprice"),json.getString("otalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"),json.getBoolean("depositpaid"));

        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin"),json.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkout"),json.getString("bookingdates.checkout"));

        // 3) Matchers Class ile
        response.then().assertThat().body("firstname", equalTo(expectedTestDataMap.get("firstname"))
                                            ,"lastname",equalTo(expectedTestDataMap.get("lastname"))
                                            ,"otalprice",equalTo(expectedTestDataMap.get("otalprice"))
                                            ,"depositpaid",equalTo(expectedTestDataMap.get("depositpaid"))
                                            ,"bookingdates[0]",equalTo(expectedTestDataMap.get(".checkin"))
                                            ,"bookingdates[1]",equalTo(expectedTestDataMap.get("checkout")));



    }
}
