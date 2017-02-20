package info.dicj.lelab4;

import java.util.ArrayList;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Partie {
    String jourSemaine;
    int jour, heure, minute;

    ArrayList<Nourriture> listNourriture;
    Nourriture ramen = new Nourriture("Ramen", 10, 1.39);
    Nourriture pfk = new Nourriture("Méga solo baril", 20, 10.99);
    Nourriture dep = new Nourriture("Hot-Dog + Polar pop", 10, 4.49);

    ArrayList<Devoir> listDevoirs;
    Devoir lab4 = new Devoir("Lab 4", 0, 10);

    public Partie(int jour, int heure, int minute, String jourSemaine){
        this.jour = jour;
        this.heure = heure;
        this.minute = minute;
        this.jourSemaine = jourSemaine;

        listNourriture = new ArrayList<Nourriture>();
        listNourriture.add(ramen);
        listNourriture.add(pfk);
        listNourriture.add(dep);

        listDevoirs = new ArrayList<Devoir>();
        listDevoirs.add(lab4);
    }

    //Permet d'avancer dans le temps.
    public void AvancerHeure(int jour, int heure, int minute){
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

        ChangeJourSemaine();
    }

    //Décide du nom du jour de la semaine.
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

    public void Sauvegarder(){

    }

    public void Charger(){

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

    public ArrayList<Nourriture> getListNourriture() {
        return listNourriture;
    }
}
