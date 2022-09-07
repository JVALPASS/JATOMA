package it.unisa.pawt;
import it.unisa.table.*;

public class pTable extends pComponent implements TableModelListener {
	static int NUMBER = 0;
	pComponent _root;
	
	protected TableModel _dataModel;
	protected ListSelectionModel _selectionModel;
	protected TableColumnModel _columnModel;
	
	protected boolean           rowSelectionAllowed;
	protected String 			selectionBackground;
	protected boolean           cellSelectionEnabled;
    protected boolean           showHorizontalLines;
    protected boolean           showVerticalLines;
    protected int               rowHeight;
    protected int               rowMargin;
    protected String 			selectionForeground;
    protected String 			foreground;
    protected String 			background;
    transient protected int     editingColumn;
    transient protected int     editingRow;
    private boolean 			isRowHeightSet;
    protected String             gridColor;
 
 
	/******************Costruttori**********************/

    public pTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
    {
    	_title = "Tabella"+this.NUMBER;
    	NUMBER++;
    	if(dm == null)
    		_dataModel = createDefaultDataModel();
    	else _dataModel = dm;
    	if (cm == null)
    	{
    		_columnModel = createDefaultColumnModel();
    		createDefaultColumnsFromModel();
    	}
    	else _columnModel = cm;
    	if (sm == null)
    		_selectionModel = createDefaultSelectionModel();
    	else _selectionModel = sm;

    	initializeLocalVars();
    }

    public pTable(){
    	this(null, null, null);
    }

    public pTable(int numRows, int numColumns) {
    	this(new DefaultTableModel(numRows, numColumns));
    }

    public pTable(final Object[][] rowData, final Object[] columnNames) {
    	this(new AbstractTableModel() {
    		public int getColumnCount() { return columnNames.length; }
    		public String getColumnName(int column) { return columnNames[column].toString(); }
    		public int getRowCount() { return rowData.length; }
    		public Object getValueAt(int row, int col) { return rowData[row][col]; }
    		public boolean isCellEditable(int row, int column) { return true; }
    		public void setValueAt(Object value, int row, int col) {
    			rowData[row][col] = value;
    			fireTableCellUpdated(row, col);
    		}
    	});
    }

    public pTable(TableModel dm){
    	this(dm, null, null);
    }

    public pTable(TableModel dm, TableColumnModel cm){
    	this(dm, cm, null);
    }

    /******************** SUPPORT *********************************/
    
    protected void initializeLocalVars() {
    	this.gridColor = "BLACK";
    	
    	this.rowHeight = 50;
    
    	this.showHorizontalLines = true;
    	this.showVerticalLines = true;
    
    	isRowHeightSet = false;
    
    	this.rowMargin = 20;
   
    	this.rowSelectionAllowed = true;
    	setColumnSelectionAllowed(false);
   
    	

    	this.editingColumn = -1;
    	
    	this.editingRow = -1;
    	
    	this.background = "GAINSBORO";
    	
    	this.foreground = "BLACK";
  
    	this.selectionBackground = "SKYBLUE";
    	this.selectionForeground = "WHITE";
    	setCellSelectionEnabled(true);
    }

    protected TableColumnModel createDefaultColumnModel() {
    	return new DefaultTableColumnModel();
    }

    public void createDefaultColumnsFromModel() {
    	TableModel m = getModel();
    	if (m != null) {
    		// Remove any current columns
    		TableColumnModel cm = getColumnModel();
    		while (cm.getColumnCount() > 0) {
    			cm.removeColumn(cm.getColumn(0));
    		}

    		// Create new columns from the data model info
    		for (int i = 0; i < m.getColumnCount(); i++) {
    			TableColumn newColumn = new TableColumn(i);
    			addColumn(newColumn);
    		}
    	}
    }

    protected TableModel createDefaultDataModel() {
    	return new DefaultTableModel();
    }

    protected ListSelectionModel createDefaultSelectionModel() {
    	return new DefaultListSelectionModel();
    }

