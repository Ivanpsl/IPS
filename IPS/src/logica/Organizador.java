package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	 * Una vez comunicado, el atleta pasar� a la tabla de inscritos.
	 * @throws IOException
	 */
	public void mostrarNumeroCuenta(Atleta atleta) throws IOException
	{
		System.out.println("A continuaci�n se mostrar� donde realizar la transferencia:");
		BufferedReader file = new BufferedReader(new FileReader("banco.txt"));
		
		String line = file.readLine();
		
		System.out.println("N�mero de cuenta: " + line);
		
		//Mientras no tengan dorsal se les pondr� el 0
		//Inscrito nuevoInscrito = new Inscrito (atleta, 0, false);
		//evento.getInscritosEvento().add(nuevoInscrito);
		
		file.close();
	}
	
	/**
	 * Asignaci�n de dorsales a partir del 10 y por fecha de inscripci�n (supuestamente ya tienen que venir ordenados en el array).
	 */
	public void asignarDorsales()
	{
		ArrayList<Inscrito> inscritos = evento.getInscritosEvento();
		int cont = 11;
		
		for (Inscrito i : inscritos){
			i.setDorsal(cont);
			cont++;
		}
	}

}
