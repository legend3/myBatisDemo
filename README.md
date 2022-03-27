# M1
mybatis:  
ibatis:apache  
2010 ibatis-> google colde, Mybatis  


MyBatis可以简化JDBC操作，实现数据的持久化 。  
ORM: Object Relational Mapping  
person对象   person表  

>ORM：概念 ，  
    Mybatis是ORM的一个实现/Hibernate   
**orm可以是的开发人员  像操作对象一样 操作数据库表。**  

开发mybatis程序从步骤：  
1.配置mybatis  
conf.xml:配置数据库信息 和 需要加载的映射文件   
表 - 类  
映射文件xxMapper.xml: 增删改查标签<select>  
测试类：  
session.selectOne("需要查询的SQL的namespace.id","SQL的参数值");  

# M2
复习第一个MyBatis程序：  
0.mybatis.jar   ojdbc.jar  
1.conf.xml (数据库配置信息、映射文件)  
2.表-类：映射文件  mapper.xml  
3.测试  


mybatis约定：  
输入参数parameterType 和 输出参数resultType ，在形式上都只能有一个  


如果输入参数 ：是简单类型（8个基本类型+String） 是可以使用任何占位符, #{xxxx}  
如果是对象类型，则必须是对象的属性 #{属性名}  

输出参数：  如果返回值类型是一个 对象（如Student），则无论返回一个、还是多个，  
在resultType都写成org.lanqiao.entity.Student  
即 resultType="org.lanqiao.entity.Student"  

注意事项：  
如果使用的 事务方式为 jdbc, 则需要 手工commit提交，即session.commit();  

# M3
复习第一个MyBatis程序：  
0.mybatis.jar   ojdbc.jar  
1.conf.xml (数据库配置信息、映射文件)  
2.表-类：映射文件  mapper.xml  
3.测试  


一、基础方式的增删改查CRUD:  
mybatis约定：  
输入参数parameterType 和 输出参数resultType ，在形式上都只能有一个  


如果输入参数 ：是简单类型（8个基本类型+String） 是可以使用任何占位符,#{xxxx}  
如果是对象类型，则必须是对象的属性 #{属性名}  

输出参数：  如果返回值类型是一个 对象（如Student），则无论返回一个、还是多个，  
再resultType都写成org.lanqiao.entity.Student  
即 resultType="org.lanqiao.entity.Student"

注意事项：  
a.如果使用的 事务方式为 jdbc,则需要 手工commit提交，即session.commit();  
b.所有的标签 <select> <update>等 ，都必须有sql语句，但是sql参数值可选  
select* from student  where stuno = #{xx}  
sql有参数：session.insert(statement, 参数值 );  

sql没参数：session.insert(statement);  


-- mybatisProject3  
二、mapper动态代理方式的crud （MyBatis接口开发）:  
原则：约定优于配置  

硬编码方式  
abc.java  
Configuration conf = new Configuration();  
con.setName("myProject");  
配置方式：  
abc.xml  
<name>myProject</name>  

约定：默认值就是myProject  


具体实现的步骤：  
1.基础环境：mybatis.jar/ojdbc.jar、conf.xml、mapper.xml  
2.（不同之处）  
约定的目标： 省略掉statement,即根据约定 直接可以定位出SQL语句  

a.接口，接口中的方法必须遵循以下约定：  
*1.方法名和mapper.xml文件中标签的id值相同  
* 2.方法的 输入参数 和mapper.xml文件中标签的 parameterType类型一致 (如果mapper.xml的标签中没有 parameterType，则说明方法没有输入参数)  
* 3.方法的返回值  和mapper.xml文件中标签的 resultType类型一致 （无论查询结果是一个 还是多个（student、List<Student>），在mapper.xml标签中的resultType中只写 一个（Student）；如果没有resultType，则说明方法的返回值为void）  

除了以上约定，要实现 接口中的方法  和  Mapper.xml中SQL标签一一对应，还需要以下1点：  
namespace的值 ，就是接口的全类名（ 接口 - mapper.xml 一一对应映射）  


匹配的过程：（约定的过程）  
1.根据 接口名找到 mapper.xml文件（根据的是namespace=接口全类名）  
2.根据 接口的方法名找到 mapper.xml文件中的SQL标签 （方法名=SQL标签Id值）  

