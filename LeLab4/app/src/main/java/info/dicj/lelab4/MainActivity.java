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
    Joueur joueur = new Joueur();

    ListView lvNourriture;
    BouffeAdapter adapter;
    ArrayList<Nourriture> listNourriture;
    Nourriture ramen = new Nourriture("Ramen", 10, 1.39);
    Nourriture pfk = new Nourriture("Méga solo baril", 20, 10.99);
    Nourriture dep = new Nourriture("Hot-Dog + Polar pop", 10, 4.49);

    boolean etat = false;
    TextView txtFaim, txtArgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNourriture = new ArrayList<Nourriture>();
        listNourriture.add(ramen);
        listNourriture.add(pfk);
        listNourriture.add(dep);

        lvNourriture = (ListView)findViewById(R.id.menuManger);
        adapter = new BouffeAdapter(this, listNourriture);
        lvNourriture.setAdapter(adapter);

        ItemClick();
        ScrollFocus();

        txtFaim = (TextView)findViewById(R.id.txtFaim);
        txtArgent = (TextView)findViewById(R.id.txtArgent);
    }

    //Effectue une action lors d'un click sur un bouton.
    public void Action(View view) {
        String text = "";
        View menuManger = findViewById(R.id.menuManger);
        //View menuDevoir = findViewById(R.id.devoir);
        switch(view.getId()){
            case R.id.manger:
                text = "manger";
                OuvreFerme(menuManger);
                break;
            case R.id.travailler:
                text = "travailler";
                break;
            case R.id.dormir:
                text = "dormir";
                break;
            case R.id.attendre:
                text = "attendre";
                break;
            case R.id.devoir:
                text = "devoir";
                //OuvreFerme(menuDevoir);
                break;
        }
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

    //Action lors d'un click sur un élément d'une listview.
    private void ItemClick(){
        //Choix de la nourriture.
        lvNourriture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                joueur.Manger(listNourriture.get(i));
                txtArgent.setText("" + joueur.argent);
                txtFaim.setText("" + joueur.faim);
                Log.i("Test", "Nom : " + listNourriture.get(i).getNom());
            }
        });
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
