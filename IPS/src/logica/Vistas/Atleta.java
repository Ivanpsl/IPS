package logica.Vistas;

import java.sql.Date;

import usuarios.Usuario;
import utiles.ConversorFechas;

public class Atleta extends Usuario{
	
	public static final int MASCULINO = 0;
	public static final int FEMENINO = 1; 
	
	public String DNI;
	String nombre;
	String fechaNacimiento;
	Date fechaNacimientoDate;
	int sexo;
	int edad;
	
	public Atleta(String dni, String nombre, String fecha, int sexo){
		super(nombre, "");
		this.DNI = dni;
		this.nombre = nombre;
		this.fechaNacimiento=fecha;
		this.sexo=sexo;
	}
	
	public Atleta(String dni, String nombre, java.sql.Date fecha, int sexo){
		super(nombre, "");
		this.DNI = dni;
		this.nombre = nombre;
		this.fechaNacimiento=fecha.toString();
		this.fechaNacimientoDate = fecha;
		this.sexo=sexo;
	}
	/**
	 * Constructor con usuario y contraseña por si acaso
	 * @param nombreUsuario
	 * @param pass
	 * @param dni
	 * @param nombre
	 * @param fecha
	 * @param sexo
	 */
	public Atleta(String nombreUsuario, String pass, String dni, String nombre, java.sql.Date fecha, int sexo){
		super(nombreUsuario, pass);
		this.DNI = dni;
		this.nombre = nombre;
		this.fechaNacimiento=fecha.toString();
		this.fechaNacimientoDate = fecha;
		this.sexo=sexo;
	}
	public static int getSexotiInt(String sexo){
		if(sexo.equals(Categoria.FEM)){
			return FEMENINO;
		}else
			return MASCULINO;
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

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}
}
