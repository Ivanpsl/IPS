package usuarios;

public abstract class Usuario {
	String nombreUsuario;
	String contrase�a;
	
	//Aqui se pueden guardar los campos en comun que tengan los atletas y los organizadores como el nombre o dni
	
	
	public Usuario(String name, String pass){
		this.nombreUsuario = name;
		this.contrase�a = pass;
	}
	
	public abstract void login();

}
