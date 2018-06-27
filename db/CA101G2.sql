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
,'張三','member1@yahoo.com.tw','member1','女',TO_DATE('1992-06-03','YYYY-MM-DD'),'0926944860','桃園市慈文路366號',1000,1000,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'李四','member2@yahoo.com.tw','member2','男',TO_DATE('1993-04-07','YYYY-MM-DD'),'0937648903','蘆竹區大竹路562號',500,300,1);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'王五','ca101g2db@gmail.com','1','女',TO_DATE('1981-09-03','YYYY-MM-DD'),'0933111973','桃園市中正路1692號',500,5,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'趙六','twm0983527254@gmail.com','1','女',TO_DATE('1988-03-01','YYYY-MM-DD'),'0971927349','中壢市平鎮區342號',1000,10,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'周七','member5@yahoo.com.tw','member5','男',TO_DATE('1922-09-03','YYYY-MM-DD'),'0958237495','台北市信義區366號',2000,100,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'趙八','member6@yahoo.com.tw','member6','女',TO_DATE('1995-11-23','YYYY-MM-DD'),'0948593478','台北市大安區46號',300,3,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'昭昭','member7@yahoo.com.tw','member7','女',TO_DATE('1992-06-03','YYYY-MM-DD'),'0974224860','桃園市文宗路352號',20000,200,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'軍宏','member8@yahoo.com.tw','member8','男',TO_DATE('1995-03-07','YYYY-MM-DD'),'0932648903','蘆竹區大竹路562號',5000,300,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'星穎','member9@yahoo.com.tw','member9','男',TO_DATE('2003-09-03','YYYY-MM-DD'),'0953711973','桃園市北回路1692號',500,20,0);
Insert into MEMBER (MEM_ID,MEM_NAME,MEM_EMAIL,MEM_PWD,MEM_SEX,MEM_BIRTH,MEM_PHONE,MEM_ADS,MEM_POINT,MEM_INT,MEM_ACC_STATUS) values ('M'||LPAD(to_char(member_seq.NEXTVAL), 6, '0')
,'正岩','y19920603@gmail.com','asd3264097','男',TO_DATE('1992-06-03','YYYY-MM-DD'),'0926944860','桃園市蘆竹區大竹路562號',10000,100,0);



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
,'後台管理員');
Insert into AUTHORITY_CLASS (AUTHORITY_ID,AUTHORITY_DES) values ('AC'||LPAD(to_char(authority_class_seq.NEXTVAL), 6, '0')
,'外送店員');
Insert into AUTHORITY_CLASS (AUTHORITY_ID,AUTHORITY_DES) values ('AC'||LPAD(to_char(authority_class_seq.NEXTVAL), 6, '0')
,'櫃台店員');

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
,'a1',0,'小一','a1@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a2',0,'小二','a2@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a3',0,'小三','a3@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a4',0,'小四','41@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a5',0,'小五','a5@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a6',0,'小六','a6@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a7',0,'小七','a7@yahoo.com.tw');
Insert into MANAGER_ACCOUNT (MAN_ACC_ID,ACCPW,MAN_ACC_STATUS,EMP_NAME,EMP_EMAIL) values ('MA'||LPAD(to_char(manager_account_seq.NEXTVAL), 6, '0')
,'a8',0,'小八','a8@yahoo.com.tw');


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

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000002',null,1,'桃園市中壢區元化路二段10號',1,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000003',null,1,null,0,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000004',null,1,'桃園市平鎮區民族路二段175號',1,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000005',null,1,null,0,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000004',null,1,'桃園市中壢區民族路二段138號',1,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000003',null,1,null,0,0);

