DROP TABLE ADDGROUP;
DROP TABLE POINT_EXC_CASH;
DROP TABLE DEPOSIT_RECORDS;
DROP TABLE INT_EXC;
DROP TABLE DISCOUNT;
DROP TABLE ANNOUNCEMENT;
DROP TABLE ABOUT_US;
DROP TABLE GROUP_ORDER_DETAIL;
DROP TABLE ORDER_DETAIL;
DROP TABLE SWEET;
DROP TABLE ICE;
DROP TABLE MSG_BOARD;
DROP TABLE PRODUCT_TRACK;
DROP TABLE PRODUCT;
DROP TABLE PRODUCT_CLASS;
DROP TABLE GROUP_TRACK;
DROP TABLE GROUP_ART;
DROP TABLE ORDER_MASTER;
DROP TABLE MANAGER_ACCOUNT_AUTHORITY;
DROP TABLE MANAGER_ACCOUNT;
DROP TABLE AUTHORITY_CLASS;
DROP TABLE MEMBER;


DROP SEQUENCE point_exc_cash_seq;
DROP SEQUENCE deposit_records_seq;
DROP SEQUENCE int_exc_seq;
DROP SEQUENCE discount_seq;
DROP SEQUENCE announcement_seq;
DROP SEQUENCE about_us_seq;
DROP SEQUENCE sweet_seq;
DROP SEQUENCE ice_seq;
DROP SEQUENCE msg_seq;
DROP SEQUENCE product_seq;
DROP SEQUENCE product_class_seq;
DROP SEQUENCE group_art_seq;
DROP SEQUENCE order_master_seq;
DROP SEQUENCE manager_account_seq;
DROP SEQUENCE authority_class_seq;
DROP SEQUENCE member_seq;

--------------------------------------------------------1 
create table MEMBER(
MEM_ID varchar2(15) primary key not null,
MEM_NAME varchar2(100) ,
MEM_EMAIL varchar2(100) ,
MEM_PWD varchar2(20) ,
MEM_SEX varchar2(10) ,
MEM_BIRTH DATE ,
MEM_PHONE varchar2(20) ,
MEM_ADS varchar(100) ,
MEM_POINT number(10) ,
MEM_INT number(10),
MEM_ACC_STATUS number(10),
MEM_PIC BLOB , 
MEM_QRCODE BLOB
);

CREATE SEQUENCE member_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'�i�T','member1@yahoo.com.tw','member1','�k',TO_DATE('1992-06-03','YYYY-MM-DD'),'0926944860','��饫�O���366��',1000,1000,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'���|','member2@yahoo.com.tw','member2','�k',TO_DATE('1993-04-07','YYYY-MM-DD'),'0937648903','Ī�˰Ϥj�˸�562��',500,300,1);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'����','ca101g2db@gmail.com','1','�k',TO_DATE('1981-09-03','YYYY-MM-DD'),'0933111973','��饫������1692��',500,5,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'����','twm0983527254@gmail.com','1','�k',TO_DATE('1988-03-01','YYYY-MM-DD'),'0971927349','���c�������342��',1000,10,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'�P�C','member5@yahoo.com.tw','member5','�k',TO_DATE('1922-09-03','YYYY-MM-DD'),'0958237495','�x�_���H�q��366��',2000,100,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'���K','member6@yahoo.com.tw','member6','�k',TO_DATE('1995-11-23','YYYY-MM-DD'),'0948593478','�x�_���j�w��46��',300,3,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'�L�L','member7@yahoo.com.tw','member7','�k',TO_DATE('1992-06-03','YYYY-MM-DD'),'0974224860','��饫��v��352��',20000,200,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'�x��','member8@yahoo.com.tw','member8','�k',TO_DATE('1995-03-07','YYYY-MM-DD'),'0932648903','Ī�˰Ϥj�˸�562��',5000,300,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'�P�o','member9@yahoo.com.tw','member9','�k',TO_DATE('2003-09-03','YYYY-MM-DD'),'0953711973','��饫�_�^��1692��',500,20,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'����','y19920603@gmail.com','asd3264097','�k',TO_DATE('1992-06-03','YYYY-MM-DD'),'0926944860','��饫Ī�˰Ϥj�˸�562��',10000,100,0);



--------------------------------------------------------2
create table AUTHORITY_CLASS(
AUTHORITY_ID varchar2(15) primary key not null,
AUTHORITY_DES varchar2(100) 
);