    private int boundRow(int row) throws IllegalArgumentException {
    	if (row < 0 || row >= getRowCount()) {
    		throw new IllegalArgumentException("Row index out of range");
    	}
    	return row;
    }

    private int boundColumn(int col) {
    	if (col< 0 || col >= getColumnCount()) {
    		throw new IllegalArgumentException("Column index out of range");
    	}
    	return col;
    }

    /******************** METODI PCOMPONENT ********************/

    public void create() {
    	parserHTML.addTable(_title, this);
    }

    public void setRoot(pComponent root){
    	this._root = root;
    }

	/*********************** METODI JTABLE ******************************/

    public void addColumn(TableColumn aColumn) {
    	if (aColumn.getHeaderValue() == null) {
    		int modelColumn = aColumn.getModelIndex();
    		String columnName = getModel().getColumnName(modelColumn);
    		aColumn.setHeaderValue(columnName);
    	}
    	getColumnModel().addColumn(aColumn);
    }

    public void addColumnSelectionInterval(int index0, int index1) {
    	getColumnModel().getSelectionModel().addSelectionInterval(boundColumn(index0), boundColumn(index1));
    }

    public void addRowSelectionInterval(int index0, int index1) {
    	getSelectionModel().addSelectionInterval(boundRow(index0), boundRow(index1));
    }

    public void clearSelection() {
    	getSelectionModel().clearSelection();
    	getColumnModel().getSelectionModel().clearSelection();
    }

    public int convertColumnIndexToModel(int viewColumnIndex) {

    	if (viewColumnIndex < 0) {
    		return viewColumnIndex;
    	}
    	return getColumnModel().getColumn(viewColumnIndex).getModelIndex();
    }

    public int convertRowIndexToModel(int viewRowIndex) {
    	/*RowSorter sorter = getRowSorter();
        if (sorter != null) {
            return sorter.convertRowIndexToModel(viewRowIndex);
        }*/
    	return viewRowIndex;
    }
    
    public boolean editCellAt(int row, int column){
        
//        if (row < 0 || row >= getRowCount() ||
//            column < 0 || column >= getColumnCount()) {
//            return false;
//        }
//
//        if (!isCellEditable(row, column))
//            return false;
//    	
//        getSelectionModel().clearSelection();
//        setEditingRow(row);
//        setEditingColumn(column);
//        
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				String str = "app.setCellSelection"
//    						+ "('"
//    						+_title+"','"	
//    						+editingRow+"','"			//table id
//    						+editingColumn				//old value
//    						+"')";				
//    				wv.sendJavascript(str);
//    			}	

//    		} catch(Exception e) {}
            return true;
    }

    public String getBackground() {
    	return this.background;
    }


    public boolean getCellSelectionEnabled() {
    	return getRowSelectionAllowed() && getColumnSelectionAllowed();
    }

    public Class<?> getColumnClass(int column) {
    	return getModel().getColumnClass(convertColumnIndexToModel(column));
    }


    public int getColumnCount() {
    	return getColumnModel().getColumnCount();
    }

    public TableColumnModel getColumnModel() {
    	return _columnModel;
    }

    public String getColumnName(int column) {
    	return getModel().getColumnName(convertColumnIndexToModel(column));
    }

    public boolean getColumnSelectionAllowed() {
    	return getColumnModel().getColumnSelectionAllowed();
    }

    public int getEditingColumn() {
    	return editingColumn;
    }
    public int getEditingRow() {
    	return editingRow;
    }

    public String getForeground() {
    	return this.foreground;
    }

    public String getGridColor() {
    	return gridColor;
    }
    public TableModel getModel() {
    	return _dataModel;
    }

    public int getRowCount() {
    	return getModel().getRowCount();
    }

    public int getRowHeight() {
    	return rowHeight;
    }

    public int getRowMargin() {
    	return rowMargin;
    }


    public boolean getRowSelectionAllowed() {
    	return rowSelectionAllowed;
    }

