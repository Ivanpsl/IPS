package logica.filtros;

import java.util.ArrayList;

import logica.Vistas.Evento;
/**
 * Filtro de eventos terminados.
 * 	Si terminados == true incluye eventos terminados
 * 	Si terminados == false no incluye eventos terminados
 *
 */
public class FiltrarTerminados implements Filtro{

	boolean terminados=true;
	public FiltrarTerminados(boolean teminados){
		this.terminados=terminados;
	}
	
	@Override
	public ArrayList<Evento> filtrar(ArrayList<Evento> eventos) {
		ArrayList<Evento> filtrado =new ArrayList<Evento>();
		for(Evento e: eventos){
			if(e.getFinalizado() && terminados || !e.getFinalizado()) 
				filtrado.add(e);
		}
		return filtrado;
	}
	
	
}
