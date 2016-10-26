package usuarios;

import java.util.ArrayList;

import logica.Evento;

public class Organizador extends Usurario {
	ArrayList<Evento> misEventos;
	String nombre; //Puede ser el nombre de la organización o el nombre del responsable
	public Organizador(String nombreUsuario, String pass, String nombreOrganizacón){
		super(nombreUsuario, pass);
		this.nombre = nombreOrganizacón;
		misEventos = new ArrayList<Evento>();
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
}
