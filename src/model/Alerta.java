package model;

import com.mapquest.android.maps.GeoPoint;

public class Alerta {
	public String valor;
	public double latitud;
	public double longitud;
	public Alerta(String valor, double latitud, double longitud) {
		super();
		this.valor = valor;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public Alerta() {

	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	public GeoPoint getGeopoint(){
		GeoPoint gp = new GeoPoint(latitud, longitud);
		return gp;
	}
	

}
