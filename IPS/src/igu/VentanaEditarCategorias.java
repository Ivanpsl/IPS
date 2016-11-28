package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logica.Vistas.Categoria;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaEditarCategorias extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable tableEditarCat;
	String[] cabecera = {"Nombre","Sexo","Edad Minima","Edad Maxima"};
	private ModeloNoEditable modeloTabla;
	private VentanaPrincipal vp ;
	private ArrayList<Categoria>categorias;
	private ArrayList<Categoria>categoriasModificadas;
	private JTextField txtNombre;
	private JTextField txtEdadMinima;
	private JTextField txtEdadMaxima;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNombre;
	private JLabel lblSexo;
	private JLabel lblEdadMinima;
	private JLabel lblEdadMaxima;
	private JButton btnModificar;
	private JButton btnEliminarCategoria;
	private int selectedRow=-1;
	private JButton btnConfirmar;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			VentanaEditarCategorias dialog = new VentanaEditarCategorias();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public VentanaEditarCategorias(VentanaPrincipal p, ArrayList<Categoria> cat) {
		this.vp=p;
		this.categorias=cat;
		this.categoriasModificadas=cat;
		setBounds(100, 100, 895, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPane());
		contentPanel.add(getTxtNombre());
		contentPanel.add(getTxtEdadMinima());
		contentPanel.add(getTxtEdadMaxima());
		contentPanel.add(getRdbtnM());
		contentPanel.add(getRdbtnF());
		contentPanel.add(getLblNombre());
		contentPanel.add(getLblSexo());
		contentPanel.add(getLblEdadMinima());
		contentPanel.add(getLblEdadMaxima());
		contentPanel.add(getBtnModificar());
		contentPanel.add(getBtnEliminarCategoria());
		contentPanel.add(getBtnConfirmar());
		this.setLocationRelativeTo(null);
		//this.setAlwaysOnTop(true);
		
		rellenarTabla(cat);

	}
	
	private void rellenarTabla(ArrayList<Categoria> categ){
		for (int i = 0; i < categ.size(); i++) {
			String[] fila = new String[4];
			
			fila[0]= categ.get(i).getNombre();
			fila[1]=categ.get(i).getSexo() ==0? "masculino":"femenino";
			fila[2]= String.valueOf(categ.get(i).getEdadMinima());
			fila[3]= String.valueOf(categ.get(i).getEdadMaxima());
			
			modeloTabla.addRow(fila);
		}
		tableEditarCat.setModel(modeloTabla);
	}
	
	private void clickTabla() {
		selectedRow=tableEditarCat.getSelectedRow();
		if(selectedRow!=-1){
			txtNombre.setText(String.valueOf(tableEditarCat.getValueAt(selectedRow, 0)));
			txtEdadMaxima.setText(String.valueOf(tableEditarCat.getValueAt(selectedRow, 3)));
			txtEdadMinima.setText(String.valueOf(tableEditarCat.getValueAt(selectedRow, 2)));
			String sexo = String.valueOf(tableEditarCat.getValueAt(selectedRow, 1));
			if(sexo.equals("masculino")){
				rdbtnM.setSelected(true);
				rdbtnF.setSelected(false);
			}else{
				rdbtnM.setSelected(false);
				rdbtnF.setSelected(true);
			}
			btnEliminarCategoria.setEnabled(true);
			btnModificar.setEnabled(true);
		}else{
			deshabilitarBotones();
		}
		
	}
	private void deshabilitarBotones(){
		btnEliminarCategoria.setEnabled(false);
		btnModificar.setEnabled(false);
	}
	private void limpiarEtiquetas(){
		txtNombre.setText("");
		txtEdadMaxima.setText("");
		txtEdadMinima.setText("");
	}
	private boolean comprobarCampos(){
		if(txtNombre.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Rellene el campo Nombre");
			return false;

		}
		if( txtEdadMaxima.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Rellene el campo Edad Minima");
			return false;

		}
		if(txtEdadMaxima.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Rellene bien el campo Edad Maxima");
			return false;

		}
		if(Integer.parseInt(txtEdadMaxima.getText())>120){
			JOptionPane.showMessageDialog(null, "Edad Maxima fuera de rango");
			return false;

		}
		if(Integer.parseInt(txtEdadMinima.getText())<=0){
			JOptionPane.showMessageDialog(null, "Edad Minima fuera de rango");
			return false;

		}
			
		
		return true;
	}
	private void modificar(){
		if(selectedRow!=-1 ){
			if(comprobarCampos()){
				categoriasModificadas.set(selectedRow, new Categoria(txtNombre.getText(),Integer.parseInt(txtEdadMinima.getText()), 
						Integer.parseInt(txtEdadMaxima.getText()),  rdbtnF.isSelected()?1:0));
				
				modeloTabla=new ModeloNoEditable(cabecera, 0);
				tableEditarCat.setModel(modeloTabla);
				rellenarTabla(categoriasModificadas);
				limpiarEtiquetas();
				deshabilitarBotones();
				selectedRow=-1;
			}
		}else{
			JOptionPane.showMessageDialog(null, "Seleccione una categoria");
		}
		
		
	}
	private void eliminarCategoria(){
		if(selectedRow!=-1){
			categoriasModificadas.remove(selectedRow);
			
			modeloTabla=new ModeloNoEditable(cabecera, 0);
			tableEditarCat.setModel(modeloTabla);
			rellenarTabla(categoriasModificadas);
			deshabilitarBotones();
			selectedRow=-1;
		}else{
			deshabilitarBotones();
		}
		
		
	}
	private void confirmar() {
		String respuesta =vp.comprobarCategorias(categoriasModificadas);
		if(respuesta.equals("ok")){
			vp.editarCategoriasAlCrearEvento(categoriasModificadas);
			dispose();
		}else{
			if(respuesta.equals("ambos")){
				JOptionPane.showMessageDialog(null, "Error, faltan rangos de edad por contemplar y las edades estan solapadas");
			}
			if(respuesta.equals("rango")){
				JOptionPane.showMessageDialog(null, "Error, no estan todos los rangos de edad contemplados");
			}
			if(respuesta.equals("solapados")){
				JOptionPane.showMessageDialog(null, "Error, las edades estan solapadas");
			}
			if(respuesta.equals("repetido")){
				JOptionPane.showMessageDialog(null, "Error, nombres repetidos");
			}
		}
		limpiarEtiquetas();
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(57, 46, 432, 292);
			scrollPane.setViewportView(getTableEditarCat());
		}
		return scrollPane;
	}
	private JTable getTableEditarCat() {
		if (tableEditarCat == null) {
			modeloTabla = new ModeloNoEditable(cabecera, 0);
			tableEditarCat = new JTable(modeloTabla);
			tableEditarCat.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					clickTabla();
				}


			});
		}
		return tableEditarCat;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if(!Character.isLetter(c)){
						arg0.consume();
					}
				}
			});
			txtNombre.setBounds(707, 76, 107, 20);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JTextField getTxtEdadMinima() {
		if (txtEdadMinima == null) {
			txtEdadMinima = new JTextField();
			txtEdadMinima.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if(!Character.isDigit(c)){
						arg0.consume();
					}
				}
			});
			txtEdadMinima.setColumns(10);
			txtEdadMinima.setBounds(707, 184, 107, 20);
		}
		return txtEdadMinima;
	}
	private JTextField getTxtEdadMaxima() {
		if (txtEdadMaxima == null) {
			txtEdadMaxima = new JTextField();
			txtEdadMaxima.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if(!Character.isDigit(c)){
						arg0.consume();
					}
				}
			});
			txtEdadMaxima.setColumns(10);
			txtEdadMaxima.setBounds(707, 235, 107, 20);
		}
		return txtEdadMaxima;
	}
	private JRadioButton getRdbtnM() {
		if (rdbtnM == null) {
			rdbtnM = new JRadioButton("M");
			rdbtnM.setSelected(true);
			buttonGroup.add(rdbtnM);
			rdbtnM.setBounds(705, 131, 46, 23);
		}
		return rdbtnM;
	}
	private JRadioButton getRdbtnF() {
		if (rdbtnF == null) {
			rdbtnF = new JRadioButton("F");
			buttonGroup.add(rdbtnF);
			rdbtnF.setBounds(768, 131, 46, 23);
		}
		return rdbtnF;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(528, 76, 97, 20);
		}
		return lblNombre;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo");
			lblSexo.setBounds(528, 135, 97, 20);
		}
		return lblSexo;
	}
	private JLabel getLblEdadMinima() {
		if (lblEdadMinima == null) {
			lblEdadMinima = new JLabel("Edad Minima");
			lblEdadMinima.setBounds(528, 187, 97, 20);
		}
		return lblEdadMinima;
	}
	private JLabel getLblEdadMaxima() {
		if (lblEdadMaxima == null) {
			lblEdadMaxima = new JLabel("Edad Maxima");
			lblEdadMaxima.setBounds(528, 238, 97, 20);
		}
		return lblEdadMaxima;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setEnabled(false);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					modificar();
				}
			});
			btnModificar.setBounds(725, 315, 89, 23);
		}
		return btnModificar;
	}
	private JButton getBtnEliminarCategoria() {
		if (btnEliminarCategoria == null) {
			btnEliminarCategoria = new JButton("Eliminar Categoria");
			btnEliminarCategoria.setEnabled(false);
			btnEliminarCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					eliminarCategoria();
				}
			});
			btnEliminarCategoria.setBounds(539, 315, 154, 23);
		}
		return btnEliminarCategoria;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					confirmar();
				}

				
			});
			btnConfirmar.setBounds(707, 378, 107, 23);
		}
		return btnConfirmar;
	}
}
