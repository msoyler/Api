package test_data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyTestData {



    public Map<String,Object> setUpTestData(){

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        List<Integer> yaslar=new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        // "id": 10,
        //        "employee_name": "Sonya Frost",
        //        "employee_salary": 103600,
        //        "employee_age": 23,
        //        "profile_image": ""
        HashMap<String,Object> onuncu=new HashMap<>();
        onuncu.put("id",10);
        onuncu.put("employee_name","Sonya Frost");
        onuncu.put("employee_salary", 103600);
        onuncu.put("employee_age", 23);
        onuncu.put("profile_image","");



        // Status kodun 200 olduğunu,
        // 14. Çalışan isminin "Haley Kennedy" olduğunu,
        // Çalışan sayısının 24 olduğunu,
        // Sondan 3. çalışanın maaşının 675000 olduğunu
        HashMap<String,Object> expected=new HashMap<>();
        expected.put("statusCode",200);
        expected.put("ondorduncucalisan","Haley Kennedy");
        expected.put("calisansayisi",24);
        expected.put("sondanucuncucalisaninmaasi",675000);
        expected.put("arananyaslar",yaslar);
        expected.put("onuncucalisan",onuncu);


       return expected;
    }
       //PostRequest02 BU CLASS İCİN OLUSTURULDU ASAGIDAKİ BİLGİLER
          /*
           "name":"Ali Can",
          "salary":"2000",
          "age":"40",
           */
        public HashMap<String,Object> setUpRequestBody(){

            HashMap<String,Object> requestBody=new HashMap<>();
            requestBody.put("name","Ali Can");
            requestBody.put("salary","2000");
            requestBody.put("age","40");

            return requestBody;

            // burdakı gönderdigimiz datalarımız ıcın once METHOD olusturyoruz sonra asagıda gelen
            // cevap icin yeniden BİR METHOD olusturuyoruz.
        }

        /*  }
          gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,
      {
         "status": "success",
         "data": {
         "id":…
       },
        "message": "Successfully! Record has been added."

         */
          public HashMap<String,Object>setUpExpectedData(){

             HashMap<String,Object> expectedData=new HashMap<>();

              expectedData.put("statusCode",200);
              expectedData.put("status","success");
              expectedData.put("message","Successfully! Record has been added.");

            return expectedData;
      }


       /*      "status": "success",
               "data": "2",
               "message": "Successfully! Record has been deleted"
        */
     public JSONObject setUpDeleteExpectedData(){

      JSONObject expectedData=new JSONObject();
      expectedData.put("status","success");
      expectedData.put("data", "2");
      expectedData.put( "message","Successfully! Record has been deleted");

      return expectedData;


     }
}


