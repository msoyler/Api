package get_https_request;

import base_url.GMIBankBasUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest17 extends GMIBankBasUrl {


         /*
            http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın

           "firstName": "Della",
           "lastName": "Heaney",
           "email": "ricardo.larkin@yahoo.com",
           "mobilePhoneNumber": "123-456-7893",
    */

    @Test
    public void test17(){

        // 1) URL OLUSTURMA

        spec03.pathParams("bir","tp-customers","iki",114351);

        // 2) EXPECTED(BEKLENEN) DATA OLUSTUR

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstName","Della");
        expectedData.put("lastName","Heaney");
        expectedData.put("email","ricardo.larkin@yahoo.com");
        expectedData.put("mobilePhoneNumber","123-456-7893");

        System.out.println("Expected Data :" + expectedData);

        //3)  REQUEST ve RESPONSE

        // https://jsonplaceholder.typicode.com
        Response response= given().spec(spec03).header("Authorization","Bearer " +generateToken()).when().get("/{bir}/{iki}");

       // response.prettyPrint();


        Map<String,Object> actualData=response.as(HashMap.class); // De-Serialization

        System.out.println("ACTUALDATA:" + actualData);

        Assert.assertEquals(expectedData.get("firstName"),actualData.get("firstName"));
        Assert.assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        Assert.assertEquals(expectedData.get("email"),actualData.get("email"));
        Assert.assertEquals(expectedData.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));
    }


}
