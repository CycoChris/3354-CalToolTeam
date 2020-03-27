package com.CalTool.GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class Monthly extends JPanel {
	
	
	//headers for the table
    private JTable jtable;
    
    
    // Object used to Fill table
    private Object[][] cellData;
    private String[] days;
    ArrayList<CellInformation> data;
	
	public Monthly() {
		// Instantiate the super class (for the JPanel)
 		super();
 		
 		populateData();
 		
 		jtable = new JTable(new CellTableModel(data, days));
 		jtable.setDefaultRenderer(CellInformation.class, new CellRenderer());
 		jtable.setRowHeight(60);
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
        
		
		cellData = new Object[][] {
			new CellInformation[] {data.get(0), data.get(1), data.get(2), data.get(3), null, null, null},
			new CellInformation[] {data.get(0), data.get(1), data.get(2), data.get(3), null, null, null},
			new CellInformation[] {data.get(0), null, data.get(1), null, data.get(2), data.get(3), null},
			new CellInformation[] {null, data.get(0), data.get(1), null, data.get(2), data.get(3), null},
		};
		
		
	}
	
	public JPanel getPanel() {
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
		return (data == null) ? null : data.get(rowIndex);
	}
	
	public Class getColumnClass(int columnIndex) { 
		return CellInformation.class; 
	}
	
	public boolean isCellEditable(int columnIndex, int rowIndex) { 
		return false;
	}
	
}

class CellRenderer implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		CellInformation data = (CellInformation)value;
		
			 
	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JCheckBox box = new JCheckBox();
	    
	    panel.add(box);
	    
	    JLabel[] label = new JLabel[data.events.length + 1];
	    label[0] = new JLabel("" + data.day);
	    panel.add(label[0]);
	    for (int i = 0; i < data.events.length; i ++) {
	    	label[i+1] = new JLabel(data.events[i]);
	    	panel.add(label[i+1]);
	    }
	    
	    
	    
	    if (isSelected) {
	      panel.setBackground(table.getSelectionBackground());
	    }else{
	      panel.setBackground(table.getBackground());
	    }
		
		return null;
	}
	
}