CREATE SEQUENCE authority_class_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE 
NOCYCLE
NOCACHE;


Insert into AUTHORITY_CLASS (AUTHORITY_ID,AUTHORITY_DES) values ('AC'||LPAD(to_char(authority_class_seq.NEXTVAL), 6, '0')
,'��x�޲z��');
Insert into AUTHORITY_CLASS (AUTHORITY_ID,AUTHORITY_DES) values ('AC'||LPAD(to_char(authority_class_seq.NEXTVAL), 6, '0')
,'�~�e����');
Insert into AUTHORITY_CLASS (AUTHORITY_ID,AUTHORITY_DES) values ('AC'||LPAD(to_char(authority_class_seq.NEXTVAL), 6, '0')
,'�d�x����');

--------------------------------------------------------3 
create table MANAGER_ACCOUNT(
MAN_ACC_ID varchar2(15) primary key not null,
ACCPW varchar2(15) ,
MAN_ACC_STATUS number(10) , 
EMP_NAME varchar2(100), 
EMP_IMG BLOB ,
EMP_EMAIL varchar2(100)
); 

CREATE SEQUENCE manager_account_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a1',0,'�p�@','a1@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a2',0,'�p�G','a2@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a3',0,'�p�T','a3@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a4',0,'�p�|','41@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a5',0,'�p��','a5@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a6',0,'�p��','a6@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a7',0,'�p�C','a7@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a8',0,'�p�K','a8@yahoo.com.tw');


--------------------------------------------------------4

create table MANAGER_ACCOUNT_AUTHORITY(
MAN_ACC_ID varchar2(100) not null, 
constraint FK_AUTH_ACC FOREIGN key(MAN_ACC_ID) references MANAGER_ACCOUNT(MAN_ACC_ID) ,
AUTHORITY_ID varchar2(100) not null,
constraint FK_AUTH_CLA FOREIGN key(AUTHORITY_ID) references AUTHORITY_CLASS(AUTHORITY_ID),
constraint PK_FK_MAN_AUTH primary key(MAN_ACC_ID,AUTHORITY_ID)
);

Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000001','AC000001');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000001','AC000002');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000001','AC000003');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000002','AC000001');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000002','AC000002');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000003','AC000002');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000004','AC000002');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000004','AC000003');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000005','AC000003');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000006','AC000001');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000006','AC000003');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000007','AC000002');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000008','AC000001');
Insert into MANAGER_ACCOUNT_AUTHORITY(MAN_ACC_ID,AUTHORITY_ID) 
values ('MA000008','AC000003');




--------------------------------------------------------5

create table ORDER_MASTER(
ORD_ID varchar2(15) primary key not null,
MEM_ID varchar2(15) not null,
constraint FK_ORD_MEM FOREIGN key(MEM_ID) references MEMBER(MEM_ID),
MAN_ACC_ID varchar2(15) ,
constraint FK_ORD_ACC FOREIGN key(MAN_ACC_ID) references MANAGER_ACCOUNT(MAN_ACC_ID) ,
GROU number(10)  ,
OUTE_ADD  varchar2(200) ,
SHIP_OPTION number(10) not null,
ORD_STATUS number(10) not null 
);

CREATE SEQUENCE order_master_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000001',null,1,null,0,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000002',null,1,'��饫���c�Ϥ��Ƹ��G�q10��',1,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000003',null,1,null,0,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000004',null,1,'��饫����ϥ��ڸ��G�q175��',1,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000005',null,1,null,0,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000004',null,1,'��饫���c�ϥ��ڸ��G�q138��',1,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000003',null,1,null,0,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000001',null,1,'��饫���c�ϥ��ڸ����q508��',1,0);


--------------------------------------------------------6 

create table GROUP_ART(
GROU_ID varchar2(15) primary key not null,
MEM_ID varchar2(15) ,
ORD_ID varchar2(15),
constraint FK_ART_MEM FOREIGN key(MEM_ID) references MEMBER(MEM_ID),
constraint FK_ART_ORDER_MASTER FOREIGN key(ORD_ID) references ORDER_MASTER(ORD_ID) ,
SHIP_LOCAT varchar2(100) ,
SEND_LOCAT varchar2(100) ,
EXP_DATE TIMESTAMP ,
ART_NAME varchar2(100) ,
GROU_PRICE number(10) ,
GROU_STATUS number(10) ,
ART_IMG BLOB
);

