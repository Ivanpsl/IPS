package usuarios;

import java.util.ArrayList;

import logica.Vistas.Evento;

public class Organizador extends Usuario {
	ArrayList<Evento> misEventos;
	String nombre; //Puede ser el nombre de la organizaci�n o el nombre del responsable
	int id;
	
	private String tipoOrganizador = null; //Si es una persona individual lanza la excepcion en el get. 
	
	public Organizador(String nombreUsuario, String pass, String nombreOrganizac�n, int id){
		super(nombreUsuario, pass);
		this.nombre = nombreOrganizac�n;
		misEventos = new ArrayList<Evento>();
		this.id=id;
	}

	@Override
	public void login() {
		// Iniciar sesion en la app, para cuando sea necesario
		//Si no entra se puede lanzar una LoginException
		
		
	}

	public void crearEvento(Evento ev){
		misEventos.add(ev);
	}
	
	public ArrayList<Evento> getMisEventos(){
		return misEventos;
	}
	
	private void setTipo(String tipo) {
		this.tipoOrganizador = tipo;
	}
	
	public String getTipo() {
		if(tipoOrganizador == null)
			throw new IllegalStateException();
		else
			return tipoOrganizador;
	}
	public int getId(){
		return id;
	}
	
}
