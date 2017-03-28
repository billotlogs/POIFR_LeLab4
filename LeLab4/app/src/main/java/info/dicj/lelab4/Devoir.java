package info.dicj.lelab4;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Devoir {
    String nom;
    int progression, tempsRequis, jourAttribution;
    Cours cours;

    public Devoir(String nom, int progression, int tempsRequis, int jourAttribution, Cours cours){
        this.nom = nom;
        this.progression = progression;
        this.tempsRequis = tempsRequis;
        this.jourAttribution = jourAttribution;
    }

    public String getNom() {
        return nom;
    }

    public int getProgression() {
        return progression;
    }

    public int getTempsRequis() {
        return tempsRequis;
    }

    public int getJourAttribution(){
        return tempsRequis;
    }

    public Cours getCours(){
        return cours;
    }
}
