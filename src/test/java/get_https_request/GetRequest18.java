package get_https_request;

import base_url.GMIBankBasUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest18 extends GMIBankBasUrl {

     /*
            http://www.gmibank.com/api/tp-customers/43703

            "firstName": "Alda",
            "lastName": "Monahan",
            "middleInitial": "Nichelle Hermann Kohler",
            "email": "com.github.javafaker.Name@7c011174@gmail.com",
            "mobilePhoneNumber": "909-162-8114",
            "city": "St Louis",
             "ssn": "108-53-6655"

          1) MATCHERS CLASS
           2) JSON PATH
          3) De-Serialization

            */

            @Test
       public void test18(){

                // 1) URL OLUSTURMA

                spec03.pathParams("bir","tp-customers","iki",43703);

                // 2) EXPECTED(BEKLENEN) DATA OLUSTUR

                Map<String,Object> expectedData=new HashMap<>();

                expectedData.put("firstName","Alda");
                expectedData.put("lastName","Monahan");
                expectedData.put("middleInitial","Nichelle Hermann Kohler");
                expectedData.put("email","com.github.javafaker.Name@7c011174@gmail.com");
                expectedData.put("mobilePhoneNumber","909-162-8114");
                expectedData.put("city","St Louis");
                expectedData.put("ssn","108-53-6655");

                System.out.println("Expected Data :" + expectedData);


                //3)  REQUEST ve RESPONSE

                //  http://www.gmibank.com/api/tp-customers/43703
                Response response= given().spec(spec03).header("Authorization","Bearer " +generateToken()).when().get("/{bir}/{iki}");

                Map<String,Object> actual1=response.as(HashMap.class); // De-Serialization jsonu javaya donusturme

                System.out.println("ACTUAL DATA :" + actual1);

                Assert.assertEquals(expectedData.get("firstName"),actual1.get("firstName"));
                Assert.assertEquals(expectedData.get("lastName"),actual1.get("lastName"));
                Assert.assertEquals(expectedData.get("middleInitial"),actual1.get("middleInitial"));
                Assert.assertEquals(expectedData.get("email"),actual1.get("email"));
                Assert.assertEquals(expectedData.get("mobilePhoneNumber"),actual1.get("mobilePhoneNumber"));
                Assert.assertEquals(expectedData.get("city"),actual1.get("city"));
                Assert.assertEquals(expectedData.get("ssn"),actual1.get("ssn"));



               // 4) MATCHERS CLASS
                response.then().assertThat().body("firstName", equalTo("Alda"),"lastName",equalTo("Monahan")
                                             ,"middleInitial",equalTo("Nichelle Hermann Kohler")
                                             ,"email",equalTo("com.github.javafaker.Name@7c011174@gmail.com")
                                             ,"mobilePhoneNumber",equalTo("909-162-8114")
                                             ,"city",equalTo("St Louis"),"ssn",equalTo("108-53-6655"));


                //  5) JSON PATH

                JsonPath json=response.jsonPath();

                Assert.assertEquals("Alda", json.getString("firstName"));
                Assert.assertEquals("Monahan", json.getString("lastName"));
                Assert.assertEquals("Nichelle Hermann Kohler", json.getString("middleInitial"));
                Assert.assertEquals("com.github.javafaker.Name@7c011174@gmail.com", json.getString("email"));
                Assert.assertEquals("909-162-8114", json.getString("mobilePhoneNumber"));
                Assert.assertEquals("St Louis", json.getString("city"));
                Assert.assertEquals("108-53-6655", json.getString("ssn"));




            }


}
