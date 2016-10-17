package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import utiles.Asignador;
import utiles.ConversorFechas;
import logica.Atleta;
import logica.Evento;
import logica.Gestor;
import logica.Inscripcion;

public class ConexionBD {

	
	public static String login="uo238031";
	public static String password = "";
	//public static String url = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	public static String url ="jdbc:hsqldb:hsql://localhost/";
	
	public Connection conectar(){
		try {
			if (DriverManager.getDriver(url) == null){
				if (url.contains("oracle"))
					DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			}else {
				DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
				login="SA";
				password= "";	
			}
		
		return DriverManager.getConnection(url, login, password);
		
		} catch (SQLException e) {
			System.err.println("No se ha podido conectar con la base de datos");
		//	e.printStackTrace();
			return null;
		}
	
	}


	public boolean cargarDatos(Gestor g){
		System.out.println("Conectando con la base de datos...");
		Connection con =conectar();
		if(con==null){
			return false;
		}else{
			Statement st;
			try {
				//Eventos
				System.out.println("Cargando datos de eventos...");
				st = con.createStatement();
				StringBuilder query = new StringBuilder();
				query.append("SELECT * FROM EVENTOS");
				ResultSet rs = st.executeQuery(query.toString());
				while(rs.next()){
					int id = rs.getInt("EV_ID");
					String nombre =rs.getString("EV_NOMBRE");
					String tipo =rs.getString("EV_TIPO");
					double distancia =rs.getDouble("EV_DISTANCIA");
					double precio =rs.getDouble("EV_PRECIO");
					Date fecha_comienzo =rs.getDate("EV_FECHA_COMIENZO");
					Date fecha_fin_insc=rs.getDate("EV_FECHA_FIN_INSC");
					int finalizado= rs.getInt("EV_FINALIZADO");
					boolean fin;
					if(finalizado==0) fin=false;
					else fin=true;
					g.getEventos().add(new Evento(id, nombre, tipo, precio, distancia,ConversorFechas.convertFechaJavaSQL(fecha_comienzo),ConversorFechas.convertFechaJavaSQL(fecha_fin_insc),200,fin));
				}
				System.out.println("Datos de eventos cargados");
				rs.close();
				//Atletas
				System.out.println("Cargando datos de atletas...");
				st = con.createStatement();
				query = new StringBuilder();
				query.append("SELECT * FROM ATLETA");
				ResultSet rs2 = st.executeQuery(query.toString());
				while(rs2.next()){
					String dni = rs2.getString("AT_DNI");
					String nombre =rs2.getString("AT_NOMBRE");
					int sexo =rs2.getInt("AT_SEXO");
					String fecha =rs2.getString("AT_FECHA_NACIMIENTO");
					
					g.getAtletas().add(new Atleta(dni, nombre,fecha, sexo));

				}
				System.out.println("Datos de atletas cargados");
				rs2.close();
				//Incripcion
				System.out.println("Cargando datos de inscripcion...");
				st = con.createStatement();
				query = new StringBuilder();
				query.append("SELECT * FROM INSCRIPCION");
				ResultSet rs3 = st.executeQuery(query.toString());
				while(rs3.next()){
					String dni = rs3.getString("AT_DNI");
					int id = rs3.getInt("EV_ID");
					int estado =rs3.getInt("INS_ESTADO");
					Date fecha_ins =rs3.getDate("INS_FECHA_INS");
					int segundos = rs3.getInt("INS_RESULTADO_SEG");
					int dorsal = rs3.getInt("INS_DORSAL");
					String categoria = rs3.getString("INS_CATEGORIA");
					Inscripcion inscripcion = new Inscripcion(id,dorsal, fecha_ins, estado, segundos, categoria);
					if(Asignador.asignarAtleta(g, inscripcion, dni))
						System.out.println("Inscripcion asignada a atleta correctamente." 
								+ "  ---> " + inscripcion.toString());
					if(Asignador.asignarAEvento(g, inscripcion, id))
						System.out.println("Inscripcion asignada a evento correctamente   -----> " + inscripcion.toString());
				}

				rs3.close();
				st.close();
				System.out.println("Datos de inscripciones cargados");
				con.close();
				return true;
			} catch (SQLException e) {
				System.err.println("Error al cargar datos de la BD.");
				e.printStackTrace();
				return false;
			}
		}
	}
	public void añadirEventoABD(Evento ev){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible añadir eventos a ninguna BD." );
		else{
			try {
				PreparedStatement st = con.prepareStatement("INSERT INTO EVENTOS VALUES (?,?,?,?,?,?,?,?,?)");
				int id= ev.getId();
				String nombre = ev.getNombre();
				String tipo= ev.getTipo();
				double distancia = ev.getDistancia();
				double precio = ev.getPrecio();
				Date fecha_comienzo = ev.getFechaCompeticion();
				Date fechaFin = ev.getFechaFinInscripcion();
				int plazasTotales= ev.getPlazasTotales();
				int fin;
				if(ev.getFinalizado()) fin =1;
				else fin=0;
				
				st.setInt(1,id);
				st.setString(2,nombre);
				st.setString(3,tipo);
				st.setDouble(4,distancia);
				st.setDouble(5,precio);
				st.setDate(6, (java.sql.Date) fecha_comienzo);
				st.setDate(7, (java.sql.Date) fechaFin);
				st.setInt(8, plazasTotales);
				st.setInt(9, fin);
				st.executeUpdate();
				st.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void añadirAtleta(Atleta at){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible añadir datos a ninguna BD." );
		else{
			try {
				PreparedStatement st = con.prepareStatement("INSERT INTO ATLETA VALUES (?,?,?,?)");
				String dni= at.getDNI();
				String nombre = at.getNombre();
				int sexo = at.getSexo();
				String fNacimiento = at.getFechaNacimiento();
				st.setString(1,dni);
				st.setString(2,nombre);
				st.setInt(3,sexo);
				st.setString(4,fNacimiento);
				st.executeUpdate();
				st.close();
				
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void añadirInscrito(Atleta at, Inscripcion ins){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible añadir datos a ninguna BD." );
		else{
			try {
				PreparedStatement st2 = con.prepareStatement("INSERT INTO INSCRIPCION VALUES (?,?,?,?,?,?,?)");
				int ev_id = ins.getIdEvento();
				int estado = ins.getEstado();
				Date fechaIns = ins.getFechaInscripcion();
				int res_Segundos = ins.getResultado();
				int dorsal = ins.getDorsal();
				st2.setString(1,at.getDNI());
				st2.setInt(2,ev_id);
				st2.setInt(3,estado);
				st2.setDate(4,(java.sql.Date) fechaIns);
				st2.setInt(5,res_Segundos);
				st2.setInt(6, dorsal);
				st2.setString(7, ins.getCategoria());
				st2.executeUpdate();
				st2.close();
				
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void asignarDorsal(Inscripcion inscripcion, int dorsal){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible encontrar ninguna BD." );
		else{ 
			try {
				
				PreparedStatement st;
				st = con.prepareStatement("UPDATE INSCRIPCION SET INS_DORSAL=? WHERE AT_DNI=?");
				st.setInt(1,dorsal);
				st.setString(2, inscripcion.getAtleta().getDNI());
				int res = st.executeUpdate();
				System.out.println("\n[BD] " + res + " Tablas han sido actualizadas: \n\t");
				System.out.println("Inscripcion " + inscripcion.toString() + " Actualizada con dorsal " + dorsal);
				st.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void asignarTiempo(Inscripcion inscripcion, int tiempo){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible encontrar ninguna BD." );
		else{ 
			try {
				
				PreparedStatement st;
				st = con.prepareStatement("UPDATE INSCRIPCION SET INS_RESULTADO_SEG=? WHERE AT_DNI=?");
				st.setInt(1,tiempo);
				st.setString(2, inscripcion.getAtleta().getDNI());
				int res = st.executeUpdate();
				System.out.println("\n[BD] " + res + " Tablas han sido actualizadas: \t");
				System.out.println("Inscripcion  [" + inscripcion.toString() + "] Actualizada con tiempo " + tiempo);
				st.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void asignarCategoria(Inscripcion inscripcion, String categoria){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible encontrar ninguna BD." );
		else{ 
			try {
				
				PreparedStatement st;
				st = con.prepareStatement("UPDATE INSCRIPCION SET INS_CATEGORIA=? WHERE AT_DNI=?");
				st.setString(1,categoria);
				st.setString(2, inscripcion.getAtleta().getDNI());
				int res = st.executeUpdate();
				System.out.println("\n[BD] " + res + " Tablas han sido actualizadas: \t");
				System.out.println("Inscripcion  [" + inscripcion.toString() + "] Actualizada con categoria " +categoria );
				st.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void actualizarEstadoPago(Inscripcion inscripcion, int estado){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible encontrar ninguna BD." );
		else{ 
			try {
				
				PreparedStatement st;
				st = con.prepareStatement("UPDATE INSCRIPCION SET INS_ESTADO=? WHERE AT_DNI=?");
				st.setInt(1,estado);
				st.setString(2, inscripcion.getAtleta().getDNI());
				int res = st.executeUpdate();
				System.out.println("\n[BD] " + res + " Tablas han sido actualizadas: \t");
				System.out.println("Inscripcion  [" + inscripcion.toString() + "] Actualizado el estado del pago"  );
				st.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void marcarComoFinalizado(Evento ev){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible encontrar ninguna BD." );
		else{ 
			try {
				
				PreparedStatement st;
				st = con.prepareStatement("UPDATE EVENTOS SET EV_FINALIZADO=1 WHERE EV_ID=?");
				st.setInt(1,ev.getId());
				int res = st.executeUpdate();
				System.out.println("\n[BD] " + res + " Tablas han sido actualizadas: \n\t");
				System.out.println("- El evento con ID : " + ev.getId() + " ha sido marcado como FINALIZADO");
				st.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

	
	public void resetearDatosBD(){
		Connection con =conectar();
		if(con==null)
			System.err.println("No es posible encontrar ninguna BD." );
		else{ 
			try {
				Statement st1 = con.createStatement();
				Statement st2 = con.createStatement();
				Statement st3 = con.createStatement();
				System.out.println("Se han borrado: " +  st2.executeUpdate("DELETE FROM ATLETA")+ " atletas .");
				System.out.println("Se han borrado: " +st3.executeUpdate("DELETE FROM INSCRIPCION") + " inscripciones ");
				System.out.println("Se han borrado: " + st1.executeUpdate("DELETE FROM EVENTOS")+ " eventos ");
				st1.close();
				st2.close();
				st3.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
}
	

