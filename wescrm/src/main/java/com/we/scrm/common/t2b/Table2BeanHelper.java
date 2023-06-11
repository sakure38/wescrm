package com.we.scrm.common.t2b;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * for SpringMVC、MyBatis
 */
public class Table2BeanHelper {
	
	/**
	 * @param mainDir  源文件绝对路径 如 : D:/workspace/amp/amp2/amp.web
	 * @param packageName 项目的package 名称 如： com.yum.amp2.web
	 * @param tableName   表名
	 * @param cols        表对应的列
	 */
	public static void convert(String mainDir, String packageName, String tableName, List<TableBean> cols) {
		String javaSrcDir = mainDir + "/java";
		String resourceDir = mainDir + "/resources";
		
		String className = underscoreToCamelCase(tableName,true);
		String packagePath = packageName.replace(".", "/");
		String packageFilePath = javaSrcDir + "/" + packagePath;

		try {
			String serviceName = className + "Service.java";
			File serviceFile = new File(packageFilePath + "/service/" + serviceName);
			if (!serviceFile.exists()) {
				createServiceClass(serviceFile, packageName, className);
			}
			
			String daoName = className + "Dao.java";
			File daoFile = new File(packageFilePath + "/dao/" + daoName);
			if (!daoFile.exists()) {
				createDaoClass(daoFile, packageName, className);
			}
			
			String domainName = className  + ".java";
			File domainFile = new File(packageFilePath + "/domain/" + domainName);
			if (!domainFile.exists()) {
				createDomainClass(domainFile, packageName, className, tableName, cols);
			}
			
			String mapperName = className + "Mapper.xml";
			File mapperFile = new File(resourceDir + "/mybatis/mapper/" + mapperName);
	        if (!mapperFile.exists()) {
	            createMapperXml(mapperFile, packageName, tableName, cols);
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Map<String, String> cols2Mapper(List<TableBean> cols) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(TableBean bean : cols) {
			String column = bean.getColName();
			String field = underscoreToCamelCase(column);
			map.put(field, column);
		}
		return map;
	}
	
	public static String jdbcType(String colType) {
		if(colType.indexOf("(") > -1) {
			colType = colType.substring(0, colType.indexOf("("));
		}
		if(colType.equalsIgnoreCase("int")) {
			colType = "INTEGER";
		}else if(colType.equalsIgnoreCase("datetime")) {
			colType = "TIMESTAMP";
		}else if(colType.equalsIgnoreCase("text")) {
			colType = "VARCHAR";
		}else if(colType.equalsIgnoreCase("binary")) {
			colType = "VARCHAR";
		}
		return colType;
	}
	
	public static String insertSql(String tableName, List<TableBean> cols) {
        Map<String, String> map = cols2Mapper(cols);
        StringBuilder sb = new StringBuilder("INSERT INTO " + tableName + "\n");
        Object[] filedNames = map.keySet().toArray();
        Object[] colNames = map.values().toArray();
        sb.append("		( " + join(colNames) + " ) \n");
        sb.append("		VALUES \n");
        sb.append("		( " + join2(filedNames) + " ) ");
        return sb.toString();
    }

    public static String selectSql(String tableName, List<TableBean> cols) {
        Map<String, String> map = cols2Mapper(cols);
        StringBuilder sb = new StringBuilder("SELECT ");
        Object[] colNames = map.values().toArray();
        sb.append(join(colNames) + "\n		FROM " + tableName);
        return sb.toString();
    }
    
    public static String updateSql(String tableName, List<TableBean> cols) {
        StringBuilder sb = new StringBuilder("UPDATE " + tableName);
        sb.append("\n		<trim prefix=\"SET\" suffixOverrides=\",\" > ");
        
        for(TableBean bean : cols) {
    		String jdbcType = jdbcType(bean.getColType().toUpperCase());
    		String field = underscoreToCamelCase(bean.getColName());
    		if("id".equalsIgnoreCase(field) 
    				|| "createAt".equalsIgnoreCase(field)) {
    			//pass
    		}else {
    			sb.append("\n			<if test=\"" + field + " != null \">");
        		sb.append("\n			" + bean.getColName() + " = #{" + field + ", jdbcType=" + jdbcType + "},");
    			sb.append("\n			</if>");
    		}
    	}
        
        sb.append("\n		</trim>");
        sb.append("\n		WHERE id = #{id} ");
        return sb.toString();
        
    }
    
    public static String beanMapSql(String packageClass, List<TableBean> cols) {
    	StringBuilder sb = new StringBuilder("<resultMap id=\"bean_map\" type=\"" + packageClass + "\" >");
    	for(TableBean bean : cols) {
    		String jdbcType = jdbcType(bean.getColType().toUpperCase());
    		String field = underscoreToCamelCase(bean.getColName());
    		sb.append("\n		<result column=\""+ bean.getColName() +"\" property=\"" + field + "\"  jdbcType=\"" + jdbcType + "\" />");
    	}
    	sb.append("\n	</resultMap>\n");
    	return sb.toString();
    }
    
    public static String sqlColumns(String packageClass, List<TableBean> cols) {
    	StringBuilder sb = new StringBuilder("<sql id=\"all_columns\">\n		");
    	for(int i = 0; i< cols.size(); i++) {
    		sb.append(cols.get(i).getColName());
    		if(i != cols.size() - 1) {
    			sb.append(", ");
    		}
    	}
    	sb.append("\n	</sql>\n");
    	return sb.toString();
    }

	public static String join(Object[] arr) {
		StringBuilder sb = new StringBuilder();
		for (Object s : arr) {
			sb.append(s + ",");
		}
		String str = sb.toString();
		return str.substring(0, str.length() - 1);
	}

	public static String join2(Object[] arr) {
		StringBuilder sb = new StringBuilder();
		for (Object s : arr) {
			sb.append("#{" + s + "},");
		}
		String str = sb.toString();
		return str.substring(0, str.length() - 1);
	}

	public static void createDaoClass(File classFile, String packageName, String className) {
		try {
			String tmpPackageName = packageName  + ".dao;";
			String tmpClassName = className + "Dao";
			classFile.createNewFile();

			StringBuilder sb = new StringBuilder();
			sb.append("package " + tmpPackageName + "\n\n");

			sb.append("import java.util.List;\n");
			sb.append("import " + packageName + ".common.page.Pagination;\n");
			sb.append("import " + packageName  + ".domain." + className + ";\n");

			sb.append("\n\n");
			sb.append("public interface " + tmpClassName + " {\n\n");

			sb.append("	public " + className + " getById(Long id);\n\n");

			sb.append("	public List<" + className + "> queryAll(" + className + " queryEntity);\n\n");

			sb.append("	public Integer getTotalItemsCount(" + className + " queryEntity);\n\n");

			sb.append("	public List<" + className + "> queryPage(" + className + " queryEntity , Pagination<"
					+ className + "> page);\n\n");

			sb.append("	public void create(" + className + " entity);\n\n");

			sb.append("	public void update(" + className + " entity);\n\n");

			sb.append("	public void delete(" + className + " entity);\n\n");

			sb.append("\n\n}\n\n");
			OutputStream os = new FileOutputStream(classFile);
			PrintStream ps = new PrintStream(os);
			ps.print(sb);

			os.flush();
			os.close();
			ps.flush();
			ps.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createDomainClass(File classFile, String packageName, String className, String tableName, List<TableBean> cols) {
		try {
			classFile.createNewFile();
			StringBuilder sb = new StringBuilder();
			sb.append("package " + packageName + ".domain;\n\n");
			sb.append("import java.util.Date;\n");

			sb.append("\n\n");
			sb.append("public class " + className + " extends AbstractEntity{\n\n");

			for (TableBean vo : cols) {
				String col = vo.getColName();
				if(!isPassColumn(col)){
					String type = vo.getColType();
					String javaType = " String ";
					if (type.toLowerCase().contains("smallint") || type.toLowerCase().contains("tinyint")) {
						javaType = " Integer ";
					} else if (type.toLowerCase().contains("date")) {
						javaType = " Date ";
					} else if (type.toLowerCase().contains("int")) {
						javaType = " Long ";
					} else if (type.toLowerCase().contains("decimal")) {
						javaType = " Double ";
					}
					sb.append("	private" + javaType + underscoreToCamelCase(col.toLowerCase()) + ";");
					sb.append("//" + new String(vo.getColComment()) + "\n");
				}
			}

			sb.append("\n");

			// setter & getter
			for (TableBean vo : cols) {
				String col = vo.getColName();
				if(!isPassColumn(col)){
					String type = vo.getColType();
					String javaType = " String ";
					if (type.toLowerCase().contains("smallint") || type.toLowerCase().contains("tinyint")) {
						javaType = " Integer ";
					} else if (type.toLowerCase().contains("date")) {
						javaType = " Date ";
					} else if (type.toLowerCase().contains("int")) {
						javaType = " Long ";
					} else if (type.toLowerCase().contains("decimal")) {
						javaType = " Double ";
					}
					String property = underscoreToCamelCase(col.toLowerCase());
					sb.append("	public" + javaType + "get" + upperCaseFirst(property) + "(){\n");
					sb.append("		return " + property + ";\n");
					sb.append("	}\n");

					sb.append("	public void set" + upperCaseFirst(property) + "(" + javaType.trim() + " " + property
							+ "){\n");
					sb.append("		this." + property + " = " + property + ";\n");
					sb.append("	}\n\n");
				}
			}

			sb.append("\n}\n");
			OutputStream os = new FileOutputStream(classFile);
			PrintStream ps = new PrintStream(os);
			ps.print(sb);

			os.flush();
			os.close();
			ps.flush();
			ps.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void createServiceClass(File classFile, String packageName, String className) {
		try {
			String tmpPackageName = packageName  + ".service;";
			String tmpClassName = className + "Service";

			classFile.createNewFile();

			StringBuilder sb = new StringBuilder();
			sb.append("package " + tmpPackageName + "\n\n");

			sb.append("import java.util.Date;\n");
			sb.append("import java.util.List;\n");
			sb.append("import " + packageName + ".common.page.Pagination;\n");
			sb.append("import org.springframework.stereotype.Service;\n");
			sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");

			sb.append("import " + packageName  + ".domain." + className + ";\n");
			sb.append("import " + packageName  + ".service." + className + "Service;\n");
			sb.append("import " + packageName  + ".dao." + className + "Dao;\n");

			sb.append("\n\n");

			sb.append("@Service\npublic class " + tmpClassName + " extends AbstractService<" + className + ">{\n\n");

			sb.append("	@Autowired\n	private " + className + "Dao entityDao;\n\n");

			sb.append("	public " + className + " getById(Long id){" + "\n		return entityDao.getById(id);\n"
					+ "	}\n\n");

			sb.append("	public List<" + className + "> queryAll(" + className + " queryEntity){"
					+ "\n		return entityDao.queryAll(queryEntity);\n" + "	}\n\n");

			sb.append("	public Pagination<" + className + "> queryPage(" + className + " queryEntity ,Pagination<"
					+ className + "> page){"
					+ "\n		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);" + "\n		List<"
					+ className + "> items = entityDao.queryPage(queryEntity,page);"
					+ "\n		page.setItemsTotalCount(itemsTotalCount);" + "\n		page.setItems(items);"
					+ "\n		return page;" + "\n	}\n\n");

			sb.append("	public void create(" + className + " entity){" + "\n		entity.setCreateAt(new Date());\n		entityDao.create(entity);\n"
					+ "	}\n\n");

			sb.append("	public void update(" + className + " entity){" + "\n		entityDao.update(entity);\n"
					+ "	}\n\n");

			sb.append("	public void delete(" + className + " entity){" + "\n		entityDao.delete(entity);\n"
					+ "	}\n\n");

			sb.append("\n\n}\n\n");
			
			OutputStream os = new FileOutputStream(classFile);
			PrintStream ps = new PrintStream(os);
			ps.print(sb);
			
			os.flush();
			os.close();
			ps.flush();
			ps.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createMapperXml(File daoXmlFile, String packageName, String tableName, List<TableBean> cols) {
		try {
			String beanName = underscoreToCamelCase(tableName, true);
			String packageAndDaoClass = packageName + ".dao." + beanName + "Dao";
			String packageAndClass = packageName + ".domain." + beanName;

			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			sb.append(
					"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://10.20.91.130/dtd/mybatis-3-mapper.dtd\" >");

			sb.append("\n\n<mapper namespace=\"" + packageAndDaoClass + "\">\n");

			sb.append("\n	" + beanMapSql(packageAndClass, cols));
			sb.append("\n	" + sqlColumns(packageAndClass, cols));
			
			sb.append("\n	<select id=\"queryAll\" parameterType=\"" + packageAndClass + "\" resultMap=\"bean_map\">");
			sb.append("\n		SELECT ");
			sb.append("\n		<include refid=\"all_columns\"  />");
			sb.append("\n		FROM " + tableName );
			sb.append("\n		ORDER BY id DESC");
			sb.append("\n	</select>");

			sb.append("\n\n	<select id=\"getTotalItemsCount\" parameterType=\"" + packageAndClass + "\" resultType=\"java.lang.Integer\">");
			sb.append("\n		SELECT COUNT(id) FROM " + tableName);
			sb.append("\n	</select>");

			sb.append("\n\n	<select id=\"queryPage\"" + " resultMap=\"bean_map\">");
			sb.append("\n		SELECT");
			sb.append("\n		<include refid=\"all_columns\"  />");
			sb.append("\n		FROM " + tableName);
			sb.append("\n		ORDER BY id DESC");
			sb.append("\n		LIMIT #{param2.startIndex} , #{param2.pageSize} ");
			sb.append("\n	</select>");

			sb.append("\n\n	<select id=\"getById\" parameterType=\"java.lang.Long\" resultMap=\"bean_map\">" );
			sb.append("\n		SELECT");
			sb.append("\n		<include refid=\"all_columns\"  />");
			sb.append("\n		FROM " + tableName);
			sb.append("\n		WHERE id = #{id}");
			sb.append("\n	</select>");

			sb.append("\n\n	<insert id=\"create\" parameterType=\"" + packageAndClass
					+ "\" flushCache=\"true\"  useGeneratedKeys=\"true\" keyProperty=\"id\"  >");
			sb.append("\n		" + insertSql(tableName, cols));
			sb.append("\n	</insert>");

			sb.append("\n\n	<update id=\"update\" parameterType=\"" + packageAndClass + "\" flushCache=\"true\">");
			sb.append("\n		" + updateSql(tableName, cols));
			sb.append("\n	</update>");

			sb.append("\n\n	<delete id=\"delete\" parameterType=\"" + packageAndClass + "\" >");
			sb.append("\n		DELETE FROM " + tableName + "\n		WHERE id = #{id}");
			sb.append("\n	</delete>");

			sb.append("\n\n</mapper>\n");

			OutputStream os = new FileOutputStream(daoXmlFile);
			PrintStream ps = new PrintStream(os);
			ps.print(sb);

			os.flush();
			os.close();
			ps.flush();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static boolean isPassColumn(String col) {
		if ("CREATETIME".equalsIgnoreCase(col) || "CREATE_USER".equalsIgnoreCase(col)
				|| "CREATE_TIME".equalsIgnoreCase(col) || "UPDATE_USER".equalsIgnoreCase(col)
				|| "UPDATE_TIME".equalsIgnoreCase(col) || "DEL".equalsIgnoreCase(col)
				|| "ID".equalsIgnoreCase(col) 
				|| "CREATEAT".equalsIgnoreCase(col) || "UPDATEAT".equalsIgnoreCase(col) ) {
			return true;
		}
		return false;
	}
	
	public static Map<String, String> getAllFieldColumns(Class<?> clazz) {
		try {
			Map<String, String> map = new LinkedHashMap<String, String>();
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null) {
				for (Field field : fields) {
					if (!"serialVersionUID".equals(field.getName())) {// 序列化ID不需要
						String fieldName = field.getName();
						String colName = camelCase2Underscore(fieldName);
						map.put(fieldName, colName);
					}
				}
			}
			Class<?> superClass = clazz.getSuperclass();// 递归获取父类的Field
			Map<String, String> superMap = getAllFieldColumns(superClass);
			if (superMap != null) {
				map.putAll(superMap);
			}
			if (map.size() == 0) {
				return null;
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 转换将第一个字母大写变成小写，并在前面加下划线
	 * @param args
	 */
	public static String camelCase2Underscore(String str) {
		char[] chars = str.toCharArray();
		String rstStr = "";
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] > 64 && chars[i] < 94) {
				rstStr += ("_" + chars[i]).toLowerCase();
			} else {
				rstStr += chars[i];
			}
		}
		return rstStr;
	}
	
	/**
	 * 下划线转骆驼命名法，首字母大写
	 */
	public static String underscoreToCamelCase(String str, boolean upperFirst) {
		if(upperFirst) {//是否首字母大写
			str = underscoreToCamelCase(str);
			return upperCaseFirst(str);
		}else {
			return underscoreToCamelCase(str);
		}
	}
	
	public static String underscoreToCamelCase(String str) {
		String[] arr = str.split("_");
		String rstStr = "";
		if (arr.length > 1) {
			rstStr = arr[0];
			for (int i = 1; i < arr.length; i++) {
				rstStr += upperCaseFirst(arr[i]);
			}
		} else {
			rstStr = str;
		}
		return lowerCaseFirst(rstStr);
	}
	
	/**
	 * 首字母变大写
	 * @param args
	 */
	public static String upperCaseFirst(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length());
	}

	/**
	 * 首字母变小写
	 * @param args
	 */
	public static String lowerCaseFirst(String str) {
		return Character.toLowerCase(str.charAt(0)) + str.substring(1, str.length());
	}

	public static String stringCap(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

}
