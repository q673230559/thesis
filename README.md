# Jeesite+swagger2 接口开发
## 功能说明  
    这本是一个APP项目，现改编成接口开发案例。望大神批评指正。
- web后台，模块忽略
- rest风格数据接口
## 项目结构

```text
Src/main/java
 ├ common	公共模块存放目录
 │ ├ beanvalidator	实体Bean验证相关类
│ ├ log	日志工具相关类
│ ├ mapper	各种Object到Xml、Object到Json的映射转换类
│ ├ persistence	持久层相关类
│ ├ security	安全相关类
│ ├ service	业务层相关类
│ ├ servlet	公共servlet相关类
│ ├ utils	各种操作小工具类
│ └ web	模型控制器层相关类
└ modules	JeeSite内置功能模块存放目录
├ act	Activiti工作流引擎目录
├ cms	内容管理、新闻发布模块目录
├ gen	Web版本代码生成器目录
├ oa	在线办公模块演示用例存放目录
└ sys	系统核心模块存放目录
 ├ dao	数据访问层相关类
 ├ entity	实体相关类
 ├ interceptor	系统模块拦截器相关类
 ├ service	业务处相关类
  ├ web	模型控制器层相关类
  └ utils	系统模块的工具类

```

## 技术选型
- 项目基本框架SpringMVC，Spring，Mybatis
- 数据库mysql
- 快速开发平台jeesite
- 数据库表ER设计ERmaster插件

## 使用说明
### 环境
- jdk7
- tomcat8.5.23
- idea或者eclipse
### 用法

