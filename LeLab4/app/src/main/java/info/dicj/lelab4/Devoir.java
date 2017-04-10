package info.dicj.lelab4;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Devoir {
    String nom, progression;
    int tempsRequis, jourAttribution, coutEnergie, coutSante, coutFaim;;
    Cours cours;

    public Devoir(String nom, String progression, int tempsRequis, int jourAttribution, Cours cours, int coutEnergie, int coutSante, int coutFaim){
        this.nom = nom;
        this.progression = progression;
        this.tempsRequis = tempsRequis;
        this.jourAttribution = jourAttribution;
        this.cours = cours;
        this.coutEnergie = coutEnergie;
        this.coutSante = coutSante;
        this.coutFaim = coutFaim;
    }

    public String getNom() {
        return nom;
    }

    public String getProgression() {
        return progression;
    }

    public void setProgression(String progression) {
        this.progression = progression;
    }

    public int getTempsRequis() {
        return tempsRequis;
    }

    public void setTempsRequis(int tempsRequis) {
        this.tempsRequis = tempsRequis;
    }

    public int getJourAttribution(){
        return jourAttribution;
    }

    public Cours getCours(){
        return cours;
    }

    public int getCoutEnergie() {
        return coutEnergie;
    }

    public int getCoutSante() {
        return coutSante;
    }

    public int getCoutFaim() {
        return coutFaim;
    }
}
