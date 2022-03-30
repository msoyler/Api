package BenimDeneme;

import base_url.GMIBankBasUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ben2 extends GMIBankBasUrl {

    //http://www.gmibank.com/api/tp-customers/42665
    //    "firstName": "US11",
    //    "lastName": "Team13",
    //    "middleInitial": "m",
    //    "email": "bg@gmail.com",
    //    "mobilePhoneNumber": "123-322-1234",
    //    "phoneNumber": "123-123-4567",
    //    "zipCode": "NewCity",
    //    "address": "sdf",

    @Test
    public void testBen2(){

        // 1) URL OLUSTURMA

        spec03.pathParams("bir","tp-customers","iki","42665");

        // 2) EXPECTED(BEKLENEN) DATA OLUSTUR

        Map<String,Object> expected=new HashMap<>();
        expected.put("firstName","US11");
        expected.put("lastName","Team13");
        expected.put("middleInitial","m");
        expected.put("email","bg@gmail.com");
        expected.put("mobilePhoneNumber", "123-322-1234");
        expected.put( "phoneNumber", "123-123-4567");
        expected.put("zipCode", "NewCity");
        expected.put("address", "sdf");

        System.out.println("EXPECTED :" + expected);

        //3)  REQUEST ve RESPONSE

        //http://www.gmibank.com/api
        Response response=given().spec(spec03).header("Authorization","Bearer " +generateToken()).when().get("/{bir}/{iki}");
                                                    //tp-customers/42665 adresin geri kalan kısmı bu sekilde yazildi.

     //   response.prettyPrint();// BUTUN TABLODAKİ VERİLERİ GORMEK İCİN

        // 4. YOL De-Serialization
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("ACTUAL :" + actualData);

        Assert.assertEquals(expected.get("firstName"),actualData.get("firstName"));
        Assert.assertEquals(expected.get("lastName"),actualData.get("lastName"));
        Assert.assertEquals(expected.get("middleInitial"),actualData.get("middleInitial"));
        Assert.assertEquals(expected.get("email"),actualData.get("email"));
        Assert.assertEquals(expected.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));
        Assert.assertEquals(expected.get("phoneNumber"),actualData.get("phoneNumber"));
        Assert.assertEquals(expected.get("zipCode"),actualData.get("zipCode"));
        Assert.assertEquals(expected.get("address"),actualData.get("address"));

        //  5) MATCHERS CLASS

        response.then().assertThat().body("firstName", equalTo("US11"),"lastName",equalTo("Team13")
                                                            ,"middleInitial",equalTo("m"),"email",equalTo("bg@gmail.com")
                                                            ,"mobilePhoneNumber",equalTo("123-322-1234")
                                                            ,"phoneNumber",equalTo("123-123-4567")
                                                            ,"zipCode",equalTo("NewCity"),"address",equalTo("sdf"));



                      //  6) JSON PATH
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals("US11",jsonPath.getString("firstName"));
        Assert.assertEquals("Team13",jsonPath.getString("lastName"));
        Assert.assertEquals("m",jsonPath.getString("middleInitial"));
        Assert.assertEquals("bg@gmail.com",jsonPath.getString("email"));
        Assert.assertEquals("123-322-1234",jsonPath.getString("mobilePhoneNumber"));
        Assert.assertEquals("NewCity",jsonPath.getString("zipCode"));
        Assert.assertEquals("sdf",jsonPath.getString("address"));
        Assert.assertEquals("123-123-4567",jsonPath.getString("phoneNumber"));







    }
}
