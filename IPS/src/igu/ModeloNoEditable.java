package igu;

import javax.swing.table.DefaultTableModel;

/**
 * 
 * Clase modeloNoEditable que usaremos para representar las tablas y definirlas como no editables.
 *
 */
public class ModeloNoEditable extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
		public ModeloNoEditable(Object[] columnNames, int rowCount) {
			super(columnNames, rowCount);
	   }
		@Override
		public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	}
