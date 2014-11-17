package register_listener;

import register_dialog.RegisterDialog;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.bikeapp.Main;

public class OpenListener implements OnClickListener{
	
	Main main;
	public OpenListener(Main main){
		this.main=main;
	}
	
	@Override
	public void onClick(View v) {
		new RegisterDialog(this.main).show();
	}
}
