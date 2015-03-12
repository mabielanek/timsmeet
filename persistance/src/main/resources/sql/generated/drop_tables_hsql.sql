
    alter table tm_company 
        drop constraint company_address_fk;

    alter table tm_company 
        drop constraint company_contact_fk;

    alter table tm_email 
        drop constraint FK_f2qt2qvke84yj56byeuc8jl4b;

    alter table tm_employee 
        drop constraint employee_company_fk;

    alter table tm_employee 
        drop constraint employee_contact_fk;

    alter table tm_employees_service_type 
        drop constraint est_employee_fk;

    alter table tm_employees_service_type 
        drop constraint est_service_fk;

    alter table tm_field 
        drop constraint field_field_templ_fk;

    alter table tm_field 
        drop constraint field_company_fk;

    alter table tm_field 
        drop constraint field_employee_fk;

    alter table tm_field 
        drop constraint field_serv_loc_fk;

    alter table tm_field_template_choice 
        drop constraint choice_field_templ_fk;

    alter table tm_phone 
        drop constraint FK_a1ldrexcl7dgf5d3qow8yc2uw;

    alter table tm_serv_loc_serv_type 
        drop constraint slst_serv_location_fk;

    alter table tm_serv_loc_serv_type 
        drop constraint slst_serv_type_fk;

    alter table tm_service_location 
        drop constraint serv_loc_address_fk;

    alter table tm_service_location 
        drop constraint serv_loc_company_fk;

    alter table tm_service_location 
        drop constraint serv_loc_contact_fk;

    alter table tm_service_type 
        drop constraint serv_type_company_fk;

    alter table tm_vacation 
        drop constraint vacation_company_fk;

    alter table tm_vacation 
        drop constraint vacation_employee_fk;

    alter table tm_vacation 
        drop constraint vacation_serv_loc_fk;

    alter table tm_web_url 
        drop constraint FK_c753namiojgiudseut7frk533;

    alter table tm_working_hour 
        drop constraint wrk_hour_company_fk;

    alter table tm_working_hour 
        drop constraint wrk_hour_employee_fk;

    alter table tm_working_hour 
        drop constraint wrk_hour_slst_fk;

    alter table tm_working_hour 
        drop constraint wrk_hour_serv_loc_fk;

    drop table tm_address if exists;

    drop table tm_company if exists;

    drop table tm_contact if exists;

    drop table tm_email if exists;

    drop table tm_employee if exists;

    drop table tm_employees_service_type if exists;

    drop table tm_field if exists;

    drop table tm_field_template if exists;

    drop table tm_field_template_choice if exists;

    drop table tm_phone if exists;

    drop table tm_serv_loc_serv_type if exists;

    drop table tm_service_location if exists;

    drop table tm_service_type if exists;

    drop table tm_vacation if exists;

    drop table tm_web_url if exists;

    drop table tm_working_hour if exists;

    drop sequence seq_tm_address_id;

    drop sequence seq_tm_company_id;

    drop sequence seq_tm_contact_id;

    drop sequence seq_tm_email_id;

    drop sequence seq_tm_empl_serv_type_id;

    drop sequence seq_tm_employee_id;

    drop sequence seq_tm_field_id;

    drop sequence seq_tm_field_templ_id;

    drop sequence seq_tm_fld_templ_choice_id;

    drop sequence seq_tm_phone_id;

    drop sequence seq_tm_serv_loc_serv_type_id;

    drop sequence seq_tm_service_location_id;

    drop sequence seq_tm_service_type_id;

    drop sequence seq_tm_vacation_id;

    drop sequence seq_tm_web_url_id;

    drop sequence seq_tm_working_hours_id;
