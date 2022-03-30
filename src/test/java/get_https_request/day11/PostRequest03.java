package get_https_request.day11;

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

public class PostRequest03 extends JsonPlaceHolderBaseUrl {

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
   //BURDAKİ BİLGİLERİN HEM GÖNDERİLEN HEMDE DÖNEN CEVAP AYNI OLDUGU İCİN  "JSONOBJECT" KULLANIYORUZ.
    // Bu bilgileri verilen site adından bır testData class olustururuz
    //Sonra bu class verilen bilglieri bir methoda eklerız dinamik hala getırırız.
    //

    @Test
    public void test03(){
        
        //1) URL OLUSTUR
        //https://jsonplaceholder.typicode.com

        // /todos  buradaki adresin devamını asagıda dınamık hala getırırız.
        spec04.pathParam("bir","todos");
        
        
        //2) EXPECTED DATA
        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();// ilk olarak obje olusturduk
        //(Veriler aynı ondan JSONObject kullanırız)
        JSONObject expectedRequest=testObje.setUpPostData();// Burada dınamık hale getırdıgımız classtaki verilere baglama yaptık.Ordakı verıleri kullanabılmek icin
        System.out.println("expectedRequest = " + expectedRequest);

        
       //3)REQUEST VE RESPONSE

        Response response=given().spec(spec04).contentType(ContentType.JSON)
                                .body(expectedRequest.toString()).when().post("/{bir}");
        response.prettyPrint();


        //4) DOGRULAMA

        //1)MATCHERS CLASS
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("userId",equalTo(expectedRequest.get("userId"))
                      ,"title",equalTo(expectedRequest.get("title"))
                      ,"completed",equalTo(expectedRequest.get("completed"))
                      ,"id",equalTo(expectedRequest.get("id")));


        //2)JSON PATH
        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequest.get("id"),json.get("id"));
        Assert.assertEquals(expectedRequest.get("userId"),json.get("userId"));
        Assert.assertEquals(expectedRequest.get("statusCode"),json.get("statusCode"));
        Assert.assertEquals(expectedRequest.get("title"),json.get("title"));
        Assert.assertEquals(expectedRequest.get("completed"),json.get("completed"));


        //3)De-Serialization
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(expectedRequest.get("id"),actualDataMap.get("id"));
        Assert.assertEquals(expectedRequest.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.get("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedRequest.get("statusCode"),response.statusCode());




        
        
        
    }


}
