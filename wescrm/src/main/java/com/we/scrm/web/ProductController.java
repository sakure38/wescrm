package com.we.scrm.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.we.scrm.bean.AttachmentBean;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.common.shiro.ShiroContext;
import com.we.scrm.common.util.IdUtil;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.AuthUser;
import com.we.scrm.domain.Category;
import com.we.scrm.domain.Product;

@Controller
public class ProductController extends  AbstractController{

    String active = "product";

    @GetMapping("/product/page")
    public ModelAndView productPage(Product queryEntity, Pagination<Product> page){
        Map<String, Object> model = new HashMap<String,Object>();
        model.put("queryEntity", queryEntity);
        model.put("page", this.productService.queryPage(queryEntity, page));
        return prepareModelAndView("productPage", active, model);
    }

    @GetMapping("/product/merge")
    public ModelAndView productMerge(Product queryEntity){
        Map<String, Object> model = new HashMap<String,Object>();
        if(null != queryEntity.getId()){
            Product product = this.productService.getById(queryEntity.getId());
            model.put("product", product);
        }
        model.put("categoryList", categoryService.queryAll(new Category()));
        return prepareModelAndView("productMerge", active, model);
    }

    @PostMapping("/product/doMerge")
    @ResponseBody
    public String doMerge(Product entity, String imageBase64, String mainImageBase64){
        if(StringUtils.isBlank(entity.getName())){
            return JsonView.failureJson("名称不能为空");
        }
        if(StringUtils.isNotBlank(imageBase64)){
            AttachmentBean attachment = new AttachmentBean();
            attachment.name = "productImg";
            attachment.data = imageBase64;
            AuthUser authUser = ShiroContext.getSessionUser();
            Long attid = this.attachmentService.createAttachment(authUser, attachment).getId();
            entity.setPicture(attid);
        }

        if(StringUtils.isNotBlank(mainImageBase64)) {//有主图上传
            AttachmentBean attachment = new AttachmentBean();
            attachment.name = "productPicture";
            attachment.data = mainImageBase64;
            AuthUser authUser = ShiroContext.getSessionUser();
            Long attId = this.attachmentService.createAttachment(authUser, attachment).getId();
            entity.setMainPicture(attId);
        }

        Category category = this.categoryService.getById(Long.valueOf(entity.getCategory()));
        if(null != category){
            entity.setCategory(category.getCode());
            entity.setCategoryName(category.getName());
        }
        if(null != entity.getId()){
            this.productService.update(entity);
        }else{
            entity.setPid(IdUtil.nextStringId());
            this.productService.create(entity);
        }
        return JsonView.successJson();
    }

    @PostMapping("/product/doSale")
    @ResponseBody
    public String doSale(Long id) {
        Product product = productService.getById(id);
        if(null != product) {
            if(product.getStatus() == 0) {
                product.setStatus(1);
            }else {
                product.setStatus(0);
            }
        }
        productService.updateStatus(product);
        return JsonView.successJson();
    }

    @PostMapping("/product/delete")
    @ResponseBody
    public String delete(Long id) {
        Product product = productService.getById(id);
        this.attachmentService.deleteAttachment(ShiroContext.getSessionUser(), product.getPicture());
        this.productService.delete(product);
        return JsonView.successJson();
    }

}