    public int[] getSelectedColumns() {
    	return getColumnModel().getSelectedColumns();
    }


    public int[] getSelectedRows() {
    	int iMin = getSelectionModel().getMinSelectionIndex();
    	int iMax = getSelectionModel().getMaxSelectionIndex();

    	if ((iMin == -1) || (iMax == -1)) {
    		return new int[0];
    	}

    	int[] rvTmp = new int[1+ (iMax - iMin)];
    	int n = 0;
    	for(int i = iMin; i <= iMax; i++) {
    		if (getSelectionModel().isSelectedIndex(i)) {
    			rvTmp[n++] = i;
    		}
    	}
    	int[] rv = new int[n];
    	System.arraycopy(rvTmp, 0, rv, 0, n);
    	return rv;
    }

  
    public String getSelectionBackground() {
    	return selectionBackground;
    }

    public String getSelectionForeground() {
    	return selectionForeground;
    }

    public ListSelectionModel getSelectionModel() {
    	return _selectionModel;
    }

    public Object getValueAt(int row, int column) {
    	return getModel().getValueAt(convertRowIndexToModel(row),
    			convertColumnIndexToModel(column));
    }


    public boolean isCellEditable(int row, int column) {
    	return getModel().isCellEditable(convertRowIndexToModel(row),
    			convertColumnIndexToModel(column));
    }

    public void setBackground(String bg) {
//    	if (bg == null) {
//    		throw new IllegalArgumentException("New color is null");
//    	}
//    	if(!bg.equals(this.background)) {
//    		String oldBg = this.background;
//    		this.background = bg;
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				System.out.println("setProperty bg");
//    				String str = "app.setProperty"
//    						+ "('"
//    						+_title+"','"			//table id
//    						+"background"+"','"     //property change
//    						+oldBg+"','"			//old value
//    						+bg+"','"				//new value
//    						+"column"				//type
//    						+"')";				
//    				wv.sendJavascript(str);
//    			}	
//    		} catch(Exception e) {}
//    	}	
    }

    public void setCellSelectionEnabled(boolean cellSelectionEnabled) {
    	setRowSelectionAllowed(cellSelectionEnabled);
    	setColumnSelectionAllowed(cellSelectionEnabled);
    	this.cellSelectionEnabled = cellSelectionEnabled;
    }

    public void setColumnSelectionAllowed(boolean columnSelectionAllowed) {
    	getColumnModel().setColumnSelectionAllowed(columnSelectionAllowed);
    }

    public void setEditingColumn(int aColumn) {
    	editingColumn = aColumn;
    }

    public void setEditingRow(int aRow) {
    	editingRow = aRow;
    }

    public void setForeground(String fg) {
//    	if (fg == null) {
//    		throw new IllegalArgumentException("New color is null");
//    	}
//    	String oldFg = this.foreground;
//    	this.foreground = fg;
//    	if(!oldFg.equals(fg)){
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				String str = "app.setProperty"
//    						+ "('"
//    						+_title+"','"			//table id
//    						+"color"+"','"     		//property change
//    						+oldFg+"','"			//old value
//    						+fg+"','"				//new value
//    						+"column"				//type
//    						+"')";				
//    				wv.sendJavascript(str);
//    			}	
//    		} catch(Exception e) {}
//    	}
    }

    public void setGridColor(String gridColor) {
//    	if (gridColor == null) {
//    		throw new IllegalArgumentException("New color is null");
//    	}
//    	
//    	String old = this.gridColor;
//    	this.gridColor = gridColor;
//    	
//    	if(!old.equals(gridColor)){
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				String str = "app.setProperty"
//    						+ "('"
//    						+_title+"','"			//table id
//    						+"border-color"+"','"   //property change
//    						+old+"','"				//old value
//    						+gridColor+"','"		//new value
//    						+"column"				//type
//    						+"')";				
//    				wv.sendJavascript(str);
//    			}	
//    		} catch(Exception e) {}
    	}
    

