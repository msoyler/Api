package get_https_request.day15;

public class Country {


     /*
     "country": {
             "id": 3,
             "name": "USA",
             "states": null
}
      */
    // 1) degişkenleri private olarak yaptık
          private int id;
          private String name;
          private  String states;


     // 2) GETTER ve SETTER yapıyorız

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    //3) PARAMETRELİ ve PARAMETRESİZ CONSTRUCTOR olustur,


    public Country() {
    }

    public Country(int id, String name, String states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }

    //4) toString()  Generate den secıyoruz.

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states='" + states + '\'' +
                '}';
    }
}
