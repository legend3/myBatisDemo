show create table studentclass;
-- student
CREATE TABLE `student` (
                           `stuno` int NOT NULL,
                           `stuname` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                           `cardid` int DEFAULT NULL,
                           `classid` int DEFAULT NULL,
                           `stuAge` int DEFAULT NULL,
                           `graname` varchar(20) DEFAULT NULL,
                           `stuSex` int DEFAULT NULL,
                           PRIMARY KEY (`stuno`),
                           KEY `fk_student_cardid_studentcard` (`cardid`),
                           KEY `fk_studentcard_student` (`classid`),
                           CONSTRAINT `fk_student_cardid_studentcard` FOREIGN KEY (`cardid`) REFERENCES `studentcard` (`cardid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- person
CREATE TABLE `person` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(100) DEFAULT NULL,
                          `age` int DEFAULT NULL,
                          KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- studentcard
CREATE TABLE `studentcard` (
                               `cardid` int NOT NULL,
                               `cardinfo` varchar(100) DEFAULT NULL,
                               PRIMARY KEY (`cardid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- studentclass
CREATE TABLE `studentclass` (
                                `classid` int NOT NULL,
                                `classname` varchar(100) DEFAULT NULL,
                                PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

update student set homeaddress='hangzhou' where stuNo=1;
update student set homeaddress='beijing' where stuNo>1;

select * from student;

update student set shcooladdress='yangguang' where stuNo>1;

alter table student drop shcooladdress;

alter table student add shcooladdress varchar(20);
alter table student add stuSex int(10);

alter table student add primary key (id);
alter table student drop column id;

alter table student change column id stuno  int(11);
alter table student change column name stuname  varchar(100);


alter table student change column stuAge stuage  int(11);
alter table student change column graName graname  varchar(100);
alter table student change column stuSex stusex varchar(100);

desc student



    CREATE TABLE studentcard(
    cardid int(100),
    cardinfo varchar(100),
    primary key(cardid)
    );
desc studentcard;

insert into studentcard values
                            (1,'zhangsan`s info'),
                            (2,'lisi`s info');

select * from studentcard;


alter table student drop graname;
alter table student drop stuage;
alter table student drop stusex;
alter table student drop homeaddress;
alter table student drop schooladdress;
desc student;
alter table student add cardid int(100);
select * from student;

alter table student add constraint fk_student_cardid_studentcard foreign key (cardid) references studentcard(cardid);

alter table student add classid int(100);

create table studentclass(
                             classid int(100) primary key,
                             classname varchar(100)
);
# student->studentclass
alter table student add constraint fk_studentcard_student foreign key (classid) references studentclass(classid);
alter table student drop foreign key fk_studentcard_student;



# console2
alter table student add column id integer(100);
show variables like '%max_connection%';
show processlist;
show global status like 'Thread%';

