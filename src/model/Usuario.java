package model;

public class Usuario {
	public int idUsuario;
	public String nombre;
	public String apellido;
	public String correo;
	public String contrasena;
	public String estado;
	public String region;
	public String ciudad;
	
	public Usuario(int idUsuario, String nombre,String apellido,String correo,String contrasena){
		this.idUsuario=idUsuario;
		this.nombre=nombre;
		this.apellido=apellido;
		this.correo=correo;
		this.contrasena=contrasena;
		
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public String toString(){
		return this.nombre+" "+this.apellido;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado() {
		return estado;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegion() {
		return region;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCiudad() {
		return ciudad;
	}
	public CharSequence getFullName() {
		return this.nombre + " " + this.apellido;
	}
	

}
