
### 参考

[maven多module项目结构]

### mobile-server项目结构说明

```
mobile-server
	----root-feature
	----base-feature
	----webapp-feature
		----api-feature
		----another-web-feature
		
mobile-server: 包含多个module的项目名称（a simple maven project，no archetype）
root-feature : 所有通用基础库（maven-archetype-quickstart）
base-feature : 二次封装的基础类库（maven-archetype-quickstart）
webapp-feature : pom类型module，只是一个包含多个module的容器（a simple maven project， no archetype）
api-feature ： 通过struts实现，并提供给外部调用者api支持的web服务器（maven-archetype-webapp）
another-web-feature ： 另一个web服务器，这里只是作为示例，并没有实现什么功能。（maven-archetype-webapp）
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

