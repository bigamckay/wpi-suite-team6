package edu.wpi.cs.wpisuitetng.modules.calendar.views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class MyCellRenderer extends DefaultTableCellRenderer{
	
	private int row;
	
	public MyCellRenderer(int row) {
		this.row = row;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (table.getValueAt(row,column) == null) {
			cellComponent.setBackground(Color.WHITE);
			//System.out.println("empty");
		}
		else if(this.row == row){
			//System.out.println("row " + row);
			//System.out.println("RED");
			cellComponent.setBackground(Color.CYAN);
		}
		else{
			cellComponent.setBackground(Color.WHITE);
		}
		return cellComponent;
	
	}
}
