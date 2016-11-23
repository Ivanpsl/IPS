package logica.filtros;

import java.util.ArrayList;

import logica.Vistas.Evento;

public interface Filtro {

	public ArrayList<Evento> filtrar(ArrayList<Evento> eventos);
	
}
