## 项目简介

这是校园一卡通网站的后台接口，主要分为五个模块，综合信息管理（一卡通简介、管理条例、使用指南、发布公告）、失卡招领、业务办理、在线咨询、领导信箱等五个模块。

## 项目展示

前端项目链接：https://github.com/shenlujie/campuscard-web

首页：

![](https://github.com/shenlujie/campuscard/blob/master/campuscard-web/src/main/resources/static/cardPic/homepage.png)

## 使用技术：

| 技术            | 简介                       | 应用                                 |
| --------------- | -------------------------- | ------------------------------------ |
| Spring Boot     | 容器、MVC框架              | Spring项目的快速搭建，技术框架的集成 |
| MyBatis         | ORM框架，数据库交互框架    | 使用Mybatis Common Mapper非常方便    |
| Spring Security | 权限管理                   | 控制角色访问的uri                    |
| JWT             | 认证管理                   | HTTP请求使用token实现认证            |
| WebSocket       | 服务器向客户端发送消息协议 | 利用该特性实现在线聊天               |
| PageHelper      | 分页插件                   | 数据库分页功能实现                   |

