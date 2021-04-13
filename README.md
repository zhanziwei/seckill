## 高并发秒杀项目

#### 项目的业务流程

![抢购流程](https://img-blog.csdnimg.cn/20200325111907951.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMxNzA5MjQ5,size_16,color_FFFFFF,t_70)

#### 用户表

![user_table](C:\Users\17150\IdeaProjects\seckill\user_table.png)

#### 商品表

![seckill_table](C:\Users\17150\IdeaProjects\seckill\seckill_table.png)

#### 秒杀成功记录表

![success_seckill_table](C:\Users\17150\IdeaProjects\seckill\success_seckill_table.png)

#### DAO层编写
1. maven创建项目
  打开WEB-INF下的web.xml，它默认为我们创建servlet版本为2.3，需要修改它的根标签为3.0

2. pom添加第三方jar包

  ```xml
  <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.12</version>
      </dependency>
      <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
        <version>1.1.1</version>
      </dependency>
      <!--实现slf4j接口并整合-->
      <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.1.1</version>
      </dependency>
  
  
      <!--1.数据库相关依赖-->
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.35</version>
        <scope>runtime</scope>
      </dependency>
      <dependency>
        <groupId>c3p0</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.1.1</version>
      </dependency>
  
      <!--2.dao框架:MyBatis依赖-->
      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.3.0</version>
      </dependency>
      <!--mybatis自身实现的spring整合依赖-->
      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.2.3</version>
      </dependency>
  
      <!--3.Servlet web相关依赖-->
      <dependency>
        <groupId>taglibs</groupId>
        <artifactId>standard</artifactId>
        <version>1.1.2</version>
      </dependency>
      <dependency>
        <groupId>jstl</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
      </dependency>
      <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.5.4</version>
      </dependency>
      <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
      </dependency>
  
      <!--4:spring依赖-->
      <!--1)spring核心依赖-->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>4.1.7.RELEASE</version>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>4.1.7.RELEASE</version>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>4.1.7.RELEASE</version>
      </dependency>
      <!--2)spring dao层依赖-->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>4.1.7.RELEASE</version>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-tx</artifactId>
        <version>4.1.7.RELEASE</version>
      </dependency>
      <!--3)springweb相关依赖-->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>4.1.7.RELEASE</version>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>4.1.7.RELEASE</version>
      </dependency>
      <!--4)spring test相关依赖-->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>4.1.7.RELEASE</version>
      </dependency>
  ```

3. mybatis配置全局文件

   mybatis为我们提供了mapper动态代理开发的方式为我们自动实现Dao的接口。

4. 在mapper包下创建对应Dao接口的xml映射文件

5. 在resources包下创建一个spring包，放置spring对Dao、Service、transaction的配置文件

6. 在spring包下创建一个spring配置dao层对象的配置文件spring-dao.xml

   ```xml
   <!--配置整合mybatis过程
       1.配置数据库相关参数-->
       <context:property-placeholder location="classpath:jdbc.properties"/>
   
       <!--2.数据库连接池-->
       <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
           <!--配置连接池属性-->
           <property name="driverClass" value="${driver}" />
   
           <!-- 基本属性 url、user、password -->
           <property name="jdbcUrl" value="${url}" />
           <property name="user" value="${username}" />
           <property name="password" value="${password}" />
   
           <!--c3p0私有属性-->
           <property name="maxPoolSize" value="30"/>
           <property name="minPoolSize" value="10"/>
           <!--关闭连接后不自动commit-->
           <property name="autoCommitOnClose" value="false"/>
   
           <!--获取连接超时时间-->
           <property name="checkoutTimeout" value="1000"/>
           <!--当获取连接失败重试次数-->
           <property name="acquireRetryAttempts" value="2"/>
       </bean>
   
       <!--约定大于配置-->
       <!--３.配置SqlSessionFactory对象-->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <!--往下才是mybatis和spring真正整合的配置-->
           <!--注入数据库连接池-->
           <property name="dataSource" ref="dataSource"/>
           <!--配置mybatis全局配置文件:mybatis-config.xml-->
           <property name="configLocation" value="classpath:mybatis-config.xml"/>
           <!--扫描entity包,使用别名,多个用;隔开-->
           <property name="typeAliasesPackage" value="cn.codingxiaxw.entity"/>
           <!--扫描sql配置文件:mapper需要的xml文件-->
           <property name="mapperLocations" value="classpath:mapper/*.xml"/>
       </bean>
   
       <!--４:配置扫描Dao接口包,动态实现DAO接口,注入到spring容器-->
       <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
           <!--注入SqlSessionFactory-->
           <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
           <!-- 给出需要扫描的Dao接口-->
           <property name="basePackage" value="cn.codingxiaxw.dao"/>
       </bean>
   </beans>
   ```

   