CREATE SEQUENCE group_art_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000001',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('1'), 6, '0'),
null,'���c�������e��',TO_TIMESTAMP('2018-6-30 12:26:43','YYYY-MM-DD hh24:mi:ss'),'�֨Ӥ@�_�R�W�n�ܪ�����',300,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000002',TO_CHAR(sysdate,'yyyymmdd')||'-'||LPAD(to_char('2'), 6, '0'),
'��饫���c�Ϥ��Ƹ��G�q10��','��饫���c�Ϥ��Ƹ��G�q10��',TO_TIMESTAMP('2018-6-30 13:41:53','YYYY-MM-DD hh24:mi:ss'),'��M�n�Q�ܺ��',200,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000003',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('3'), 6, '0'),
null,'��饫���c�Ϥ��_��200��',TO_TIMESTAMP('2018-6-30 19:32:51','YYYY-MM-DD hh24:mi:ss'),'�Q�ܬ������֨�',400,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000004',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('4'), 6, '0'),
'��饫����ϥ��ڸ��G�q175��','��饫����ϥ��ڸ��G�q175��',TO_TIMESTAMP('2018-6-30 14:12:43','YYYY-MM-DD hh24:mi:ss'),'�R�V�h�V�K�y�֨Ӱ�',500,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000005',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('5'), 6, '0'),
null,'�����j�ǥ����f',TO_TIMESTAMP('2018-6-30 10:36:23','YYYY-MM-DD hh24:mi:ss'),'�u�f�N�t�A�@��~',150,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000004',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('6'), 6, '0'),
'��饫���c�ϥ��ڸ��G�q138��','��饫���c�ϥ��ڸ��G�q138��',TO_TIMESTAMP('2018-6-30 20:55:33','YYYY-MM-DD hh24:mi:ss'),'�H�K�R�R�N�n',200,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000003',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('7'), 6, '0'),
null,'�����j��',TO_TIMESTAMP('2018-6-30 15:44:10','YYYY-MM-DD hh24:mi:ss'),'�Q�ܳܳܳܳܯ�',450,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000001',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('8'), 6, '0'),
'��饫���c�ϥ��ڸ����q508��','��饫���c�ϥ��ڸ����q508��',TO_TIMESTAMP('2018-6-30 17:16:00','YYYY-MM-DD hh24:mi:ss'),'�Q�n�K�y�i�ӴN���',300,0);

--------------------------------------------------------7

create table GROUP_TRACK(
MEM_ID varchar2(15) not null,
GROU_ID varchar2(15) not null,
constraint FK_TRACK_MEM FOREIGN key(MEM_ID) references MEMBER(MEM_ID),
constraint FK_TRACK_ART FOREIGN key(GROU_ID) references GROUP_ART(GROU_ID) ,
constraint PK_FK_MEM_ART primary key(MEM_ID,GROU_ID)
);

Insert into GROUP_TRACK(MEM_ID,GROU_ID) values ('M000001','GA000001');

Insert into GROUP_TRACK(MEM_ID,GROU_ID) values ('M000002','GA000001');

Insert into GROUP_TRACK(MEM_ID,GROU_ID) values ('M000003','GA000001');

Insert into GROUP_TRACK(MEM_ID,GROU_ID) values ('M000004','GA000001');

Insert into GROUP_TRACK(MEM_ID,GROU_ID) values ('M000005','GA000001');

Insert into GROUP_TRACK(MEM_ID,GROU_ID) values ('M000001','GA000002');

Insert into GROUP_TRACK(MEM_ID,GROU_ID) values ('M000002','GA000003');


--------------------------------------------------------8

CREATE TABLE PRODUCT_CLASS(
PRODUCT_CL_ID varchar2(15) PRIMARY KEY NOT NULL,
PRODUCT_CL_NAME VARCHAR2(50) NOT NULL,
PRODUCT_CL_STATUS number(10)
);

CREATE SEQUENCE product_class_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'�A�ժG�����t�C',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'�ʨť������t�C',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'�����A�~���t�C',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'�A�^���G�t�C',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'�h�f�P�����t�C',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'�t�D�B�F�t�C',1);

--------------------------------------------------------9

