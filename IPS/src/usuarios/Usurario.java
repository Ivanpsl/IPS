package usuarios;

public abstract class Usurario {
	String nombreUsuario;
	String contraseña;
	public Usurario(String name, String pass){
		this.nombreUsuario = name;
		this.contraseña = pass;
	}
	
	public abstract void login();

}
