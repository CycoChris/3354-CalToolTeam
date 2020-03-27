package com.CalTool.GUI;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class Monthly extends JPanel{
	
	
	//headers for the table
    private JTable jtable;
    
    
    // Object used to Fill table
    private Object[][] cellData;
    private String[] days;
    ArrayList<CellInformation> data;
	
	public Monthly() {
		// Instantiate the super class (for the JPanel)
 		super();

		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxlayout);
 		
 		populateData();
 		
 		jtable = new JTable(new CellTableModel(data, days));
 		jtable.setDefaultRenderer(CellInformation.class, new CellRenderer());
 		jtable.setDefaultEditor(CellInformation.class, new CellEditor());
 		jtable.setRowHeight(100);
 		
 		
 		// Add it to the current panel
 		add(new JScrollPane(jtable));
	}
	
	// This can be the function that calls a JSON parser to get all of the information
	public void populateData() {
		days = new String[] {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
		data = new ArrayList<CellInformation>();
		
		data.add(new CellInformation(1, new String[] {"Event 1", "Event 2"}));
		data.add(new CellInformation(2, new String[] {"Event 3", "Event 4"}));
		data.add(new CellInformation(3, new String[] {"Event 5", "Event 6"}));
		data.add(new CellInformation(4, new String[] {"Event 7", "Event 8"}));
        data.add(null);
        data.add(null);
        data.add(null);
        
        
		cellData = new Object[][] {
			new CellInformation[] {data.get(0), data.get(1), data.get(2), data.get(3), null, null, null},
			new CellInformation[] {data.get(0), data.get(1), data.get(2), data.get(3), null, null, null},
			new CellInformation[] {data.get(0), null, data.get(1), null, data.get(2), data.get(3), null},
			new CellInformation[] {null, data.get(0), data.get(1), null, data.get(2), data.get(3), null},
		};
		
		
	}
	
	public Container getPanel() {
		return this;
	}
	
}

// A class used as a data structure for the information shown in each cell
class CellInformation {
	int day;
	String[] events;	// This can be changed to an object later on
	
	public CellInformation(int day, String[] events) {
		this.day = day;
		this.events = events;
	}
}

// A class for making each cell in the table look exactly how we need it
class CellTableModel extends AbstractTableModel {

	List data;
	String[] columnHeaders;
	
	public CellTableModel(List data, String[] columnHeaders) {
		this.data = data;
		this.columnHeaders = columnHeaders;
	}
	
	@Override
	public int getRowCount() {
		return (data == null) ? 0 : data.size(); 
	}

	@Override
	public int getColumnCount() {
		return columnHeaders.length;
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (data == null) ? null : data.get(columnIndex);
	}
	
	public Class getColumnClass(int columnIndex) { 
		return CellInformation.class; 
	}
	
	public boolean isCellEditable(int columnIndex, int rowIndex) { 
		return true;
	}
	
	public String getColumnName (int columnIndex) {
		return columnHeaders[columnIndex];
	}
}

class CellRenderer implements TableCellRenderer {

	CellComponent cellComp;
	
	public CellRenderer() {
		cellComp = new CellComponent();
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		CellInformation data = (CellInformation)value;
		
		cellComp.updateData(data, isSelected, table);
			 
	    
		return cellComp;
	}
	
}


class CellComponent extends JPanel {
	CellInformation cell;
	
	JCheckBox[] completed;
	JLabel[] label;

  	public CellComponent() {
  		super();
  		
	    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	    setLayout(layout);
	    
	    label = new JLabel[2];
    	completed = new JCheckBox[2];
    	
  	    for (int i = 0; i < 2; i ++) {
  	    	completed[i] = new JCheckBox();
  	    	label[i] = new JLabel();
  	    	
  	    	add(completed[i]);
  	    	add(label[i]);
  	    }
  }

	public void updateData(CellInformation cell, boolean isSelected, JTable table) {
		this.cell = cell;
		
		
		if (cell != null) {	
			for (int i = 0; i < cell.events.length; i ++) {	
				label[i].setText(cell.events[i]);
				completed[i].setVisible(true);
			} 	
			
		} else {
			for (int i = 0; i < 2; i ++) {  	  
				label[i].setText(" ");
				completed[i].setVisible(false);
			}
			
		}
		
		
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getBackground());
		}
		
	}

}


class CellEditor extends AbstractCellEditor implements TableCellEditor {

	CellComponent cellComp;
	
	public CellEditor() {
		cellComp = new CellComponent();
	}
	
	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		CellInformation cell = (CellInformation)value;
		cellComp.updateData(cell, true, table);
		return cellComp;
	}
	
}



