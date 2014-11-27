package com.example.bikeapp;

import model.Usuario;
import model.UsuarioFacade;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import components.TextEdit;
import components.TextView;

public class TipsListAdapter extends BaseExpandableListAdapter {
	Activity activity;
	private String selecteditem;
	private TextEdit pwd1;
	private TextEdit pwd2;
	private EditText pwddelete;

	public TipsListAdapter(Activity a) {
		this.activity = a;
	}

	private String[] groups = { "Agregar Ciudad", "Cambiar Contraseña",
			"Privacidad", "Eliminar Cuenta" };

	private String[][] children = { { "Ingresar Región", "Ingresar Ciudad" },
			{ "Nueva Contraseña" }, { "Estado", "Foto" },
			{ "Contraseña"} };

	@Override
	public int getGroupCount() {
		return groups.length;
	}

	@Override
	public int getChildrenCount(int i) {
		return children[i].length;
	}

	@Override
	public Object getGroup(int i) {
		return groups[i];
	}

	@Override
	public Object getChild(int i, int i1) {
		return children[i][i1];
	}

	@Override
	public long getGroupId(int i) {
		return i;
	}

	@Override
	public long getChildId(int i, int i1) {
		return i1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
		TextView textView = new TextView(this.activity);
		textView.setText(getGroup(i).toString());
		textView.setPadding(70, 70, 70, 70);

		textView.setTextColor(Color.rgb(158, 158, 159));
		textView.setTextSize(18);

		textView.setBackgroundColor(Color.WHITE);
		return textView;
	}

