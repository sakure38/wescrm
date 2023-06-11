package com.we.scrm.dao;

import java.util.List;

import com.we.scrm.common.t2b.TableBean;

public interface TableBeanDao {
	public String getCurrentTimestamp();
	public List<String> listTables(TableBean vo);
	public List<TableBean> listTableCols(TableBean vo);
}