Insert into ORDER_MASTER (ORD_ID,MEM_ID,MAN_ACC_ID,GROU,OUTE_ADD,SHIP_OPTION,ORD_STATUS)values (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(order_master_seq.NEXTVAL), 6, '0'),'M000001',null,1,'桃園市中壢區民族路六段508號',1,0);


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
null,'中壢火車站前站',TO_TIMESTAMP('2018-6-30 12:26:43','YYYY-MM-DD hh24:mi:ss'),'快來一起買超好喝的奶茶',300,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000002',TO_CHAR(sysdate,'yyyymmdd')||'-'||LPAD(to_char('2'), 6, '0'),
'桃園市中壢區元化路二段10號','桃園市中壢區元化路二段10號',TO_TIMESTAMP('2018-6-30 13:41:53','YYYY-MM-DD hh24:mi:ss'),'突然好想喝綠茶',200,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000003',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('3'), 6, '0'),
null,'桃園市中壢區中北路200號',TO_TIMESTAMP('2018-6-30 19:32:51','YYYY-MM-DD hh24:mi:ss'),'想喝紅茶的快來',400,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000004',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('4'), 6, '0'),
'桃園市平鎮區民族路二段175號','桃園市平鎮區民族路二段175號',TO_TIMESTAMP('2018-6-30 14:12:43','YYYY-MM-DD hh24:mi:ss'),'買越多越便宜快來啊',500,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000005',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('5'), 6, '0'),
null,'中央大學正門口',TO_TIMESTAMP('2018-6-30 10:36:23','YYYY-MM-DD hh24:mi:ss'),'優惠就差你一個~',150,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000004',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('6'), 6, '0'),
'桃園市中壢區民族路二段138號','桃園市中壢區民族路二段138號',TO_TIMESTAMP('2018-6-30 20:55:33','YYYY-MM-DD hh24:mi:ss'),'隨便買買就好',200,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000003',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('7'), 6, '0'),
null,'中央大學',TO_TIMESTAMP('2018-6-30 15:44:10','YYYY-MM-DD hh24:mi:ss'),'想喝喝喝喝喝茶',450,0);

Insert into GROUP_ART (GROU_ID,MEM_ID,ORD_ID,SHIP_LOCAT,SEND_LOCAT,EXP_DATE,ART_NAME,GROU_PRICE,GROU_STATUS) 
values ('GA'||LPAD(to_char(group_art_seq.NEXTVAL), 6, '0'),
'M000001',to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char('8'), 6, '0'),
'桃園市中壢區民族路六段508號','桃園市中壢區民族路六段508號',TO_TIMESTAMP('2018-6-30 17:16:00','YYYY-MM-DD hh24:mi:ss'),'想要便宜進來就對啦',300,0);

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

INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'鮮調果滋味系列',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'戀乳巧滋味系列',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'閒情鮮品茶系列',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'鮮榨水果系列',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'多口感奶茶系列',1);
INSERT INTO PRODUCT_CLASS (PRODUCT_CL_ID,PRODUCT_CL_NAME,PRODUCT_CL_STATUS) values ('PC'||LPAD(to_char(PRODUCT_CLASS_SEQ.NEXTVAL), 6, '0'),'飆涼冰沙系列',1);

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
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '百香綠茶', 50, '洋溢熱帶歡愉的酸甜百香，與茉莉花的清新、綠茶的典雅，交織成一種獨特的想像，讓您體驗酸甜又芬芳的獨特風味。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '南非國寶檸檬茶', 50, '不含咖啡因的國寶茶搭配新鮮檸檬汁，舒緩一天的壓力。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '金桔檸檬', 50, '台灣本土金桔，含豐富維他命A、C，對人體具有抗老化功效，搭配新鮮現榨檸檬原汁，CoCo讓您每日都健康。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '百香雙響炮', 50, '富含維他命C嚴選百香果，去油解膩，搭配高纖椰果及Q彈珍珠，口感層次豐富！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000001', '鮮檸檬綠茶', 50, '嚴選上等綠茶，搭配鮮採檸檬原汁，酸甜對話，滋潤心窩！(檸檬產地：屏東)',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '冬瓜拿鐵', 60, '香醇的冬瓜露，加入高優質鮮純牛乳，風味清香迷人，給您不同的甜蜜滋味！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '芋頭牛奶', 60, '嚴選芋頭，真材實料，手工精心熬煮後，搭配新鮮牛奶，口感獨特，絕對讓您一口就愛上！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '芋頭牛奶西谷米', 60, '嚴選芋頭，真材實料，手工精心熬煮後，搭配新鮮牛奶與QQ西谷米，口感獨特，是您不可不嚐的幸福滋味！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '紅茶拿鐵', 60, '嚴選優質紅茶，加入高優質鮮純牛乳，帶您體驗濃醇的高尚品味。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '珍珠紅茶拿鐵', 60, 'QQ彈力口感珍珠，完美比例搭配香濃鮮奶與紅茶，結合出絕佳的好滋味！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '南非國寶拿鐵', 60, '與黃金、鑽石並稱南非三寶的南非國寶茶，搭配濃郁鮮奶，香甜無咖啡因，喜愛嚐鮮的你，一定要試試看！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000002', '巧克力珍珠奶茶', 60, '香甜濃醇的巧克力，搭配上QQ珍珠的絕佳口感，濃情甜蜜的好滋味留存在心頭',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '芒果養樂多', 70, '首選高優質芒果，果香濃郁、風味迷人與經典養樂多交織酸甜好滋味',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '南非國寶茶', 70, '與黃金、鑽石並稱南非三寶的南非國寶茶，不含咖啡因，讓您舒緩一天的壓力。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '檸檬冬瓜露', 70, '新鮮檸檬汁結合古早味冬瓜露，酸甜滋味深深回味。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '蕎麥冬瓜露', 70, '蕎麥米香混合純古法提煉的冬瓜露，忘不了的香甜順口。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '台灣包種茶', 70, '特選台灣北部山區優質包種茶，茶湯金黃，純和清澈不苦澀，幽雅回甘的好滋味。', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '綠茶養樂多', 70, '香醇綠茶與經典養樂多交織清新好滋味，健康又清爽的組合~', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000003', '檸檬養樂多', 70, '新鮮檸檬汁與經典養樂多交織酸甜好滋味，健康又清爽的組合。(檸檬產地：屏東)', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '鮮榨蘋果養樂多', 80, '現榨新鮮蘋果肉與經典養樂多交織酸甜好滋味，蘋果多酚讓您氣色滿分。', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '檸檬霸', 80, '整顆新鮮檸檬，原味入茶採浸→洗→浸→洗→切五道檸檬安心清洗工序，堅持讓您喝得安心，享受夏日酸甜滋味！', 1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '鮮葡萄柚冰茶', 80, '新鮮葡萄柚汁配上香醇綠茶，果香在前，清香茶味回甘，豐富您的味蕾。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '鮮香橙冰茶', 80, '新鮮香橙果汁搭配醇品紅茶，天然果香給您一天好心情',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '鮮橙柚冰茶', 80, '香橙果汁搭上葡萄柚汁，滿滿水果營養，喜愛新鮮調味的您一定要試試看。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000004', '鮮榨蘋果百香', 80, '現榨新鮮蘋果肉與鮮百香果汁融合的果香風味，值得您細細品嚐。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000005', '珍珠奶茶', 80, 'Q彈富口感的珍珠，搭配香濃奶茶，邀您體驗濃醇好滋味！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000005', '奶茶三兄弟', 80, '香濃奶茶搭配香滑布丁、Q彈珍珠及鮮嫩仙草，一次就有三種口感，多種享受。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000005', '布丁奶茶', 80, '滑嫩的布丁加入香濃奶茶，隨時帶給您甜蜜的好心情。',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000006', '綠豆沙牛奶', 80, '採用上等綠豆精心手作煉煮，口感綿密，搭配香醇鮮奶，營養又清涼!',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000006', '芒果冰沙', 80, '首選上等芒果，果香濃郁，製成冰沙擁有綿密口感， 炎炎夏日，是清涼的好味道！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000006', '芒果歐蕾', 80, '首選上等芒果，果香濃郁，製成冰沙擁有綿密口感， 搭配香醇鮮奶，炎炎夏日，是清涼的好味道！',1);
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CL_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_DES,PRODUCT_STATUS)
values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 6, '0'),'PC000006', '綠豆沙', 80, '採用上等綠豆精心手作煉煮，口感綿密，沁涼消暑風味，讓您回味無窮！',1);




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
values ('MS'||LPAD(to_char(msg_seq.NEXTVAL), 6, '0'),'M000001','P000001','烏龍茶很好喝',0);

