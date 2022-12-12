show user;

create table usermember(
 id varchar2(20),
 name varchar2(20),
 pwd  varchar2(20),
 email varchar2(20),
 age number
);

select * from usermember;

alter table member
rename column "UID" to "USERID";

select * from member;

desc member;

alter table member
modify gender varchar2(20);

commit;

alter table notices
add FileSrc2 varchar2(500);

select * from notices;

desc notices;

commit;

select * from notices;

select * from member;

