package com.we.scrm.common.freemarker;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import freemarker.template.TemplateModelException;

/**
 * @author QiCong
 * @version 2021年4月2日
 */
@Configuration
public class FreemarkerConfiguration implements ApplicationContextAware {
	
	ApplicationContext applicationContext ;
	
	@Autowired
    freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable(){
        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());
        
        /**
         * 根据注解获取FreemarkerTag对象
         */
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(FreemarkerTag.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            try {
				configuration.setSharedVariable(entry.getKey(), entry.getValue());
			} catch (TemplateModelException e) {
				e.printStackTrace();
			}
        }
        
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
}

