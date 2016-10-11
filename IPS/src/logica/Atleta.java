package logica;


public class Atleta {
	
	
	public static final int MASCULINO = 0;
	public static final int FEMENINO = 1; 
	
	String DNI;
	String nombre;
	String categoria;
	int sexo;
	int edad;
	
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
	private void setSexo(int sexo)
	{
		this.sexo = sexo;
	}
	private void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getDNI() {
		return this.DNI;
	}
	public String getNombre() {
		return this.nombre;
	}
	public String getCategoria() {
		return this.categoria;
	}
	public int getSexo() {
		return this.sexo;
	}
	
	public String toString(){
		StringBuilder sB= new StringBuilder();
		sB.append("DNI: "+getDNI()+" Nombre: "+getNombre()+" Sexo(0 masc, 1fem): " + getSexo() + " Categoría: "+getCategoria());
		return sB.toString();
	}
}
