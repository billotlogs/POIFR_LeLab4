package info.dicj.lelab4;

/**
 * Created by utilisateur on 30/01/2017.
 */
public class Partie {
    String jourSemaine;
    int jour, heure, minute;

    public Partie(int jour, int heure, int minute, String jourSemaine){
        this.jour = jour;
        this.heure = heure;
        this.minute = minute;
        this.jourSemaine = jourSemaine;
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

    public String getjourSemaine() {
        return jourSemaine;
    }
}
