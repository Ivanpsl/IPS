package usuarios;

public abstract class Usurario {
	String nombreUsuario;
	String contrase�a;
	public Usurario(String name, String pass){
		this.nombreUsuario = name;
		this.contrase�a = pass;
	}
	
	public abstract void login();

}
