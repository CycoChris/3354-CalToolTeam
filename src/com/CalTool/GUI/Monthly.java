package com.CalTool.GUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
    private JButton prev, next;
    private CellTableModel tableModel;
    
    // Object used to Fill table
    private String[] days;
    ArrayList<CellInformation> data;
	
	public Monthly() {
		// Instantiate the super class (for the JPanel)
 		super();

		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxlayout);
 		
 		populateData();
 		
 		tableModel = new CellTableModel(data, days);
 		
 		jtable = new JTable(tableModel);
 		jtable.setDefaultRenderer(CellInformation.class, new Cell());
 		jtable.setDefaultEditor(CellInformation.class, new Cell());
 		jtable.setRowHeight(100);
 		
 		
 		// create and add buttons
 		JPanel buttons = new JPanel();
 		prev = new JButton("Previous");
 		next = new JButton("Next");
 		buttons.add(prev);
 		buttons.add(next);
 		
 		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				data.add(new CellInformation(1000, new String[] {"SPECIAL EVENT"}));
				tableModel.fireTableDataChanged();	// To update the table
			}
		});
 		
 		// Add it to the current panel
 		add(buttons);
 		add(new JScrollPane(jtable));
	}
	
	// This can be the function that calls a JSON parser to get all of the information
	public void populateData() {
		days = new String[] {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
		data = new ArrayList<CellInformation>();
		
		for (int i = 1; i <= 31; i ++) {
			data.add(new CellInformation(i, new String[] {"Event 1", "Event 2"}));
		}

		
	}
	
	public Container getPanel() {
		return this;
	}
	
}

// A class used as a data structure for the information shown in each cell
class CellInformation {
	int day;
	String[] events;	// This can be changed to an object later on
	boolean[] completed;
	
	public CellInformation(int day, String[] events) {
		this.day = day;
		this.events = events;
		completed = new boolean[events.length];
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
		return (cellInfo == null) ? 0 : cellInfo.size()/7 + 1; 
	}

	@Override
	public int getColumnCount() {
		return columnHeaders.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {	// TODO: Change so it gets the correct one
		
		if (cellInfo == null) {
			return null;
		}
		
		if (rowIndex*7 + columnIndex >= cellInfo.size()) {
			return null;
		} else 
			return cellInfo.get(rowIndex*7 + columnIndex);
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
	JLabel dayNum;
	JLabel[] label;
	JCheckBox[] completed;
	CellInformation cellInfo;
	
	public Cell() {
		panel = new JPanel();
		dayNum = new JLabel();
		
	    label = new JLabel[20];
	    completed = new JCheckBox[20];
	    
	    panel.add(dayNum);
	    
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
			
			dayNum.setText("" + cellInfo.day);
			dayNum.setVisible(true);
			
			for (int i = 0; i < cellInfo.events.length; i ++) {
				label[i].setText(cellInfo.events[i]);
				completed[i].setSelected(cellInfo.completed[i]);
				completed[i].addActionListener(new CheckBoxActionListener(cellInfo, i));
				
				//SET TO VISIBLE
				label[i].setVisible(true);
				completed[i].setVisible(true);
			}
			for (int i = cellInfo.events.length; i < 20; i ++) {
				//SET TO inVISIBLE
				label[i].setText(" ");
				label[i].setVisible(false);
				completed[i].setVisible(false);
			}
			
			
			
		}
		// If the cell is not defined (null)
		else {
			dayNum.setText(" ");
			dayNum.setVisible(false);
			for (int i = 0; i < 20; i ++) {
				label[i].setText(" ");
				
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
		updateData(cellInfo, isSelected, table);
		return panel;
	}
	
	public Object getCellEditorValue() {
		return null;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		CellInformation cellInfo = (CellInformation)value;
		updateData(cellInfo, isSelected, table);
		return panel;
	}
}

class CheckBoxActionListener implements ActionListener {

	CellInformation cellInfo;
	int eventIndex;
	
	public CheckBoxActionListener(CellInformation cellInfo, int eventIndex) {
		this.cellInfo = cellInfo;
		this.eventIndex = eventIndex;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (((JCheckBox)e.getSource()).isSelected()) {
			cellInfo.completed[eventIndex] = true;
		} else {
			cellInfo.completed[eventIndex] = false;
		}
	}

}

























