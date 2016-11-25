package logica.filtros;

import java.util.ArrayList;

import logica.Vistas.Evento;

public class FiltrarPlazoCerrado implements Filtro{

	@Override
	public ArrayList<Evento> filtrar(ArrayList<Evento> eventos) {
		ArrayList<Evento> filtrado = new ArrayList<Evento>();
		for(Evento e: filtrado){
			if(e.getUltimoPlazo()!=null) 
				filtrado.add(e);
		}
		return filtrado;
	}

	
}
