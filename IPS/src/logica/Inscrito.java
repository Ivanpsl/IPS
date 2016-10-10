package logica;

public class Inscrito {
	
	private boolean pagado;
	private int dorsal;
	private Atleta atleta;
	
	public Inscrito (Atleta atleta, int dorsal, boolean pagado) 
	{
		this.atleta = atleta;
		this.dorsal = dorsal;
		this.pagado = pagado;
	}
	
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	
	public int getDorsal() {
		return dorsal;
	}
	
	public boolean isPagado() {
		return pagado;
	}
}
