package logica;

import usuarios.Atleta;

public class Categoria{
	String nombre;
	int edadMinima;
	int edadMaxima;
	int sexo; //
	public final static int MASCULINO = Atleta.MASCULINO;
	public final static int FEMENINO = Atleta.FEMENINO;
	
	public Categoria(String nombre, int edadMinima, int edadMaxima, int sex){
		this.nombre = nombre;
		this.edadMaxima = edadMaxima;
		this.edadMinima = edadMinima;
		this.sexo = sex;
	}
	//------------------------ COMPROBAR LOS LIMITES POR SI NO SON ASI ---------------------------------------
	public boolean estaDentro(Atleta a){
		if(a.getEdad() >= edadMinima && a.getEdad() < edadMaxima && a.getSexo() == this.sexo)
			return true;
		return false;
	}
	public String getNombre() {
		return nombre;
	}
	public int getEdadMinima() {
		return edadMinima;
	}
	public int getEdadMaxima() {
		return edadMaxima;
	}
	public int getSexo() {
		return sexo;
	}
	public int getMASCULINO() {
		return MASCULINO;
	}
	public int getFEMENINO() {
		return FEMENINO;
	}
	/**
	 * La categoria que se le pasa como parametro es la que esperas que sea mayor o menor
	 * @param cat2
	 * @return
	 */
	public int compareTo(Categoria cat2){
		if(getEdadMaxima() < cat2.getEdadMinima())
			return 1;
		if(getEdadMinima() > cat2.getEdadMaxima())
			return -1;
		else return 0;
	}
}
