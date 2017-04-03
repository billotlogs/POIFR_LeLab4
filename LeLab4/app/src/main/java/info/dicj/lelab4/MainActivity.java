package info.dicj.lelab4;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/*
*   Trouver un moyen pour avancer le temps a chaque action.
*   Trouver un moyen de mettre un événement random selon le type d'action.
 */
public class MainActivity extends AppCompatActivity{
    Temps temps = new Temps(1, 6, 30, "Lundi");
    Partie partie = new Partie();
    Joueur joueur = new Joueur(100, 100, 100, 0, 200, temps);

    ListView lvNourriture;
    ListView lvCours;
    BouffeAdapter bouffeAdapter;
    CoursAdapter coursAdapter;

    TextView txtFaim, txtArgent, txtEnergie, txtSante, txtConnaissance, txtNbHeure;
    TextView txtHeure, txtNbJour, txtJourSemaine;

    ProgressBar progressFaim, progressSante, progressEnergie;

    int nbHeure = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNourriture = (ListView)findViewById(R.id.menuManger);
        bouffeAdapter = new BouffeAdapter(this, partie.getListNourriture());
        lvNourriture.setAdapter(bouffeAdapter);

        lvCours = (ListView)findViewById(R.id.menuCours);
        coursAdapter = new CoursAdapter(this, partie.getListCours());
        lvCours.setAdapter(coursAdapter);

        txtFaim = (TextView)findViewById(R.id.txtFaim);
        txtArgent = (TextView)findViewById(R.id.txtArgent);
        txtEnergie = (TextView)findViewById(R.id.txtEnergie);
        txtSante = (TextView)findViewById(R.id.txtSante);
        txtNbHeure = (TextView)findViewById(R.id.nbHeure);
        txtHeure = (TextView)findViewById(R.id.txtHeure);
        txtNbJour = (TextView)findViewById(R.id.txtNbJours);
        txtJourSemaine = (TextView)findViewById(R.id.txtJourSemaine);

        progressEnergie = (ProgressBar)findViewById(R.id.progressEnergie);
        progressFaim = (ProgressBar)findViewById(R.id.progressFaim);
        progressSante = (ProgressBar)findViewById(R.id.progressSante);

        UpdateText();
        Action();
        ScrollFocus();
    }

    //Défini quel menu d'action doit ouvrir lors d'un click sur un bouton du scrollview.
    public void OuvreMenuAction(View view) {
        View menuManger = findViewById(R.id.menuManger);
        View menuHeure = findViewById(R.id.menuHeure);
        View menuCours = findViewById(R.id.menuCours);
        View menuDevoir = findViewById(R.id.menuDevoir);

        switch(view.getId()){
            case R.id.manger:
                OuvreFerme(menuManger);
                break;
            case R.id.travailler:
                OuvreFerme(menuHeure);
                txtNbHeure.setText("" + nbHeure);
                break;
            case R.id.dormir:
                joueur.Dormir();
                UpdateElementsTemporels();
                UpdateText();
                AfficherEvenement();
                break;
            case R.id.attendre:
                //event.ChanceRealisation(90);
                //UpdateText();
                break;
            case R.id.devoir:
                OuvreFerme(menuDevoir);
                break;
            case R.id.assisterCours:
                OuvreFerme(menuCours);
                break;
            case R.id.btnFermer:
                OuvreFerme(menuHeure);
                break;
        }
    }

    //Action lors d'un click sur un élément d'une listview (ClickListeners).
    private void Action(){
        //Choix de la nourriture.
        lvNourriture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                joueur.Manger(partie.getListNourriture().get(i));
                UpdateText();
            }
        });

        //Choix du cours.
        lvCours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cours coursChoisi = partie.getListCours().get(i);

                if(joueur.AssisterCours(coursChoisi)){
                    for (Devoir devoir : partie.getListDevoirsBD()) {
                        if((devoir.getCours() == coursChoisi) && (temps.getJour() == devoir.getJourAttribution())){
                            partie.getListDevoirsBD().add(devoir);
                        }
                    }
                    UpdateElementsTemporels();
                    UpdateText();
                }
            }
        });
    }

    //Permet de choisir le nombre d'heures travaillées.
    public void HeureTravail(View view){
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

    //Affiche le text de l'événement.
    private void AfficherEvenement(){
        if(joueur.getEvent() != null)
            Toast.makeText(MainActivity.this, joueur.getEvent().getText(), Toast.LENGTH_SHORT).show();
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

    //Permet aux sous menus d'être scrollable.
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

    //Met à jour tous les éléments qui sont changeable selon le temps.
    private void UpdateElementsTemporels(){
        if(temps.nouveauJour){
            partie.ChangeCoursJour(temps.jourSemaine);
            coursAdapter.notifyDataSetChanged();

            AjusterListView(lvCours);
        }
    }

    //Ajuste le Height du ListView selon le nombre d'élément à l'intérieur.
    private void AjusterListView(ListView lv){
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        float hauteurItem = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
        int height = 0;

        for(int i = 0; i < coursAdapter.getCount(); i++){
            height += hauteurItem + lv.getDividerHeight();
        }

        params.height = height;
        lv.setLayoutParams(params);
    }
}
