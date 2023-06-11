package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Department;


public interface DepartmentDao {

	public Department getById(Long id);

	public List<Department> queryAll(Department queryEntity);

	public Integer getTotalItemsCount(Department queryEntity);

	public List<Department> queryPage(Department queryEntity , Pagination<Department> page);

	public void create(Department entity);

	public void update(Department entity);

	public void delete(Department entity);

	public void removeAll();

	public void createList(List<Department> list);

}

