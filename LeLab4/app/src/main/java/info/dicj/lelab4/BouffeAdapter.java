package info.dicj.lelab4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Francis on 2017-01-31.
 */

public class BouffeAdapter extends ArrayAdapter<Nourriture>{
    ArrayList<Nourriture> donnee;
    Context context;

    public BouffeAdapter(Context context, ArrayList<Nourriture> donnee){
        super(context, R.layout.choix_manger, donnee);
        this.context = context;
        this.donnee = donnee;
    }


    private static class ViewHolder {
        TextView txtNom;
        TextView txtAlimentation;
        TextView txtPrix;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Nourriture donnee = getItem(position);

        View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.choix_manger, parent, false);
            viewHolder.txtAlimentation = (TextView) convertView.findViewById(R.id.alimentation_nourriture);
            viewHolder.txtNom = (TextView) convertView.findViewById(R.id.nom_nourriture);
            viewHolder.txtPrix = (TextView) convertView.findViewById(R.id.prix_nourriture);

            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtNom.setText(donnee.getNom());
        viewHolder.txtPrix.setText("" + donnee.getPrix());
        viewHolder.txtAlimentation.setText("+" + donnee.getAlimentation());

        return convertView;
    }
}
