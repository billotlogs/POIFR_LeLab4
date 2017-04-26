package info.dicj.lelab4;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by utilisateur on 25/04/2017.
 */
public class MessageBox extends Dialog implements View.OnClickListener {
    Activity activity;
    TextView titre, contenu;
    Button btnOk;

    public MessageBox(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagebox_layout);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        btnOk = (Button)findViewById(R.id.btnMsgBoxOK);
        btnOk.setOnClickListener(this);

        titre = (TextView)findViewById(R.id.txtMsgBoxTitre);
        contenu = (TextView)findViewById(R.id.txtMsgBoxContenu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMsgBoxOK:
                dismiss();
                break;
        }
    }

    public void Show(String titre, String contenu) {
        show();
        this.titre.setText(titre);
        this.contenu.setText(contenu);
    }


}
