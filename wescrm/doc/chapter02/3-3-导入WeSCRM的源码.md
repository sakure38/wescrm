
## 导入WeSCRM源码

## 导入项目文件
- IDEA导入项目源码
- Maven下载项目依赖的Jar包
- 项目编码配置
- 项目Java Build Path配置JRE

## 数据库配置
- application.yml中的数据库配置: datasource 
- 创建数据库
- 执行ddl.sql和init.sql

## 运行项目
- application.yml中的端口配置：server.port
- 运行Application.java启动项目
- 访问项目 http://localhost:port

## 实现登录
- 输入用户名密码 admin/admin123
- 关于登录的加密方式
- 如何修改登录密码：通过HashUtil修改密码，并保存到auth_user表的password字段

## 项目结构介绍
- 左侧的Package Explorer设置
- src/main/java目录
- src/test/java目录
- src/main/resources目录
- doc目录
- release目录

