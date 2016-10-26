package usuarios;

public abstract class Usuario {
	String nombreUsuario;
	String contraseña;
	
	//Aqui se pueden guardar los campos en comun que tengan los atletas y los organizadores como el nombre o dni
	
	
	public Usuario(String name, String pass){
		this.nombreUsuario = name;
		this.contraseña = pass;
	}
	
	public abstract void login();

}
