package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.WerunSteps;


public interface WerunStepsDao {

	public WerunSteps getById(Long id);

	public WerunSteps getTodayStep(WerunSteps werunStep);

	public List<WerunSteps> queryAll(WerunSteps queryEntity);

	public Integer getTotalItemsCount(WerunSteps queryEntity);

	public List<WerunSteps> queryPage(WerunSteps queryEntity , Pagination<WerunSteps> page);

	public void create(WerunSteps entity);

	public void update(WerunSteps entity);

	public void delete(WerunSteps entity);



}