以上2点可以保证： 当我们调用接口中的方法时，  
程序能自动定位到 某一个Mapper.xml文件中的sqL标签  


习惯：SQL映射文件（mapper.xml） 和 接口放在同一个包中 （注意修改conf.xml中加载mapper.xml文件的路径）  
补充：  
SQL映射文件（mapper.xml） 和 接口放在同一个包中，还需要pom.xml中<build>标签添加：(不然找不到映射文件)  
<!--  在pom文件 标明xml的位置  -->  
    <resources>
      <resource>
        <directory>src/main/java</directory>
        <includes>
          <include>**/*.xml</include>
        </includes>
        <filtering>true</filtering>
      </resource>
    </resources>

以上，可以通过接口的方法->SQL语句  
执行：
StudentMapper studentMapper = session.getMapper(StudentMapper.class);  
studentMapper.方法();  
通过session对象获取接口（session.getMapper(接口.class);），再调用该接口中的方法，程序会自动执行该方法对应的SQL。  


网上资料补充：  
mybatis配置文件namespace用法总结  
由于在应用过程中，发现namespace在配置文件中的重要性，以及配置的影响，在网上看了很多博客，发现很多人对namespace存在误解，所以总结一下namespace的用以及个人的理解：  
官网（http://www.mybatis.org/mybatis-3/zh/getting-started.html）的解释如下：  

namespace即空间命名名称，用于区分实现数据持久化的方式。namespace一般绑定对应的文件的全路径，有三种全路径：namespace绑定实体类的全路径，绑定dao接口的全路径，绑定mapper的sql.xml文件。  
第一种：namespace绑定实体类的全路径：  
当namespace绑定的是实体类的全路径时，其实现数据持久化的方式为无代理模式实现数据持久化。需要手动实现dao层的接口。  

    <mapper namespace="com.system.pojo.LiveInfo"></namespace>  
第二种：namespace绑定dao层接口的全路径：  
当namespace绑定的是dao接口的全路径时，其实现数据持久化的方式为有代理模式实现数据持久化。  
即会自动产生代理，自动实现数据的持久化，不需要实现dao层的接口。  
<mapper namespace="com.system.dao.LiveInfoDao"></namespace>  
第三种：namespace绑定的是mapper接口对应的sql.xml文件是，其也是有代理模式自动实现数据持久化。  
但mapper接口对应的sql.xml文件名必须保持一致才能自动实现数据持久化。  
<mapper namespace="com.system.sql.LiveInfoMapper"></namespace>  
总结：本课中都是第三种，绑定mapper的sql.xml文件.

优化  
1.可以将配置信息 单独放入db.properties文件中，然后再动态引入  

    db.properties：
    k=v
    <configuration>
    <properties  resource="db.properties"/>
引入之后，使用${key}  

2.MyBatis全局参数  
在conf.xml中设置  

    <!-- 
    <settings>
            <setting name="cacheEnabled" value="false"  />
            <setting name="lazyLoadingEnabled" value="false"  />
    </settings>
     -->

3.别名 conf.xml  
a.设置单个(类)别名  

b.批量设置(类)别名  

	<typeAliases>
		<!-- 单个别名 （别名 忽略大小写） -->
		<!-- <typeAlias type="org.lanqiao.entity.Student" alias="student"/> -->
		<!--  批量定义别名  （别名 忽略大小写），以下会自动将该包中的所有类 批量定义别名： 别名就是类名（不带包名，忽略大小写）   -->
		<package name="org.lanqiao.entity"/>
	</typeAliases>


除了自定义别名外，MyBatis还内置了一些常见类的别名。  

类型处理器（类型转换器）  
1.MyBatis自带一些常见的类型处理器  
int  - number  

2.自定义MyBatis类型处理器  

	java -数据库(jdbc类型)
示例：  
实体类Student :  boolean   stuSex  	
true:男  
false：女  

表student：	number  stuSex  
1:男  
0：女  
自定义类型转换器（boolean -number）步骤：  
a.创建转换器：需要实现TypeHandler接口  
通过阅读源码发现，此接口有一个实现类 BaseTypeHandler ，因此 要实现转换器有2种选择：  
i.实现接口TypeHandler接口  
ii.继承BaseTypeHandler  
b.配置conf.xml  

需要注意的问题：  INTEGER  

insert into student(stuno,stuname,stuage,graname,stusex)  
values(#{stuNo},#{stuName},#{stuAge},#{graName} ,#{stuSex ,javaType=boolean  ,jdbcType=INTEGER   } )  

注意#{stuNo} 中存放的是 属性值，需要严格区分大小写。  

resultMap可以实现2个功能：  
1.类型转换  
2.属性-字段的映射关系  

    <select id="queryStudentByStuno" 	parameterType="int"  	resultMap="studentMapping" >
            select * from student where stuno = #{stuno}
        </select>
	<resultMap type="student" id="studentMapping">
			<!-- 分为主键id 和非主键 result-->
			<id property="id"  column="stuno"  />
			<result property="stuName"  column="stuname" />
			<result property="stuAge"  column="stuage" />
			<result property="graName"  column="graname" />
			<result property="stuSex"  column="stusex"  javaType="boolean" jdbcType="INTEGER"/>
	</resultMap>  
focusky  

# M4
输入参数：parameterType
1.类型为 简单类型（8个基本类型+String）
#{}、${}的区别：
	a.
	#{任意值}
	${value} ，其中的标识符只能是value

	b.#{}自动给String类型加上''  （自动类型转换）
	  ${} 原样输出，但是适合于 动态排序（动态字段）



select stuno,stuname,stuage  from student where stuname = #{value}

select stuno,stuname,stuage  from student where stuname = '${value}'

动态排序：
select stuno,stuname,stuage  from student  order by ${value} asc


c.#{}可以防止SQL注入
${}不防止



${}、#{}相同之处：
a.都可以 获取对象的值（嵌套类型对象）



i.获取对象值：
模糊查询，方式一：
select stuno,stuname,stuage  from student where stuage= #{stuAge}  or stuname like #{stuName}
Student student = new Student();
student.setStuAge(24);
student.setStuName("%w%");
List<Student> students = studentMapper.queryStudentBystuageOrstuName(student) ;//接口的方法->SQL

模糊查询，方式二：
student.setStuName("w");
select stuno,stuname,stuage  from student where stuage= #{stuAge}  or stuname like '%${stuName}%'

ii.嵌套类型对象

2.对象类型
例如：Map时
#{属性名}
${属性名}


# M5
输入参数：parameterType
1.类型为 简单类型（8个基本类型+String）
#{}、${}的区别
a.
#{任意值}
${value} ，其中的标识符只能是value

b.#{}自动给String类型加上''  （自动类型转换）

${} 原样输出，但是适合于 动态排序（动态字段）


select stuno,stuname,stuage  from student where stuname = #{value}

select stuno,stuname,stuage  from student where stuname = '${value}'

动态排序：
select stuno,stuname,stuage  from student  order by ${value} asc


c.#{}可以防止SQL注入
${}不防止



${}、#{}相同之处：
a.都可以 获取对象的值 （嵌套类型对象）



i.获取对象值：
模糊查询，方式一：
select stuno,stuname,stuage  from student where stuage= #{stuAge}  or stuname like #{stuName}
Student student = new Student();
student.setStuAge(24);
student.setStuName("%w%");
List<Student> students = studentMapper.queryStudentBystuageOrstuName(student) ;//接口的方法->SQL

模糊查询，方式二：
student.setStuName("w");
select stuno,stuname,stuage  from student where stuage= #{stuAge}  or stuname like '%${stuName}%'

ii.嵌套类型对象
parameterType="student"
#{实例类中嵌套类型对象的属性名.嵌套类型的属性名}
${address.schoolAddress}



2.对象类型
#{属性名}
${属性名}




输入对象为HashMap：
where stuage= #{stuAge}

用map中key的值 匹配 占位符#{stuAge}，如果匹配成功 就用map的value替换占位符


mybatis调用存储过程

    <select id="queryCountByGradeWithProcedure" statementType="CALLABLE"  parameterType="HashMap" >
    {
    CALL queryCountByGradeWithProcedure(
    #{gName,jdbcType=VARCHAR,mode=IN},
    #{scount,jdbcType=INTEGER,mode=OUT}
    )
    }
    </select>
其中 通过statementType="CALLABLE"设置SQL的执行方式是存储过程。 存储过程的输入参数gName需要通过HashMap来指定  
在使用时，通过hashmap的put方法传入输入参数的值；通过hashmap的Get方法 获取输出参数的值。  
要注意Jar问题：ojdbc6.jar不能在 调存储过程时  打回车、tab，但是ojdbc7.jar可以。  


如果报错： No enum constant org.apache.ibatis.type.JdbcType.xx，则说明mybatis不支持xx类型，需要查表。 

存储过程 无论输入参数是什么值，语法上都需要 用map来传递该值；  

只要 是  <transactionManager type="JDBC" />，则增删改都需要手工commit;  

mapper.xml->mapper接口->测试方法  

## 存储过程脚本：  
1.查询某个年级的 学生总数
输入：年级
输出：该年级的学生总数  

    -- oracle
    create or replace procedure queryCountByGradeWithProcedure(gName in varchar, scount out number )
    as
    begin
    select count(*) into scount from student where graname = gname ;
    end;
    /
    
    --mysql
    DELIMITER $$
    CREATE
    PROCEDURE `queryCountByGradeWithProcedure`(IN gName VARCHAR(100), OUT scount INT(100))
    BEGIN
    SELECT COUNT(*) INTO scount FROM student WHERE graname = gName;
    END$$
    DELIMITER ;

2.根据学号删除学生

    create or replace procedure deleteStuBynoWithProcedure(sno in number)
    as
    begin
    delete from student where stuno = sno  ;
    end;
    /
# M6
输出参数resultType
1.简单类型（8个基本+String）
2.输出参数为实体对象类型
3.输出参数为实体对象类型的集合 ：虽然输出类型为集合，但是resultType依然写 集合的元素类型（resyltType="Student"）
4.输出参数类型为HashMap
--HashMap本身是一个集合，可以存放多个元素，
但是根据提示发现  返回值为HashMap时  ，查询的结果只能是1个学生（no,name）；
-->结论：一个HashMap 对应一个学生的多个元素（多个属性）  【一个map，一个学生】

二维数组
{
{1,zs,23,xa},    -一个HashMap对象
{2,ls,24,bj},
{3,ww,25,tj}
}


resultType
resultMap:实体类的属性、数据表的字段： 类型、名字不同时（stuno,id）
注意：当属性名 和字段名 不一致时，除了使用resultMap以外，还可以使用resultType+HashMap:

a.resultMap
<resultMap type="student" id="queryStudentByIdMap">
<!-- 指定类中的属性 和 表中的字段 对应关系 -->
    <id property="stuNo"  column="id" />
    <result property="stuName" column="name" />
    </resultMap>

b.resultType+HashMap
select  表的字段名 "类的属性名" from... 来制定字段名 和属性名的对应关系  

    <select id="queryStudentByIdWithHashMap" 	 parameterType="int"	resultType="student" >
    select id "stuNo",name "stuName" from student where id = #{id}
    </select>

注意:  如果如果10个字段，但发现 某一个字段结果始终为默认值（0，0.0，null），则可能是 表的字段  和 类的属性名字写错。

//查询全部

    String statement = "select stuno,stuname from student";

//根据年龄查询学生

    String statement = "select stuno,stuname from student where stuage = #{stuage}";


//根据姓名和年龄查询学生

    String statement = "select stuno,stuname from student where stuage = #{stuage} and stuage = #{stuage} ";
    select stuno,stuname from student where  stuname = #{stuName}and  stuage = #{stuAge}
    select stuno,stuname,stuage from student <where> and stuname = #{stuName}  and  stuage = #{stuAge}

<where>会自动处理第一个<if>标签中的 and，但不会处理之后<if>中的and

`<foreach>`  
查询学号为1、2、53的学生信息  

    ids = {1,2,53}; 
    select stuno,stuname from student  where stuno in(1,2,53)
<foreach>迭代的类型：数组、对象数组、集合、属性(Grade类： List<Integer> ids)

属性(Grade类： List<Integer> ids)  

    select * from student
    open:
    select * from student and  stuno in (
    item:
    select * from student and  stuno in (1253
    close:
    select * from student and  stuno in (1,2,53)

简单类型的数组:
无论编写代码时，传递的是什么参数名(stuNos)，在mapper.xml中 必须用array代替该数组

集合：
无论编写代码时，传递的是什么参数名(stuNos)，在mapper.xml中 必须用list代替该数组

对象数组：  

    Student[] students = {student0,student1,student2}  每个studentx包含一个学号属性
注意的几点：

    parameterType="Object[]"
    <foreach collection="array" open=" and  stuno in (" close=")"
    item="student" separator=",">   
    #{student.stuNo}
    </foreach>

SQL片段：  
java：方法  
数据库：存储过程、存储函数  
Mybatis :SQL片段  

a.提取相似代码  
b.引用  

关联查询：  
一对一:  
a.业务扩展类  
核心：用resultType指定类的属性 包含 多表查询的所有字段  

b.resultMap  

一对多  

（MyBatis:多对一，多对多的本质就是  一对多的变化）  
# M7
输出参数resultType
1.简单类型（8个基本+String）
2.输出参数为实体对象类型
3.输出参数为实体对象类型的集合 ：虽然输出类型为集合，但是resultType依然写 集合的元素类型（resyltType="Student"）
4.输出参数类型为HashMap
--HashMap本身是一个集合，可以存放多个元素，
但是根据提示发现  返回值为HashMap时  ，查询的结果只能是1个学生（no,name）；
-->结论：一个HashMap 对应一个学生的多个元素（多个属性）  【一个map，一个学生】

二维数组
{
{1,zs,23,xa},    -一个HashMap对象
{2,ls,24,bj},
{3,ww,25,tj}
}


resultType
resultMap:实体类的属性、数据表的字段： 类型、名字不同时（stuno,id）
注意：当属性名 和字段名 不一致时，除了使用resultMap以外，还可以使用resultType+HashMap:

a.resultMap

    <resultMap type="student" id="queryStudentByIdMap">
    <!-- 指定类中的属性 和 表中的字段 对应关系 -->
    <id property="stuNo"  column="id" />
    <result property="stuName" column="name" />
    </resultMap>

b.resultType+HashMap
select  表的字段名 "类的属性名" from... 来制定字段名 和属性名的对应关系
<select id="queryStudentByIdWithHashMap" 	 parameterType="int"	resultType="student" >
select id "stuNo",name "stuName" from student where id = #{id}
</select>

注意:  如果如果10个字段，但发现 某一个字段结果始终为默认值（0，0.0，null），则可能是 表的字段  和 类的属性名字写错。

//查询全部
String statement = "select stuno,stuname from student";

//根据年龄查询学生

String statement = "select stuno,stuname from student where stuage = #{stuage}";


//根据姓名和年龄查询学生


String statement = "select stuno,stuname from student where stuage = #{stuage} and stuage = #{stuage} ";

select stuno,stuname from student where  stuname = #{stuName}and  stuage = #{stuAge}





select stuno,stuname,stuage from student <where> and stuname = #{stuName}  and  stuage = #{stuAge}

<where>会自动处理第一个<if>标签中的 and，但不会处理之后<if>中的and



<foreach>
查询学号为1、2、53的学生信息


ids = {1,2,53};

select stuno,stuname from student  where stuno in(1,2,53)


<foreach>迭代的类型：数组、对象数组、集合、属性(Grade类： List<Integer> ids)

属性(Grade类： List<Integer> ids)

select * from student
open:
select * from student and  stuno in (
item:
select * from student and  stuno in (1253
close:
select * from student and  stuno in (1,2,53)



简单类型的数组:
无论编写代码时，传递的是什么参数名(stuNos)，在mapper.xml中 必须用array代替该数组


集合：
无论编写代码时，传递的是什么参数名(stuNos)，在mapper.xml中 必须用list代替该数组


对象数组：
Student[] students = {student0,student1,student2}  每个studentx包含一个学号属性
注意的几点：
parameterType="Object[]"
<foreach collection="array" open=" and  stuno in (" close=")"
item="student" separator=",">   
#{student.stuNo}
</foreach>



SQL片段：
java：方法
数据库：存储过程、存储函数
Mybatis :SQL片段

a.提取相似代码
b.引用


关联查询：
一对一:
a.业务扩展类
核心：用resultType指定类的属性 包含 多表查询的所有字段

b.resultMap
i.通过 属性成员 将2个类建立起联系
2. ...速度法

    <resultMap type="student" id="student_card_map">
    <!-- 学生的信息 -->
        <id  property="stuNo" column="stuNo"/>
            <result property="stuName" column="stuName" />
            <result property="stuAge" column="stuAge" />
            <!-- 一对一时，对象成员使用 association映射;javaType指定该属性的类型-->
            <association property="card" javaType="StudentCard" >
        <id property="cardId" column="cardId"/>
   <result property="cardInfo" column="cardInfo"/>
    </association>
    </resultMap>

一对一：association
一对多：collection

一对多：
表：student studentclass  (关联：classid)
类：student studentClass  (关联：List<Student> students )



select  c.*,s.* from student s
inner join studentclass c
on c.classid = s.classid
where c.classid = 1;

一对多

（MyBatis:多对一，多对多的本质就是  一对多的变化）  

日志：Log4j  



a.Log4j:	log4j.jar (mybatis.zip中lib中包含此jar)  
b.开启日志，conf.xml  

    <settings>
    <!-- 开启日志，并指定使用的具体日志 -->
    <setting name="logImpl" value="LOG4J"/>
	</settings>
如果不指定，Mybatis就会根据以下顺序 寻找日志
SLF4J →Apache Commons Logging →Log4j 2 → Log4j →JDK logging

c.编写配置日志输出文件
log4j.properties，内容
log4j.rootLogger=DEBUG, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n

日志级别：
DEBUG<INFO<WARN<ERROR
如果设置为info，则只显示 info及以上级别的信息；
建议：在开发时设置debug，在运行时设置为info或以上。




可以通过日志信息，相信的阅读mybatis执行情况（ 观察mybatis实际执行sql语句 以及SQL中的参数 和返回结果）




延迟加载（懒加载）：
一对一、一对多、多对一、多对多
一对多：班级-学生 ，
如果不采用延迟加载  （立即加载），查询时会将 一 和多 都查询，班级、班级中的所有学生。
如果想要  暂时只查询1的一方，  而多的一方 先不查询 而是在需要的时候再去查询 -->延迟加载


一对一：学生、学生证


mybatis中使用延迟加载，需要先配置：

    <settings>
    <!-- 开启延迟加载 -->
    <setting name="lazyLoadingEnabled" value="true"/>
		<!-- 关闭立即加载 -->
		<setting name="aggressiveLazyLoading" value="false"/>
	</settings>

如果增加了mapper.xml ,要修改conf.xml配置文件（将新增的mapper.xml加载进去）

通过debug可以发现， 如果程序只需要学生，则只向数据库发送了查询学生的SQL；
当我们后续 需要用到学生证的时候，再第二次发送 查询学生证的SQL。


一对多：和一对一的延迟加载配置方法相同



延迟加载的步骤：先查班级，按需查询学生
1.开启延迟加载conf.xml配置settings
2.配置mapper.xml
写2个Mapper:
班级mapper.xml

    <select id="queryClassAndStudents"   resultMap="class_student_lazyLoad_map">
			select  c.* from studentclass c
		</select>
	<resultMap type="studentClass" id="class_student_lazyLoad_map">
			<!-- 因为 type的主类是班级，因此先配置班级的信息-->
			<id  property="classId" column="classId"/>
			<result  property="className" column="className"/>
			<!-- 配置成员属性学生，一对多;属性类型：javaType，属性的元素类型ofType -->
			<!-- 2222222再查班级对应的学生 -->
			<collection property="students" ofType="student" select="org.lanqiao.mapper.StudentMapper.queryStudentsByClassId" column="classid">

			</collection>
	</resultMap>

		即查询 学生的sql是通过 select属性指定，并且通过column指定外键
	学生mapper.xml
	<!-- 一对多,延迟加载需要的： 查询班级中的所有学生 -->
	<select id="queryStudentsByClassId" parameterType="int" resultType="student">
		select * from student where classId = #{classId}
	</select>
# M8

# M9










