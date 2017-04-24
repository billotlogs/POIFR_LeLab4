package info.dicj.lelab4;

/**
 * Created by utilisateur on 24/04/2017.
 */
public class Examen {

    String nom;
    int nbQuestions, jourAttribution, pourcentage;
    Cours cours;

    public Examen(String nom, int nbQuestions, int pourcentage, int jourAttribution, Cours cours){
        this.nom = nom;
        this.nbQuestions = nbQuestions;
        this.cours = cours;
        this.jourAttribution = jourAttribution;
        this.pourcentage = pourcentage;
    }

    public String getNom() {
        return nom;
    }

    public int getNbQuestions() {
        return nbQuestions;
    }

    public Cours getCours(){
        return cours;
    }

    public int getJourAttribution(){
        return jourAttribution;
    }

    public int getPourcentage(){
        return pourcentage;
    }
}
