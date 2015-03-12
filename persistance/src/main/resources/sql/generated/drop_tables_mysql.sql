
    alter table tm_company 
        drop 
        foreign key company_address_fk;

    alter table tm_company 
        drop 
        foreign key company_contact_fk;

    alter table tm_email 
        drop 
        foreign key FK_f2qt2qvke84yj56byeuc8jl4b;

    alter table tm_employee 
        drop 
        foreign key employee_company_fk;

    alter table tm_employee 
        drop 
        foreign key employee_contact_fk;

    alter table tm_employees_service_type 
        drop 
        foreign key est_employee_fk;

    alter table tm_employees_service_type 
        drop 
        foreign key est_service_fk;

    alter table tm_field 
        drop 
        foreign key field_field_templ_fk;

    alter table tm_field 
        drop 
        foreign key field_company_fk;

    alter table tm_field 
        drop 
        foreign key field_employee_fk;

    alter table tm_field 
        drop 
        foreign key field_serv_loc_fk;

    alter table tm_field_template_choice 
        drop 
        foreign key choice_field_templ_fk;

    alter table tm_phone 
        drop 
        foreign key FK_a1ldrexcl7dgf5d3qow8yc2uw;

    alter table tm_serv_loc_serv_type 
        drop 
        foreign key slst_serv_location_fk;

    alter table tm_serv_loc_serv_type 
        drop 
        foreign key slst_serv_type_fk;

    alter table tm_service_location 
        drop 
        foreign key serv_loc_address_fk;

    alter table tm_service_location 
        drop 
        foreign key serv_loc_company_fk;

    alter table tm_service_location 
        drop 
        foreign key serv_loc_contact_fk;

    alter table tm_service_type 
        drop 
        foreign key serv_type_company_fk;

    alter table tm_vacation 
        drop 
        foreign key vacation_company_fk;

    alter table tm_vacation 
        drop 
        foreign key vacation_employee_fk;

    alter table tm_vacation 
        drop 
        foreign key vacation_serv_loc_fk;

    alter table tm_web_url 
        drop 
        foreign key FK_c753namiojgiudseut7frk533;

    alter table tm_working_hour 
        drop 
        foreign key wrk_hour_company_fk;

    alter table tm_working_hour 
        drop 
        foreign key wrk_hour_employee_fk;

    alter table tm_working_hour 
        drop 
        foreign key wrk_hour_slst_fk;

    alter table tm_working_hour 
        drop 
        foreign key wrk_hour_serv_loc_fk;

    drop table if exists tm_address;

    drop table if exists tm_company;

    drop table if exists tm_contact;

    drop table if exists tm_email;

    drop table if exists tm_employee;

    drop table if exists tm_employees_service_type;

    drop table if exists tm_field;

    drop table if exists tm_field_template;

    drop table if exists tm_field_template_choice;

    drop table if exists tm_phone;

    drop table if exists tm_serv_loc_serv_type;

    drop table if exists tm_service_location;

    drop table if exists tm_service_type;

    drop table if exists tm_vacation;

    drop table if exists tm_web_url;

    drop table if exists tm_working_hour;

    drop table if exists seq_tm_address_id;

    drop table if exists seq_tm_company_id;

    drop table if exists seq_tm_contact_id;

    drop table if exists seq_tm_email_id;

    drop table if exists seq_tm_empl_serv_type_id;

    drop table if exists seq_tm_employee_id;

    drop table if exists seq_tm_field_id;

    drop table if exists seq_tm_field_templ_id;

    drop table if exists seq_tm_fld_templ_choice_id;

    drop table if exists seq_tm_phone_id;

    drop table if exists seq_tm_serv_loc_serv_type_id;

    drop table if exists seq_tm_service_location_id;

    drop table if exists seq_tm_service_type_id;

    drop table if exists seq_tm_vacation_id;

    drop table if exists seq_tm_web_url_id;

    drop table if exists seq_tm_working_hours_id;