    public void setIntercellSpacing(int height, int width) {
    	// Set the rowMargin here and columnMargin in the TableColumnModel
//  
//    	int old = getColumnModel().getColumnMargin();
//    	setRowMargin(height);
//    	getColumnModel().setColumnMargin(width);
//    	
//    	if(old != width){
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				String str1 = "app.setProperty"
//    						+ "('"
//    						+_title+"','"			//table id
//    						+"padding-left"+"','"   //property change
//    						+old+"px"+"','"			//old value
//    						+width+"px"+"','"		//new value
//    						+"column"				//type
//    						+"')";				
//    				
//    				String str2 = "app.setProperty"
//    						+ "('"
//    						+_title+"','"			//table id
//    						+"padding-right"+"','" 	//property change
//    						+old+"px"+"','"			//old value
//    						+width+"px"+"','"		//new value
//    						+"column"				//type
//    						+"')";			
//    				wv.sendJavascript(str1);
//    				wv.sendJavascript(str2);
//    			}	
//    		} catch(Exception e) {}
//    	}
    }

    public void setRowHeight(int rowHeight) {
//    	if (rowHeight <= 0) {
//    		throw new IllegalArgumentException("New row height less than 1");
//    	}
//    	int old = this.rowHeight;
//    	this.rowHeight = rowHeight;
//    	isRowHeightSet = true;
//
//    	
//       	if(old != rowHeight){
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				String str = "app.setProperty"
//    						+ "('"
//    						+_title+"','"			//table id
//    						+"height"+"','"   		//property change
//    						+old+"px"+"','"			//old value
//    						+rowHeight+"px"+"','"	//new value
//    						+"row"					//type
//    						+"')";				
//    				
//    					
//    				wv.sendJavascript(str);
//    				
//    			}	
//    		} catch(Exception e) {}
//    	}

    }
    

    public void setRowMargin(int rowMargin) {
//    	int old = this.rowMargin;
//    	this.rowMargin = rowMargin;
//    	if(old != rowMargin){
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				String str1 = "app.setProperty"
//    						+ "('"
//    						+_title+"','"			//table id
//    						+"padding-top"+"','"   	//property change
//    						+old+"px"+"','"					//old value
//    						+rowMargin+"px"+"','"		//new value
//    						+"column"				//type
//    						+"')";				
//    				
//    				String str2 = "app.setProperty"
//    						+ "('"
//    						+_title+"','"			//table id
//    						+"padding-bottom"+"','" //property change
//    						+old+"px"+"','"				//old value
//    						+rowMargin+"px"+"','"	//new value
//    						+"column"				//type
//    						+"')";			
//    				wv.sendJavascript(str1);
//    				wv.sendJavascript(str2);
//    			}	
//    		} catch(Exception e) {}
//    	}
//    
    }

    public void setRowSelectionAllowed(boolean rowSelectionAllowed) {
    	this.rowSelectionAllowed = rowSelectionAllowed;
    }


    public void setRowSelectionInterval(int index0, int index1) {
//    	int[] rowsOld = this.getSelectedRows();
//    	int oldIndex=-1;
//    	if(rowsOld.length != 0){
//    		oldIndex = rowsOld[rowsOld.length-1];
//    	}
//    	getSelectionModel().setSelectionInterval(boundRow(index0), boundRow(index1)); 
//    	
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				
//    				String str = "app.setRowSelection"
//    						+ "('"
//    						+_title+"','"		//table id
//    						+oldIndex+"','"     //old value
//    						+index0			 //new value			
//    						+"')";				
//    				wv.sendJavascript(str);
//    				if(oldIndex == index0){
//    	    			getSelectionModel().clearSelection();
//    	    		}
//    			}	
//    		} catch(Exception e) {}
    }

