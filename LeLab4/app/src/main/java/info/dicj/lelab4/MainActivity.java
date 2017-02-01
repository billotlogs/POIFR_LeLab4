package info.dicj.lelab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Joueur joueur = new Joueur();
    ArrayList<Nourriture> nourritures;
    Nourriture ramen = new Nourriture("Ramen", 10, 1.49);
    boolean etat = false;
    TextView txtFaim, txtArgent;
    ListView choixNourriture;
    BouffeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nourritures = new ArrayList<Nourriture>();
        nourritures.add(ramen);

        choixNourriture = (ListView)findViewById(R.id.menuManger);
        adapter = new BouffeAdapter(this, nourritures);
        choixNourriture.setAdapter(adapter);

        txtFaim = (TextView)findViewById(R.id.txtFaim);
        txtArgent = (TextView)findViewById(R.id.txtArgent);
    }

    public void Action(View view) {
        String text = "";
        View menuManger = findViewById(R.id.menuManger);
        //View menuDevoir = findViewById(R.id.devoir);
        switch(view.getId()){
            case R.id.manger: text = "manger";
                              OuvreFerme(menuManger);
                break;
            case R.id.travailler: text = "travailler";
                break;
            case R.id.dormir: text = "dormir";
                break;
            case R.id.attendre: text = "attendre";
                break;
            case R.id.devoir: text = "devoir";
                              //OuvreFerme(menuDevoir);
                break;
        }
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void ChoixNourriture(View view){
        switch(view.getId()){
            //case R.id.ramen : joueur.Manger(ramen);
        }
        txtFaim.setText("" + joueur.faim);
        txtArgent.setText("" + joueur.argent);
    }

    //Ouvre ou ferme un menu d'actions
    private void OuvreFerme(View menu){
        if(!etat){
            etat = true;
            menu.setVisibility(menu.VISIBLE);
        }
        else{
            etat = false;
            menu.setVisibility(menu.GONE);
        }
    }
}
