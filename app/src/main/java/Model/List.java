package Model;

import android.content.res.Resources;
import android.widget.ImageView;
import android.widget.TextView;

public class List {

   private int icon;
   private String textView;

    public List(int icon, String textView) {
        this.icon = icon;
        this.textView = textView;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }
}
