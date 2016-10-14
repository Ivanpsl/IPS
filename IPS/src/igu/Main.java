package igu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
//		System.out.println(g.getEventos().toString());
//		Date fechaprueba = new Date(116, 2, 24);
//		Inscripcion.sumarRestarDiasFecha(fechaprueba, 2);
		mostrarComandosDisponibles();
	
	}

	private static void mostrarComandosDisponibles() {
		while (ejecucion) {
			System.out.println("Escribir los siguientes comandos para mostrar contenido:");
			System.out.println(); 
			System.out.println("mostrarEventos -> muestra los eventos disponibles para un atleta.");
			System.out.println("mostrarAtletas -> Muestra los atletas inscritos a un evento a seleccionar.");
			System.out.println("mostrarInformacionDNI - DNI -> Muestra los eventos a los que se ha inscrito alguien con ese DNI");
			System.out.println("inscribirseAEvento - idEvento(int) - DNI - ");
			System.out.println("Fin -> termina con la ejecucion del programa.");
			
			System.out.print("\nEscriba: ");
			String lectura = consoleRead();
			comandoEscogido(lectura);
			System.out.println("\n -------------------------------------------- \n");
		}
	}

	private static void comandoEscogido(String comand) {
		System.out.println("Resultado: \n");
		String[] cachos = comand.split(" - ");
		switch (cachos[0]) {
		case "mostrarEventos":
			imprimirEventos(g.getEventosAbiertos());
			break;
		case "mostrarAtletas":
			//g.mostrarInscritosDeEvento(Integer.parseInt(cachos[1]));
			mostrarAtletasDeUnEvento();
			break;
		case "mostrarInformacionDNI":
			mostrarInformacionDNI(cachos[1]);
			break;
		case "Fin":
			ejecucion = false;
			break;
		case "inscribirseAEvento":
			System.out.println("Seleccione una opcion: ");
			g.listarEventosAbiertos();
			int respuesta = Integer.parseInt(consoleRead());
			pedirDatosInscripcion(respuesta );
			
			break;
		default:
			System.out.println("Comando no reconocido.");
		}
	}
	private static void pedirDatosInscripcion(int evento) {
		//BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		String DNI="";
		String nombre="";
		String categoria="";
		int sexo=-1;
		int edad=-1;
		System.out.println("Introduzca su DNI");
		DNI = consoleRead();
		if(!DNI.equals("")&& !g.comprobarAtletaRegistrado(DNI)){
			while(nombre.equals("") || nombre==null){
			System.out.println("Introduzca su nombre");
			nombre =consoleRead();
			}
			while(edad <0 || edad >120){
				System.out.println("Introduzca su edad (0 - 120)");
				edad =Integer.parseInt(consoleRead());
			}
			while(sexo <0 || sexo >1){
				System.out.println("Introduzca su sexo ( 0 -> M , 1 -> F");
				sexo =Integer.parseInt(consoleRead());
			}
			Atleta atl =new Atleta(DNI, nombre, categoria, edad, sexo);
			g.addAtleta(atl);
			g.a�adirInscripcionEvento(atl,evento);
		}else{
			Atleta atl = g.buscarAtletaPorDNI(DNI);
//				String respuesta="";
			System.out.println("Ya est� registrado");
//				System.out.println("DNI: "+ DNI +"\n Nombre :"+atl.getNombre()+
//						"\n Sexo: "+ (atl.getSexo()==0 ? "Masculino":"Femenino")+
//						"\n edad: "+ atl.getEdad());
//				respuesta = in.readLine();
			g.a�adirInscripcionEvento(atl,evento);
		}
		
	}

	private static void mostrarAtletasDeUnEvento(){
		System.out.println("\n Seleccione un id de los eventos existentes: \n");
		//imprimirEventos(g.getEventosDisponibles());
		for(Evento e : g.getEventosDisponibles()){
			System.out.println("ID: "+e.getId()+"; Nombre: "+e.getNombre());
		}
		System.out.print("\nID de evento seleccionado: ");
		int idEvento = Integer.parseInt(consoleRead());
		Evento evento = g.obtenerEventoPorId(idEvento);
		System.out.println("");
		for(Inscripcion ins : evento.getInscritosEvento()){
			System.out.println(ins.toString());
		}
	}
	private static void imprimirEventos(ArrayList<Evento> eventos){
		if(eventos.size() == 0)
			System.out.println("No hay eventos disponibles.");
		else{
			for(Evento e : eventos){
				imprimeEvento(e);
			}
		}
	}
	private static void imprimeEvento(Evento e){
		System.out.println(e.toString());
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
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	//	InputStreamReader isr = new InputStreamReader(System.in);
	//	BufferedReader br = new BufferedReader (isr);
		String lectura = "";
		try {
			lectura = br.readLine();
		} catch (IOException e) {
			System.err.println("No se ha le�do adecuadamente");
		}
		return lectura;
	}

}
