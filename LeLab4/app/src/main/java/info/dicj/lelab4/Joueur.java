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
    int energie, santeMentale, faim,
    connaissanceProg, connaissanceFrancais, connaissancePhilo, connaissanceBD, connaissanceAndroid,
    connaissanceAnglais, connaissanceMath, connaissanceOS;
    double argent;

    public Joueur(int energie, int santeMentale, int faim, double argent, Temps temps){
        listEventDormir = new ArrayList<Evenement>();
        listEventTravail = new ArrayList<Evenement>();
        CreerEvenement();

        rnd = new Random();

        this.temps = temps;
        this.energie = energie;
        this.santeMentale = santeMentale;
        this.faim = faim;
        this.argent = argent;

        connaissanceProg = 0;
        connaissanceFrancais = 0;
        connaissancePhilo = 0;
        connaissanceBD = 0;
        connaissanceAndroid = 0;
        connaissanceAnglais = 0;
        connaissanceMath = 0;
        connaissanceOS = 0;
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

    public int getConnaissanceProg() {
        return connaissanceProg;
    }

    public int getConnaissanceFrancais() {
        return connaissanceFrancais;
    }

    public int getConnaissancePhilo() {
        return connaissancePhilo;
    }

    public int getConnaissanceBD() {
        return connaissanceBD;
    }

    public int getConnaissanceAndroid() {
        return connaissanceAndroid;
    }

    public int getConnaissanceAnglais() {
        return connaissanceAnglais;
    }

    public int getConnaissanceOS() {
        return connaissanceOS;
    }

    public int getConnaissanceMath() {
        return connaissanceMath;
    }

    public double getArgent() {
        return argent;
    }

    //Initialisation des événements et insertion de ceux-ci dans les listes d'événements.
    private void CreerEvenement(){
        Evenement cauchemar = new Evenement("Cauchemar", "Vous rêvez du Lab4", 20, -10, -10, 0, 0);
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

    //Augmente la faim du joueur et réduit son argent.
    public boolean Manger(Nourriture bouffe){
        if((argent >= bouffe.prix) && (faim < 100)){
            argent -= bouffe.prix;
            faim += bouffe.alimentation;
            return true;
        }

        if (faim > 100)
            faim = 100;

        return false;
    }

    //Permet au joueur de récupérer de l'énergie.
    public void Dormir(){
        energie = 100;

        temps.AvancerHeure(0, 8, 0);

        EvenementRandom(listEventDormir);
    }

    //Permet au joueur de progresser dans un devoir.
    public boolean FaireDevoir(Devoir devoir, int heure){
        int progression = ((heure * 100) / devoir.getTempsRequis()) + Integer.parseInt(devoir.getProgression());

        energie -= devoir.getCoutEnergie() * heure;
        faim -= devoir.getCoutFaim() * heure;
        santeMentale -= devoir.getCoutSante() * heure;

        CompletionDevoir(devoir.getCours().getNom(), devoir.getGainConnaissance(), progression);

        devoir.setProgression("" + progression);

        temps.AvancerHeure(0, heure, 0);
        return true;
    }

    //Augmente la connaissance d'une certaine matière lors de la complétion d'un devoir.
    private void CompletionDevoir(String nomCours, int gainConnaissance, int progression){
        if(progression >= 100){
            progression = 100;

            switch (nomCours){
                case "Programmation": connaissanceProg += gainConnaissance;
                    break;
                case "Français": connaissanceFrancais += gainConnaissance;
                    break;
                case "Android": connaissanceAndroid += gainConnaissance;
                    break;
                case "Math": connaissanceMath += gainConnaissance;
                    break;
                case "Philosophie": connaissancePhilo += gainConnaissance;
                    break;
                case "BD": connaissanceBD += gainConnaissance;
                    break;
                case "OS": connaissanceOS += gainConnaissance;
                    break;
                case "Anglais": connaissanceAnglais += gainConnaissance;
                    break;
            }
        }
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