CREATE TABLE PRODUCT(
PRODUCT_ID varchar2(15) PRIMARY KEY NOT NULL,
PRODUCT_CL_ID varchar2(15)  ,
CONSTRAINT FK_PRODUCT_CLASS FOREIGN KEY(PRODUCT_CL_ID) REFERENCES PRODUCT_CLASS(PRODUCT_CL_ID),
PRODUCT_NAME VARCHAR2(50) ,
PRODUCT_PRICE NUMBER(10) ,
PRODUCT_DES VARCHAR2(300) ,
PRODUCT_STATUS number(10) , 
PRODUCT_IMG BLOB  
);

CREATE SEQUENCE product_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '�ʭ����', 50, '�v�����a�w�r���Ĳ��ʭ��A�P�[���᪺�M�s�B������嶮�A��´���@�ؿW�S���Q���A���z����Ĳ��S��ڪ��W�S�����C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '�n�D���_�f�c��', 50, '���t�@�ئ]�����_���f�t�s�A�f�c�ġA�νw�@�Ѫ����O�C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '�����f�c', 50, '�x�W���g���ܡA�t�״I���L�RA�BC�A��H��㦳�ܦѤƥ\�ġA�f�t�s�A�{�^�f�c��ġACoCo���z�C�鳣���d�C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '�ʭ����T��', 50, '�I�t���L�RC�Y��ʭ��G�A�h�o�ѿ��A�f�t���ַ��G��Q�u�ï]�A�f�P�h���״I�I',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '�A�f�c���', 50, '�Y��W������A�f�t�A���f�c��ġA�Ĳ���ܡA����ߺۡI(�f�c���a�G�̪F)',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '�V�ʮ��K', 60, '���J���V���S�A�[�J���u���A�¤��šA�����M���g�H�A���z���P�����e�����I',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '���Y����', 60, '�Y�﨡�Y�A�u����ơA��u��߼��N��A�f�t�s�A�����A�f�P�W�S�A�������z�@�f�N�R�W�I',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '���Y�����訦��', 60, '�Y�﨡�Y�A�u����ơA��u��߼��N��A�f�t�s�A�����PQQ�訦�̡A�f�P�W�S�A�O�z���i���|�����ִ����I',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '�������K', 60, '�Y���u������A�[�J���u���A�¤��šA�a�z����@�J�����|�~���C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '�ï]�������K', 60, 'QQ�u�O�f�P�ï]�A������ҷf�t���@�A���P�����A���X�X���Ϊ��n�����I',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '�n�D���_���K', 60, '�P�����B�p�ۨú٫n�D�T�_���n�D���_���A�f�t�@���A���A�����L�@�ئ]�A�߷R�|�A���A�A�@�w�n�ոլݡI',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '���J�O�ï]����', 60, '�����@�J�����J�O�A�f�t�WQQ�ï]�����Τf�P�A�@�����e���n�����d�s�b���Y',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '�~�G�i�֦h', 70, '���ﰪ�u��~�G�A�G���@���B�����g�H�P�g��i�֦h��´�Ĳ��n����',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '�n�D���_��', 70, '�P�����B�p�ۨú٫n�D�T�_���n�D���_���A���t�@�ئ]�A���z�νw�@�Ѫ����O�C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '�f�c�V���S', 70, '�s�A�f�c�ĵ��X�j�����V���S�A�Ĳ������`�`�^���C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '�L���V���S', 70, '�L���̭��V�X�¥j�k���Ҫ��V���S�A�Ѥ��F���������f�C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '�x�W�]�د�', 70, '�S��x�W�_���s���u��]�د��A���������A�©M�M�����W�ߡA�ն��^�̪��n�����C', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '����i�֦h', 70, '���J����P�g��i�֦h��´�M�s�n�����A���d�S�M�n���զX~', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '�f�c�i�֦h', 70, '�s�A�f�c�ĻP�g��i�֦h��´�Ĳ��n�����A���d�S�M�n���զX�C(�f�c���a�G�̪F)', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '�A�^ī�G�i�֦h', 80, '�{�^�s�Aī�G�׻P�g��i�֦h��´�Ĳ��n�����Aī�G�h�����z��⺡���C', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '�f�c�Q', 80, '�����s�A�f�c�A����J���Į����~�������~�������D�f�c�w�߲M�~�u�ǡA������z�ܱo�w�ߡA�ɨ��L��Ĳ������I', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '�A����c�B��', 80, '�s�A����c�İt�W���J����A�G���b�e�A�M�������^�̡A�״I�z�������C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '�A����B��', 80, '�s�A����G�ķf�t�J�~�����A�ѵM�G�����z�@�Ѧn�߱�',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '�A��c�B��', 80, '����G�ķf�W����c�ġA�������G��i�A�߷R�s�A�ը����z�@�w�n�ոլݡC',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '�A�^ī�G�ʭ�', 80, '�{�^�s�Aī�G�׻P�A�ʭ��G�ĿĦX���G�������A�ȱo�z�Ӳӫ~�|�C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000005', '�ï]����', 80, 'Q�u�I�f�P���ï]�A�f�t���@�����A�ܱz����@�J�n�����I',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000005', '�����T�S��', 80, '���@�����f�t���ƥ��B�BQ�u�ï]���A��P��A�@���N���T�ؤf�P�A�h�بɨ��C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000005', '���B����', 80, '�ƹ઺���B�[�J���@�����A�H�ɱa���z���e���n�߱��C',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000006', '�񨧨F����', 80, '�ĥΤW���񨧺�ߤ�@�ҵN�A�f�P���K�A�f�t���J�A���A��i�S�M�D!',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000006', '�~�G�B�F', 80, '����W���~�G�A�G���@���A�s���B�F�֦����K�f�P�A �����L��A�O�M�D���n���D�I',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000006', '�~�G����', 80, '����W���~�G�A�G���@���A�s���B�F�֦����K�f�P�A �f�t���J�A���A�����L��A�O�M�D���n���D�I',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000006', '�񨧨F', 80, '�ĥΤW���񨧺�ߤ�@�ҵN�A�f�P���K�A�G�D���������A���z�^���L�a�I',1);




--------------------------------------------------------10

CREATE TABLE PRODUCT_TRACK(
MEM_ID varchar2(15) NOT NULL,
CONSTRAINT FK_PRODUCT_TR_MEM FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID),
PRODUCT_ID varchar2(15) NOT NULL,
CONSTRAINT FK_PRODUCT_TR_PRODUCT FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID),
constraint PK_FK_MEM_PRODUCT primary key(MEM_ID,PRODUCT_ID)
);

