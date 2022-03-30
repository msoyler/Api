package get_https_request;

import base_url.GMIBankBasUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest14 extends GMIBankBasUrl {



    @Test
    public void test14(){


           /*
            http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın

           "firstName": "Melva",
          "lastName": "Bernhard",
          "email": "chas.kuhlman@yahoo.com"
         "zipCode": "40207"
         "country" "name": "San Jose"
       "login": "delilah.metz"
           */

        spec03.pathParams("bir","tp-customers","iki","110472");

        Response response=given().spec(spec03).header("Authorization","Bearer " + generateToken())
                .when().get("/{bir}/{iki}");


          response.prettyPrint();


          //MATCHERS CLASS İLE YAPIN

        response.then().assertThat().body("firstName", equalTo("Melva"),
                "lastName",equalTo("Bernhard"),"email", equalTo("chas.kuhlman@yahoo.com"),
                "zipCode", equalTo("40207"),
                "country.name", equalTo("San Jose"),
                "user.login", equalTo("delilah.metz"));

      // JSON PATH İLE

        JsonPath json = response.jsonPath();
        Assert.assertEquals("Melva", json.getString("firstName"));
        Assert.assertEquals("Bernhard", json.getString("lastName"));
        Assert.assertEquals("chas.kuhlman@yahoo.com", json.getString("email"));
        Assert.assertEquals("40207", json.getString("zipCode"));
        Assert.assertEquals("San Jose", json.getString("country.name"));
        Assert.assertEquals("delilah.metz", json.getString("user.login"));


    }


}
