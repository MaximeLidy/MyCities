package app.model;

public class User {

    private int id;
    private String nom;
    private boolean isAdmin;

    public User(){}

    public User(int id, String nom, boolean isAdmin){
        this.id = id;
        this.nom = nom;
        this.isAdmin = isAdmin;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public boolean isAdmin() { return isAdmin; }

    public void setAdmin(boolean admin) { isAdmin = admin; }
}
