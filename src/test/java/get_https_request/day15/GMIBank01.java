package get_https_request.day15;

import base_url.GMIBankBasUrl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ReadText;
import utilities.WriteToText;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GMIBank01 extends GMIBankBasUrl {
    /*
    http://www.gmibank.com/api/tp-customers end point'ine
    request gönderin
     1) Tüm Customer bilgilerini ekrana yazdırın.
     2) Tüm Customer SSN lerini ekrana yazdırın.
     3) Tüm Customer SSN lerini text dosyası olarak kaydedin
     4) Olusturduğunuz text dosyasından  SSNleri okuyarak
        "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
     */

    @Test
    public void test() throws JsonProcessingException {

        //Customer classındakı dataları cagırıp dogrulama yapmak ıcın olusturduk.
        Customer[] customers;
        // URL OLUSTUR
        // http://www.gmibank.com/api/tp-customers
        spec03.pathParam("parametre1", "tp-customers");

        Response response = given().headers("Authorization", "Bearer " + generateToken())
                .when().spec(spec03).get("/{parametre1}")
                .then().contentType(ContentType.JSON).extract().response();

       // response.prettyPrint(); yukardakı bilgileri gormek ıcın yazdırdık

        //ObjectMapper De-Seriliazation yapmak icin
        //JSON veya Java data okumak icin kullanabiliriz.
        ObjectMapper obj=new ObjectMapper();
        customers = obj.readValue(response.asString(), Customer[].class);//Customer classındaki datalları cagırıyoruz bu objeyle.


       //1) Tüm Customer bilgilerini ekrana yazdırın.
        for( int i = 0 ; i<customers.length; i ++)
            System.out.println(i+1 + ". Customer: " + customers[i]);

       //2) Tüm Customer SSN lerini ekrana yazdırın.
        for( int i = 0 ; i<customers.length; i ++)
            System.out.println(i+1 + ". Customer SSN: " + customers[i].getSsn());

         //3) Tüm Customer SSN lerini text dosyası olarak kaydedin
        String fileName ="src/test/java/get_https_request/day15/GMIBankTextData/SSNList.txt";// once bir package olusturuyoruz.
        WriteToText.saveSSNData(fileName,customers);                                        //uzerıne sag tıklayıp pathten Content roottan uzantısını alıyoruz.
       // text dosyaysına bilgileri kaydettı.


        //4) Olusturduğunuz text dosyasından  SSNleri okuyarak
        //"531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
        List<String> expectedSsnIds = new ArrayList<>(); //yukardaki snnleri önce listeye atadık
        expectedSsnIds.add("531-95-8437");
        expectedSsnIds.add("049-43-2360");
        expectedSsnIds.add("123-34-3434");

        List<String> actualSSNIds = ReadText.readCustomerSSNList(fileName);// WriteToTExt teki datalari okumak ıcın yaptık
        Assert.assertTrue("SSN'LER ESLESMiYOR AGA :)", actualSSNIds.containsAll(expectedSsnIds));



    }
}