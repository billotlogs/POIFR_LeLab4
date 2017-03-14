package info.dicj.lelab4;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Joueur {
    Random rnd;
    Evenement cauchemar = new Evenement("Cauchemar", "Vous rêvez du Lab4", 50, -10);
    ArrayList<Evenement> listEventDormir;

    int energie, santeMentale, faim, connaissance;
    double argent;

    public Joueur(int energie, int santeMentale, int faim, int connaissance, double argent){
        listEventDormir = new ArrayList<Evenement>();
        rnd = new Random();
        this.energie = energie;
        this.santeMentale = santeMentale;
        this.faim = faim;
        this.connaissance = connaissance;
        this.argent = argent;
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

    //**************************************************************************************
    private boolean EvenementRandom(ArrayList<Evenement> listEvent){
        if(listEventDormir.get(rnd.nextInt(listEventDormir.size())).ChanceRealisation())
            return true;
        else
            return false;
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
        if(EvenementRandom(listEventDormir))
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
        if((energie - cours.getCoutEnergie() >= 0) && (faim - cours.getCoutFaim() >= 0) && (santeMentale - cours.getCoutSante() >= 0)){
            faim -= cours.getCoutFaim();
            energie -= cours.getCoutEnergie();
            santeMentale -= cours.getCoutSante();
            return true;
        }
        else
            return false;
    }

    public void Attendre(){

    }
}
