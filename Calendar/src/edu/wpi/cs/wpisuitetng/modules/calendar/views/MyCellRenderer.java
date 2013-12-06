package edu.wpi.cs.wpisuitetng.modules.calendar.views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class MyCellRenderer extends DefaultTableCellRenderer{
	
	private int row;
	private boolean personal;
	
	public MyCellRenderer(int row, boolean personal) {
		this.row = row;
		this.personal = personal;
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
			if(this.personal){
				cellComponent.setBackground(Color.CYAN);
			}
			if(!this.personal){
				cellComponent.setBackground(Color.BLUE);
			}
		}
		else{
			cellComponent.setBackground(Color.WHITE);
		}
		return cellComponent;
	
	}
}
