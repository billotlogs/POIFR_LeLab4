package info.dicj.lelab4;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Nourriture {
    String nom;
    int alimentation;
    double prix;

    public Nourriture(String nom, int alimentation, double prix){
        this.nom = nom;
        this.alimentation = alimentation;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public int getAlimentation() {
        return alimentation;
    }

    public double getPrix() {
        return prix;
    }
}