INSERT INTO PRODUCT_TRACK (MEM_ID,PRODUCT_ID) VALUES ('M000001','P000001');

--------------------------------------------------------11

CREATE TABLE MSG_BOARD(
MSG_BOARD_ID varchar2(15) PRIMARY KEY NOT NULL,
MEM_ID varchar2(15) NOT NULL,
PRODUCT_ID varchar2(15) NOT NULL,
CONSTRAINT FK_BOARD_MEM FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID),
CONSTRAINT FK_BOARD_PRODUCT FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID),
MSG_DES VARCHAR2(100) NOT NULL,
MSG_STATUS number(10) NOT NULL
);

CREATE SEQUENCE msg_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


Insert into MSG_BOARD (MSG_BOARD_ID,MEM_ID,PRODUCT_ID,MSG_DES,MSG_STATUS) 
values ('MS'||LPAD(to_char(msg_seq.NEXTVAL), 6, '0'),'M000001','P000001','�Q�s���ܦn��',0);

Insert into MSG_BOARD (MSG_BOARD_ID,MEM_ID,PRODUCT_ID,MSG_DES,MSG_STATUS) 
values ('MS'||LPAD(to_char(msg_seq.NEXTVAL), 6, '0'),'M000002','P000004','������K�ܦn��',0);



--------------------------------------------------------12
CREATE TABLE ICE(
ICE_ID varchar2(15) PRIMARY KEY not null,
ICE_DES VARCHAR2(20) 
);

CREATE SEQUENCE ice_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

Insert into ICE(ICE_ID,ICE_DES) 
values ('I'||LPAD(to_char(ice_seq.NEXTVAL), 6, '0'),'�h�B');
Insert into ICE(ICE_ID,ICE_DES) 
values ('I'||LPAD(to_char(ice_seq.NEXTVAL), 6, '0'),'�֦B');
Insert into ICE(ICE_ID,ICE_DES) 
values ('I'||LPAD(to_char(ice_seq.NEXTVAL), 6, '0'),'���`�B');



--------------------------------------------------------13

CREATE TABLE SWEET(
SWEET_ID varchar2(15) PRIMARY KEY not null,
SWEET_DES VARCHAR2(20) 
);

CREATE SEQUENCE sweet_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

