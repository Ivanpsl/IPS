package logica.filtros;

import java.util.ArrayList;

import logica.Vistas.Evento;
/**
 * Filtra sin mostrar eventos terminados.
 *
 */
public class FiltrarTerminados implements Filtro{

	
	
	@Override
	public ArrayList<Evento> filtrar(ArrayList<Evento> eventos) {
		ArrayList<Evento> filtrado =new ArrayList<Evento>();
		for(Evento e: eventos){
			if(!e.getFinalizado()) 
				filtrado.add(e);
		}
		return filtrado;
	}
	
	
}
