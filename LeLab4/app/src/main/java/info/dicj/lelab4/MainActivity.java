package info.dicj.lelab4;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.ClipData;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

/*
*   Trouver un moyen pour avancer le temps a chaque action.
*   Trouver un moyen de mettre un événement random selon le type d'action.
 */
public class MainActivity extends AppCompatActivity{
    MessageBox messageBox;
    AnimatedProgress animatedProgress;
    Temps temps = new Temps(1, 6, 30, "Lundi");
    Partie partie = new Partie();
    Joueur joueur = new Joueur(100, 100, 100, 200, temps);

    ListView lvNourriture, lvCours, lvDevoirs;
    BouffeAdapter bouffeAdapter;
    CoursAdapter coursAdapter;
    DevoirAdapter devoirAdapter;

    TextView txtFaim, txtArgent, txtEnergie, txtSante, txtConnaissance, txtNbHeure;
    TextView txtHeure, txtNbJour, txtJourSemaine;
    TextView txtProgressionExam;
    ImageView btnExamen;

    TextView txtNomDevoir, txtMenuHeure_HeureSuivante, txtMenuHeure_coutEnergie, txtMenuHeure_coutFaim, txtMenuHeure_coutSante,
             txtMoyenneGeneral, txtMessageFin;

    DonutProgress progressDevoir, progressTempsExamen;

    ProgressBar progressFaim, progressSante, progressEnergie;

    Devoir devoirSelectionne = null;
    Examen examEnCours = null;
    int nbHeure = 1, progressionDevoir, progressionExam, progressionExamenRestante;
    boolean menuHeureDevoirActif = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//region Initialisation des ListViews et des Adapters.
        lvNourriture = (ListView)findViewById(R.id.menuManger);
        bouffeAdapter = new BouffeAdapter(this, partie.getListNourriture());
        lvNourriture.setAdapter(bouffeAdapter);

        lvCours = (ListView)findViewById(R.id.menuCours);
        coursAdapter = new CoursAdapter(this, partie.getListCours());
        lvCours.setAdapter(coursAdapter);

        lvDevoirs = (ListView)findViewById(R.id.menuDevoir);
        devoirAdapter = new DevoirAdapter(this, partie.getListDevoirsActif());
        lvDevoirs.setAdapter(devoirAdapter);
//endregion

//region Initialisation des TextViews.
        txtFaim = (TextView)findViewById(R.id.txtFaim);
        txtArgent = (TextView)findViewById(R.id.txtArgent);
        txtEnergie = (TextView)findViewById(R.id.txtEnergie);
        txtSante = (TextView)findViewById(R.id.txtSante);
        txtNbHeure = (TextView)findViewById(R.id.nbHeure);
        txtHeure = (TextView)findViewById(R.id.txtHeure);
        txtNbJour = (TextView)findViewById(R.id.txtNbJours);
        txtJourSemaine = (TextView)findViewById(R.id.txtJourSemaine);
        txtProgressionExam = (TextView)findViewById(R.id.txtProgressionExamen);

        //TextViews dans le menu menuHeure.
        txtNomDevoir = (TextView)findViewById(R.id.menuHeureNomDevoir);
        txtMenuHeure_HeureSuivante = (TextView)findViewById(R.id.menuHeure_HeureSuivante);
        txtMenuHeure_coutEnergie = (TextView)findViewById(R.id.menuHeure_coutEnergie);
        txtMenuHeure_coutFaim = (TextView)findViewById(R.id.menuHeure_coutFaim);
        txtMenuHeure_coutSante = (TextView)findViewById(R.id.menuHeure_coutSante);
//endregion

//region Initialisation des ProgressBars.
        progressEnergie = (ProgressBar)findViewById(R.id.progressEnergie);
        progressFaim = (ProgressBar)findViewById(R.id.progressFaim);
        progressSante = (ProgressBar)findViewById(R.id.progressSante);
        progressionExamenRestante = 30000;

        //DonutProgress
        progressDevoir = (DonutProgress)findViewById(R.id.progressionDevoir);
        progressTempsExamen = (DonutProgress)findViewById(R.id.examen_temps_restant);
//endregion

