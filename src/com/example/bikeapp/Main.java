package com.example.bikeapp;

import model.Usuario;
import model.UsuarioFacade;
import register_dialog.RegisterDialog;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import components.Button;
import components.ImageView;
import components.TextEdit;
import components.TextView;


@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") 
public class Main extends Activity {

	public int width;
	public int height;
	public TextEdit userEdit;
	public TextEdit passwordEdit;
	final UsuarioFacade db=new UsuarioFacade(this);
	
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	this.width =getWindowManager().getDefaultDisplay().getWidth();
		this.height=getWindowManager().getDefaultDisplay().getHeight();
		
		/**
		 * Activity Layouts
		 */
        RelativeLayout mainLayout=new RelativeLayout(this);
        LinearLayout layout=new LinearLayout(this);
        
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        
        /**
         * Graphic content
         * Add textEdit of user and password to main layout
         */
        new ImageView(this,layout,this.width*0.5f,this.height*0.15f,R.raw.logo);
        this.userEdit=new TextEdit(this,layout,"Usuario",R.drawable.oval);
        this.passwordEdit=new TextEdit(this,layout,"Password",R.drawable.oval);
        
        Button ingresar = new Button(this,layout,"Entrar",R.drawable.gradient_orange);
        TextView text=new TextView(this,"Regístrate en Bikeapp", 16, (Object) this,"clickRegister");
        
        mainLayout.addView(layout,-1,-1);
        mainLayout.addView(text);    
   
        /**
         * Layout configurations
         */
        layout.setPadding((int)(this.width*0.15),0, (int)(this.width*0.15),0);
        LinearLayout.LayoutParams margin=new LinearLayout.LayoutParams(-1,-2);
        margin.bottomMargin=(int)(this.height*0.025);     
  
        this.userEdit.setSingleLine();
        this.passwordEdit.setSingleLine();
        this.userEdit.setLayoutParams(margin);
        this.passwordEdit.setLayoutParams(margin);
        this.passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                
        RelativeLayout.LayoutParams paramsLayout=new RelativeLayout.LayoutParams(-1,-1);        
        RelativeLayout.LayoutParams paramsText=new RelativeLayout.LayoutParams(-2,-2);
        
        text.setId(18);
        paramsLayout.addRule(RelativeLayout.ABOVE,18);
        paramsText.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramsText.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        paramsText.bottomMargin=(int)(this.height*0.05);
        
        text.setLayoutParams(paramsText);
        layout.setLayoutParams(paramsLayout);
        
        /**
         * TEMPORARY FIX
         */
//        this.tmpHelp();
        /**
         * END OF TEMPORARY FIX
         */
        
        
        setContentView(mainLayout);
        ingresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Perform action on click
            	//int buscar=new UsuarioFacade().findByCorreo(txtECorreo.getText().toString(),txtEContrasena.getText().toString());
            	if(userEdit.getText().toString().isEmpty() && passwordEdit.getText().toString().isEmpty() ){
            		AlertDialog alertDialog = new AlertDialog.Builder(Main.this).create();
            		alertDialog.setTitle("Datos inválidos");
            		alertDialog.setMessage("Verifique los campos");
            		
            		alertDialog.show();
            	} else{//txtECorreo.getText().toString().equals("b") && txtEContrasena.getText().toString().equals("b")){
            	//else if(buscar!=0){
            		
            		Usuario user=new Usuario();
            		user=db.login(userEdit.getText().toString().toLowerCase(), passwordEdit.getText().toString().toLowerCase());
            		if(user!=null){
	                    Intent l = new Intent(Main.this, Perfil.class);
	                    
	                    //enviar datos a activity con usuario
	                    l.putExtra("idUsuario", user.getIdUsuario());
	                    l.putExtra("nombre", user.getNombre());
	                    l.putExtra("apellido", user.getApellido());
	                    l.putExtra("correo", user.getCorreo());
	                    
	                    startActivity(l);
	                }else{
	                	AlertDialog alertDialog = new AlertDialog.Builder(Main.this).create();
                		alertDialog.setTitle("Error");
                		alertDialog.setMessage("Este usuario no existe");//+buscar);
                		alertDialog.show();       	
                    }
            	}
               }
        }); // END OF ingresarSetOnClickListener()
    }

    /**
     * Open user register window. This allow user to
     * register into app.
     * 
     * @param main Main Activity
     */
    public void clickRegister(Main main){
    		new RegisterDialog(main).show();
    }
    
    public void tmpHelp() {
    	this.userEdit.setText("@");
    }
  
}
