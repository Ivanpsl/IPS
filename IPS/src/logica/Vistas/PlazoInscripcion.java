package logica.Vistas;


import java.sql.Date;
public class PlazoInscripcion {

	Date inicio;
	Date fin;
	double precio;
	
	public PlazoInscripcion(Date inicio, Date fin, double precio){
		this.inicio=inicio;
		this.fin=fin;
		this.precio=precio;
	}
	public Date getFechaInicio(){
		return inicio;
	}
	public Date getFechaFin(){
		return fin;
	}
	public double getPrecio(){
		return precio;
	}
}