        btnExamen = (ImageView)findViewById(R.id.btn_examen);

        messageBox = new MessageBox(this);

        UpdateText();
        Action();
        ScrollFocus();
    }

    //Action lors d'un click sur un élément d'une listview (EventListeners).
    private void Action(){

//region Choix de la nourriture.
        lvNourriture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!joueur.Manger(partie.getListNourriture().get(i))){
                    if(joueur.getArgent() < partie.getListNourriture().get(i).getPrix())
                        messageBox.Show("Pauvreté", "Vous n'avez plus d'argent pour vous nourrir!");
                    else if(joueur.getFaim() >= 100)
                        messageBox.Show("Ventre plein", "Vous avez le ventre plein, cessez de manger!");
                }
                UpdateText();
            }
        });
//endregion

//region Choix du cours.
        lvCours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cours coursChoisi = partie.getListCours().get(i);
                Devoir devoirRecu = null;

                if(joueur.AssisterCours(coursChoisi)){
                    for (Devoir devoir : partie.getListDevoirs()) {
                        if((devoir.getCours() == coursChoisi) && (temps.getJour() == devoir.getJourAttribution())){
                            messageBox.Show("Nouveau Devoir", "Vous avez obtenu le devoir : " + devoir.getNom() + " en " + devoir.getCours().getNom());
                            partie.getListDevoirsActif().add(devoir);
                            devoirRecu = devoir;
                        }
                    }

                    for (Examen examen : partie.getListExamen()){
                        if((examen.getCours() == coursChoisi) && (examen.getJourAttribution() == temps.getJour())){
                            OuvreFerme((View)findViewById(R.id.menuExam));
                            examEnCours = examen;
                            UpdateProgressAnimation(progressTempsExamen, 30000, 0, 30000);
                            TempsExamenEcoule();
                        }
                    }

                    if(devoirRecu != null)
                        partie.getListDevoirs().remove(devoirRecu);

                    partie.getListCours().remove(coursChoisi);
                    AjusterListView(lvCours, partie.getListCours());
                    AjusterListView(lvDevoirs, partie.getListDevoirsActif());
                    UpdateElementsTemporels();
                    UpdateText();
                }
            }
        });
//endregion

//region Choix du devoir.
        lvDevoirs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                devoirSelectionne = partie.getListDevoirsActif().get(i);

                progressDevoir.setDonut_progress(devoirSelectionne.getProgression());

                menuHeureDevoirActif = true;
                nbHeure = 1;
                UpdateElementsMenuHeure();
                txtNomDevoir.setText(devoirSelectionne.getNom());
                OuvreFerme(findViewById(R.id.menuHeure));
            }
        });
//endregion

//region Action lors du click sur le bouton d'examen.
        btnExamen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btnExamen.setImageResource(R.drawable.btn_examen_click);
                    if(partie.FaireExamen(examEnCours, joueur)){
                        OuvreFerme(findViewById(R.id.menuExam));
                    }

                    txtProgressionExam.setText("" + examEnCours.getPourcentage());
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btnExamen.setImageResource(R.drawable.btn_examen);
                }
                return true;
            }
        });
