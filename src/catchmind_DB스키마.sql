


-- 테이블 정의

-- 기존 캐릭터 테이블을 삭제후
drop table CHARACTER;

-- 새로 캐릭터 테이블을 만듬
create table CHARACTER
(
	cno		number(4) constraint character_charno_pk primary key,	-- 캐릭터 pk값 
	name 	varchar2(40) not null,							-- 캐릭터 이름
	chartype number(4) check (chartype between 0 and 5),	-- 캐릭터(로봇) 타입(0 ~ 5)
	charlevel  number(4) default 1,							-- 캐릭터 레벨
	charexp	number default 0,								-- 캐릭터 경험치
	mno		number(4),										-- 맴버 pk값 foreign key
	regdate date default sysdate							-- 캐릭터 생성날짜
);

-- 기존 맴버 테이블을 삭제후
drop table MEMBER;

-- 신규 맴버 테이블(회원정보) 생성
create table MEMBER 
(
	mno 	number(4) constraint member_mno_pk primary key,	-- 회원 pk값 
	id  	varchar2(21) not null unique,					-- 아이디
	pw  	varchar2(32) not null,							-- 패스워드
	regdate date default sysdate							-- 회원 생성날짜
);

-- 맴버 테이블과 캐릭터 테이블을 관계 생성
alter table CHARACTER add constraint character_mno_fk foreign key (mno) references MEMBER(mno);

-- 새로운 시퀀스 생성
drop sequence member_mno_sq;
create sequence member_mno_sq start with 1;

-- 새로운 시퀀스 생성
drop sequence character_cno_sq;
create sequence character_cno_sq start with 1;

-- 게임에 필요한 데이터 일부 입력
insert into MEMBER values (member_mno_sq.nextval, 'ahnjy', '1234', sysdate);
insert into CHARACTER values (character_cno_sq.nextval, '때릴꼬야', 0, 10, 1000, member_mno_sq.currval, sysdate);

insert into MEMBER values (member_mno_sq.nextval, 'mjqoo', '1234', sysdate);
insert into CHARACTER values (character_cno_sq.nextval, '민덩쿠야', 1, 12, 1200, member_mno_sq.currval, sysdate);

insert into MEMBER values (member_mno_sq.nextval, 'ofsoul', '1234', sysdate);
insert into CHARACTER values (character_cno_sq.nextval, '완장이야', 2, 7, 800, member_mno_sq.currval, sysdate);

insert into MEMBER values (member_mno_sq.nextval, 'khs92', '1234', sysdate);
insert into CHARACTER values (character_cno_sq.nextval, '너구리야', 3, 14, 1500, member_mno_sq.currval, sysdate);

commit;