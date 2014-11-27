package model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AlertaFacade extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "db.db";

	public AlertaFacade(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE ALERTA (VALOR TEXT, LATITUD DOUBLE, LONGITUD DOUBLE)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXIST ALERTA");
		onCreate(db);
	}

	public void addAlerta(Alerta alert) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues recordUser = new ContentValues();
		// recordUser.put(USUARIO_COL_ID,"null");
		recordUser.put("VALOR", alert.getValor());
		recordUser.put("LATITUD", alert.getLatitud());
		recordUser.put("LONGITUD", alert.getLongitud());

		db.insert("ALERTA", null, recordUser);
	}

	/**
	 * Get User data from DB using user email.
	 * 
	 * @param correo
	 * @return Usuario
	 */
	public ArrayList<Alerta> getAllAlertas() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Alerta> arr = new ArrayList<Alerta>();
		Cursor c = db.query("ALERTA", new String[] { "VALOR", "LATITUD",
				"LONGITUD" }, null, null, null, null, null);
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {

			arr.add(new Alerta(c.getString(0),c.getDouble(1),c.getDouble(2)));
			c.moveToNext();
		}
		c.moveToFirst();
		for (int i = 0; i < arr.size(); i++) {
			Log.i("JAJA", arr.get(i).getValor());
		}
		return arr;

	}
}
