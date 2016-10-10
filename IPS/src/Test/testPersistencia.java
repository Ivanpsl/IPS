package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logica.Evento;

import org.junit.Test;

import persistencia.GestorFicheros;

public class testPersistencia {

	@Test
	public void test() {
		ArrayList<Evento> ev = new ArrayList<Evento>();
		GestorFicheros gF = new GestorFicheros();
		gF.cargarEventos(ev);
		System.out.println(ev.toString());
	}

}