Insert into SWEET(SWEET_ID,SWEET_DES) 
values ('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),'�L�}');
Insert into SWEET(SWEET_ID,SWEET_DES) 
values ('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),'�ֿ}');
Insert into SWEET(SWEET_ID,SWEET_DES) 
values ('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),'�b�}');
Insert into SWEET(SWEET_ID,SWEET_DES) 
values ('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),'���`�}');


--------------------------------------------------------14

CREATE TABLE ORDER_DETAIL(
ORD_ID varchar2(15) ,
MEM_ID varchar2(15) ,
PRODUCT_ID varchar2(15) not null,
ICE_ID varchar2(15) not null,
SWEET_ID varchar2(15) not null,
constraint FK_ORDER_MEM FOREIGN key(MEM_ID) references MEMBER(MEM_ID),
CONSTRAINT FK_ORDER_DETAIL_ORDER FOREIGN KEY(ORD_ID) REFERENCES ORDER_MASTER(ORD_ID),
CONSTRAINT FK_ORDER_PRODUCT FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID),
CONSTRAINT FK_ORDER_ICE FOREIGN KEY(ICE_ID) REFERENCES ICE(ICE_ID),
CONSTRAINT FK_ORDER_SWEET FOREIGN KEY(SWEET_ID) REFERENCES SWEET(SWEET_ID),
constraint PK_ORDER_DETAIL primary key(ORD_ID,MEM_ID,PRODUCT_ID,ICE_ID,SWEET_ID),
ORD_PRICE NUMBER(10) ,
TOL_CUP NUMBER(10) 
);

Insert into ORDER_DETAIL(ORD_ID,MEM_ID,PRODUCT_ID,ICE_ID,SWEET_ID,ORD_PRICE,TOL_CUP) 
values ( to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.CURRVAL), 6, '0'),'M000001','P000001', 'I000001','S000001',100,2);

--------------------------------------------------------15

CREATE TABLE GROUP_ORDER_DETAIL(
GROU_ID varchar2(15) not null,
MEM_ID varchar2(15) not null,
PRODUCT_ID varchar2(15) not null,
ORD_ID varchar2(15) not null,
constraint FK_GROUP_DETAL_ORDER FOREIGN key(ORD_ID) references ORDER_MASTER(ORD_ID) ,
CONSTRAINT FK_GROUP_DETAL_ART FOREIGN KEY(GROU_ID) REFERENCES GROUP_ART(GROU_ID),
CONSTRAINT FK_GROUP_DETAL_MEM FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID),
CONSTRAINT FK_GROUP_DETAL_PRODUCT FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID),
constraint PK_GROUP_ORDER_DETAIL primary key(GROU_ID,MEM_ID,PRODUCT_ID,ICE_ID,SWEET_ID,ORD_ID),
SWEET_ID varchar2(15) not null,
ICE_ID varchar2(15) not null,
CONSTRAINT FK_GROUP_DETAIL_SWEET FOREIGN KEY(SWEET_ID) REFERENCES SWEET(SWEET_ID),
CONSTRAINT FK_GROUP_DETAIL_ICE FOREIGN KEY(ICE_ID) REFERENCES ICE(ICE_ID),
GROUP_ORD_PRICE NUMBER(10) ,
GROUP_TOL_CUP NUMBER(10) 
);


--------------------------------------------------------16

CREATE TABLE ABOUT_US(
ABOUT_ID varchar2(15) PRIMARY KEY NOT NULL,
ABOUT_DES VARCHAR2(3000), 
ABOUT_TIME VARCHAR2(100),
ABOUT_PHONE VARCHAR2(100),
ABOUT_ADD VARCHAR2(100)
);

CREATE SEQUENCE about_us_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO ABOUT_US VALUES ('A'||LPAD(to_char(about_us_seq.NEXTVAL), 6, '0'),'1997�~���H���p��A�O�M�D�@�L�̪쪺�}�ݡC�b�o���H��P�����ݨ㪺�g�a�W�A�M�D�@�L�@�B�@�}�L�B��u�~��P�A�ȡA�ҰʤF�ڷQ����~�C������U�ȳ̦n���A�Ʊ�n�����B�n�����~������h�H�~�|�A�M�D�@�L�b1999�~��X�H���a�ϡA�}�l�`�J�x�W�U�a�A�é�2007�~�i�x�j�������A��n���w�Ʃݮi�@�ɪ��ϡC�ë��ۡu���~���y�ơB�޲z�b�a�ơv���g��z���A�ۥx�W�ݮi�ܥ��y�A�M�D�@�L��2015�~������}2,000���A�������@�ɦU�w�����W�����s��~�P�C �����X�ΤW���BĬ�{�B�_�ʡB�����B�s�{�B�C�{�B�Z�~�έ��䵥�U�j�����A �éݮi�ܬ���ì��B�����F�B�[���j�h�ۦh�B�ŭ��ءB����B�n�D�B����B�L���B�V�n�B��߻��H�έ^��۴��P�D�w���@�ɦU�a�A ���\�إߡu����ǲΡB�i��зs�B�񲴰�ڡv�������~�C �i�楼�ӡA�M�D�@�L�N�~���X�j������쪺�g��A�z�L�s�Ʒ~�骺�ݮi�P�����Ʒ~�������_���A�����ߨ����@�ɪ������s��~�P�C', '12:00p.m. ~ 20:00p.m.', '02-12345678', '320��饫���c�Ϥ��j��300��');



--------------------------------------------------------17 

CREATE TABLE ANNOUNCEMENT(
ANN_ID  varchar2(15)  NOT NULL,
ANN_TITLE VARCHAR2(100) ,
ANN_DES VARCHAR2(1000) ,
ANN_DATE DATE ,
ANN_IMG BLOB ,
ANN_STATUS number(10)
);

CREATE SEQUENCE  announcement_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '���Q�f�L�E�E�D�����s�W���I', '�Ѯ���Q�n�ֳt�����Ѵ��ܡH�֨ӪM���Q�f�L�A�M�n�ѿ��A�a�A�������E�D�ַP�I�Y��b�a�x�W�f�c�ġA�f�t�Ÿq�²b�����Q�A���W�̪F���f�c��u�����A���� �h�h���������A�ϩ����B�����ɨ��D�ַP�A���[���L�R C���A�۰ʶ}�Ҭ��C�\��� �C', to_date('2018-03-20 13:23:44','yyyy-mm-dd hh24:mi:ss'), 1);
Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '�W�W���c�G���t�C�E�s�AJuicy�n��', '�W�W���c�G���t�C�Y��H��C���_�۬c�G�ġA�[�J��M����c�G���A�t�����ͯ��ѡB�����ֺ��B�G��...���A�M�n�����f�A���d�S���� �A�f�t�x�W�]�د��A�W�@�W�B�ͤ@�͡A�W�X�������A�G�n�����I', to_date('2018-03-26 13:23:44','yyyy-mm-dd hh24:mi:ss'), 1);
Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), 'ٮ�a�A��G���E�s�A���q�W��', 'CoCo���X�u���q�v�s�~�A�ĥΥx�W�x����ٮ�a�ġA��J�����h������A�f�t�x�W�]�د��A���D²��Ӧ��h���A�G���P�Ĳ��ĦX�����n�B�A�γ̳�ª���������A�O�d�̯º骺�A�G�����C�ƶq�����槹�����I', to_date('2018-04-02 13:23:44','yyyy-mm-dd hh24:mi:ss'), 1);
Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '�P�Ż��fx�ؾ���Z �ڤۤW��', '�Ѯ���Q�n�ֳt�����Ѵ��ܡH�֨ӪM���Q�f�L�A�M�n�ѿ��A�a�A�������E�D�ַP�I', to_date('2018-06-01 13:23:44','yyyy-mm-dd hh24:mi:ss'), 0);
Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '��������', '�ϥΨӦ۳߰��Զ��s�������~�ءA�b�D�w�شӱĦ��C�g�����N�s�B�e�}�ʸˡA��߽հt3:2�����d�f�P�A�ĦX�b���~�����C�C�@�f���P���o��������P�n�����C', to_date('2018-06-08 13:23:44','yyyy-mm-dd hh24:mi:ss'), 0);



