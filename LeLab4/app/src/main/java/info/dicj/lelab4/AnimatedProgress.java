package info.dicj.lelab4;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.github.lzyzsd.circleprogress.DonutProgress;

/**
 * Created by Francis on 2017-04-30.
 */

public class AnimatedProgress extends Animation {
    DonutProgress progressBar;
    int debut, fin;

    public AnimatedProgress(DonutProgress progressBar, int debut, int fin){
        this.progressBar = progressBar;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = debut + (fin - debut) * interpolatedTime;
        progressBar.setProgress((int) value);
    }
}
