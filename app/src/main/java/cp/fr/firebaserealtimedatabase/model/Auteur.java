package cp.fr.firebaserealtimedatabase.model;

/**
 * Created by Formation on 19/01/2018.
 */

public class Auteur {

    private String name;
    private String firstname;
    private String nationality;

    public Auteur() {
    }

    public Auteur(String name, String firstname, String nationality) {
        this.name = name;
        this.firstname = firstname;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
