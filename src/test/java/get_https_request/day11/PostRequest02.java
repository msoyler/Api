package get_https_request.day11;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends DummyBaseUrl {

    /*
            http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
             {
          "name":"Ali Can",
          "salary":"2000",
          "age":"40",
        }
          gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,
      {
         "status": "success",
         "data": {
         "id":…
       },
        "message": "Successfully! Record has been added." olduğunu test edin
      }
       */

    // BURADA GÖNDERİLEN BİLGİLER İLE DÖNEN CEVAP FARKLİ OLDUGU İCİN "JSONOBJECT KULLANILMAZ.
    // öNCE DUMMYTESTDATA DİYE BİR CLASS ACIP BİLGİLERI ORDA DİNAMİK HALE GETİRİYORUZ
    @Test
    public void test02(){

        // 1) URL OLUSTUR
        http://dummy.restapiexample.com
         // /api/v1/create BU KSIM İCİN ASAGIDA TANIMLAMA YAPIYORUZ
         spec02.pathParams("bir","api","iki","v1","uc","create");

         //2) EXPECTED DATA OLUSTUR
        DummyTestData obje=new DummyTestData();// ilk obje olusturyoruz burada.
        // bu gonderdigimiz datalar icin HashMap olusturduk
        HashMap<String,Object> requestBodyMap=obje.setUpRequestBody();// VERİLERİMİZİ BURADA İLİŞKİLENDİRDİK KULLANMAK İCİN

        // bu gonderdigimiz datalardan dönen cevap icin  olusturduk.
        HashMap<String,Object> expectedDataMap=obje.setUpExpectedData();

        System.out.println("expectedDataMap = " + expectedDataMap);

        // 3) REQUEST VE RESPONSE
        Response response=given().contentType(ContentType.JSON).spec(spec02).body(requestBodyMap) //body kısmına gönderdıgımız verıyı eklıyoruz parantez icine.
                                 .when().post("/{bir}/{iki}/{uc}");
       // POST işleminde Map kullandigimizda toString'e gerek yok.ToString sadce jsonobjectte kullanılır

        response.prettyPrint();

         //4) DOGRULAMA
        // DÖNEN CEVAP İCİN DOGRULAMA

        //De-Serialization
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));

     //JSON PATH

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedDataMap.get("status"),json.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"),json.getString("message"));




    }

}
