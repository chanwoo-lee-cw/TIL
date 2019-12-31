create table visitor (
    name varchar(18),
    writedate DATE,
    memo VARCHAR(180)
);

insert into visitor (writedate)
VALUES(SysdaTE);
insert into visitor (writedate)
VALUES(TO_DATE('2017/12/25','rrrr/MM/DD'));
insert into visitor (writedate)
VALUES(TO_DATE('1999/08/11'));

commit;

create table member (
    m_id varchar2(12),
    m_pwd varchar2(12) not null,
    name varchar2(18) not null,
    joindate DATE DEFAULT sysdate,
    PRIMARY key(m_id)
);

create table news (
    id number(8),
    writer varchar2(18),
    title varchar2(120),
    content varchar2(900),
    writedate DATE,
    cnt number(8),
    PRIMARY key(id)
);

CREATE SEQUENCE news_seq
INCREMENT by 1
start with 1;

create table meeting (
    id number(8),
    name varchar(18),
    title varchar2(120),
    meetingdate DATE,
    PRIMARY key(id)
);

CREATE SEQUENCE meeting_seq
INCREMENT by 1
start with 1;

create table imgtest (
    name varchar(18),
    imgcontent blob,
    PRIMARY key(name)
);