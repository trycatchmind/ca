


-- ���̺� ����

-- ���� ĳ���� ���̺��� ������
drop table CHARACTER;

-- ���� ĳ���� ���̺��� ����
create table CHARACTER
(
	cno		number(4) constraint character_charno_pk primary key,	-- ĳ���� pk�� 
	name 	varchar2(40) not null,							-- ĳ���� �̸�
	chartype number(4) check (chartype between 0 and 5),	-- ĳ����(�κ�) Ÿ��(0 ~ 5)
	charlevel  number(4) default 1,							-- ĳ���� ����
	charexp	number default 0,								-- ĳ���� ����ġ
	mno		number(4),										-- �ɹ� pk�� foreign key
	regdate date default sysdate							-- ĳ���� ������¥
);

-- ���� �ɹ� ���̺��� ������
drop table MEMBER;

-- �ű� �ɹ� ���̺�(ȸ������) ����
create table MEMBER 
(
	mno 	number(4) constraint member_mno_pk primary key,	-- ȸ�� pk�� 
	id  	varchar2(21) not null unique,					-- ���̵�
	pw  	varchar2(32) not null,							-- �н�����
	regdate date default sysdate							-- ȸ�� ������¥
);

-- �ɹ� ���̺�� ĳ���� ���̺��� ���� ����
alter table CHARACTER add constraint character_mno_fk foreign key (mno) references MEMBER(mno);

-- ���ο� ������ ����
drop sequence member_mno_sq;
create sequence member_mno_sq start with 1;

-- ���ο� ������ ����
drop sequence character_cno_sq;
create sequence character_cno_sq start with 1;

-- ���ӿ� �ʿ��� ������ �Ϻ� �Է�
insert into MEMBER values (member_mno_sq.nextval, 'ahnjy', '1234', sysdate);
insert into CHARACTER values (character_cno_sq.nextval, '��������', 0, 10, 1000, member_mno_sq.currval, sysdate);

insert into MEMBER values (member_mno_sq.nextval, 'mjqoo', '1234', sysdate);
insert into CHARACTER values (character_cno_sq.nextval, '�ε����', 1, 12, 1200, member_mno_sq.currval, sysdate);

insert into MEMBER values (member_mno_sq.nextval, 'ofsoul', '1234', sysdate);
insert into CHARACTER values (character_cno_sq.nextval, '�����̾�', 2, 7, 800, member_mno_sq.currval, sysdate);

insert into MEMBER values (member_mno_sq.nextval, 'khs92', '1234', sysdate);
insert into CHARACTER values (character_cno_sq.nextval, '�ʱ�����', 3, 14, 1500, member_mno_sq.currval, sysdate);

commit;