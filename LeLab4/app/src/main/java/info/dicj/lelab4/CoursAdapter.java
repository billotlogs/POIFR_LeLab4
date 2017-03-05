package info.dicj.lelab4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Francis on 2017-03-03.
 * Références
 * http://www.journaldev.com/10416/android-listview-with-custom-adapter-example-tutorial
 */

public class CoursAdapter extends ArrayAdapter<Cours> {
    ArrayList<Cours> donnee;
    Context context;

    public CoursAdapter(Context context, ArrayList<Cours> donnee){
        super(context, R.layout.choix_cours, donnee);
        this.context = context;
        this.donnee = donnee;
    }

    private static class ViewHolder {
        TextView txtNom;
        TextView txtCoutEnergie;
        TextView txtCoutSante;
        TextView txtTempsRequis;
        TextView txtCoutFaim;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CoursAdapter.ViewHolder viewHolder;
        Cours donnee = getItem(position);

        View result;

        if (convertView == null) {

            viewHolder = new CoursAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.choix_cours, parent, false);
            viewHolder.txtCoutEnergie = (TextView) convertView.findViewById(R.id.txtCoutEnergie);
            viewHolder.txtNom = (TextView) convertView.findViewById(R.id.txtNomCours);
            viewHolder.txtCoutSante = (TextView) convertView.findViewById(R.id.txtCoutSante);
            viewHolder.txtTempsRequis = (TextView) convertView.findViewById(R.id.txtTempsRequis);
            viewHolder.txtCoutFaim = (TextView) convertView.findViewById(R.id.txtCoutFaim);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CoursAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txtNom.setText("" + donnee.getNom());
        viewHolder.txtCoutEnergie.setText("-" + donnee.getCoutEnergie());
        viewHolder.txtCoutSante.setText("-" + donnee.getCoutSante());
        viewHolder.txtTempsRequis.setText("" + donnee.getDureeHeure() + "h");
        viewHolder.txtCoutFaim.setText("-" + donnee.getCoutFaim());

        return convertView;
    }
}
