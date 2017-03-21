package info.dicj.lelab4;

import android.util.Log;
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

    ArrayList<Evenement> listEventDormir, listEventTravail;

    Temps temps;
    int energie, santeMentale, faim, connaissance;
    double argent;

    public Joueur(int energie, int santeMentale, int faim, int connaissance, double argent, Temps temps){
        listEventDormir = new ArrayList<Evenement>();
        listEventTravail = new ArrayList<Evenement>();
        CreerEvenement();

        rnd = new Random();

        this.temps = temps;
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


    private void CreerEvenement(){
        Evenement cauchemar = new Evenement("Cauchemar", "Vous rêvez du Lab4", 50, -10, -10, 0, 0);
        listEventDormir.add(cauchemar);

        Evenement laveVaisselle = new Evenement("Problème technique", "Le lave vaisselle est brisé, vous devez lavez tout à la main.", 10, -20, -10, -5, 0);
        listEventTravail.add(laveVaisselle);
    }

    private Evenement EvenementRandom(ArrayList<Evenement> listEvent){
        Evenement evenement = listEvent.get(rnd.nextInt(listEvent.size()));
        return evenement;
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

        temps.AvancerHeure(0, 8, 0);

        Evenement evenement = EvenementRandom(listEventDormir);
        evenement.EffectuerEvent(this);
    }

    public void FaireDevoir(){

    }

    public boolean Travailler(int heure){
        if((energie - 10 * heure >= 0) && (faim - 5 * heure >= 0)){
            faim -= 5 * heure;
            energie -= 10 * heure;
            argent += 11.25 * heure;

            temps.AvancerHeure(0, heure, 0);

            Evenement evenement = EvenementRandom(listEventTravail);
            evenement.EffectuerEvent(this);
            return true;
        }
        else
            return false;
    }

    public void Vivre(){

    }

    public boolean AssisterCours(Cours cours){
        if((energie - cours.getCoutEnergie() >= 0) && (faim - cours.getCoutFaim() >= 0) && (santeMentale - cours.getCoutSante() >= 0)){
            faim -= cours.getCoutFaim();
            energie -= cours.getCoutEnergie();
            santeMentale -= cours.getCoutSante();

            temps.AvancerHeure(0, cours.dureeHeure, 0);
            return true;
        }
        else
            return false;
    }

    public void Attendre(){

    }
}
