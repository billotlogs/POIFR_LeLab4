package info.dicj.lelab4;

import java.util.ArrayList;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Partie {
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

    ArrayList<Devoir> listDevoirsBD;
    ArrayList<Devoir> listDevoirsActif;
    Devoir lab4 = new Devoir("Lab 4", 0, 10, 3, bd);

    public Partie(){
        listCours = new ArrayList<Cours>();
        listCours.add(prog);
        listCours.add(francais);
        listCours.add(android);

        listNourriture = new ArrayList<Nourriture>();
        listNourriture.add(ramen);
        listNourriture.add(pfk);
        listNourriture.add(dep);

        listDevoirsActif = new ArrayList<Devoir>();

        listDevoirsBD = new ArrayList<Devoir>();
        listDevoirsBD.add(lab4);
    }

    public ArrayList<Nourriture> getListNourriture() {
        return listNourriture;
    }

    public ArrayList<Cours> getListCours() {
        return listCours;
    }

    public ArrayList<Devoir> getListDevoirsBD(){
        return listDevoirsBD;
    }

    public ArrayList<Devoir> getListDevoirsActif(){
        return listDevoirsActif;
    }



    //Change les cours du jour.
    public void ChangeCoursJour(String jourSemaine){
        listCours.clear();

        switch(jourSemaine){
            case "Lundi":
                listCours.add(prog);
                listCours.add(francais);
                listCours.add(android);
                break;
            case "Mardi":
                listCours.add(math);
                listCours.add(francais);
                listCours.add(philo);
                break;
            case "Mercredi":
                listCours.add(bd);
                listCours.add(os);
                break;
            case "Jeudi":
                listCours.add(anglais);
                listCours.add(math);
                break;
            case "Vendredi":
                listCours.add(prog);
                listCours.add(math);
                break;
        }
    }

    public void NouveauDevoir(){

    }

    //Sauvegarde la partie en cours.
    public void Sauvegarder(){

    }

    //Charge une partie autrefois sauvegardée.
    public void Charger(){

    }
}
