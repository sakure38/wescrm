package com.we.scrm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.we.scrm.common.page.Pagination;
import com.we.scrm.common.util.CalendarUtil;
import com.we.scrm.dao.WerunStepsDao;
import com.we.scrm.dao.WerunUserDao;
import com.we.scrm.domain.WerunSteps;
import com.we.scrm.domain.WerunUser;


@Service
public class WerunStepsService extends AbstractService<WerunSteps>{

	@Autowired
	private WerunStepsDao entityDao;

	@Autowired
	private WerunUserDao werunUserDao;


	public WerunSteps getById(Long id){
		return entityDao.getById(id);
	}


	@Transactional
	public WerunUser updateTodayStep(String openid, Integer step) {
		//今天的数据入库
		WerunUser werunUser = this.werunUserDao.getByOpenid(openid);
		if(null == werunUser) {
			werunUser = new WerunUser();
			werunUser.setSteps(0);
			werunUser.setOpenid(openid);
			werunUser.setCreateAt(new Date());
			this.werunUserDao.create(werunUser);
		}

		WerunSteps werunStep = new WerunSteps();
		werunStep.setOpenid(openid);
		werunStep.setYear(CalendarUtil.getYear());
		werunStep.setMonth(CalendarUtil.getMonth());
		werunStep.setDates(CalendarUtil.getDate());
		WerunSteps dbWerunStep = entityDao.getTodayStep(werunStep);

		if(null == dbWerunStep) {//今天的步数还没有入库
			werunUser.setSteps(werunUser.getSteps() + step);
			werunUserDao.update(werunUser);//更新用户总步数

			werunStep.setSteps(step);
			werunStep.setKillSteps(step);//消耗掉的
			werunStep.setCreateAt(new Date());
			entityDao.create(werunStep);//创建今天步数
		}else {
			dbWerunStep.setSteps(step);
			Integer unKillStep = step - dbWerunStep.getKillSteps();

			werunUser.setSteps(werunUser.getSteps() + unKillStep);
			werunUserDao.update(werunUser);//更新用户总步数

			dbWerunStep.setKillSteps(step);
			entityDao.update(dbWerunStep);//更新今天的步数
		}
		return werunUser;
	}

	public WerunSteps getTodayStep(WerunSteps werunStep){
		return entityDao.getTodayStep(werunStep);
	}

	public List<WerunSteps> queryAll(WerunSteps queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<WerunSteps> queryPage(WerunSteps queryEntity ,Pagination<WerunSteps> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<WerunSteps> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(WerunSteps entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(WerunSteps entity){
		entityDao.update(entity);
	}

	public void delete(WerunSteps entity){
		entityDao.delete(entity);
	}



}

