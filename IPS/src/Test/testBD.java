package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import logica.Atleta;
import logica.Evento;
import logica.Gestor;
import logica.Inscripcion;

import org.junit.Test;

import persistencia.ConexionBD;
import utiles.ConversorFechas;

public class testBD {

	ConexionBD bd = new ConexionBD();
	Gestor g = new Gestor();
	
	@Test
	public void testConecta() {
		bd.conectar();
	}
	
	@Test 
	public void añadirAtletaABD() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fs1 = "2016-06-12";
		String fs2 = "2016-08-12";
		Date fecha1 = sdf.parse(fs1);
		Date fecha2 = sdf.parse(fs2);
//		bd.añadirEventoABD(new Evento(g.getEventos().size(), "Evento1", "Maraton", 2, 25, ConversorFechas.convertFechaJavaSQL(fecha1), ConversorFechas.convertFechaJavaSQL(fecha2)));
//		Atleta a= new Atleta("DNI", "Ivan",10,4);
//		Inscripcion ins = new Inscripcion(g.getEventos().size(),a , 1, ConversorFechas.convertFechaJavaSQL(fecha1));
//		bd.añadirInscrito(a, ins);
	}
	@Test
	public void testCargarDatos(){
		System.out.println("Preparando para cargar");
		bd.cargarDatos(g);
	}
	@Test
	public void testAñadirEventos() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fs1 = "2016-06-12";
		String fs2 = "2016-08-12";
		Date fecha1 = sdf.parse(fs1);
		Date fecha2 = sdf.parse(fs2);
		bd.añadirEventoABD(new Evento(2, "Evento1", "Maraton", 2, 25, ConversorFechas.convertFechaJavaSQL(fecha1), ConversorFechas.convertFechaJavaSQL(fecha2)));
		bd.cargarDatos(g);
		System.out.println(g.getEventos().toString());
		
	}

}
