package info.dicj.lelab4;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Partie partie = new Partie(1, 6, 30, "Lundi");
    Joueur joueur = new Joueur(100, 100, 100, 0, 200);

    ListView lvNourriture;
    BouffeAdapter adapter;

    int nbHeure = 3;
    TextView txtFaim, txtArgent, txtEnergie, txtSante, txtConnaissance, txtNbHeure;
    TextView txtHeure, txtNbJour, txtJourSemaine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNourriture = (ListView)findViewById(R.id.menuManger);
        adapter = new BouffeAdapter(this, partie.getListNourriture());
        lvNourriture.setAdapter(adapter);

        ItemClick();
        ScrollFocus();

        txtFaim = (TextView)findViewById(R.id.txtFaim);
        txtArgent = (TextView)findViewById(R.id.txtArgent);
        txtEnergie = (TextView)findViewById(R.id.txtEnergie);
        txtSante = (TextView)findViewById(R.id.txtSante);
        txtNbHeure = (TextView)findViewById(R.id.nbHeure);
        txtHeure = (TextView)findViewById(R.id.txtHeure);
        txtNbJour = (TextView)findViewById(R.id.txtNbJours);
        txtJourSemaine = (TextView)findViewById(R.id.txtJourSemaine);

        UpdateText();
    }

    //Effectue une action lors d'un click sur un bouton.
    public void Action(View view) {
        View menuManger = findViewById(R.id.menuManger);
        View menuHeure = findViewById(R.id.menuHeure);
        //View menuDevoir = findViewById(R.id.devoir);
        switch(view.getId()){
            case R.id.manger:
                OuvreFerme(menuManger);
                break;
            case R.id.travailler:
                OuvreFerme(menuHeure);
                break;
            case R.id.dormir:
                joueur.Dormir();
                partie.AvancerHeure(0, 8, 0);
                UpdateText();
                break;
            case R.id.attendre:
                UpdateText();
                break;
            case R.id.devoir:
                //OuvreFerme(menuDevoir);
                break;
        }
    }

    //Action lors d'un click sur un élément d'une listview.
    private void ItemClick(){
        //Choix de la nourriture.
        lvNourriture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                joueur.Manger(partie.getListNourriture().get(i));
                UpdateText();
            }
        });
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
                if(joueur.Travailler(nbHeure))
                    partie.AvancerHeure(0, nbHeure, 0);
                UpdateText();
                break;
        }
        txtNbHeure.setText("" + nbHeure);
    }

    //Met à jour les textes.
    private void UpdateText(){
        txtFaim.setText("" + joueur.getFaim());
        txtArgent.setText("" + joueur.getArgent());
        txtEnergie.setText("" + joueur.getEnergie());
        txtSante.setText("" + joueur.getSanteMentale());
        txtHeure.setText("" + partie.getHeure() + "h" + partie.getMinute());
        txtJourSemaine.setText("" + partie.getJourSemaine());
        txtNbJour.setText("Jour " + partie.getJour());
    }

    //Permet au sous menus d'être scrollable.
    private void ScrollFocus(){
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
