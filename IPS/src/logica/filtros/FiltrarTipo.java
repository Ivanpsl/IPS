package logica.filtros;

import java.util.ArrayList;

import logica.Vistas.Evento;

public class FiltrarTipo implements Filtro{

	String tipo;
	public FiltrarTipo(String tipo) {
		this.tipo=tipo;
	}
	@Override
	public ArrayList<Evento> filtrar(ArrayList<Evento> eventos) {
		if(tipo.equals("TODOS")) return eventos;
		ArrayList<Evento> filtrado = new ArrayList<Evento>();
		for(Evento e: eventos)
			if(e.getTipo().toUpperCase().equals(tipo))
				filtrado.add(e);
		return filtrado;
	}

}
