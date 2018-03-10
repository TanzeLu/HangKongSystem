use HangKongDB

if exists(select* from sysobjects where name='Book')       --��Ʊ��Ϣ
drop table Book

if exists(select* from sysobjects where name='PWF')       --¼�뺽����Ϣ��
drop table PWF

if exists(select* from sysobjects where name='PF')       --¼�빫˾�ͻ���Ϣ��
drop table PF

if exists(select* from sysobjects where name='Users')       --�ͻ���Ϣ��
drop table Users

if exists(select* from sysobjects where name='Plane')       --�ͻ���Ϣ��
drop table Plane

if exists(select* from sysobjects where name='Header')       --��˾�����˱�
drop table Header

if exists(select* from sysobjects where name='Firm')       --��˾��Ϣ��
drop table Firm

if exists(select* from sysobjects where name='Way')       --������Ϣ��
drop table Way



--�ͻ���Ϣ��
create table Users(
U_no char(24) constraint Users_prim primary key,                   --�˺�
U_na varchar(8)  not NULL,                                         --�ͻ�����
U_pw varchar(20)   not NULL,                                       --����
U_id char(32)  not NULL unique,                                    --���֤����,����,�����ظ�
U_sex char(2) check (U_sex in('��','Ů')) not NULL,                --�Ա𣬶���ֻ���л�Ů
U_age int not NULL,                                                --����
U_tel varchar(22) not NULL,                                        --��ϵ�绰
U_add varchar(50) not NULL,                                        --�ͻ���ַ
)

insert into Users values('123','123','123','123','��','19','123','123')


--�ͻ���Ϣ��
create table Plane(
P_no char(6) constraint Plane_prim primary key,                --�ͻ����
P_type varchar(6)  not NULL,                                   --�ͻ�����
P_na varchar(12) not NULL,                                     --�ͻ�����
P_firseat int not NULL,                                        --ͷ�Ȳ���λ��   >=0

P_ecoseat int not NULL,                                        --���ò���λ��   >=0

flight varchar(20) not NULL,
airfirm varchar(20) not NULL,
start 	varchar(20) not NULL,
destination varchar(20) not NULL,
leaveTime varchar(20) not NULL,
arriveTime varchar(20) not NULL,
childFare float not NULL,
adultFare float not NULL,
discount1 float not NULL,
discount2 float not NULL,
seat int not NULL,
week1 varchar(20) not NULL,
remark int not NULL,					  

)
insert into Plane values('001','�����','��0034',30,30,'AC001','���캽��','����','�ɶ�',1022,1639,678,996,23,56,45,'1',2),
						('002','�����','��0034',30,30,'AC002','���ݺ���','�Ϻ�','����',1022,1639,678,996,23,56,45,'2',4),
						('003','�����','��0034',30,30,'AC003','�Ĵ�����','�׶�','�ɶ�',1022,1639,678,996,23,56,45,'3',7),
						('004','�����','��0034',30,30,'AC004','���캽��','����','����',1022,1639,678,996,23,56,45,'4',4)


--��˾��Ϣ��
create table Firm( 
F_no char(6)  constraint Firm_prim primary key,          --��˾���
F_name varchar(10)  not NULL,                            --��˾����
F_add varchar(8) not NULL,                               --��˾��ַ
F_tel char(22) not NULL                                 --��ϵ�绰
)



--��˾������
create table Header(
H_no char(6) constraint Header_prim primary key,                                 --�����˱��
H_na char(8) not NULL,                                                           --����������
H_id char(32)  not NULL unique,                                    --���֤����,����,�����ظ�
H_sex char(2) check (H_sex in('��','Ů')) not NULL,                --�Ա𣬶���ֻ���л�Ů
H_age int not NULL,                                                --����
F_no char(6) constraint Header_Firm foreign key references Firm(F_no) not NULL,  --��˾���
)




--������Ϣ��
create table Way(
W_no char(6) constraint Flight_prim primary key,          --���߱��
W_Start_City varchar(10) not NULL,                        --��������
W_Arrive_City varchar(10) not NULL,                       --�ִ����
W_stayear varchar(8) not NULL,                            --������
W_stamon varchar(4) not NULL,                             --������
W_staday varchar(4) not NULL,                             --������
W_Start_Time varchar(20) not NULL,                        --����ʱ��
W_ariyear varchar(8) not NULL,                            --�ִ���
W_arimon varchar(4) not NULL,                             --�ִ���
W_ariday varchar(4) not NULL,                             --�ִ���
W_Arrive_Time varchar(20) not NULL,                       --�ִ�ʱ��
W_firprice int not NULL,                                       --ͷ�Ȳ�Ʊ��     >0
W_ecoprice int not NULL,                                       --���ò�Ʊ��     >0
) 



--��˾~����~�ͻ� ��   ��Ʊ��Ϣ��
create table Book(
U_no char(24) constraint Users_Fore foreign key references Users(U_no) unique not NULL,       --�˺�     Ψһ
F_no char(6) constraint Flight_Fore foreign key references Firm(F_no) not NULL,               --��˾���
P_no char(6) constraint Plane_Fore foreign key references Plane(P_no) not NULL,               --�ͻ����
W_no char(6) constraint Way_Fore foreign key references Way(W_no) not NULL,                   --���߱��
constraint Users_Firm_Plane_Way primary key(U_no,F_no,P_no,W_no),
seat_no int not NULL,                                                                --��λ��   //�Զ�����  <=Ʊ��
seat_na char(6) check (seat_na in('���ò�','ͷ�Ȳ�')) not NULL,                      --��λ����
B_firseat int check (B_firseat=0 or B_firseat=1) not NULL,                           --ͷ�Ȳճ�����λ��   ����0��1
B_ecoseat int check (B_ecoseat=0 or B_ecoseat=1) not NULL,                           --���òճ�����λ��   ����0��1
)


--¼�빫˾�ͻ���Ϣ��
create table PF(
F_no char(6) constraint Flight_PF foreign key references Firm(F_no),        --��˾���
P_no char(6) constraint Plane_PF foreign key references Plane(P_no),        --�ͻ����
constraint Firm_Plane primary key(F_no,P_no)
)



--¼�뺽����Ϣ��
create table PWF(
F_no char(6) constraint Flight_PWF foreign key references Firm(F_no),        --��˾���
P_no char(6) constraint Plane_PWF foreign key references Plane(P_no),        --�ͻ����
W_no char(6) constraint Way_PWF foreign key references Way(W_no),            --���߱��
constraint Firm_Plane_Way primary key(F_no,P_no,W_no)
)