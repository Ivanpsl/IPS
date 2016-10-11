package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Organizador {
	
	//club, federeacion, entidad publica o cooperativa
	private String tipo;
	private Evento evento;
	
	public Organizador (Evento evento, String tipo) {
		
		this.evento = evento;
		this.tipo = tipo;
	}
	
	private void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Pasar el n�mero de cuenta al usuario para que pueda pagar, viene en un txt antes de los que han pagado.
	 * @throws IOException
	 */
	public void mostrarNumeroCuenta() throws IOException
	{
		System.out.println("A continuaci�n se mostrar� donde realizar la transferencia: ");
		BufferedReader file = new BufferedReader(new FileReader("ficheros/banco.txt"));
		
		String line = file.readLine();
		
		System.out.println("N�mero de cuenta: " + line);
		System.out.println("Cuota: " + evento.getPrecio());
		
		file.close();
	}
	
	/**
	 * Asignaci�n de dorsales a partir del 10.
	 * Antes de ello se ordenan los inscritos por fecha de inscripci�n y estado del pago.
	 */
	public void asignarDorsales()
	{
		Collections.sort(evento.getInscritosEvento());
		
		ArrayList<Inscripcion> inscritos = evento.getInscritosEvento();
		int cont = 11;
		
		for (Inscripcion i : inscritos){
			i.setDorsal(cont);
			cont++;
		}
	}
	
	/**
	 * M�todo que verifica los DNI del txt para saber que atletas han pagado.
	 * @throws IOException 
	 */
	public void comprobarPagados()
	{
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("ficheros/banco.txt"));
			String linea = fichero.readLine(); //La primera es el numero de cuenta
			
			ArrayList<Inscripcion> inscritos = evento.getInscritosEvento();
			
			while (fichero.ready()) {
				linea = fichero.readLine();
		    	String[] trozos = linea.split("\n");
		    	for (Inscripcion i : inscritos)
		    	{
		    		if (i.getAtleta().getDNI().equals(trozos[0]))
		    			i.setEstado(2);
		    		else
		    			i.setEstado(1);
		    	}
		    }
		    fichero.close();
		    }
		    catch (FileNotFoundException fnfe) {
		    	new FileNotFoundException("Fichero del banco no encontrado");
		    }
		    catch (IOException ioe) {
		    	new RuntimeException("Error de entrada/salida.");
		    }
	}

}
