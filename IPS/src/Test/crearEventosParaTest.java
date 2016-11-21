package Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import logica.Gestor;
import logica.GestorCategorias;
import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;
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
		categoria = new Categoria("seniorII" ,20, 50, 1);
		defecto.add(categoria);
		PlazoInscripcion p = new PlazoInscripcion(fecha, fin, 20.5);
		ArrayList<PlazoInscripcion> pI = new ArrayList<PlazoInscripcion>();
		
		pI.add(p);
		for(int i=0; i<24; i++){
			fin.setYear(fin.getYear()+(i-2));
			//g.crearEvento("Evento"+i, "Maraton"+i, 45-(i%2)/1.0, inicio, i+10,defecto , pI);
		
		}
		
		fechaActualJava = new java.util.Date();
		fecha = new java.sql.Date(fechaActualJava.getTime());
		fecha.setYear(100);
		Atleta a1 = new Atleta("1", "a1", "1995/12/01", 0);
		Atleta a2 = new Atleta("2", "a2", "1995/12/01", 1);
		Atleta a3 = new Atleta("3", "a3", "1995/12/01", 0);
		Atleta a4 = new Atleta("4", "a4", "1995/12/01", 0);
		Atleta a5 = new Atleta("5", "a5", "1995/12/01", 0);
		
		
		g.addAtleta(a1);
		g.addAtleta(a2);
		g.addAtleta(a3);
		g.addAtleta(a4);
		g.addAtleta(a5);
	
		g.añadirInscripcionEvento(a1, g.obtenerEventoPorId(0));
		g.añadirInscripcionEvento(a2, g.obtenerEventoPorId(0));
		g.añadirInscripcionEvento(a3, g.obtenerEventoPorId(0));
		g.añadirInscripcionEvento(a4, g.obtenerEventoPorId(0));
		g.añadirInscripcionEvento(a5, g.obtenerEventoPorId(0));
	}

}
