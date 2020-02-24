CREATE TABLE prodReview (
	reViewID	Number	NOT NULL,
	IDCord	Number	NOT NULL,
	NickName	varchar(30)	NOT NULL,
	IDEmail	varchar(100)	NOT NULL,
	prodID	varchar(50)	NOT NULL,
	viewTitle	varchar(300)	NULL,
	ViewDate	date	NULL,
	ViewContenxt	varchar(1999)	NULL,
	Viewcnt	number	NULL,
	ViewUpCnt	number	NULL,
	ViewImg	varchar(64)	NULL,
	ViewStar	Number	NULL,
	ViewPoice	number	NULL
);

select reViewID, IDCord, NickName, IDEmail, prodID, viewTitle, ViewDate, ViewContenxt, Viewcnt, ViewUpCnt, ViewImg, ViewStar, ViewPoice
		from prodReview;

insert into prodReview (reViewID, IDCord, NickName, IDEmail, prodID, viewTitle, ViewDate, ViewContenxt, Viewcnt, ViewUpCnt, ViewImg, ViewStar, ViewPoice) values (1, 1, 'testing','IDEmail','prodID','viewTitle',sysdate,'ViewContenxt',0,0,null,0,0);

commit;

drop table prodReview;

ALTER TABLE prodReview MODIFY ViewImg varchar(64);

CREATE SEQUENCE prodreview_seq
INCREMENT by 1
start with 2;

SELECT prodreview_SEQ.NEXTVAL FROM DUAL;

SELECT * FROM USER_SEQUENCES;
DROP SEQUENCE prodreview_SEQ;