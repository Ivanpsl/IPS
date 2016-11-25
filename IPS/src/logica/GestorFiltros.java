package logica;

import java.util.ArrayList;

import logica.Vistas.Evento;
import logica.filtros.Filtro;


public class GestorFiltros {

/**
 * Metodo que permite aplicar varios filtros a una lista
 * @param lista: Lista de eventos a la que aplicar los filtros
 * @param filtros: conjunto de filtros a aplicar
 * @return ArrayList que contiene los eventos ya filtrados
 */
	public ArrayList<Evento> filtrar(ArrayList<Evento> lista,Filtro... filtros){
		ArrayList<Evento> filtrado = lista;
		for(Filtro f : filtros){
			filtrado=f.filtrar(filtrado);
		}
		return filtrado;
		
	}

}
