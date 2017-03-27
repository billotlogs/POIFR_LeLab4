package info.dicj.lelab4;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Evenement {
    Random rnd;
    int chanceSucces, modifEnergie, modifSanteMentale, modifFaim, modifArgent;
    String text, nom;

    public Evenement(String nom, String text, int chanceSucces, int modifEnergie, int modifSanteMentale, int modifFaim, int modifArgent) {
        this.chanceSucces = chanceSucces;
        this.text = text;
        this.modifEnergie = modifEnergie;
        this.modifArgent = modifArgent;
        this.modifFaim = modifFaim;
        this.modifSanteMentale = modifSanteMentale;
        this.nom = nom;

        rnd = new Random();
    }

    public String getText() {
        return text;
    }

    public String getNom() {
        return nom;
    }

    //Modifie les statistiques selon l'événement et montre un texte.
    public boolean EffectuerEvent(Joueur joueur){
        if(ChanceRealisation()){
            joueur.santeMentale += modifSanteMentale;
            joueur.argent += modifArgent;
            joueur.faim += modifFaim;
            joueur.energie += modifEnergie;
            return true;
        }
        else
            return false;
    }
    
    //Détermine la chance en pourcentage qu'une action se réalise.
    private boolean ChanceRealisation(){
        int chance = rnd.nextInt(101);
        if(chance < chanceSucces)
            return true;
        else
            return false;
    }
}