Insert into MSG_BOARD (MSG_BOARD_ID,MEM_ID,PRODUCT_ID,MSG_DES,MSG_STATUS) 
values ('MS'||LPAD(to_char(msg_seq.NEXTVAL), 6, '0'),'M000002','P000004','綠茶拿鐵很好喝',0);



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
values ('I'||LPAD(to_char(ice_seq.NEXTVAL), 6, '0'),'去冰');
Insert into ICE(ICE_ID,ICE_DES) 
values ('I'||LPAD(to_char(ice_seq.NEXTVAL), 6, '0'),'少冰');
Insert into ICE(ICE_ID,ICE_DES) 
values ('I'||LPAD(to_char(ice_seq.NEXTVAL), 6, '0'),'正常冰');



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
values ('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),'無糖');
Insert into SWEET(SWEET_ID,SWEET_DES) 
values ('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),'少糖');
Insert into SWEET(SWEET_ID,SWEET_DES) 
values ('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),'半糖');
Insert into SWEET(SWEET_ID,SWEET_DES) 
values ('S'||LPAD(to_char(sweet_seq.NEXTVAL), 6, '0'),'正常糖');


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

INSERT INTO ABOUT_US VALUES ('A'||LPAD(to_char(about_us_seq.NEXTVAL), 6, '0'),'1997年的淡水小鎮，是清涼一夏最初的開端。在這塊人文與茶香兼具的土地上，清涼一夏一步一腳印、堅守品質與服務，啟動了夢想的實業。堅持給顧客最好的，希望好的茶、好的產品能讓更多人品嚐，清涼一夏在1999年跨出淡水地區，開始深入台灣各地，並於2007年進軍大陸市場，更積極籌備拓展世界版圖。秉持著「企業全球化、管理在地化」的經營理念，自台灣拓展至全球，清涼一夏於2015年門市突破2,000間，成為橫跨世界各洲的知名茶飲連鎖品牌。 版圖擴及上海、蘇州、北京、成都、廣州、杭州、武漢及香港等各大城市， 並拓展至美國紐約、洛杉磯、加拿大多倫多、溫哥華、韓國、南非、泰國、印尼、越南、菲律賓以及英國倫敦與澳洲等世界各地， 成功建立「奠基傳統、勇於創新、放眼國際」的跨國企業。 展望未來，清涼一夏將繼續擴大美食領域的經營，透過新事業體的拓展與茶飲事業的成長茁壯，成為立足全世界的美食連鎖品牌。', '12:00p.m. ~ 20:00p.m.', '02-12345678', '320桃園市中壢區中大路300號');



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

Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '雪鹽檸夏‧激涼消暑新上市！', '天氣熱想要快速消暑解渴嗎？快來杯雪鹽檸夏，清爽解膩，帶你體驗海邊激涼快感！嚴選在地台灣檸檬汁，搭配嘉義純淨日曬鹽，佐上屏東黃檸檬手工切片，體驗 多層次的風味，彷彿身處於海邊享受涼快感，內涵維他命 C讓你自動開啟美顏功能唷 。', to_date('2018-03-20 13:23:44','yyyy-mm-dd hh24:mi:ss'), 1);
Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '戳戳紅柚果茶系列‧新鮮Juicy登場', '戳戳紅柚果茶系列嚴選以色列紅寶石柚果汁，加入整杯葡萄柚果片，含有維生素Ｃ、膳食纖維、果酸...等，清爽不膩口，健康又美味 ，搭配台灣包種茶，戳一戳、攪一攪，戳出滿滿的鮮果好滋味！', to_date('2018-03-26 13:23:44','yyyy-mm-dd hh24:mi:ss'), 1);
Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '椪柑鮮橙果茶‧新鮮限量上市', 'CoCo推出「限量」新品，採用台灣台中的椪柑汁，放入整顆柳橙切片，搭配台灣包種茶，味道簡單而有層次，果香與酸甜融合的恰到好處，用最單純的原味食材，保留最純粹的鮮果滋味。數量有限賣完為止喔！', to_date('2018-04-02 13:23:44','yyyy-mm-dd hh24:mi:ss'), 1);
Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '星空輕檸x啡橙勿擾 夢幻上市', '天氣熱想要快速消暑解渴嗎？快來杯雪鹽檸夏，清爽解膩，帶你體驗海邊激涼快感！', to_date('2018-06-01 13:23:44','yyyy-mm-dd hh24:mi:ss'), 0);
Insert into ANNOUNCEMENT(ANN_ID,ANN_TITLE,ANN_DES,ANN_DATE,ANN_STATUS) values ('AI'||LPAD(to_char(announcement_seq.NEXTVAL), 6, '0'), '紅豆之戀', '使用來自喜馬拉雅山的赤豆品種，在澳洲種植採收。經高壓煮製、蜜糖封裝，精心調配3:2的豆泥口感，融合在飲品之中。每一口都感受得到紅豆的鬆軟甜香。', to_date('2018-06-08 13:23:44','yyyy-mm-dd hh24:mi:ss'), 0);



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
values ('D'||LPAD(to_char(discount_seq.NEXTVAL), 6, '0'),'今天買10杯百打九折,滿500再打九折',10,0.9,500,0.9);


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
