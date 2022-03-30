package POJOS;

public class BookingResponsePojo {
     /*
    {
     "bookingid": 11, // 3.) OLARAK BURDAN BASLIYORUZ.
                      // olusturulan class İSMİ: BookingResponsePojo.Asagidaki 2 kısımı clas ismıyle buarada birlestırdık
        "booking": {
         "firstname": "Ali",
         "lastname": "Can",
         "totalprice": 500,                     //2.) OLARAK BURDAN BASLIYORUZ AYRI AYRI CLASSLARDAN
         "depositpaid": true,                   // olusturlan class İSMİ: ---->> BookingPojo asagıdakiniide buaradaa bırlestırdık.
         "bookingdates": {
                      "checkin": "2022-03-01", // 1.) İLK OLARAK BURDAN BASLIYORUZ OLUSTURMAYA
                      "checkout": "2022-03-11" // OLUSTURULAN CLASS İSMİ ---->> BookingDatesPojo buraya olsuturduk bu kısmı
                           }
                     }
         }
  */
         // 1) PRİVATE degişken olustur
      private  int   bookingId;
      private  BookingPojo bookingPojo;

       // 2) GETTER ve SETTER Olustur


    public int getbookingId() {
        return bookingId;
    }

    public void setbookingId(int bookingid) {
        this.bookingId = bookingid;
    }

    public BookingPojo getBookingPojo() {
        return bookingPojo;
    }

    public void setBookingPojo() {
        this.bookingPojo = bookingPojo;
    }

    // 3) PARAMETRELİ ve PARAMETRESİZ CONSTRUCTOR OLUSTUR

    public BookingResponsePojo() {
    }


    public BookingResponsePojo(int bookingId, BookingPojo bookingPojo) {
        this.bookingId = bookingId;
        this.bookingPojo = bookingPojo;
    }


    // 4) toString() olustur


    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingId=" + bookingId +
                ", bookingPojo=" + bookingPojo +
                '}';
    }
}
