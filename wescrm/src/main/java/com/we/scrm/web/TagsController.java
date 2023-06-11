package com.we.scrm.web;

import com.we.scrm.common.enums.TagsTypeEnum;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.Tags;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TagsController extends  AbstractController{

    String active = "tags";

    @RequestMapping("/tags/page")
    public ModelAndView tagsPage(Tags queryEntity, Pagination<Tags> page){
        Map<String, Object> model = new HashMap<String ,Object>();
//        page.setPageSize(2);
        page = tagsService.queryPage(queryEntity, page);
        model.put("page", page);
        model.put("tagsType", TagsTypeEnum.toMap());
        model.put("queryEntity", queryEntity);
        return prepareModelAndView("tagsPage", active, model);
    }

    @RequestMapping("/tags/merge")
    @ResponseBody
    public String tagsMerge(Tags entity){
        if(StringUtils.isNotBlank(entity.getContent())){
            if(null != entity.getId()){//是否是添加
                tagsService.update(entity);
            }else{
                tagsService.create(entity);
            }
        }else{
            return JsonView.failureJson("标签内容不能为空");
        }
        return JsonView.successJson();
    }

    @RequestMapping("tags/get")
    @ResponseBody
    public String tagsGet(Tags entity){
        entity = tagsService.getById(entity.getId());
        return JsonView.successJson(entity);
    }

    @RequestMapping("tags/delete")
    @ResponseBody
    public String tagsDelete(Tags entity){
        tagsService.delete(entity);
        return JsonView.successJson();
    }

    @RequestMapping("/tags/all")
    @ResponseBody
    public String getAll(Tags queryEntity) {
        return JsonView.successJson(tagsService.queryAll(queryEntity));
    }

}
