package info.dicj.lelab4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;

/**
 * Created by Francis on 2017-04-05.
 */

public class DevoirAdapter extends ArrayAdapter<Devoir> {
    ArrayList<Devoir> donnee;
    Context context;

    public DevoirAdapter(Context context, ArrayList<Devoir> donnee){
        super(context, R.layout.choix_devoirs, donnee);
        this.context = context;
        this.donnee = donnee;
    }

    private static class ViewHolder {
        TextView txtNomDevoir;
        TextView txtNomCours;
        DonutProgress progress;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DevoirAdapter.ViewHolder viewHolder;
        Devoir donnee = getItem(position);

        View result;

        if (convertView == null) {

            viewHolder = new DevoirAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.choix_devoirs, parent, false);
            viewHolder.txtNomDevoir = (TextView) convertView.findViewById(R.id.txtNomDevoir);
            viewHolder.txtNomCours = (TextView) convertView.findViewById(R.id.txtNomCours);
            viewHolder.progress = (DonutProgress) convertView.findViewById(R.id.completionDevoir);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DevoirAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txtNomDevoir.setText("" + donnee.getNom());
        viewHolder.txtNomCours.setText("" + donnee.getCours().getNom());
        viewHolder.progress.setDonut_progress(String.format("" + donnee.getProgression(), "#"));

        return convertView;
    }
}
