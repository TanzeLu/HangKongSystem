use HangKongDB

--��Ʊ��ͼ
select Bno as 'Ʊ��',B_seat_no as '��λ��',F_no as '�����'
from Book

select U_no as '����'
from Users

select Flight_day as '��������',Start_Time as '����ʱ��',Arrive_Time as '�ִ�ʱ��',Start_City as '��������',Arrive_City as '�ִ����'
from Flight

--��Ʊ��ͼ
select Air_type as '�������',EcoClass_seat as '���ò�Ʊ��',FirClass_seat as 'ͷ�Ȳ�Ʊ��',BusClass_seat as '�����Ʊ��'
from Airplane

select Eco_Class_pri as '���òռ۸�',Fir_Class_pri as 'ͷ�Ȳռ۸�',Bus_Class_pri as '����ռ۸�',Ctype as '��������'
from Cabin

select Firm_na as '���չ�˾'
from AirFirm