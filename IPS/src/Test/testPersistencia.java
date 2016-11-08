package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logica.Vistas.Atleta;
import logica.Vistas.Evento;

import org.junit.Test;

import igu.Main;
import persistencia.GestorFicheros;
import utiles.ConversorFechas;

public class testPersistencia {

//	@Test
//	public void test() {
//		ArrayList<Evento> ev = new ArrayList<Evento>();
//		GestorFicheros gF = new GestorFicheros();
//		//gF.cargarEventos(ev);
//		System.out.println(ev.toString());
//	}
	
	@Test
	public void testFecha(){
		ConversorFechas f = new ConversorFechas();
		assertEquals(21,f.fechaNacimientoEdad("14/03/1995"));
		
		Atleta a = new Atleta("1587", "fgsf", "14/03/1995", 0);
		assertEquals(21, a.getEdad());
	}

}
