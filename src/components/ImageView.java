package components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class ImageView extends android.widget.ImageView{

	@SuppressLint("NewApi")
	public ImageView(Context context,LinearLayout layout,float ancho,float largo,int raw) {
		super(context);
		SVG svg = SVGParser.getSVGFromResource(getResources(), raw);
		
	    this.setImageDrawable(svg.createPictureDrawable());
	    this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

	    layout.addView(this,(int)ancho,(int)largo);
	
	}

}
