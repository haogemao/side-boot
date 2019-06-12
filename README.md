# side-boot

部署概要：

1、vue作为前端框架，需要在开发环境中安装nodejs
2、后台架构采用spring cloud微服务框架，基于eureka为注册中心。本项目中注册中心只配置了两个节点作为集群，因此在开发环境中，需要在本地的host文件增加两项地址配置。如下所示：
127.0.0.1       service-register-server1
127.0.0.1       service-register-server2
注册中心服务地址指向service-register-server1和service-register-server2即可。
