package Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import logica.Gestor;
import logica.GestorCategorias;
import logica.Vistas.Categoria;
import logica.Vistas.PlazoInscripcion;
import oracle.sql.SQLUtil;

import org.junit.Test;

public class crearEventosParaTest {


	Gestor g = new Gestor();
	@Test
	public void testAñadirEventos() {
		java.util.Date fechaActualJava = new java.util.Date();
		Date fecha = new java.sql.Date(fechaActualJava.getTime());
		Date inicio=fecha;
		Date fin= fecha;
		ArrayList<Categoria> defecto = new ArrayList<Categoria>();
		Categoria categoria = new Categoria("SeniorI" ,18, 20, 1);
		defecto.add(categoria);
		PlazoInscripcion p = new PlazoInscripcion(fecha, fin, 20.5);
		ArrayList<PlazoInscripcion> pI = new ArrayList<PlazoInscripcion>();
		pI.add(p);
		for(int i=0; i<24; i++){
			fin.setYear(fin.getYear()+(i-5));
			g.crearEvento("Evento"+i, "Maraton"+i, 45-(i%2)/1.0, inicio, i+10,defecto , pI);
		
		}
	}

}
