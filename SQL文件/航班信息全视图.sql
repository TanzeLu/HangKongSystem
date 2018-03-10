use HangKongDB
GO

--��Ʊ��Ϣ��ѯ
create view JP(���߱��,�ͻ�����,��������,�ִ����,������,������,������,����ʱ��,�ִ���,�ִ���,�ִ���,�ִ�ʱ��,ͷ�Ȳ���λ��,ͷ�Ȳ�Ʊ��,���ò���λ��,���ò�Ʊ��,���չ�˾)
as select Way.W_no,
          P_type,
          W_Start_City,
          W_Arrive_City,
          W_stayear,
          W_stamon,
          W_staday,
          W_Start_Time,
          W_ariyear,
          W_arimon,
          W_ariday,
          W_Arrive_Time,
          P_firseat,
          P_firprice,
          P_ecoseat,
          P_ecoprice,
          F_name
from Book,Way,Firm,Plane
where Book.W_no=Way.W_no and 
      Book.F_no=Firm.F_no and
      Book.P_no=Plane.P_no
      
      
GO
--������Ϣ��ѯ
create view DP(���߱��,����,��λ��,������,������,������,����ʱ��,�ִ���,�ִ���,�ִ���,�ִ�ʱ��,��λ����)
as select Way.W_no,
          U_na,
          seat_no,
          W_stayear,
          W_stamon,
          W_staday,
          W_Start_Time,
          W_ariyear,
          W_arimon,
          W_ariday,
          W_Arrive_Time,
          seat_na
from Way,Users,Book
where Way.W_no=Book.W_no and
      Users.U_no=Book.U_no
      
GO
--¼�뺽����Ϣ
create view LRHB(���߱��,�ͻ�����,��������,�ִ����,������,������,������,����ʱ��,�ִ���,�ִ���,�ִ���,�ִ�ʱ��,
                 ���չ�˾,ͷ�Ȳ���λ��,ͷ�Ȳ�Ʊ��,���ò���λ��,���ò�Ʊ��)
as select Way.W_no,
          P_type,
          W_Start_City,
          W_Arrive_City,
          W_stayear,
          W_stamon,
          W_staday,
          W_Start_Time,
          W_ariyear,
          W_arimon,
          W_ariday,
          W_Arrive_Time,
          F_name,
          P_firseat,
          W_firprice,
          P_ecoseat,
          W_ecoprice
from PWF,Way,Firm,Plane
where PWF.W_no=Way.W_no and 
      PWF.F_no=Firm.F_no and
      PWF.P_no=Plane.P_no