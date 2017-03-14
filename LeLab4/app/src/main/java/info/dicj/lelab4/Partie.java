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
    Nourriture dep = new Nourriture("2x Hot-Dog", 10, 4.49);

    ArrayList<Cours> listCours;
    Cours prog = new Cours("Programmation", 3, 5, 5, 5);
    Cours francais = new Cours("Français", 2, 5, 5, 5);
    Cours android = new Cours("Android", 3, 5, 5, 5);
    Cours math = new Cours("Math", 2, 5, 5, 5);
    Cours philo = new Cours("Philosophie", 3, 5, 5, 5);
    Cours bd = new Cours("BD", 3, 5, 5, 5);
    Cours os = new Cours("OS", 3, 5, 5, 5);
    Cours anglais = new Cours("Anglais", 3, 5, 5, 5);

    ArrayList<Devoir> listDevoirs;
    ArrayList<Devoir> listDevoirsActifs;
    Devoir lab4 = new Devoir("Lab 4", 0, 10);

    public Partie(int jour, int heure, int minute, String jourSemaine){
        this.jour = jour;
        this.heure = heure;
        this.minute = minute;
        this.jourSemaine = jourSemaine;

        listCours = new ArrayList<Cours>();
        listCours.add(prog);
        listCours.add(francais);
        listCours.add(android);

        listNourriture = new ArrayList<Nourriture>();
        listNourriture.add(ramen);
        listNourriture.add(pfk);
        listNourriture.add(dep);

        listDevoirs = new ArrayList<Devoir>();
        listDevoirs.add(lab4);

        listDevoirsActifs = new ArrayList<Devoir>();
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

    public ArrayList<Cours> getlistCours() {
        return listCours;
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
            ChangeJourSemaine();
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
        listCours.clear();

        switch(jour%7){
            case 1:
                jourSemaine = "Lundi";
                listCours.add(prog);
                listCours.add(francais);
                listCours.add(android);
                break;
            case 2:
                jourSemaine = "Mardi";
                listCours.add(math);
                listCours.add(francais);
                listCours.add(philo);
                break;
            case 3:
                jourSemaine = "Mercredi";
                listCours.add(bd);
                listCours.add(os);
                break;
            case 4:
                jourSemaine = "Jeudi";
                listCours.add(anglais);
                listCours.add(math);
                break;
            case 5:
                jourSemaine = "Vendredi";
                listCours.add(prog);
                listCours.add(math);
                break;
            case 6: jourSemaine = "Samedi";
                break;
            case 0: jourSemaine = "Dimanche";
                break;
        }
    }

    //Sauvegarde la partie en cours.
    public void Sauvegarder(){

    }

    //Charge une partie autrefois sauvegardée.
    public void Charger(){

    }
}
