package get_https_request.day13;

import POJOS.DummyData;
import POJOS.DummyPojo;
import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequestPojo01 extends DummyBaseUrl {
    /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                           Status code is 200
{
 "status": "success",
 "data": {
   "id": 1,
   "employee_name": "Tiger Nixon",
   "employee_salary": 320800,
   "employee_age": 61,
   "profile_image": ""
   },
 "message": "Successfully! Record has been fetched."
 }
 */


   @Test
     public void test(){


        //1) URL OLUSTUR
        spec02.pathParams("bir", "api", "iki", "v1", "uc", "employee", "dort", 1);

        //2) EXPECTED DATA
        DummyData dummyData = new DummyData(1, "Tiger Nixon", 320800, 61,"");
       System.out.println("dummyData = " + dummyData);

        DummyPojo expectedData = new DummyPojo("success", dummyData, "Successfully! Record has been fetched.");
        System.out.println("expextedData = " + expectedData);

        //3) REQUEST ve RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec02)
                .when()
                .get("/{bir}/{iki}/{uc}/{dort}");

        response.prettyPrint();

      //4) DOĞRULAMA
        DummyPojo actual=response.as(DummyPojo.class);
        Assert.assertEquals(expectedData.getStatus(),actual.getStatus());
        Assert.assertEquals(expectedData.getDummyData().getId(),actual.getDummyData().getId());
        Assert.assertEquals(expectedData.getDummyData().getEmployee_name(),actual.getDummyData().getEmployee_name());
        Assert.assertEquals(expectedData.getDummyData().getEmployee_salary(),actual.getDummyData().getEmployee_salary());
        Assert.assertEquals(expectedData.getDummyData().getEmployee_age(),actual.getDummyData().getEmployee_age());
        Assert.assertEquals(expectedData.getDummyData().getProfile_image(),actual.getDummyData().getProfile_image());
        Assert.assertEquals(expectedData.getMessage(),actual.getMessage());



 /*
        Serialization -->> Java yapsındaki datayı JSON formatına dönüsturur
        Gson gson = new Gson();
        String jsonFromJava = gson.toJson(actual);
        System.err.println("jsonFromJava = " + jsonFromJava);
        //jsonFromJava = {
        // "status":"success",
        // "data":{"id":1,
        // "employee_name":"Tiger Nixon",
        // "employee_salary":320800,
        // "employee_age":61,
        // "profile_image":""},
        // "message":"Successfully! Record has been fetched."}

     */
    }
}















