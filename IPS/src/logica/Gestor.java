package logica;

import java.util.ArrayList;

import persistencia.ConexionBD;
import persistencia.GestorFicheros;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	private ConexionBD bd = new ConexionBD();
	private GestorFicheros gF = new GestorFicheros();
	ArrayList<Atleta> atletas;
	
	public Gestor(){
		this.eventos  = new ArrayList<Evento>();
		
		//--Se usa gF a la hora de cargar eventos desde los ficheros de datos
		//--Se usa bd cuando queramos cargar los eventos desde la base de datos (Aun no funciona)
		gF.cargarEventos(eventos);
		//bd.cargarEventos(eventos);
		
		//cargarEventosDisponibles();
	}
	
	private void cargarEventosDisponibles(){
		bd.cargarEventos(eventos);
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
			ArrayList<Inscrito> inscritos = ev.getInscritosEvento();
			for(Inscrito a: inscritos){
				System.out.println(a.toString());
			}
		}
	}
	
	
}
