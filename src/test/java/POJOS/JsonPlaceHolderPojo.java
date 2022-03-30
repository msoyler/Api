package POJOS;

public class JsonPlaceHolderPojo {

    /*
           https://jsonplaceholder.typicode.com/todos url 'ine bir request gönderildiğinde
         {
         "userId": 21,
        "id": 201,
       "title": "Tidy your room",
       "completed": false

          */
    //1) Değişkenleri PRİVATE olarak tanımlarız

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    // 2) Değişkenlerin değerlerine ulasmak icin GETTER ve SETTER olustururuz.

    // fare nin sağ tıklıyoruz.Generate -- Getter and Setter-- acılan sayfada hepsini secip OK tusuna basıyoruz.


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    //3) Parametreli ve Parametresiz Constructor olustur
   // Sağ tikla GENERATE SEC ve TİKLA acılan sayfa CONSTRUCTORU TIKLA EN USTTEKINİ SECİP OK DİYORuZ

    //PARAMETRESİZ Constructor
    public JsonPlaceHolderPojo() {
    }

    // PARAMETRELİ CONSTRUCTOR
    public JsonPlaceHolderPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // 4) toString Method olustur
    // YUKARDAKİ YOLLA AYNİ SEKİLDE YAPILIR

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
