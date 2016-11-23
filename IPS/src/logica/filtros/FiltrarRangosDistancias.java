package logica.filtros;

import java.util.ArrayList;

import logica.Vistas.Evento;

public class FiltrarRangosDistancias implements Filtro{

	double inicio=0;
	double fin=Double.POSITIVE_INFINITY ;
	public FiltrarRangosDistancias(int inicio, int fin){
		this.inicio=inicio;
		this.fin=fin;
	}
	
	@Override
	public ArrayList<Evento> filtrar(ArrayList<Evento> eventos) {
		ArrayList<Evento> filtrada = new ArrayList<Evento>();
		for(Evento e: eventos){
			if(e.getDistancia()>inicio && e.getDistancia()<fin)
				filtrada.add(e);
		}
		return filtrada;
	}

}
