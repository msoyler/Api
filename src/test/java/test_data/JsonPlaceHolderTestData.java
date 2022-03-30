package test_data;

import org.codehaus.groovy.transform.sc.transformers.RangeExpressionTransformer;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {


    public Map<String,Object> setUpTestData(){

           HashMap<String,Object> expectedData=new HashMap<>();
            expectedData.put("statusCode",200);
            expectedData.put("completed",false);
            expectedData.put( "title","quis ut nam facilis et officia qui");
            expectedData.put("userId", 1 );
            expectedData.put("via", "1.1 vegur");
            expectedData.put( "Server", "cloudflare");

        return expectedData;

    }
       /*
             https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            "id": 55

             StatusCode:201
        } BURADA GİDEN VERİYLE DÖNEN VERİ AYNI OLDUGU İCİN JSONOBJECT KULLANIYORUZ.
            */

    public JSONObject setUpPostData(){

        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId", 55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put( "completed", false);
        expectedRequest.put( "statusCode", 201);
        expectedRequest.put( "id", 201);

        return expectedRequest;
    }

          /*
            https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
           {
          "userId": 55,
          "title": "Tidy your room",
          "completed": false
           "id": 55
            */

    public JSONObject gidenVerilersetUp(){
 // burası ben kendim yaptım.
        JSONObject expectedVeriler=new JSONObject();

        expectedVeriler.put("userId",55);
        expectedVeriler.put("title","Tidy your room");
        expectedVeriler.put("completed",false);
        expectedVeriler.put("id",201);
        expectedVeriler.put( "statusCode", 201);


        return expectedVeriler;
        //Burada verileri ekledik.Dinamik hale getırdık.
    }

     /*
           https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde

       {
           "userId": 21,
           "title": "Wash the dishes",
           "completed": false
          }
      */
       public JSONObject setUpPutData(){

           JSONObject expectedRequest=new JSONObject();
           expectedRequest.put("userId", 21);
           expectedRequest.put("title", "Wash the dishes");
           expectedRequest.put("completed", false);


           return expectedRequest;
       }
           /*
   https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
  {
     "title": "Batch44"
    }
            */
       public JSONObject setUpPAtchRequestData(){

           JSONObject requestData=new JSONObject();
           requestData.put("title", "Batch44");

           return requestData;
       }
   /* Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
        "userId": 10,
         "title": "Batch44"
        "completed": true,
            "id": 198
    */
    // PatchReguest01 icn
    public JSONObject setUpPatchExpectedData(){
        JSONObject expectedData=new JSONObject();
        expectedData.put("userId", 10);
        expectedData.put("title", "Batch44");
        expectedData.put("completed",true);
        expectedData.put("id", 198);


        return expectedData;


    }

    }


