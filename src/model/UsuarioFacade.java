package model;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsuarioFacade extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME="db.db";
	
	public static final String TABLA_USUARIO="USUARIO";
	public static final String USUARIO_COL_ID="IDUSUARIO";
	public static final String USUARIO_COL_NOMBRE="NOMBRE";
	public static final String USUARIO_COL_APELLIDO="APELLIDO";
	public static final String USUARIO_COL_CORREO="CORREO";
	public static final String USUARIO_COL_CONTRASENA="CONTRASENA";
	
	
	
	public UsuarioFacade(Context context) {
		super(context,DATABASE_NAME,null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE USUARIO (IDUSUARIO INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT,"+
					" APELLIDO TEXT, CORREO TEXT, CONTRASENA TEXT)");
	}
    @Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
		db.execSQL("DROP TABLE IF EXIST USUARIO");
		onCreate(db);
	}
    //VERFICAR EL USUARIO
    public Usuario login(String correo,String contrasena){
    	
    	SQLiteDatabase db=this.getReadableDatabase();
    	Usuario user = new Usuario();
	
    	String selectQuery1 = "SELECT*FROM USUARIO WHERE CORREO='"+correo+"' AND CONTRASENA='"+contrasena+"'";
	    Cursor cursor = db.rawQuery ( selectQuery1, null );
	    
	 	if (cursor.getCount()==0){
	 		Log.d("CountCursor",String.valueOf(cursor.getCount()+"Cursor vacio "));
	 		// looping through all rows and adding to list
	 		user=null;
	 	}else{
	 		Log.d("login",String.valueOf(cursor.getCount()));
			    Log.d("SQLquery",selectQuery1);
			     if (cursor.moveToFirst()) {
			         do {
			        	 user.setIdUsuario(cursor.getInt(0));
			        	 user.setNombre(cursor.getString(cursor.getColumnIndex(USUARIO_COL_NOMBRE)));
			        	 user.setApellido(cursor.getString(cursor.getColumnIndex(USUARIO_COL_APELLIDO)));
			        	 user.setCorreo(cursor.getString(cursor.getColumnIndex(USUARIO_COL_CORREO)));
			        	 user.setContrasena(cursor.getString(cursor.getColumnIndex(USUARIO_COL_CONTRASENA)));
			         } while (cursor.moveToNext());
			     }
	 	}	      
	     return user;
	     
    	
    }
 
    //Agregar nuevo usuario
    public void addUsuario(Usuario user){
    	SQLiteDatabase db=this.getWritableDatabase();
    	ContentValues recordUser=new ContentValues();
    	//recordUser.put(USUARIO_COL_ID,"null");
    	recordUser.put(USUARIO_COL_NOMBRE, user.getNombre());
    	recordUser.put(USUARIO_COL_APELLIDO,user.getApellido());
    	recordUser.put(USUARIO_COL_CORREO,user.getCorreo());
    	recordUser.put(USUARIO_COL_CONTRASENA,user.getContrasena());
    	
    	db.insert("USUARIO", null, recordUser);
    }	
	

}
