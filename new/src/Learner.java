import java.util.*;
public class Learner {

    private  int id;
    private  String firstname;
    private String lastname;

    public  Learner(int id, String firstName, String lastname){
        this.id=id;
        this.firstname=firstName;
        this.lastname=lastname;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}
