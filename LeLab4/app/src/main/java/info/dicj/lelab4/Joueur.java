package info.dicj.lelab4;

import android.provider.CalendarContract;
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

    Evenement event;
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

    public Evenement getEvent(){
        return event;
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

    //Sélectionne un événement aléatoire à partir d'une liste d'événement.
    private void EvenementRandom(ArrayList<Evenement> listEvent){
        event = listEvent.get(rnd.nextInt(listEvent.size()));

        if(!event.EffectuerEvent(this))
            event = null;
    }

    public void Manger(Nourriture bouffe){
        if((argent >= bouffe.prix) && (faim < 100)){
            argent -= bouffe.prix;
            faim += bouffe.alimentation;
        }

        if(faim > 100)
            faim = 100;
    }

    //Permet au joueur de récupérer de l'énergie.
    public void Dormir(){
        energie = 100;

        temps.AvancerHeure(0, 8, 0);

        EvenementRandom(listEventDormir);
    }

    public void FaireDevoir(){

    }

    //Permet au joueur de gagner de l'argent au dépend de certains stats.
    public boolean Travailler(int heure){
        if((energie - 10 * heure >= 0) && (faim - 5 * heure >= 0)){
            faim -= 5 * heure;
            energie -= 10 * heure;
            argent += 11.25 * heure;

            temps.AvancerHeure(0, heure, 0);

            EvenementRandom(listEventTravail);
            return true;
        }
        else
            return false;
    }

    public void Vivre(){

    }

    //Permet au joueur d'assister à un cours.
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
