package app.model;

import java.time.LocalDate;
import java.util.Date;

public class Batiment {

    private static int instances = 0;
    private int id;
    private String nom;
    private String adresse;
    private String coordonnees;
    private String protection;
    private String architecture;
    private int dateConstruction;
    private String image;

    public Batiment(){
        instances++;
    };

    public Batiment(int id, String nom, String adresse, String coordonnees, String protection, String architecture, int dateConstruction, String image){
        instances++;
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.coordonnees = coordonnees;
        this.protection = protection;
        this.architecture = architecture;
        this.dateConstruction = dateConstruction;
        this.image = image;
    }

    public static int getInstances() { return instances; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }

    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getCoordonnees() { return coordonnees; }

    public void setCoordonnees(String coordonnees) { this.coordonnees = coordonnees; }

    public String getProtection() { return protection; }

    public void setProtection(String protection) { this.protection = protection; }

    public String getArchitecture() { return architecture; }

    public void setArchitecture(String architecture) { this.architecture = architecture; }

    public int getDateConstruction() { return dateConstruction; }

    public void setDateConstruction(int dateConstruction) { this.dateConstruction = dateConstruction; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

}
