package Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import logica.Gestor;
import logica.GestorCategorias;
import logica.Vistas.Atleta;
import logica.Vistas.Evento;
import logica.Vistas.PlazoInscripcion;
import persistencia.ConexionBD;

public class crearEventosTerminados {
	static ConexionBD bd;
	static Gestor g;
	
	static int numEvento = 0; //Si sumas esto el evento cambia de nombre y de id;
	public static void main(String[] args) {
		bd = new ConexionBD();
		g = new Gestor();
	}
	
	private static void crearEventos(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(cal.getTime());
		Date f1 = new Date(cal.getTimeInMillis());
		Date f2 = new Date(cal.getTimeInMillis() + 20 * 1000); //10 seg despues
		Date f3 = new Date(cal.getTimeInMillis() + 60 * 1000); //Empieza 1 min despues de la primera fecha de inscrip
		PlazoInscripcion p1 = new PlazoInscripcion(f1, f2, 69);
		ArrayList<PlazoInscripcion> listPla = new ArrayList<PlazoInscripcion>();
		listPla.add(p1);
		Evento e = new Evento(669 + numEvento, "Evento trucado"+numEvento, "truco", 69, 69, false, GestorCategorias.getCategoriasDefecto(), listPla, f3, 699);
		bd.añadirEventoABD(e);
		//Ya tenemo el evento. Ahora a crear inscripciones
		cal.set(1995, 3, 2);
		Date cumples = new Date(cal.getTimeInMillis());
		
		ArrayList<Atleta> machotes = new ArrayList<>();
		Atleta m1 = new Atleta("711", "Juanra", cumples, 0);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		cumples = new Date(cal.getTimeInMillis());
		Atleta m2 = new Atleta("712", "Oliverio", cumples, 0);
		cal.set(Calendar.DAY_OF_MONTH,14);
		cumples = new Date(cal.getTimeInMillis());
		Atleta m3 = new Atleta("713", "Oscar", cumples, 0);
		machotes.add(m1);
		machotes.add(m2);
		machotes.add(m3);
		g.setEventoSelccionado(e);
		g.inscribirLote(machotes);
		//Aqui se supone que ya hay atletas que han pagado y se han inscrito al evento. Ahora hay que esperar a que se cierre el plazo y comience la carrera.
		
		try {
			TimeUnit.SECONDS.sleep(20); //Esperamos 20 seg a que termine el plazo
			TimeUnit.SECONDS.sleep(60); //Esperamos 1 min a que fijo termine el evento. 
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Datos del evento: \n"+e.toString());
	}

}
