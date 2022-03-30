package BenimDeneme;

import base_url.GMIBankBasUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ben1 extends GMIBankBasUrl {


    @Test
    public void testben(){

        // https://www.gmibank.com/api/tp-customers/85694

        spec03.pathParams("bir","tp-customers","iki","85694");

        // https://www.gmibank.com/api
        Response response=given().spec(spec03).header("Authorization", "Bearer " + generateToken()).when().get("/{bir}/{iki}");

        response.prettyPrint();

      //  "firstName": "Winona",
        //    "lastName": "Abernathy",



        //"description": "saving update 1234",
        //    "balance": 110000,
        //"accountType": "SAVING",
        //"accountStatusType": "ACTIVE",
        //   "createDate": "2021-03-03T06:00:00Z",
        // "closedDate": "2021-03-03T06:00:00Z",
        // "employee": null,
        //  "accountlogs": null



       /* response.then().body("accounts[0].description", equalTo("saving update 1234")
                ,"accounts[0].balance",equalTo(110000)
                ,"accounts[0].accountType",equalTo("SAVING")
                ,"accounts[0].accountStatusType",equalTo("ACTIVE")
                ,"accounts[0].createDate",equalTo("2021-03-03T06:00:00Z")
                ,"accounts[0].closedDate",equalTo("2021-03-03T06:00:00Z")
                ,"accounts[0].employee",equalTo(null)
                ,"accounts[0].accountlogs",equalTo(null));

        */

        response.then().body("accounts[1].description", equalTo("checking update 1234")
                ,"accounts[1].balance",equalTo(0)
                ,"accounts[1].accountType",equalTo("CHECKING")
                ,"accounts[1].accountStatusType",equalTo("ACTIVE")
                ,"accounts[1].createDate",equalTo("2021-03-03T06:00:00Z")
                ,"accounts[1].closedDate",equalTo("2021-03-03T06:00:00Z")
                ,"accounts[1].employee",equalTo(null)
                ,"accounts[1].accountlogs",equalTo(null));
    }
}
