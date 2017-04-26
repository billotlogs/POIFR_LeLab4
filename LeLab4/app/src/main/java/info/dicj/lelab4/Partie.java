package info.dicj.lelab4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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

    ArrayList<Devoir> listDevoirs, listDevoirsActif, listDevoirsFini;
    Devoir lab4 = new Devoir("Lab 4", "0", 15, 10, bd, 5, 3, 5, 20);
    Devoir RPG = new Devoir("RPG", "0", 10, 1, prog, 5, 2, 5, 7);
    Devoir poeme = new Devoir("Rédaction d'un Poème", "0", 3, 8, francais, 5, 1, 5, 2);
    Devoir site = new Devoir("Site web", "0", 8, 8, android, 5, 1, 5, 4);

    ArrayList<Examen> listExamen;
    Examen examProg1 = new Examen("Examen1", 300, 8, prog);

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
        listDevoirsFini = new ArrayList<Devoir>();
        listDevoirs = new ArrayList<Devoir>(Arrays.asList(new Devoir[]{lab4, RPG, poeme, site}));

        listExamen = new ArrayList<Examen>(Arrays.asList(new Examen[]{examProg1}));
    }

    public ArrayList<Nourriture> getListNourriture() {
        return listNourriture;
    }

    public ArrayList<Cours> getListCours() {
        return listCours;
    }

    public ArrayList<Devoir> getListDevoirs(){
        return listDevoirs;
    }

    public ArrayList<Devoir> getListDevoirsActif(){
        return listDevoirsActif;
    }

    public ArrayList<Devoir> getListDevoirsFini(){
        return listDevoirsFini;
    }

    public ArrayList<Examen> getListExamen(){
        return listExamen;
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

    //Enlève le devoir de la liste si celui-ci est termnié.
    public boolean VerifierDevoirTermine(Devoir devoir){
        if(Integer.parseInt(devoir.getProgression()) >= 100){
            listDevoirsFini.add(devoir);
            listDevoirsActif.remove(devoir);
            return true;
        }
        else
            return false;
    }

    //Augmente la note lors d'un examen selon le niveau de connaissance dans le cours.
    public boolean FaireExamen(Examen exam, Joueur joueur){
        int gain = 1;

        gain += (ConnaissanceUtilisee(exam, joueur) * 100) / exam.getNbQuestions();
        exam.setPourcentage(exam.getPourcentage() + gain);

        return VerifierExamenTermine(exam);
    }

    //Vérifie si l'examen est terminé.
    private boolean VerifierExamenTermine(Examen examen){
        if(examen.getPourcentage() >= 100){
            examen.setPourcentage(100);
            return true;
        }
        else
            return false;
    }

    //Détermine la connaissance de quel cours doit être utilisée pour faire l'examen.
    private int ConnaissanceUtilisee(Examen exam, Joueur joueur){
        int connaissance = 0;

        switch(exam.getCours().getNom()){
            case "Programmation": connaissance = joueur.getConnaissanceProg();
                break;
            case "Français": connaissance = joueur.getConnaissanceFrancais();
                break;
            case "Android": connaissance = joueur.getConnaissanceAndroid();
                break;
            case "Math": connaissance = joueur.getConnaissanceMath();
                break;
            case "Philosophie": connaissance = joueur.getConnaissancePhilo();
                break;
            case "BD": connaissance = joueur.getConnaissanceBD();
                break;
            case "OS": connaissance = joueur.getConnaissanceOS();
                break;
            case "Anglais": connaissance = joueur.getConnaissanceAnglais();
                break;
        }

        return connaissance;
    }

    //Sauvegarde la partie en cours.
    public void Sauvegarder(){

    }

    //Charge une partie autrefois sauvegardée.
    public void Charger(){

    }
}
