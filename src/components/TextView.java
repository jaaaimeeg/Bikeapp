package components;

import java.lang.reflect.Method;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class TextView extends android.widget.TextView implements OnClickListener{
	
	private Object object;
	private String methodName;
	
	public TextView(Context context) {
		super(context);
		this.object=null;
	}
	
	public TextView(Context context,String text) {
		this(context);
		this.setText(text);
	}
	
	public TextView(Context context,String text,int size) {
		this(context,text);
		this.setText(text);
		this.setTextSize(size);
	}
	
	public TextView(Context context,String text,int size, int drawable) {
		this(context,text);
		this.setText(text);
		this.setTextSize(size);
		this.setBackgroundDrawable(context.getResources().getDrawable(drawable));
	}
	
	public TextView(Context context,String text,int size,Object object,String methodName) {
		this(context,text,size);
		this.object=object;
		this.methodName=methodName;	
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
