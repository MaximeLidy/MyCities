package app.model;

import app.DBConnection;

import java.sql.*;
public class VilleModel {

    Connection connection;
    private int batiment_id;
    private String batiment_nom;
    private String adresse;
    private String coordonnees;
    private String protection;
    private String architecture;
    private int dateConstruction;
    private String image;


    public VilleModel() {
        super();
    }

    public VilleModel(int batiment_id, String batiment_nom, String adresse, String coordonnees, String protection, String architecture, int dateConstruction, String image) {
        super();
        connection = DBConnection.Connector();
        if (connection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }

        this.batiment_id = batiment_id;
        this.batiment_nom = batiment_nom;
        this.adresse = adresse;
        this.coordonnees = coordonnees;
        this.protection = protection;
        this.architecture = architecture;
        this.dateConstruction = dateConstruction;
        this.image = image;

    }

    public int getBatiment_id() {
        return batiment_id;
    }

    public void setBatiment_id(int batiment_id) {
        this.batiment_id = batiment_id;
    }

    public String getBatiment_nom() {
        return batiment_nom;
    }

    public void setBatiment_nom(String batiment_nom) {
        this.batiment_nom = batiment_nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public int getDateConstruction() {
        return dateConstruction;
    }

    public void setDateConstruction(int dateConstruction) {
        this.dateConstruction = dateConstruction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}