--------------------------------------------------------18

CREATE TABLE DISCOUNT(
DIS_ID varchar2(15) PRIMARY KEY NOT NULL,
DIS_DES VARCHAR2(200) NOT NULL,
DIS_CUP NUMBER(10) NOT NULL,
DIS_CUP_RATE NUMBER(10,2) NOT NULL,
DIS_PRICE NUMBER(10) NOT NULL,
DIS_PRICE_RATE NUMBER(10,2) NOT NULL
);

CREATE SEQUENCE discount_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into DISCOUNT(DIS_ID ,DIS_DES,DIS_CUP,DIS_CUP_RATE,DIS_PRICE,DIS_PRICE_RATE) 
values ('D'||LPAD(to_char(discount_seq.NEXTVAL), 6, '0'),'���ѶR10�M�ʥ��E��,��500�A���E��',10,0.9,500,0.9);


--------------------------------------------------------19 

CREATE TABLE INT_EXC(
INT_EXT_REC_ID varchar2(15) PRIMARY KEY NOT NULL,
MEM_ID varchar2(15) NOT NULL,
CONSTRAINT FK_EXC_MEM FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID),
INT_EXC_DATE DATE,
INTRGRAL NUMBER(10) 
);

CREATE SEQUENCE int_exc_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000001', 
sysdate,10);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000003', 
sysdate,30);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000002', 
sysdate,70);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000006', 
sysdate,80);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000005', 
sysdate,40);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000001', 
sysdate,30);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000008', 
sysdate,70);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000001', 
sysdate,20);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000002', 
sysdate,50);

Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000007', 
sysdate,30);


Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000004', 
sysdate,30);


Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000009', 
sysdate,90);


