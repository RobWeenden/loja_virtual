select constraint_name
from information_schema.constraint_column_usage 
where table_name  = 'tb_usuario_role_acesso'
and column_name = 'role_acesso_id'
and constraint_name <> 'unique_acesso_usuario' 

alter table tb_usuario_role_acesso drop constraint "uk_7ce0jqk2sh64vdemu5erj0tai"