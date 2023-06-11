package com.test.we.scrm;

import com.we.scrm.Application;
import com.we.scrm.common.t2b.Table2BeanHelper;
import com.we.scrm.common.t2b.TableBean;
import com.we.scrm.dao.TableBeanDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class TableBeanDaoTest {

    @Autowired
    private TableBeanDao tableBeanDao;

    @Test
    public void createTableBeans(){

        //换成开发者的目录
        String mainDir = "D:\\workspaceVideo\\wescrm\\src\\main";
        String packageName = "com.we.scrm";
        String database = "wescrm";//换成开发者的数据库名称

        TableBean vo = new TableBean();
        vo.setTableSchema(database);

        List<String> tables = tableBeanDao.listTables(vo);
        for (String table : tables) {
            System.out.println("table = " + table);
            vo.setTableName(table);
            List<TableBean> cols = tableBeanDao.listTableCols(vo);
            Table2BeanHelper.convert(mainDir, packageName , table, cols);
        }
    }
}