	@Override
	public View getChildView(int i, int i1, boolean b, View view,
			ViewGroup viewGroup) {
		RelativeLayout rl = new RelativeLayout(this.activity);
		rl.setGravity(Gravity.CENTER);
		if (i == 0) {
			Spinner sp = new Spinner(this.activity);
			ArrayAdapter adapter = null;
			if (i1 == 0) {
				adapter = ArrayAdapter.createFromResource(this.activity,
						R.array.regiones, android.R.layout.simple_spinner_item);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sp.setAdapter(adapter);
				SharedPreferences settings = activity.getSharedPreferences(
						"Bikeapp", 0);
				String username = settings.getString("username", null);

				UsuarioFacade uf = new UsuarioFacade(activity);
				Usuario user = uf.get(username);

				int position = adapter.getPosition(user.getRegion());
				if (user.getRegion() == null) {
					position = 0;
				}
				sp.setSelection(position);
				sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView adapter, View v,
							int i, long lng) {

						selecteditem = adapter.getItemAtPosition(i).toString();
						if (selecteditem.equals(adapter.getItemAtPosition(0))) {
							selecteditem = null;
						}
						SharedPreferences settings = activity
								.getSharedPreferences("Bikeapp", 0);
						String username = settings.getString("username", null);

						UsuarioFacade uf = new UsuarioFacade(activity);
						Usuario user = uf.get(username);
						user.setRegion(selecteditem);
						int x = uf.editarUser(user);
						// or this can be also right: selecteditem = level[i];
					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
				rl.addView(sp);
			}
			if (i1 == 1) {
				adapter = ArrayAdapter.createFromResource(this.activity,
						R.array.ciudades, android.R.layout.simple_spinner_item);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sp.setAdapter(adapter);
				SharedPreferences settings = activity.getSharedPreferences(
						"Bikeapp", 0);
				String username = settings.getString("username", null);

				UsuarioFacade uf = new UsuarioFacade(activity);
				Usuario user = uf.get(username);

				int position = adapter.getPosition(user.getCiudad());
				if (user.getCiudad() == null) {
					position = 0;
				}
				sp.setSelection(position);
				sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView adapter, View v,
							int i, long lng) {

						selecteditem = adapter.getItemAtPosition(i).toString();
						if (selecteditem.equals(adapter.getItemAtPosition(0))) {
							selecteditem = null;
						}
						SharedPreferences settings = activity
								.getSharedPreferences("Bikeapp", 0);
						String username = settings.getString("username", null);

						UsuarioFacade uf = new UsuarioFacade(activity);
						Usuario user = uf.get(username);
						user.setCiudad(selecteditem);
						int x = uf.editarUser(user);
						// or this can be also right: selecteditem = level[i];
					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
				rl.addView(sp);
			}
		}
		if (i == 1) {
			LinearLayout ly = new LinearLayout(activity);
			
			pwd1 = new TextEdit(this.activity);
			pwd1.setHint("Nueva contraseña");
			pwd1.setTextSize(16);
			pwd1.setPadding(50, 50, 50, 50);
			pwd1.setTransformationMethod(PasswordTransformationMethod
					.getInstance());
			pwd2 = new TextEdit(this.activity);
			pwd2.setHint("Confirma nueva contraseña");
			pwd2.setTextSize(16);
			pwd2.setPadding(50, 50, 50, 50);
			pwd2.setTransformationMethod(PasswordTransformationMethod
					.getInstance());
			Button x = new Button(this.activity);
			x.setText("Guardar");
			x.setBackgroundDrawable(this.activity.getResources().getDrawable(
					R.drawable.gradient_orange));
			x.setPadding(0, 20, 0, 0);
			x.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SharedPreferences settings = activity
							.getSharedPreferences("Bikeapp", 0);
					String username = settings.getString("username", null);

					UsuarioFacade uf = new UsuarioFacade(activity);
					Usuario user = uf.get(username);
					
					if(pwd1.getText().toString().equals(pwd2.getText().toString())){
						user.setContrasena(pwd1.getText().toString());
						uf.editarUser(user);
					}

				}

			});
			ly.setOrientation(LinearLayout.VERTICAL);
			ly.setGravity(Gravity.CENTER);
			ly.addView(pwd1);
			ly.addView(pwd2);
			ly.addView(x);
			rl.setGravity(Gravity.CENTER);
			rl.addView(ly);
		}
		if (i == 2) {
			Spinner sp = new Spinner(this.activity);
			ArrayAdapter adapter = null;
			if (i1 == 0) {
				LinearLayout ly = new LinearLayout(this.activity);
				ly.setOrientation(LinearLayout.HORIZONTAL);
				ly.setGravity(Gravity.CENTER);
				TextView tv = new TextView(this.activity, "Estado");
				adapter = ArrayAdapter.createFromResource(this.activity,
						R.array.Estado, android.R.layout.simple_spinner_item);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sp.setAdapter(adapter);
				ly.addView(tv);
				ly.addView(sp);
				rl.addView(ly);
			}
			if (i1 == 1) {
				LinearLayout ly = new LinearLayout(this.activity);
				ly.setOrientation(LinearLayout.HORIZONTAL);
				ly.setGravity(Gravity.CENTER);
				TextView tv = new TextView(this.activity, "Foto");
				adapter = ArrayAdapter.createFromResource(this.activity,
						R.array.Estado, android.R.layout.simple_spinner_item);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sp.setAdapter(adapter);
				ly.addView(tv);
				ly.addView(sp);
				rl.addView(ly);
			}
		}
		if(i==3){
			LayoutInflater mInflater = (LayoutInflater)
                    activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View v = mInflater.inflate(R.layout.eliminar_cuenta, null);
            pwddelete = (EditText) v.findViewById(R.id.pwddelete);
            Button btndelete = (Button) v.findViewById(R.id.btndelete);
            btndelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SharedPreferences settings = activity
							.getSharedPreferences("Bikeapp", 0);
					String username = settings.getString("username", null);

					UsuarioFacade uf = new UsuarioFacade(activity);
					Usuario user = uf.get(username);
					
					if(pwddelete.getText().toString().equals(user.getContrasena())){
						uf.borrarUser(user);
					}

				}

			});
            rl.addView(v);
		}

		return rl;
	}

	@Override
	public boolean isChildSelectable(int i, int i1) {
		return true;
	}

}