    public void setSelectionBackground(String selectionBackground) {
//    	if (selectionBackground == null) {
//    		throw new IllegalArgumentException("New color is null");
//    	}
//    	if(!selectionBackground.equals(this.selectionBackground)) {
//    		String old = this.selectionBackground;
//        	this.selectionBackground = selectionBackground;
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				System.out.println("setProperty bg");
//    				String str = "app.setProperty"
//    						+ "('"
//    						+_title+"','"				//table id
//    						+"background"+"','"     	//property change
//    						+old+"','"					//old value
//    						+selectionBackground+"','"	//new value
//    						+"column"+"','"				//type
//    						+"selection"
//    						+"')";				
//    				wv.sendJavascript(str);
//    			}	
//    		} catch(Exception e) {}
//    	}	
    }

    public void setSelectionForeground(String selectionForeground) { 	
//    	if (selectionForeground == null) {
//    		throw new IllegalArgumentException("New color is null");
//    	}
//    	String old = this.selectionForeground;
//    	this.selectionForeground = selectionForeground;
//    	if(!old.equals(selectionForeground)){
//    		try {
//    			CordovaWebView wv = Constants.view;
//    			if(wv != null){
//    				String str = "app.setProperty"
//    						+ "('"
//    						+_title+"','"				//table id
//    						+"color"+"','"     			//property change
//    						+old+"','"					//old value
//    						+selectionForeground+"','"	//new value
//    						+"column"+"','"				//type
//    						+"selection"
//    						+"')";				
//    				wv.sendJavascript(str);
//    			}	
//    		} catch(Exception e) {}
//    	}
    }

    public void setValueAt(Object aValue, int row, int column) {
//    	getModel().setValueAt(aValue, convertRowIndexToModel(row),
//    			convertColumnIndexToModel(column));
//    	
//    	try {
//			CordovaWebView wv = Constants.view;
//			if(wv != null){
//				
//				String str = "app.setValue"
//						+ "('"
//						+_title+"','"		//table id
//						+row+"','"     		//row
//						+column+"','"		//column
//						+aValue				//value			
//						+"')";		
//				
//			
//				
//				wv.sendJavascript(str);
//			}	
//		} catch(Exception e) {}
//    	
//    }
//
//    /***********NEW METHOD *******/
//    /**
//     * Track changes to the table contents (row insertions)
//     */
//    public void tableRowsInserted(TableModelEvent e) {
//    	
//    	try
//    	{
//    	CordovaWebView wv = Constants.view;
//		if(wv != null){
//			
//			String data = "{";
//		    for(int i=0; i<getColumnCount(); i++)
//		    {
//		    	data += "\""+i+"\":\""+getValueAt(e.getFirstRow(), i)+"\"";
//		    	int c=i+1;
//		    	if(c != getColumnCount()) data+=",";
//		    }
//		    data += "}";
//			String str = "app.insertRow"
//					+ "('"
//					+_title+"','"
//					+data		
//					+"')";		
//
//			wv.sendJavascript(str);
//		}	
//	} catch(Exception exc) {}
}


    /**
     * Track changes to the table contents (row deletions)
     */
    public void tableRowsDeleted(TableModelEvent e) {
//    	int start = e.getFirstRow();
//        int end = e.getLastRow();
//        if (start < 0) {
//            start = 0;
//        }
//        if (end < 0) {
//            end = getRowCount()-1;
//        }
//        
//        int deletedCount = end - start + 1;
//        int previousRowCount = getRowCount() + deletedCount;
//        getSelectionModel().removeIndexInterval(start, end);
//    	try
//    	{
//    	CordovaWebView wv = Constants.view;
//		if(wv != null){
//			String str = "app.removeRow"
//					+ "('"
//					+_title+"','"
//					+start		
//					+"')";		
//
//			wv.sendJavascript(str);
//		}	
//	} catch(Exception exc) {}
    }

    /*********** LISTENER *******/
    
    public void tableChanged(TableModelEvent e) {
    	 
//    	if (e.getType() == TableModelEvent.INSERT) {
//            tableRowsInserted(e);
//           
//            return;
//        }
//	
//    	 if (e.getType() == TableModelEvent.DELETE) {
//             tableRowsDeleted(e);
//             return;
//         }
//	}

}}
