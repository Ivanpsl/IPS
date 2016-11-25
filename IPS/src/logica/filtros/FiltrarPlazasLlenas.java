package logica.filtros;

import java.util.ArrayList;

import logica.Vistas.Evento;

/**
 *Filtra sin mostrar los eventos en los que ya no queden plazas disponibles
 *
 */
public class FiltrarPlazasLlenas implements Filtro {

	@Override
	public ArrayList<Evento> filtrar(ArrayList<Evento> eventos) {
		ArrayList<Evento> filtrado = new ArrayList<Evento>();
		for(Evento e: eventos){
			if(e.getPlazasDisponibles()>0){
				filtrado.add(e);
			}
		}
		
		return filtrado;
	}

}
