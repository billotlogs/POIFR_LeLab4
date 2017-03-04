package info.dicj.lelab4;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Francis on 2017-03-03.
 */

public class NewFontTextView extends TextView {

    public NewFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Prototype.ttf"));
    }
}
