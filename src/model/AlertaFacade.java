package model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlertaFacade extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME="db.db";
	
	public AlertaFacade(Context context) {
		super(context,DATABASE_NAME,null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE ALERTA (VALOR TEXT, LATITUD DOUBLE, LONGITUD DOUBLE)");
	}
    @Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
		db.execSQL("DROP TABLE IF EXIST ALERTA");
		onCreate(db);
	}

    public void addAlerta(Alerta alert){
    	SQLiteDatabase db=this.getWritableDatabase();
    	ContentValues recordUser=new ContentValues();
    	//recordUser.put(USUARIO_COL_ID,"null");
    	recordUser.put("VALOR", alert.getValor());
    	recordUser.put("LATITUD", alert.getLatitud());
    	recordUser.put("LONGITUD", alert.getLongitud());
    	
    	db.insert("ALERTA", null, recordUser);
    }
	
    /**
     * Get User data from DB using user email.
     * @param correo
     * @return Usuario
     */
    public ArrayList<Alerta> getAllAlertas(){
    	SQLiteDatabase db=this.getReadableDatabase();
    	ArrayList<Alerta> arr = new ArrayList<Alerta>();
    	Alerta alert = new Alerta();
    	Cursor c = db.query("ALERTA", new String[] { "VALOR", "LATITUD",
    	        "LONGITUD" }, null, null, null, null, null);
    	if (c.moveToFirst()) {
    	      do {
      	        alert.setValor(c.getString(0));
    	        alert.setLatitud(c.getDouble(1));
    	        alert.setLongitud(c.getDouble(2));
    	        arr.add(alert);
    	        
    	      } while (c.moveToNext());
    	    }
    	return arr;
    	
    }
}
