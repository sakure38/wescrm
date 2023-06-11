package com.we.scrm.common.t2b;

public class TableBean {
	
	private String tableSchema;
	private String tableName;
	
	private String colName;
	private String colType;
	private String colComment;
	
	
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColType() {
		return colType;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getColComment() {
		return colComment;
	}
	public void setColComment(String colComment) {
		this.colComment = colComment;
	}

	
}
