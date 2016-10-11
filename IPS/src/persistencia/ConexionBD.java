package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logica.Atleta;
import logica.Evento;
import logica.Gestor;
import logica.Inscripcion;

public class ConexionBD {

	
	public static String login="uo238031";
	public static String password = "";
	public static String url = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	//public static String url ="jdbc:hsqldb:hsql://localhost/labdb";
	
	private Connection conectar(){
		try {
			if (DriverManager.getDriver(url) == null){
				if (url.contains("oracle"))
					DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			}else
					DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
		login="SA";
		password= "";
		return DriverManager.getConnection(url, login, password);
		
		} catch (SQLException e) {
			System.out.println("No se ha podido conectar");
			e.printStackTrace();
			return null;
		}
	
	}


	public void cargarDatos(Gestor g){
		Connection con =conectar();
		Statement st;
		Asignador asignador = new Asignador();
		try {
		//Eventos
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
			g.getEventos().add(new Evento(id, nombre, tipo, precio, distancia,fecha_comienzo,fecha_fin_insc));
		}
		rs.close();
		
		
		//Atletas
		st = con.createStatement();
		query = new StringBuilder();
		query.append("SELECT * FROM ATLETAS");
		ResultSet rs2 = st.executeQuery(query.toString());
		while(rs2.next()){
			String dni = rs2.getString("AT_DNI");
			String nombre =rs2.getString("AT_NOMBRE");
			String categoria =rs2.getString("AT_CATEGORIA");
			int sexo =rs2.getInt("AT_SEXO");
			int edad =rs2.getInt("AT_EDAD");
			g.getAtletas().add(new Atleta(dni, nombre, categoria, edad, sexo));
			
		}
		rs2.close();
		
		//Incripcion
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
			Inscripcion inscripcion = new Inscripcion(dorsal, fecha_ins, estado, segundos);
			if(asignador.asignarAtleta(g, inscripcion, dni))
				System.out.println("Inscripcion asignada a atleta correctamente." 
			+ "  ---> " + inscripcion.toString());
			if(asignador.asignarAEvento(g, inscripcion, id))
				System.out.println("Inscripcion asignada a evento correctamente   -----> " + inscripcion.toString());
		}
		rs3.close();
		st.close();
		
		con.close();
		} catch (SQLException e) {
			System.out.println("Error al cargar datos de la BD.");
			e.printStackTrace();
		}
	}
}
	

