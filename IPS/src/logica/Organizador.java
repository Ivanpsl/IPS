package logica;

import java.io.BufferedReader;
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
	 * Pasar el número de cuenta al usuario para que pueda pagar, viene en un txt antes de los que han pagado.
	 * @throws IOException
	 */
	public void mostrarNumeroCuenta() throws IOException
	{
		System.out.println("A continuación se mostrará donde realizar la transferencia:");
		BufferedReader file = new BufferedReader(new FileReader("banco.txt"));
		
		String line = file.readLine();
		
		System.out.println("Número de cuenta: " + line);
		System.out.println("Cuota: " + evento.getPrecio());
		
		file.close();
	}
	
	/**
	 * Asignación de dorsales a partir del 10.
	 * Antes de ello se ordenan los inscritos por fecha de inscripción y estado del pago.
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

}
