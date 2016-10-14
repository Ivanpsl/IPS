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
			ArrayList<Evento> eve = g.getEventosAbiertos();
			// Registramos al atleta y se a�ade a la inscripcion seleccionada
			registrarAtleta(eve.get(respuesta) );
			
			break;
		default:
			System.out.println("Comando no reconocido.");
		}
	}
	
	/**
	 * Metodo que registra a un atleta si no estaba registrado y ademas
	 * lo apunta al envento que se le pasa como parametro
	 * */
	
	private static void registrarAtleta(Evento evento) {
		String DNI="";

		System.out.println("Introduzca su DNI");
		DNI = consoleRead();
		if(!DNI.equals("")&& !g.comprobarAtletaRegistrado(DNI)){
			String[] datos = pedirDatos();
			Atleta atl =new Atleta(DNI, datos[0], datos[1], 
					Integer.parseInt(datos[2]));
			g.addAtleta(atl);
			g.a�adirInscripcionEvento(atl,evento);
		}else{
			Atleta atl = g.buscarAtletaPorDNI(DNI);
				String respuesta="";
			System.out.println("Ya est� registrado �Quiere modificar sus datos? s/n");
				System.out.println("DNI: "+ DNI +"\n Nombre :"+atl.getNombre()+
						"\n Sexo: "+ (atl.getSexo()==0 ? "Masculino":"Femenino")+
						"\n edad: "+ atl.getEdad());
				while(!respuesta.equals("s")|| !respuesta.equals("n")){
					
					respuesta = consoleRead();
				}
				
				if(respuesta.equals("s")){
					String[] datos = pedirDatos();
					atl.setNombre(datos[0]);
					atl.setFechaNacimiento(datos[1]);
					atl.setSexo(Integer.parseInt(datos[2]));
				}
			g.a�adirInscripcionEvento(atl,evento);
		}
		
	}
	
	private static String[] pedirDatos(){
		String nombre="";
		int sexo=-1;
		String fechaNacimiento="";
		String[] datos = new String[3];
		while(nombre.equals("") || nombre==null){
			System.out.println("Introduzca su nombre");
			nombre =consoleRead();
		}
		datos[0]=nombre;
		while(!comprobarFecha(fechaNacimiento)){
			System.out.println("Introduzca fecha nacimiento 'DD/MM/YYYY'");
			fechaNacimiento =consoleRead();
		}
		datos[1]=fechaNacimiento;
		while(sexo <0 || sexo >1){
			System.out.println("Introduzca su sexo ( 0 -> M , 1 -> F");
			sexo =Integer.parseInt(consoleRead());
		}
		datos[2]=String.valueOf(sexo);
		return datos;
	}

	private static boolean comprobarFecha(String fecha){
		
		char[] array = fecha.toCharArray();
		if(array.length!=10){
			return false;
		}
		if(Character.isDigit(array[0]) && Character.isDigit(array[1]) &&
			Character.isDigit(array[3]) && Character.isDigit(array[4]) &&
			Character.isDigit(array[6]) &&Character.isDigit(array[7]) &&
			Character.isDigit(array[8]) &&Character.isDigit(array[9]) &&
			array[2]=='/' && array[5]=='/'){
			
			String[] trozos = fecha.split("/");
			int a�o = Integer.parseInt(trozos[2]);
			int dia = Integer.parseInt(trozos[0]);

			
			int mes = Integer.parseInt(trozos[1]);
			if(a�o<=2015 && a�o>1896 && mes >0 && mes <13 && dia >0){
				if(mes==1 ||mes==3 ||mes==5 ||mes==7 ||mes==9 ||mes==11 ){
					if(dia <=31){
						return true;
					}
				}
				else if(mes%2==0 && mes !=2){
					if(dia <=30){
						return true;
					}
				}else{
				    if(a�o % 4 == 0 && a�o % 100 != 0 || a�o % 400 == 0){
				         //es bisiesto
				         if(dia <=29){
								return true;
							}
				     }else{
				    	 if(dia <=28){
								return true;
							}
				     }
				}
				
			}else{
				System.out.println("Rango de edad no permitido");
			}
		}else{
			System.out.println("Formato de fecha mal introducido");
		}
		return false;
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
			System.out.print(">");
			lectura = br.readLine();
		} catch (IOException e) {
			System.err.println("No se ha le�do adecuadamente");
		}
		return lectura;
	}

}
