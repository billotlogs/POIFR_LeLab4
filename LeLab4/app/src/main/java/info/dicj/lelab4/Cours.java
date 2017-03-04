package info.dicj.lelab4;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Cours {
    String nom;
    int dureeHeure, coutEnergie, coutSante, coutFaim;

    public Cours(String nom, int dureeHeure, int coutEnergie, int coutSante, int coutFaim){
        this.nom = nom;
        this.dureeHeure = dureeHeure;
        this.coutEnergie = coutEnergie;
        this.coutSante = coutSante;
        this.coutFaim = coutFaim;
    }

    public int getCoutFaim() {
        return coutFaim;
    }

    public int getDureeHeure() {
        return dureeHeure;
    }

    public String getNom() {
        return nom;
    }

    public int getCoutEnergie() {
        return coutEnergie;
    }

    public int getCoutSante() {
        return coutSante;
    }
}
