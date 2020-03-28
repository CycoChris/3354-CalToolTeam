package com.CalTool.GUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
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
 		
 		Cell cell = new Cell();
 		
 		jtable = new JTable(new CellTableModel(data, days));
 		jtable.setDefaultRenderer(CellInformation.class, cell);
 		jtable.setDefaultEditor(CellInformation.class, cell);
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
	boolean completed;
	
	public CellInformation(int day, String[] events) {
		this.day = day;
		this.events = events;
	}
}

// A class for making each cell in the table look exactly how we need it
class CellTableModel extends AbstractTableModel {

	List cellInfo;
	String[] columnHeaders;
	
	public CellTableModel(List cellInfo, String[] columnHeaders) {
		this.cellInfo = cellInfo;
		this.columnHeaders = columnHeaders;
	}
	
	@Override
	public int getRowCount() {
		return (cellInfo == null) ? 0 : cellInfo.size(); 
	}

	@Override
	public int getColumnCount() {
		return columnHeaders.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {	// TODO: Change so it gets the correct one
		return (cellInfo == null) ? null : cellInfo.get(columnIndex);
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

class Cell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	JPanel panel;
	JLabel[] label;
	JCheckBox[] completed;
	CellInformation cellInfo;
	
	public Cell() {
		panel = new JPanel();
//	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    
	    label = new JLabel[20];
	    completed = new JCheckBox[20];
	    for (int i = 0; i < label.length; i ++) {
	    	label[i] = new JLabel();
	    	completed[i] = new JCheckBox();
	    	
	    	panel.add(label[i]);
	    	panel.add(completed[i]);
	    }
	    
	}
	
	private void updateData(CellInformation cellInfo, boolean isSelected, JTable table) {
		this.cellInfo = cellInfo;
		

		
		// If the cell is defined
		if (cellInfo != null) {
			
			
			for (int i = 0; i < cellInfo.events.length; i ++) {
				label[i].setText(cellInfo.events[i]);
				completed[i].setSelected(cellInfo.completed);
				
				//SET TO VISIBLE
				label[i].setVisible(true);
				completed[i].setVisible(true);
			}
			for (int i = cellInfo.events.length; i < 20; i ++) {
				//SET TO inVISIBLE
				label[i].setVisible(false);
				completed[i].setVisible(false);
			}
			
			
			
		}
		// If the cell is not defined (null)
		else {
			for (int i = 0; i < 20; i ++) {
		
				//SET TO inVISIBLE
				label[i].setVisible(false);
				completed[i].setVisible(false);
			}
		}
		
		if (isSelected)
			panel.setBackground(table.getSelectionBackground());
		else 
			panel.setBackground(table.getBackground());
		
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		CellInformation cellInfo = (CellInformation)value;
		System.out.println("1");
		updateData(cellInfo, isSelected, table);
		return panel;
	}
	
	public Object getCellEditorValue() {
		return null;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		CellInformation cellInfo = (CellInformation)value;
		System.out.println("1");
		updateData(cellInfo, isSelected, table);
		return panel;
	}
}






























