package logica;


public class Atleta {
	String DNI;
	String nombre;
	String categoria;
	int sexo;
	int edad;
	public final int MASCULINO = 0;
	public final int FEMENINO = 1; 
	
	public Atleta(String dni, String nombre, String categoria, int edad, int sexo){
		this.DNI = dni;
		this.nombre = nombre;
		this.categoria = categoria;
		this.edad=edad;
		this.sexo=sexo;
	}
	private void setDNI(String dNI) {
		DNI = dNI;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getDNI() {
		return DNI;
	}
	public String getNombre() {
		return nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	
	
	public String toString(){
		StringBuilder sB= new StringBuilder();
		sB.append("DNI: "+getDNI()+" Nombre: "+getNombre()+" Categoría: "+getCategoria());
		return sB.toString();
	}
}
