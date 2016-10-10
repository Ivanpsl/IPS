package logica;

import java.util.ArrayList;

import persistencia.ConexionBD;
import persistencia.GestorFicheros;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	private ConexionBD bd = new ConexionBD();
	private GestorFicheros gF = new GestorFicheros();
	
	public Gestor(){
		this.eventos  = new ArrayList<Evento>();
		
		//--Se usa gF a la hora de cargar eventos desde los ficheros de datos
		//--Se usa bd cuando queramos cargar los eventos desde la base de datos (Aun no funciona)
		gF.cargarEventos(eventos);
		//bd.cargarEventos(eventos);
		
		cargarEventosDisponibles();
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
}
