# side-boot

部署概要：

1、vue作为前端框架，需要在开发环境中安装nodejs<br>
<br>
2、后台架构采用spring cloud微服务框架，基于eureka为注册中心。本项目中注册中心只配置了两个节点作为集群，因此在开发环境中，需要在本地的host文件增加两项地址配置。如下所示：<br>
127.0.0.1       service-register-server1<br>
127.0.0.1       service-register-server2<br>
注册中心服务地址指向service-register-server1和service-register-server2即可。<br>
<br>
3、数据库中间件使用Hibernate框架，使用Redis作为Hibernaet的二级缓存实现。因此需要在本地开发环境搭建Redis单机模式。<br>
