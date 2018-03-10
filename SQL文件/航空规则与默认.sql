use HangKongDB
GO
create rule pri_rule
as @pri>0
GO
exec sp_bindrule 'pri_rule' , 'Plane.P_price'    --Ʊ��

GO
create rule age_rule
as @age>=1 and @age<=110
GO
exec sp_bindrule 'age_rule' , 'Users.U_age'      --����
exec sp_bindrule 'age_rule' , 'Header.H_age'      --����

GO
create rule seat_rule
as @seat>=0
GO
exec sp_bindrule 'seat_rule' , 'Plane.P_seat'    --Ʊ��

GO
create default sex_defa
as '��'
GO
exec sp_bindefault sex_defa,'Users.U_sex'        --�Ա�Ĭ��
exec sp_bindefault sex_defa,'Header.H_sex'        --�Ա�Ĭ��