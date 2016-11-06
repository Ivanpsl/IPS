package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import utiles.Comprobaciones;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCreaCategoria extends JDialog {

	private static final long serialVersionUID = -8144900689006861847L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnCrear;
	private JButton btnCancelar;
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JLabel lblSexo;
	private JComboBox<String> comboSexo;
	private JLabel lblEdadDesde;
	private JTextField tfedadMinima;
	private JLabel lblHasta;
	private JTextField tfEdadMaxima;
	private VentanaPrincipal vp;

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public VentanaCreaCategoria(VentanaPrincipal v) {
		this.vp = v;
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setTitle("Crear categor\u00EDa");
		setBounds(100, 100, 441, 237);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtnCrear());
		contentPanel.add(getBtnCancelar());
		contentPanel.add(getLblNombre());
		contentPanel.add(getTfNombre());
		contentPanel.add(getLblSexo());
		contentPanel.add(getComboSexo());
		contentPanel.add(getLblEdadDesde());
		contentPanel.add(getTfedadMinima());
		contentPanel.add(getLblHasta());
		contentPanel.add(getTfEdadMaxima());
	}
	
	/**
	 * Otro constructor para cuando se editen las categorias
	 * @param v
	 * @param c
	 */
	public VentanaCreaCategoria(VentanaPrincipal v, Categoria c) {
		this.vp = v;
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setTitle("Crear categor\u00EDa");
		setBounds(100, 100, 441, 237);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtnCrear());
		contentPanel.add(getBtnCancelar());
		contentPanel.add(getLblNombre());
		contentPanel.add(getTfNombre());
		contentPanel.add(getLblSexo());
		contentPanel.add(getComboSexo());
		contentPanel.add(getLblEdadDesde());
		contentPanel.add(getTfedadMinima());
		contentPanel.add(getLblHasta());
		contentPanel.add(getTfEdadMaxima());
		
		//Cargamos los datos en las tf
		cargarDatosCategoria(c);
	}
	private void cargarDatosCategoria(Categoria c){
		getTfNombre().setText(c.getNombre());
		getTfedadMinima().setText(c.getEdadMinima()+"");
		getTfEdadMaxima().setText(c.getEdadMaxima()+"");
		if(c.getSexo() == Atleta.FEMENINO)
			getComboSexo().setSelectedIndex(0);
		getComboSexo().setSelectedIndex(1);
	}
	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!datosBienMetidos()){
						JOptionPane.showMessageDialog(null, "Por favor, compruebe los datos");
					}else{
						crearCategoria();
						dispose();
					}
				}
			});
			btnCrear.setToolTipText("Crea una categoria nueva.");
			btnCrear.setBounds(326, 164, 89, 23);
		}
		return btnCrear;
	}
	/**
	 * Crea la categoria y la añade a la lista de la ventana principal
	 */
	private void crearCategoria(){
		if(vp.getCategoriasCrearEvento() == null)
			throw new IllegalStateException("La lista de categorias de la vp al crear el evento es nula.");
		String nombre = getTfNombre().getText();
		int edadMin = Integer.parseInt(getTfedadMinima().getText());
		int edadMax = Integer.parseInt(getTfEdadMaxima().getText());
		int Sexo = Atleta.getSexotiInt(getComboSexo().getSelectedItem().toString());
		Categoria cat = new Categoria(nombre, edadMin, edadMax, Sexo);
		vp.añadirCategoriaAlCrearEvento(cat);
		
	}
	private boolean datosBienMetidos(){
		if(Comprobaciones.esString(getTfNombre().getText()) 
				&& Comprobaciones.esNumero(getTfedadMinima().getText())
				&& Comprobaciones.esNumero(getTfEdadMaxima().getText())
				){
			int edadMin = Integer.parseInt(getTfedadMinima().getText());
			int edadMax = Integer.parseInt(getTfEdadMaxima().getText());
			if(!(edadMin < edadMax)){
				return false;
			}
			return true;
			
		}
		return false;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setToolTipText("Cancela la creaci\u00F3n de la categor\u00EDa");
			btnCancelar.setBounds(10, 164, 89, 23);
		}
		return btnCancelar;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(112, 31, 46, 14);
		}
		return lblNombre;
	}

	private JTextField getTfNombre() {
		if (tfNombre == null) {
			tfNombre = new JTextField();
			tfNombre.setBounds(189, 28, 122, 20);
			tfNombre.setColumns(10);
		}
		return tfNombre;
	}

	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSexo.setBounds(112, 75, 46, 14);
		}
		return lblSexo;
	}

	private JComboBox<String> getComboSexo() {
		if (comboSexo == null) {
			comboSexo = new JComboBox<String>();
			DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
			modelo.addElement(Categoria.FEM);
			modelo.addElement(Categoria.MAS);
			comboSexo.setModel(modelo);
			comboSexo.setBounds(189, 72, 122, 20);
		}
		return comboSexo;
	}

	private JLabel getLblEdadDesde() {
		if (lblEdadDesde == null) {
			lblEdadDesde = new JLabel("Edad desde:");
			lblEdadDesde.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEdadDesde.setBounds(27, 117, 89, 14);
		}
		return lblEdadDesde;
	}

	private JTextField getTfedadMinima() {
		if (tfedadMinima == null) {
			tfedadMinima = new JTextField();
			tfedadMinima.setBounds(126, 114, 57, 20);
			tfedadMinima.setColumns(10);
			if (getTfEdadMaxima().getText() == "" && tfedadMinima.getText() != "") {
				getTfEdadMaxima().setText(Integer.parseInt(tfedadMinima.getText()) + 1 + "");
			}
			tfedadMinima.addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyTyped(KeyEvent e) {

		           if(Comprobaciones.esNumero(e.getKeyChar()+""))
		        	   tfedadMinima.setText(tfedadMinima.getText()+e.getKeyChar());
		        }
		        });
		}
		return tfedadMinima;
	}

	private JLabel getLblHasta() {
		if (lblHasta == null) {
			lblHasta = new JLabel("hasta");
			lblHasta.setBounds(203, 117, 32, 14);
		}
		return lblHasta;
	}

	private JTextField getTfEdadMaxima() {
		if (tfEdadMaxima == null) {
			tfEdadMaxima = new JTextField();
			tfEdadMaxima.setBounds(249, 114, 62, 20);
			tfEdadMaxima.setColumns(10);
			if (getTfedadMinima().getText() == "" && tfEdadMaxima.getText() != "") {
				getTfedadMinima().setText(Integer.parseInt(tfEdadMaxima.getText()) - 1 + "");
			}
			tfEdadMaxima.addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyTyped(KeyEvent e) {

		           if(Comprobaciones.esNumero(e.getKeyChar()+""))
		        	   tfEdadMaxima.setText(tfEdadMaxima.getText()+e.getKeyChar());
		        }
		        });
		}
		return tfEdadMaxima;
	}
}
