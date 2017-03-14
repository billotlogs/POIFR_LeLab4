package info.dicj.lelab4;

import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;

import java.util.List;
import java.util.Random;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Evenement {
    Random rnd;
    int chanceSucces, energie;
    String text, nom;

    public Evenement(String nom, String text, int chanceSucces, int energie) {
        this.chanceSucces = chanceSucces;
        this.text = text;
        this.energie = energie;
        this.nom = nom;
        rnd = new Random();
    }

    public int getChanceSucces() {
        return chanceSucces;
    }

    public int getEnergie() {
        return energie;
    }

    public String getText() {
        return text;
    }

    public String getNom() {
        return nom;
    }

    /*
    public void EvenementCours(Cours cours){
        switch(cours.getNom()){
            case "Programmation":

                break;
            case "Français":
                break;
            case "Android":
                break;
            case "Math":
                break;
            case "Philosophie":
                break;
            case "BD":
                break;
            case "OS":
                break;
            case "Anglais":
                break;
        }
    }
    */

    /*
    public String EvenementDormir(){
        String text = "Asd";

        return text;
    }
    */

    //Détermine la chance en pourcentage qu'une action se réalise.
    public boolean ChanceRealisation(){
        int chance = rnd.nextInt(101 - chanceSucces) + chanceSucces;

        if(chance == 100)
            return true;
        else
            return false;
    }
}