//endregion

    }

    //Permet de choisir le nombre d'heures travaillées.
    public void SelectionHeure(View view){
        if(menuHeureDevoirActif){
            heureDevoir(view);
        }
        else {
            heureTravail(view);
        }
    }

    //Choisi le nombre d'heures travaillées.
    private void heureTravail(View view){
        switch (view.getId()){
            case R.id.increase:
                if(nbHeure < 8)
                    nbHeure++;
                break;
            case R.id.decrease:
                if(nbHeure > 3)
                    nbHeure--;
                break;
            case R.id.valider:
                if(joueur.Travailler(nbHeure)){
                    UpdateElementsTemporels();
                    UpdateText();
                    AfficherEvenement();
                }
                break;
        }

        txtNbHeure.setText("" + nbHeure);
    }

    //Choisi le nombre d'heures de devoirs.
    private void heureDevoir(View view){
        switch (view.getId()){
            case R.id.increase:
                if(nbHeure < 9){
                    nbHeure++;
                }
                break;
            case R.id.decrease:
                if(nbHeure > 1){
                    nbHeure--;
                }
                break;
            case R.id.valider:
                if(joueur.FaireDevoir(devoirSelectionne, nbHeure)){
                    UpdateElementsTemporels();
                    UpdateText();
                    AfficherEvenement();
                    devoirAdapter.notifyDataSetChanged();
                }
                RetirerDevoirTermine();
                break;
        }

        UpdateElementsMenuHeure();
    }



    //Affiche le text de l'événement.
    private void AfficherEvenement(){
        if(joueur.getEvent() != null)
            messageBox.Show(joueur.getEvent().getNom(), joueur.getEvent().getText());
    }

    //Ouvre ou ferme un menu d'actions
    private void OuvreFerme(View menu){
        if(menu.getVisibility() == menu.GONE){
            menu.setVisibility(menu.VISIBLE);
        }
        else{
            menu.setVisibility(menu.GONE);
        }
    }

    //Défini quel menu d'action doit ouvrir lors d'un click sur un bouton du scrollview.
    public void OuvreMenuAction(View view) {
        View menuManger = findViewById(R.id.menuManger);
        View menuHeureTravail = findViewById(R.id.menuHeure);
        View menuCours = findViewById(R.id.menuCours);
        View menuDevoir = findViewById(R.id.menuDevoir);

        switch(view.getId()){
            case R.id.manger:
                OuvreFerme(menuManger);
                break;
            case R.id.travailler:
                nbHeure = 3;
                menuHeureDevoirActif = false;
                OuvreFerme(menuHeureTravail);
                txtNbHeure.setText("" + nbHeure);
                break;
            case R.id.dormir:
                joueur.Dormir();
                UpdateElementsTemporels();
                UpdateText();
                AfficherEvenement();
                break;
            case R.id.vivre:
                joueur.Vivre();
                messageBox.Show("Vivre", "Vous vous sentez en vie en faisant une partie de bowling.");
                UpdateText();
                UpdateElementsTemporels();
                break;
            case R.id.attendre:
                messageBox.Show("Martin", "Bienvenu martin luther");
                break;
            case R.id.devoir:
                OuvreFerme(menuDevoir);
                break;
            case R.id.assisterCours:
                OuvreFerme(menuCours);
                break;
            case R.id.btnFermer:
                OuvreFerme(menuHeureTravail);
                break;
        }
    }




    //Retire le devoir de la listView une fois terminé.
    private void RetirerDevoirTermine(){
        if(partie.VerifierDevoirTermine(devoirSelectionne)){
            messageBox.Show("Devoir Terminé", "Vous avez terminé le devoir : " + devoirSelectionne.getNom() + " et avez obtenu +" +
            devoirSelectionne.getGainConnaissance() + " de connaissance en " + devoirSelectionne.getCours().getNom());
            OuvreFerme(findViewById(R.id.menuHeure));
            AjusterListView(lvDevoirs, partie.getListDevoirsActif());
        }
    }

    //Met à jour les éléments du menuHeure.
    private void UpdateElementsMenuHeure(){
        progressionDevoir = ((nbHeure * 100) / devoirSelectionne.getTempsRequis()) + Integer.parseInt(devoirSelectionne.getProgression());

        if(progressionDevoir > 100)
            progressionDevoir = 100;

        progressDevoir.setDonut_progress("" + progressionDevoir);
        txtNbHeure.setText("" + nbHeure);
        txtMenuHeure_coutEnergie.setText(joueur.getEnergie() + " --► " + (joueur.getEnergie() - (devoirSelectionne.getCoutEnergie() * nbHeure)));
        txtMenuHeure_coutFaim.setText(joueur.getFaim() + " --► " + (joueur.getFaim() - (devoirSelectionne.getCoutFaim() * nbHeure)));
        txtMenuHeure_coutSante.setText(joueur.getSanteMentale() + " --► " + (joueur.getSanteMentale() - (devoirSelectionne.getCoutSante() * nbHeure)));
        txtMenuHeure_HeureSuivante.setText(temps.getHeure() + "h" + temps.getMinute() + "  --►  " + (temps.getHeure() + nbHeure) + "h" + temps.getMinute());//Bug d'heure
    }

    //Met à jour un DonutProgess à l'aide d'une animation
    private void UpdateProgressAnimation(DonutProgress progressBar, int debut, int fin, int duree){
        animatedProgress = new AnimatedProgress(progressBar, debut, 0);
        animatedProgress.setInterpolator(new LinearInterpolator());
        animatedProgress.setDuration(duree);
        progressBar.startAnimation(animatedProgress);
    }

    //Met à jour tous les éléments qui sont changeable selon le temps.
    private void UpdateElementsTemporels(){
        if(temps.nouveauJour){
            partie.ChangeCoursJour(temps.jourSemaine);
            coursAdapter.notifyDataSetChanged();

            AjusterListView(lvCours, partie.getListCours());

            FinJeu();
        }
    }

    //Met à jour les textes et les progress bars.
    private void UpdateText(){
        txtFaim.setText("" + joueur.getFaim() + "%");
        txtArgent.setText("" + joueur.getArgent());
        txtEnergie.setText("" + joueur.getEnergie() + "%");
        txtSante.setText("" + joueur.getSanteMentale() + "%");
        txtHeure.setText("" + temps.getHeure() + "h" + temps.getMinute());
        txtJourSemaine.setText("" + temps.getJourSemaine());
        txtNbJour.setText("Jour " + temps.getJour());

        progressSante.setProgress(joueur.getSanteMentale());
        progressFaim.setProgress(joueur.getFaim());
        progressEnergie.setProgress(joueur.getEnergie());
    }




    //Permet aux sous menus d'être scrollable (Utile s'il y a plus que 3 éléments).
    private void ScrollFocus(){
        if(lvNourriture.getCount() > 3){
            lvNourriture.setOnTouchListener(new ListView.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                    v.onTouchEvent(event);
                    return true;
                }
            });
        }
    }

    //Ajuste le Height du ListView selon le nombre d'élément à l'intérieur.
    private void AjusterListView(ListView lv, ArrayList list){
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        float hauteurItem = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
        int height = 0;

        for(int i = 0; i < list.size(); i++){
            height += hauteurItem + lv.getDividerHeight();
        }

        params.height = height;
        lv.setLayoutParams(params);
    }




    //Met fin à l'examen lorsque le temps est écoulé.
    private void TempsExamenEcoule(){
        animatedProgress.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                messageBox.Show("Examen terminé", "Vous avez terminé l'examen avec une note de " + examEnCours.getPourcentage() + "%");
                OuvreFerme((View)findViewById(R.id.menuExam));
                partie.getListExamenTermine().add(examEnCours);
                partie.getListExamen().remove(examEnCours);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //Gère la fin du jeu.
    private void FinJeu(){
        if(temps.getJour() == 9){
            setContentView(R.layout.layout_final);
            txtMoyenneGeneral = (TextView)findViewById(R.id.moyenneGenerale);
            txtMessageFin = (TextView)findViewById(R.id.txtMessageFin);

            if(partie.MoyenneExamen() >= 60)
                txtMessageFin.setText("Vous avez réussit votre session. ");
            else
                txtMessageFin.setText("Vous avez échoué lamentablement.");

            txtMoyenneGeneral.setText("" + partie.getMoyenne() + "%");
        }

        if(joueur.santeMentale <= 0){
            setContentView(R.layout.layout_final);
            txtMoyenneGeneral = (TextView)findViewById(R.id.txtMoyenneGenerale);
            txtMessageFin = (TextView)findViewById(R.id.txtMessageFin);

            txtMessageFin.setText("420 Blaze it haha XDxd !!!1!!111");
            txtMoyenneGeneral.setText("Vous avez abandonné l'école.");
        }
    }
}
