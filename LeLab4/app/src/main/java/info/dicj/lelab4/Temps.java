package info.dicj.lelab4;

import java.util.ArrayList;

/**
 * Created by Francis on 2017-03-20.
 */

public class Temps {
    String jourSemaine;
    int jour, heure, minute;

    public Temps(int jour, int heure, int minute, String jourSemaine) {
        this.jour = jour;
        this.heure = heure;
        this.minute = minute;
        this.jourSemaine = jourSemaine;
    }

    public int getMinute() {
        return minute;
    }

    public int getHeure() {
        return heure;
    }

    public int getJour() {
        return jour;
    }

    public String getJourSemaine() {
        return jourSemaine;
    }

    //Permet d'avancer dans le temps.
    public boolean AvancerHeure(int jour, int heure, int minute){
        int jourActuel = this.jour;
        this.jour += jour;

        if(this.heure + heure < 24)
            this.heure += heure;
        else {
            this.heure = (this.heure + heure) - 24;
            this.jour++;
        }

        if(this.minute + minute < 60)
            this.minute += minute;
        else{
            this.minute = (this.minute + minute) - 60;
            this.heure++;
        }

        if(jourActuel != this.jour){
            ChangeJourSemaine();
            return true;
        }
        else
            return false;
    }

    //Décide du nom du jour de la semaine et change les cours du jour.
    private void ChangeJourSemaine(){
        switch(jour%7){
            case 1: jourSemaine = "Lundi";
                break;
            case 2: jourSemaine = "Mardi";
                break;
            case 3: jourSemaine = "Mercredi";
                break;
            case 4: jourSemaine = "Jeudi";
                break;
            case 5: jourSemaine = "Vendredi";
                break;
            case 6: jourSemaine = "Samedi";
                break;
            case 0: jourSemaine = "Dimanche";
                break;
        }
    }
}