Insert into INT_EXC (INT_EXT_REC_ID,MEM_ID,INT_EXC_DATE,INTRGRAL) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(int_exc_seq.NEXTVAL), 6, '0'),'M000010', 
sysdate,40);


--------------------------------------------------------20  

CREATE TABLE DEPOSIT_RECORDS(
DEP_REC_ID varchar2(15) PRIMARY KEY NOT NULL,
MEM_ID varchar2(15) NOT NULL,
CONSTRAINT FK_DEP_MEM FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID),
DEP_CASH NUMBER(10)NOT NULL,
DEP_SUSS_DATE DATE NOT NULL
);

CREATE SEQUENCE deposit_records_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000001',1000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000002',2000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000003',3000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000004',1000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000005',1000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000006',2000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000007',500,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000008',2000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000010',2000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000009',2000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000010',2000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000010',2000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000009',2000,sysdate);
Insert into DEPOSIT_RECORDS (DEP_REC_ID,MEM_ID,DEP_CASH,DEP_SUSS_DATE) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(deposit_records_seq.NEXTVAL), 6, '0'),'M000001',2000,sysdate);



--------------------------------------------------------21 

CREATE TABLE POINT_EXC_CASH(
EXC_REC_ID varchar2(15) PRIMARY KEY NOT NULL,
MEM_ID varchar2(15) NOT NULL,
CONSTRAINT FK_POINT_MEM FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID),
EXC_DATE DATE NOT NULL,
EXC_CASH NUMBER NOT NULL,
BANK_ACC NUMBER NOT NULL,
REQ_STATUS number(10) NOT NULL
);

CREATE SEQUENCE point_exc_cash_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000001',sysdate,1000,'231211',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000002',sysdate,1000,'231982',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000003',sysdate,1000,'229483',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000004',sysdate,1000,'129484',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000005',sysdate,1000,'129485',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000006',sysdate,1000,'129486',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000007',sysdate,1000,'239487',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000008',sysdate,1000,'231488',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000009',sysdate,1000,'229489',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000010',sysdate,1000,'129410',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000010',sysdate,1000,'231410',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000009',sysdate,1000,'239489',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000008',sysdate,1000,'239488',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000007',sysdate,1000,'239487',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000006',sysdate,1000,'231486',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000005',sysdate,1000,'239485',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000004',sysdate,1000,'239484',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000003',sysdate,1000,'239483',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000002',sysdate,1000,'239482',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000001',sysdate,1000,'239481',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000010',sysdate,2000,'231410',0);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000010',sysdate,3000,'239410',1);
Insert into POINT_EXC_CASH(EXC_REC_ID,MEM_ID,EXC_DATE,EXC_CASH,BANK_ACC,REQ_STATUS) 
values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(point_exc_cash_seq.NEXTVAL), 6, '0'),'M000010',sysdate,1000,'231410',0);


--------------------------------------------------------22
CREATE TABLE ADDGROUP(
MEM_ID varchar2(15) NOT NULL,
GROU_ID varchar2(15) not null,
CONSTRAINT FK_ADDGROUP_ART FOREIGN KEY(GROU_ID) REFERENCES GROUP_ART(GROU_ID),
CONSTRAINT FK_ADDGROUP_MEM FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID)
);

commit;
