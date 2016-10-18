package logica;

import java.sql.Date;

import utiles.ConversorFechas;

public class Atleta {
	
	public static final int MASCULINO = 0;
	public static final int FEMENINO = 1; 
	
	String DNI;
	String nombre;
	String fechaNacimiento;
	Date fechaNacimientoDate;
	int sexo;
	int edad;
	
	public Atleta(String dni, String nombre, String fecha, int sexo){
		this.DNI = dni;
		this.nombre = nombre;
		this.fechaNacimiento=fecha;
		this.sexo=sexo;
	}
	
	public Atleta(String dni, String nombre, java.sql.Date fecha, int sexo){
		this.DNI = dni;
		this.nombre = nombre;
		this.fechaNacimiento=fecha.toString();
		this.fechaNacimientoDate = fecha;
		this.sexo=sexo;
	}
	public String getSexoString(){
		if(this.sexo == MASCULINO){
			return "MASCULINO";
		}else{
			return "FEMENINO";
		}
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setSexo(int sexo)
	{
		this.sexo = sexo;
	}
	
	public void setEdad()
	{
		this.edad = ConversorFechas.fechaNacimientoEdad(fechaNacimiento);
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
