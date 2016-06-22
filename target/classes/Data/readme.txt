1、到要部署的Tomcat下的<Host name="localhost"  appBase="webapps" unpackWARs="true" autoDeploy="true"></Host>这个标签下添加如下代码
<Context path="/test" reloadable="true" docBase="D:\test" workDir="D:\test\userImages"/>

2、把项目里面src\main\resources\Data\ovs.sql导入到数据库，数据库名为ovs，修改该目录下的config.properties的
jdbc.username = root
jdbc.password = YES

3、管理员的账号邮箱均为admin,密码是ovs