package components;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TextEdit extends EditText {

	public TextEdit(Context context) {
		super(context);
	}
	
	@SuppressWarnings("deprecation")
	public TextEdit(Context context,LinearLayout layout,String text,int drawable) {
		super(context);
		this.setHint(text);
		this.setBackgroundDrawable(context.getResources().getDrawable(drawable));
		layout.addView(this);
	}
	
	
}
