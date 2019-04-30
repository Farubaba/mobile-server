
### 参考

[maven多module项目结构]

### mobile-server 多 module 项目结构说明

项目创建过程中使用到两个maven archetype:

1) maven-archetype-quickstart，创建root、base、okhttp module 时使用。

2) maven-archetype-webapp，创建api、another-web、test module 时使用。

```
【project】mobile-server
	----【module】root-feature	
	----【module】base-feature
	----【module】okhttp-feature
	----【module】webapp-feature
		----【module】api-feature
		----【module】another-web-feature
		----【module】knowledge-base
		----【module】test-feature
		
mobile-server: 包含多个module的项目名称（a simple maven project，no archetype）
root-feature : 所有通用基础库（maven-archetype-quickstart）
base-feature : 二次封装的基础类库（maven-archetype-quickstart）
okhttp-feature : 依赖okhttp，对okhttp二次封装，http相关抽象都被集成在了base-feature中，该module是基于base-feature来开发，base-feature并不依赖于okhttp

webapp-feature : pom类型module，只是一个包含多个module的容器（a simple maven project， no archetype）
api-feature : 通过struts实现，并提供给外部调用者api支持的web服务器（maven-archetype-webapp）
another-web-feature: 另一个web服务器，这里只是作为示例，并没有实现什么功能。（maven-archetype-webapp）
knowledge-base : 另一个web服务器，这将用来管理知识库文档。（maven-archetype-webapp）
test-feature : 可以独立运行的server，早期完整版单独module的mobile-server项目

```
根据maven项目结构，创建必要的目录和源码目录，包等。

```
src/test/java
src/test/resource
src/main/webapp/resources
```


### Maven相关概念

|名称|描述|示例|
|----|----|----|
|聚合(modules标签)|1.聚合主要是为了方便快速构建项目<br> 2. 对于聚合模块而言，它知道有哪些被聚合的模块，但那些被聚合的模块不知道这个聚合模块的存在<br> 3. 聚合POM与继承中的父POM的packaging都必须是pom；同时，聚合模块与继承中的父模块除了POM外，都没有实际的内容||
|继承(parent标签)|1.继承主要是为了消除重复配置<br> 2.对于继承的父pom而言，它不知道有哪些子模块继承它，但那些子模块都必须知道自己的父POM是什么；<br>3. 聚合POM与继承中的父POM的packaging都必须是pom；同时，聚合模块与继承中的父模块除了POM外，都没有实际的内容 ||
|dependencyManagement|用于统一管理所引入的依赖(dependency)的版本version,避免多个module中引入同时某个库时，版本冲突||
| dependency |真正用于引入依赖||
|project-->maven-->update project|||
|mvn install|打包运行||
|mvn build|打包web子工程，配置目标clean package||
|mvn jetty:run|||
|部署到tomcat|将war包，放到tomcat的webapps中，启动tomcat，即可访问||


[maven多module项目结构]:http://www.cnblogs.com/h--d/p/6001366.html

