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
	public String toString(){
//		String miString = inicio.getDay()+"/"+inicio.getMonth()+1+"/"+inicio.getYear()+1900 + " - "+
//				fin.getDay()+"/"+fin.getMonth()+1+"/"+fin.getYear()+1900+" -> "+precio+"€ ";
		String miString = inicio.toString() + " - " + fin.toString() + " "+ precio + "€";
		return miString;
	}
	public int compareTo(PlazoInscripcion pl2){
		if(inicio.getTime() > pl2.getFechaFin().getTime())
			return 1;
		else if(fin.getTime() < pl2.getFechaInicio().getTime())
			return -1;
		else 
			return 0;
	}
}
