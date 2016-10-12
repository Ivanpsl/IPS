package igu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import logica.Atleta;
import logica.Evento;
import logica.Gestor;
import logica.Inscripcion;
import oracle.net.aso.g;

public class Main {
	static Gestor g;
	static boolean ejecucion = true;

	public static void main(String[] args) throws IOException {
		g = new Gestor();
		System.out.println(g.getEventos().toString());
//		Date fechaprueba = new Date(116, 2, 24);
//		Inscripcion.sumarRestarDiasFecha(fechaprueba, 2);
	
	}

	private static void mostrarComandosDisponibles() {
		while (ejecucion) {
			System.out.println("Escribir los siguientes comandos para mostrar contenido:");
			System.out.println(); 
			System.out.println("mostrarEventos -> muestra los eventos disponibles para un atleta.");
			System.out.println("mostrarAtletas - idEvento(int) -> Muestra los atletas para el evento que se le pasa como id.");
			System.out.println("mostrarInformacionDNI - DNI -> Muestra los eventos a los que se ha inscrito alguien con ese DNI");
			System.out.println("añadirInscripcionEvento - idEvento(int) - DNI - ");
			System.out.println("Fin -> termina con la ejecucion del programa.");
			
			String lectura = consoleRead();
			comandoEscogido(lectura);
		}
	}

	private static void comandoEscogido(String comand) {
		System.out.println("Resultado: \n");
		String[] cachos = comand.split(" - ");
		switch (cachos[0]) {
		case "mostrarEventos":
			g.mostrarEventosDisponibles();
		case "mostrarAtletas":
			g.mostrarInscritosDeEvento(Integer.parseInt(cachos[1]));
		case "mostrarInformacionDNI":
			mostrarInformacionDNI(cachos[1]);
		case "Fin":
			ejecucion = false;
		default:
			System.out.println("Comando no reconocido.");
		}
	}
	private static void mostrarInformacionDNI(String dni){
		ArrayList<Evento> evInscritos = g.obtenerEventosEInscripciones(dni);
		System.out.println("Eventos inscritos: ");
		for(Evento e: evInscritos){
			Inscripcion inscripcion = g.getInscripcion(dni, e);
			System.out.println(" *** Evento: " + e.getId() + " Nombre del evento: " + e.getNombre() );
			
			System.out.println("/n Datos de la inscripcion: ");
			System.out.println("/n/t/t "+ inscripcion.toString());
		}
	}
	private static String consoleRead(){
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		return br.toString();
	}

}
