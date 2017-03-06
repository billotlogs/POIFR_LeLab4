package info.dicj.lelab4;

import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;

import java.util.Random;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Evenement {
    Random rnd;

    public Evenement() {
        rnd = new Random();
    }

    public void GenererEvent(){

    }

    private void EvenementCours(){
        int chance = rnd.nextInt(100);

        switch(chance){

        }
    }

    public void ChanceRealisation(int chanceSucces){
        int chance = rnd.nextInt(101 - chanceSucces) + chanceSucces;

        if(chance == 100){
            Log.i("Test", "Fuck you");
        }
        Log.i("Test", "Chance : " + chance);
    }

    private void NouveauDevoir(Cours cours){
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
}
