show user;
create table users
(
	userid varchar2(20),
	username varchar2(20),
	userpwd varchar2(20),
	usercmt varchar2(20)
);

select * from users;

CREATE TABLE guest(
NO NUMBER , 
NAME VARCHAR2(20), 
pwd VARCHAR(20), 
email VARCHAR(20), 
home VARCHAR(20), 
content VARCHAR(30), 
regdate date);

select * from guest;

select * from emp;


----------------------------------
show user;

select * from users;

create table guest(
  no number,
  name varchar2(20),
  pwd varchar2(20),
  email  varchar2(20),
  home  varchar2(20),
  content varchar2(20),
  regdate date
);

create sequence guest_seq;

select * from guest;

CREATE TABLE board
  (
  num NUMBER, 
  NAME VARCHAR2(50), 
  email  VARCHAR2(50), 
  pwd  VARCHAR2(50), 
  subject  VARCHAR2(50), 
  content  VARCHAR2(50), 
  regdate  date,
  hit  NUMBER, 
  parent  NUMBER, 
  SORT  NUMBER, 
  tab  NUMBER
  );
----

select * from board;

desc guest;


select * from guest where name='a' and  no =1;


SELECT * FROM
	(SELECT ROWNUM NUM, N.* 
     FROM (
        SELECT * 
        FROM NOTICES 
        WHERE ${} LIKE '%${}%' 
        ORDER BY REGDATE DESC) N)
		WHERE NUM BETWEEN ${} AND ${} ;
        
        
        select seq, title, writer, content, regdate, hit, fileSrc, fileSrc2
		from notices
		where seq=2;
        
        	 delete from notices where seq=1;
             
             select * from notices;
             
             rollback;
             
             select max(to_number(seq))+1 from notices;
                          

             
             
select * from notices;
