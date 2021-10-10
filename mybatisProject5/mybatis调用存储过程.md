## mybatis调用存储过程
>--oracle  
create or replace procedure queryCountByGradeWithProcedure(gName in varchar, scount out number )  
as  
begin  
select count(*) into scount from student where graname = gname ;  
end;  
/

>--mysql  
DELIMITER $$  
CREATE  
PROCEDURE `queryCountByGradeWithProcedure`(IN gName VARCHAR(100), OUT scount INT(100))  
BEGIN  
SELECT COUNT(*) INTO scount FROM student WHERE graname = gName;  
END$$  
DELIMITER ;  



>&lt;select id="queryCountByGradeWithProcedure" statementType="CALLABLE"  parameterType="HashMap" >  
{  
CALL queryCountByGradeWithProcedure(  
&#35;{gName,jdbcType=VARCHAR,mode=IN},  
&#35;{scount,jdbcType=INTEGER,mode=OUT}  
)  
}  
&lt;/select>

其中 通过statementType="CALLABLE"设置SQL的执行方式是存储过程。 存储过程的输入参数gName需要通过HashMap来指定
在使用时，通过hashmap的put方法传入输入参数的值；通过hashmap的Get方法 获取输出参数的值。
要注意Jar问题：ojdbc6.jar不能在 调存储过程时  打回车、tab，但是ojdbc7.jar可以。


如果报错： No enum constant org.apache.ibatis.type.JdbcType.xx，则说明mybatis不支持xx类型，需要查表。

存储过程 无论输入参数是什么值，语法上都需要 用map来传递该值；

只要 是  &lt;transactionManager type="JDBC" />，则增删改都需要手工commit ;


mapper.xml->mapper接口->测试方法