package test_data;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuAppTestData {

                //  "firstname": "Ali",
                //  "lastname": "Can",
               //   "totalprice": 500,
              //    "depositpaid": true,
              //    "bookingdates": {
          //                     "checkin": "2022-02-01",
          //                     "checkout": "2022-02-11"
            // burdaki verileri asagıya eklıyoruz


       public HashMap<String,Object> SetUpTestData(){

      HashMap<String,Object> bookingdates=new HashMap<>();
      bookingdates.put("checkin", "2022-02-01");  // bu kisim ilk basta yapılır
      bookingdates.put("checkout", "2022-02-11");

      HashMap<String,Object> expectedData=new HashMap<>();
      expectedData.put("firstname", "Ali");
      expectedData.put("lastname", "Can");
      expectedData.put("totalprice", 500);
      expectedData.put( "depositpaid", true);
      expectedData.put("bookingdates",bookingdates);

      // Burada verilerimizi dinamik hala getiriyoruz.
      // Tekrar tekrar yazmamak icin

       return expectedData;


       }

                 /*
   https://restful-booker.herokuapp.com/booking
   { "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2022-03-01",
                  "checkout": "2022-03-11"
          */
             // post işlemi icin JSONObject ten yararlanıcaz.
             public JSONObject setUpTestAndRequestData(){

                 JSONObject bookindates=new JSONObject();
                 bookindates.put("checkin","2022-03-01");
                 bookindates.put("checkout","2022-03-11");

                 JSONObject expectedRequest=new JSONObject();
                 expectedRequest.put("firstname","Ali");
                 expectedRequest.put("lastname", "Can");
                 expectedRequest.put("totalprice", 500);
                 expectedRequest.put("depositpaid", true);
                 expectedRequest.put("bookindates", bookindates);

                 return expectedRequest;
             }
}
