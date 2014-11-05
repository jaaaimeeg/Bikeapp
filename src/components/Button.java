package components;

import java.lang.reflect.Method;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;


public class Button extends android.widget.Button implements OnClickListener {

	private String methodName;
	private Object object;
	
	public Button(Context context) {
		super(context);
		this.setTextColor(Color.parseColor("#ffffff"));
		this.setTextSize(20);
	}

	@SuppressWarnings("deprecation")
	public Button(Context context, LinearLayout layout, String text,int drawable) {
		this(context);
		this.setText(text);
		this.setBackgroundDrawable(context.getResources().getDrawable(drawable));
		layout.addView(this);
	}


	@SuppressWarnings("deprecation")
	public Button(Context context, LinearLayout layout, String text,int drawable,Object object, String methodName) {
		this(context);
		this.object=object;
		this.methodName = methodName;
		this.setText(text);
		this.setBackgroundDrawable(context.getResources().getDrawable(drawable));
		layout.addView(this);
		this.setOnClickListener(this);
	}

	
	@SuppressWarnings("deprecation")
	public Button(Context context, LinearLayout layout, String text,int drawable, String methodName) {
		this(context);
		this.methodName = methodName;
		//this.classCall=context.getClass();
		this.setText(text);
		this.setBackgroundDrawable(context.getResources().getDrawable(drawable));
		layout.addView(this);
		this.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		try {
			Class <?> class_=object.getClass();
			Object object =class_.newInstance();
			Method method =class_.getDeclaredMethod(this.methodName,class_);
			method.invoke(object, this.object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
