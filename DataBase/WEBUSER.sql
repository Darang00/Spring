show user;

create table member
(
USERID VARCHAR2(50),   
PWD VARCHAR2(2000), 
NAME VARCHAR2(50),   
GENDER VARCHAR2(20), 
BIRTH VARCHAR2(10),  
IS_LUNAR VARCHAR2(10),  
PHONE VARCHAR2(15),   
EMAIL VARCHAR2(200),
HABIT VARCHAR2(200), 
joindate DATE 
);

ALTER TABLE member RENAME COLUMN userid to memberid;

select * from member;

select * from board;
INSERT INTO board(seq, title, content, writer, writedate, hit, filesrc, filesrc2)
 	VALUES (0, 'hi', 'what have you been up to?', 'dayeong', sysdate, 0, 'file1', 'file2');
commit;

ALTER TABLE member DROP COLUMN habit;
commit;

desc member;

ALTER TABLE member DROP COLUMN IS_LUNAR;
commit;

drop table users;
commit;

select * from users;



create table board
(
SEQ VARCHAR2(10),  
TITLE VARCHAR2(200),  
WRITER VARCHAR2(50),   
CONTENT VARCHAR2(4000),
REGDATE TIMESTAMP(6), 
HIT NUMBER,        
FILESRC VARCHAR2(500), 
FILESRC2 VARCHAR2(500)  
);

select * from board;

ALTER TABLE board RENAME COLUMN regdate to writedate;

commit;

desc board;