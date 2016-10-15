package logica;


public class Atleta {
	
	public static final int MASCULINO = 0;
	public static final int FEMENINO = 1; 
	
	String DNI;
	String nombre;
	String fechaNacimiento;
	int sexo;
	int edad;
	
	public Atleta(String dni, String nombre, String fecha, int sexo){
		this.DNI = dni;
		this.nombre = nombre;
		this.fechaNacimiento=fecha;
		this.sexo=sexo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setSexo(int sexo)
	{
		this.sexo = sexo;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDNI() {
		return this.DNI;
	}
	public String getNombre() {
		return this.nombre;
	}

	public int getSexo() {
		return this.sexo;
	}
	public int getEdad(){
		return edad;
	}
	public String getFechaNacimiento(){
		return fechaNacimiento;
	}
	public String toString(){
		StringBuilder sB= new StringBuilder();
		sB.append("DNI: "+getDNI()+" Nombre: "+getNombre()+" Sexo(0-masc, 1-fem): " + getSexo());
		return sB.toString();
	}
}
