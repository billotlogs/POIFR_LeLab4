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
    }

    public void Dormir(){
        energie = 100;
    }

    public void FaireDevoir(){

    }

    public void ChangerLieu(){

    }

    public void Travailler(int heure){
        energie -= 10 * heure;
        argent += 11.25 * heure;
    }

    public void Vivre(){

    }

    public void AssisterCours(){

    }

    public void Attendre(){

    }
}
