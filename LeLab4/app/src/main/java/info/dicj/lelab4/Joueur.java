package info.dicj.lelab4;

import android.view.View;
import android.widget.TextView;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Joueur {
    int energie, santeMentale, faim, connaissance;
    double argent;

    public Joueur(){

    }

    public void Manger(Nourriture bouffe){
        argent -= bouffe.prix;
        faim += bouffe.alimentation;
    }

    public void Dormir(){

    }

    public void FaireDevoir(){

    }

    public void ChangerLieu(){

    }

    public void Travailler(){

    }

    public void Vivre(){

    }

    public void AssisterCours(){

    }

    public void Attendre(){

    }
}
