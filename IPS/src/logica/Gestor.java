package logica;

import java.util.ArrayList;

import persistencia.ConexionBD;
import persistencia.GestorFicheros;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	ArrayList<Atleta> atletas;
	private ConexionBD bd = new ConexionBD();
	private GestorFicheros gF = new GestorFicheros();
	
	
	public Gestor(){
		this.eventos  = new ArrayList<Evento>();
		this.atletas= new ArrayList<Atleta>();
		cargarDatos();
	}
	
	private void cargarDatos(){
		bd.cargarDatos(this);
	}
	public ArrayList<Evento> getEventos(){
		return eventos;
	}
	
	public ArrayList<Atleta> getAtletas(){
		return atletas;
	}
	public void mostrarEventosDisponibles(){
		for(Evento evento : eventos){
			System.out.println(evento.toString());
		}
	}
	public ArrayList<Evento> getEventosDisponibles(){
		return this.eventos;
	}
	
	public void mostrarInscritosDeEvento(int id){
		Evento ev = null;
		for(Evento e : eventos){
			if(e.getId() == id){
				ev = e;
			}
		}
		if(ev == null){
			System.out.println("No se ha encontrado el evento");
		}else{
			ArrayList<Inscripcion> inscritos = ev.getInscritosEvento();
			for(Inscripcion a: inscritos){
				System.out.println(a.toString());
			}
		}
	}
	
	
}
