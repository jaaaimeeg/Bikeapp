package register_dialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Usuario;
import model.UsuarioFacade;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.bikeapp.Main;
import com.example.bikeapp.R;

import components.Button;
import components.ImageView;
import components.TextEdit;

public class RegisterDialog extends Dialog implements OnClickListener{
	
	final UsuarioFacade mydb = new UsuarioFacade(getContext());
	static boolean close = false;
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressWarnings("deprecation")
	public RegisterDialog(Context context) {
		super(context,android.R.style.Theme_Translucent_NoTitleBar);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		int width =((Main)context).getWindowManager().getDefaultDisplay().getWidth();
		int height=((Main)context).getWindowManager().getDefaultDisplay().getHeight();
		
		/**
		 * Dialog Layouts
		 */
		LinearLayout mainLayout=new LinearLayout(context);
		LinearLayout layoutV = new LinearLayout(context);
		layoutV.setPadding((int)(width*0.15),0, (int)(width*0.15),0);
		this.setContentView(mainLayout);

		this.getWindow().getAttributes().width=width;
		this.getWindow().getAttributes().height=height;

        LinearLayout.LayoutParams margin=new LinearLayout.LayoutParams(-1,-2);
        margin.bottomMargin=(int)(height*0.025);   
        
		layoutV.setOrientation(LinearLayout.VERTICAL);
		layoutV.setGravity(Gravity.CENTER);
		
		/**
		 * Layout attributes
		 */
        new ImageView(context,layoutV,(int)(width*0.5f),(int)(height*0.15f),R.raw.logo);
		final TextEdit nombre = new TextEdit(context, layoutV, "Nombre", R.drawable.oval);
		final TextEdit apellido = new TextEdit(context, layoutV, "Apellido", R.drawable.oval);
		final TextEdit email = new TextEdit(context, layoutV, "Correo Electronico", R.drawable.oval);
		final TextEdit pass = new TextEdit(context, layoutV, "Contraseña", R.drawable.oval);
		
        nombre.setLayoutParams(margin);
        apellido.setLayoutParams(margin);
        email.setLayoutParams(margin);
        pass.setLayoutParams(margin);
		pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
		
		
		/**
		 * TEMPORARY FIX 
		 */
		email.setText("@");
		/**
		 * END OF TEMPORARY FIX
		 */
		Button registrar = new Button(context, layoutV, "Registrar", R.drawable.gradient_orange);
		
		layoutV.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.oval));
		
		//layoutV.setBackgroundColor(Color.WHITE);
		mainLayout.setGravity(Gravity.CENTER);
		mainLayout.setBackgroundColor(Color.parseColor("#70000000"));
		mainLayout.addView(layoutV, (int)(width*0.95), (int)(height*0.92));
		mainLayout.setOnClickListener(this);
		layoutV.setOnClickListener(null);
		
		registrar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (email.getText().toString().isEmpty()
						&& pass.getText().toString().isEmpty()) {
					AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
					alertDialog.setTitle("Datos Faltantes");
					alertDialog.setMessage("Verificar datos");

					alertDialog.show();
				} else {
					if (checkEmail(email.getText().toString())) {
						Usuario user = new Usuario(0, nombre.getText()
								.toString(), apellido
								.getText().toString(), email
								.getText().toString(),
								pass.getText().toString());
						mydb.addUsuario(user);
						
						/*
						 * Alert Dialog
						 */
						AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
						alertDialog.setTitle("Registro");
						alertDialog.setMessage("Registro Exitoso");
						alertDialog.show();
						
					} else {
						AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
						alertDialog.setTitle("Registro");
						alertDialog.setMessage("Correo Inválido");
						alertDialog.show();
					}
				}
			}
		});
		
		if(close){
			this.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		this.dismiss();
	}
	
	/**
	 * Verifies that user email is valid.
	 * @param email string to be verified
	 * @return boolean
	 */
	public static boolean checkEmail(String email) {
		// Establecer el patron
		Pattern p = Pattern.compile("[-\\w\\.]+@[\\.\\w]+\\.\\w+");
		// Asociar el string al patron
		Matcher m = p.matcher(email);
		// Comprobar si encaja
		return m.matches();
	}
}
