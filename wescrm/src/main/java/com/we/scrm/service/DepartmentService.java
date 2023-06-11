package com.we.scrm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.we.scrm.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.Department;
import com.we.scrm.service.DepartmentService;
import com.we.scrm.dao.DepartmentDao;


@Service
public class DepartmentService extends AbstractService<Department>{

	@Autowired
	private DepartmentDao entityDao;

	public Department getById(Long id){
		return entityDao.getById(id);
	}

	public List<Department> queryAll(Department queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<Department> queryPage(Department queryEntity ,Pagination<Department> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<Department> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(Department entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void syncDepartment(JSONArray jsonArray) {
		entityDao.removeAll();
		List<Department> list = new ArrayList<Department>();
		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsObj = jsonArray.getJSONObject(i);
			Department dept = new Department();
			dept.setName(jsObj.getString("name"));
			dept.setNameEn(jsObj.getString("name_en"));
			dept.setSort(jsObj.getInteger("order"));
			dept.setParentid(jsObj.getInteger("parentid"));
			dept.setPid(jsObj.getInteger("id"));
			dept.setCreateAt(new Date());
			list.add(dept);
		}
		entityDao.createList(list);
	}

	public void update(Department entity){
		entityDao.update(entity);
	}

	public void delete(Department entity){
		entityDao.delete(entity);
	}



}

