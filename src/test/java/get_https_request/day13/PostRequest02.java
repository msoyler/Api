package get_https_request.day13;
import POJOS.BookingDatesPojo;
import POJOS.BookingPojo;
import POJOS.BookingResponsePojo;
import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerOkuAppBaseUrl {
    /*
 https://restful-booker.herokuapp.com/booking
 request body
 {          "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                     "checkin": "2022-03-01",
                     "checkout": "2022-03-11"
             }
 }}
     Status code is 200
     response body
 {
    "bookingid": 11,
       "booking": {
         "firstname": "Ali",
         "lastname": "Can",
         "totalprice": 500,
         "depositpaid": true,
         "bookingdates": {
               "checkin": "2022-03-01",
              "checkout": "2022-03-11"  // ilk basta burdan baslıyoruz.Yani en alttan baslıyoruz.
                             }
                         }
                     }
  */

    @Test
    public void test() {

        //1) url oluşturalım
        spec05.pathParam("bir", "booking");

        //2) EXPECTED DATA
        // buarada diger classlarda ismiyle olsuturdugumuz classlari aşagidan baslayarak ykarıya dogru method olusturup cagırıyoruz
        BookingDatesPojo bookingDates=new BookingDatesPojo("2022-03-01","2022-03-11");
        System.out.println("bookingDates = " + bookingDates);

        BookingPojo  bookingPojo=new BookingPojo("Ali","Can",500,true,bookingDates);
        System.out.println("bookingPojo = " + bookingPojo);

        // 3) REQUEST VE RESPONSE OLUSTUR
         Response response=given().contentType("application/json; charset=utf-8").spec(spec05)
                          .auth().basic("admin","password123").body(bookingPojo).when().post("/{bir}");
        
         response.prettyPrint();
         
         //4) DOGRULAMA

        BookingResponsePojo actualData= response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(bookingPojo.getFirstname(),actualData.getBookingPojo().getFirstname());
        Assert.assertEquals(bookingPojo.getLastname(),actualData.getBookingPojo().getLastname());
        Assert.assertEquals(bookingPojo.getTotalprice(),actualData.getBookingPojo().getTotalprice());
        Assert.assertEquals(bookingPojo.isDepositpaid(),actualData.getBookingPojo().isDepositpaid());

        Assert.assertEquals(bookingPojo.getBookingDatesPojo().getCheckin(),actualData.getBookingPojo().getBookingDatesPojo().getCheckin());
        Assert.assertEquals(bookingPojo.getBookingDatesPojo().getCheckout(),actualData.getBookingPojo().getBookingDatesPojo().getCheckout());







    }
}