package get_https_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;


public class GetRequest19 extends DummyBaseUrl {


           /*
           http://dummy.restapiexample.com/api/v1/employees
          */

      @Test
    public void test19(){

                       //        /api/v1/employees bu kısım icin asagıda dinamik yapılcak
         // 1) URL OLUSTUR
          spec02.pathParams("bir","api","iki","v1","uc","employees");

          //3)  REQUEST ve RESPONSE
          Response response=given().spec(spec02).when().get("/{bir}/{iki}/{uc}");

          response.prettyPrint();

        //  1) Status kodunun 200,
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.statusCode());

       // 2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,

          JsonPath json=response.jsonPath();

         // List<Integer> idList=json.getList("data.id.findAll{it>10}"); //bu sekıldede calısrı 2.YOL
          List<Integer> idList=json.getList("data.findAll{it.id>10}.id");
          System.out.println("İDLİST : " +idList);

          //Groovy Java platformu uzerınden calısan bir bilgisayar dilidir.
          //Groovy ile loop kullanmadan response den gelen degerleri bir sarta gore alabılırsınız.

          // 3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
          List<Integer> yasList=json.getList("data.findAll{it.employee_age<30}.employee_age");
          System.out.println("Yasları 30 dan kucuk olanlar : " +yasList);

         Collections.sort(yasList); // yaşlari sıralama yapar.
         Assert.assertEquals(23,(int)yasList.get(yasList.size()-1));// yada 23 sayının onune(Integer) yazabılırız
         //  Assert.assertTrue(yasList.get(yasList.size()-1)==23); bu sekıldede olur


          // 4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın

          List<Integer> salaryList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");

          System.out.println("maas listesi: " + salaryList);


          // ve bunların içerisinde "Charde Marshall" olduğunu test edin
          Assert.assertTrue(salaryList.contains("Charde Marshall")); // sonuc Failed olmalı.
      }

}
