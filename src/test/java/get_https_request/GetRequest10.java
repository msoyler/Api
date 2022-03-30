package get_https_request;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest10 extends DummyBaseUrl {

     /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde,
    status kodun 200,
    gelen body de,
    5. çalışanın isminin "Airi Satou" olduğunu ,
    6. çalışanın maaşının "372000" olduğunu ,
    Toplam 24 tane çalışan olduğunu,
    "Rhona Davidson" ın employee lerden biri olduğunu
    "21", "23", "61" yaşlarında employeeler olduğunu test edin
    JSONPATH KULLARAK
     */

    @Test
    public void test10(){

        spec02.pathParams("birinci","api"
                ,"ikinci","v1"
                ,"ucuncu","employees");

        //https://dummy.restapiexample.com

        Response response=given().spec(spec02).when().get("/{birinci}/{ikinci}/{ucuncu}");
        //"/{birinci}/{ikinci}/{ucuncu}"-->/api/v1/employee/12 demek

         response.prettyPrint();

         // JSON PATH OLARAK YAPILACAK

        JsonPath json=response.jsonPath();

        //status kodun 200 mu
        Assert.assertEquals(200,response.statusCode());

        // 5. çalışanın isminin "Airi Satou" olduğunu
        Assert.assertEquals("Airi Satou",json.getString("data[4].employee_name"));

        //6. çalışanın maaşının "372000" olduğunu
        Assert.assertEquals(372000,json.getInt("data[5].employee_salary"));

        //Toplam 24 tane çalışan olduğunu
        Assert.assertEquals(24,json.getList("data.id").size());

        //"Rhona Davidson" ın employee lerden biri olduğunu
        Assert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));

        //"21", "23", "61" yaşlarında employeeler olduğunu

        // 1.YOL
        List<Integer> list= new ArrayList<>();
        list.add(21);
        list.add(23);
        list.add(61);
        Assert.assertTrue(json.getList("data.employee_age").containsAll(list));

        //2.YOL
        List<Integer> ages = Arrays.asList(21,23,61);
        Assert.assertTrue(json.getList("data.employee_age").containsAll(ages));






    }


}
