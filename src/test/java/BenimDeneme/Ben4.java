package BenimDeneme;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ben4 extends JsonPlaceHolderBaseUrl {
                   // burada extends yaptık orda dinamik hale getirdigimiz url kullanmak icin

          /*
            https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
           {
          "userId": 55,
          "title": "Tidy your room",
          "completed": false
           "id": 55
         }
      Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
      {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false,
        "id": …
       }

           */
     //BU ARADA GİDEN DATAYLA DÖNEN DATA AYNI OLDUGU ICIN "JSONOBJECT KULLANABILIRIZ"
     // burdaki dataları dogrulama yapalım.
    // ilk olarak test-data olusturuyoruz.Gönderilecek verileri oraya kaydediyoruz dinamik hala getırmek icn



            @Test
    public void testben4(){


                    //1) URL OLUSTUR
                // https://jsonplaceholder.typicode.com/todos
                // burada JsonPlaceHolderTestData tanımlanan url den eksik olan kısmı tanımlıyoruz
                spec04.pathParam("bir","todos");

                //2) EXPECTED DATA

                // obje olusturdk TEST_DATADAKİ VERİLERİ KULLANMAK İCİN
                JsonPlaceHolderTestData jsonVeriler=new JsonPlaceHolderTestData();
                //(Veriler aynı ondan JSONObject kullanırız)
                // Buarada olusturdugumuz objeyi setUpdaki veriye baglantı yapıyoruz .
             JSONObject dataVeriler=jsonVeriler.gidenVerilersetUp();
                System.out.println("dataVeriler = " + dataVeriler);

             //3)REQUEST VE RESPONSE
                Response response=given().spec(spec04).contentType(ContentType.JSON).body(dataVeriler.toString()).when().post("/{bir}");
                 response.prettyPrint();

                //4) DOGRULAMA

                //1)MATCHERS CLASS
                response.then().assertThat().statusCode(201);
                response.then().assertThat().body("userId",equalTo(dataVeriler.get("userId"))
                                                 ,"title",equalTo(dataVeriler.get("title"))
                                                 ,"completed",equalTo(dataVeriler.get("completed"))
                                                 ,"id",equalTo(dataVeriler.get("id")));


               //2)JSON PATH
                JsonPath jsonPath=response.jsonPath(); //buarada responsa bagladık verılerı kullanmak ıcın

                Assert.assertEquals(dataVeriler.get("userId"),jsonPath.get("userId"));
                Assert.assertEquals(dataVeriler.get("title"),jsonPath.get("title"));
                Assert.assertEquals(dataVeriler.get("completed"),jsonPath.get("completed"));
                Assert.assertEquals(dataVeriler.get("id"),jsonPath.get("id"));



             //3)De-Serialization
                //jsondan javaya donusturme
               HashMap<String,Object> actualObject=response.as(HashMap.class);
                Assert.assertEquals(dataVeriler.get("userId"),actualObject.get("userId"));
                Assert.assertEquals(dataVeriler.get("title"),actualObject.get("title"));
                Assert.assertEquals(dataVeriler.get("completed"),actualObject.get("completed"));
                Assert.assertEquals(dataVeriler.get("id"),actualObject.get("id"));




            }
}
