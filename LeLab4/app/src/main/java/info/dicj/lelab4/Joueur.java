package info.dicj.lelab4;

import android.view.View;
import android.widget.TextView;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Joueur {
    int energie, santeMentale, faim, connaissance;
    double argent;

    public Joueur(int energie, int santeMentale, int faim, int connaissance, double argent){
        this.energie = energie;
        this.santeMentale = santeMentale;
        this.faim = faim;
        this.connaissance = connaissance;
        this.argent = argent;
    }

    public void Manger(Nourriture bouffe){
        if((argent >= bouffe.prix) && (faim < 100)){
            argent -= bouffe.prix;
            faim += bouffe.alimentation;
        }

        if(faim > 100)
            faim = 100;
    }

    public void Dormir(){
        energie = 100;
    }

    public void FaireDevoir(){

    }

    public boolean Travailler(int heure){
        if((energie - 10 * heure >= 0) && (faim - 5 * heure >= 0)){
            faim -= 5 * heure;
            energie -= 10 * heure;
            argent += 11.25 * heure;
            return true;
        }
        return false;
    }

    public void Vivre(){

    }

    public boolean AssisterCours(Cours cours){
        if((energie - 10 * cours.getDureeHeure() >= 0) && (faim - 5 * cours.getDureeHeure() >= 0)){
            faim -= 5 * cours.getDureeHeure();
            energie -= 10 * cours.getDureeHeure();
            return true;
        }
        else
            return false;
    }

    public void Attendre(){

    }

    public int getEnergie() {
        return energie;
    }

    public int getSanteMentale() {
        return santeMentale;
    }

    public int getFaim() {
        return faim;
    }

    public int getConnaissance() {
        return connaissance;
    }

    public double getArgent() {
        return argent;
    }
}
