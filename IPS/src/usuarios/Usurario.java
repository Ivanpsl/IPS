package usuarios;

public abstract class Usurario {
	String nombreUsuario;
	String contraseņa;
	public Usurario(String name, String pass){
		this.nombreUsuario = name;
		this.contraseņa = pass;
	}
	
	public abstract void login();

}
