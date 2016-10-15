package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logica.Evento;

import org.junit.Test;

import igu.Main;
import persistencia.GestorFicheros;

public class testPersistencia {

	@Test
	public void test() {
		ArrayList<Evento> ev = new ArrayList<Evento>();
		GestorFicheros gF = new GestorFicheros();
		//gF.cargarEventos(ev);
		System.out.println(ev.toString());
	}
	
//	@Test
//	public void testFecha(){
//		Main m = new Main();
//		assertTrue(m.comprobarFecha("05/03/2015"));
//		assertTrue(m.comprobarFecha("31/03/2015"));
//		assertFalse(m.comprobarFecha("31/04/2015"));
//	}

}
