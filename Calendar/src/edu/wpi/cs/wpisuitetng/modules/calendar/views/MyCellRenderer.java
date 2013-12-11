package edu.wpi.cs.wpisuitetng.modules.calendar.views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class MyCellRenderer extends DefaultTableCellRenderer{
	
	private int row;
	private int whatType;
	
	public MyCellRenderer(int row, int whatType) {
		this.row = row;
		this.whatType = whatType;
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
			if(this.whatType == 0){
				cellComponent.setBackground(new Color(204, 99, 99));
			}
			if(this.whatType == 1){
				cellComponent.setBackground(new Color(33, 166, 166));
			}
			if(this.whatType == 2){
				cellComponent.setBackground(new Color(204, 66, 255));
			}
		}
		else{
			cellComponent.setBackground(Color.WHITE);
		}
		return cellComponent;
	
	}
}
