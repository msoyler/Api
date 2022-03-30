package POJOS;

public class DummyPojo {

                    /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                           Status code is 200
{
 "status": "success",
 "data": {
   "id": 1,
   "employee_name": "Tiger Nixon",
   "employee_salary": 320800,
   "employee_age": 61,
   "profile_image": ""
   },
 "message": "Successfully! Record has been fetched."
 }
*/
    //1) PRİVATE değişkenleri oluştur

    private String status;
    private DummyData dummyData;
    private String message;

    //2) GETTER ve SETTER oluştur(Generate den secıyoruz)


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyData getDummyData() {
        return dummyData;
    }

    public void setDummyData(DummyData dummyData) {
        this.dummyData = dummyData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //3) PARAMETRELİ ve PARAMETRESİZ CONSTRUCTOR olustur,


    public DummyPojo(String status, DummyData dummyData, String message) {
        this.status = status;
        this.dummyData = dummyData;
        this.message = message;
    }

    public DummyPojo() {
    }


    // 4) toString() olustur


    @Override
    public String toString() {
        return "DummyPojo{" +
                "status='" + status + '\'' +
                ", dummyData=" + dummyData +
                ", message='" + message + '\'' +
                '}';
    }
}
