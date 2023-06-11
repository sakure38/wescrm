
# 本节重点：wescrm部署到公网服务器，通过域名访问
- 服务器上创建数据库
- 修改数据库的连接密码
- 修改tomcat端口号
- nginx配置二级域名：nginx.conf
- 项目通过maven打包，生成 wescrm.war包
    - 去掉登录验证码
    - pom.xml修改，去掉start Application
- 发布到服务器，启动
    - 启动Application.java： nohup java -jar wescrm.jar >wescrm.log 2>&1 &  
    - 查看日志：tail -f ./wescrm.log   
- 访问项目
- 小程序中配置

