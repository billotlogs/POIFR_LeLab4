package info.dicj.lelab4;

/**
 * Created by Francis on 2017-01-31.
 */

public class Donnee {
    double prix;
    String nom;
    int alimentation;

    public Donnee(double prix, String nom, int alimentation){
        this.prix = prix;
        this.nom = nom;
        this.alimentation = alimentation;
    }

    public double getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public int getAlimentation() {
        return alimentation;
    }
}
