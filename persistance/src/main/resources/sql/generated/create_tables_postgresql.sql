
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

    drop table if exists tm_address cascade;

    drop table if exists tm_company cascade;

    drop table if exists tm_contact cascade;

    drop table if exists tm_email cascade;

    drop table if exists tm_employee cascade;

    drop table if exists tm_employees_service_type cascade;

    drop table if exists tm_field cascade;

    drop table if exists tm_field_template cascade;

    drop table if exists tm_field_template_choice cascade;

    drop table if exists tm_phone cascade;

    drop table if exists tm_serv_loc_serv_type cascade;

    drop table if exists tm_service_location cascade;

    drop table if exists tm_service_type cascade;

    drop table if exists tm_vacation cascade;

    drop table if exists tm_web_url cascade;

    drop table if exists tm_working_hour cascade;

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

    create table tm_address (
        id int8 not null,
        address1 varchar(255),
        address2 varchar(255),
        city varchar(255),
        comment_text varchar(1024),
        country varchar(255),
        display_index int4 not null,
        last_modification_id int8,
        state varchar(40),
        status varchar(1) not null,
        zip_code varchar(15),
        primary key (id)
    );

    create table tm_company (
        id int8 not null,
        last_modification_id int8,
        name varchar(255) not null,
        status varchar(1) not null,
        address_id int8,
        contact_id int8,
        primary key (id)
    );

    create table tm_contact (
        id int8 not null,
        last_modification_id int8,
        status varchar(1) not null,
        primary key (id)
    );

    create table tm_email (
        id int8 not null,
        comment_text varchar(1024),
        display_index int4 not null,
        email_address varchar(255) not null,
        last_modification_id int8,
        status varchar(1) not null,
        contact_id int8 not null,
        primary key (id)
    );

    create table tm_employee (
        id int8 not null,
        email_preferences varchar(1) not null,
        first_name varchar(100),
        last_modification_id int8,
        last_name varchar(100),
        status varchar(1) not null,
        title varchar(100),
        company_id int8,
        contact_id int8,
        primary key (id)
    );

    create table tm_employees_service_type (
        id int8 not null,
        last_modification_id int8,
        employee_id int8 not null,
        service_type_id int8 not null,
        primary key (id)
    );

    create table tm_field (
        owner_type varchar(1) not null,
        id int8 not null,
        last_modification_id int8,
        value_date_time timestamp,
        value_decimal numeric(19, 2),
        value_integer int8,
        value_string varchar(1024),
        field_template_id int8,
        company_id int8,
        employee_id int8,
        service_location_id int8,
        primary key (id)
    );

    create table tm_field_template (
        id int8 not null,
        display_type varchar(1) not null,
        last_modification_id int8,
        name varchar(255) not null,
        status varchar(1) not null,
        value_type varchar(1) not null,
        primary key (id)
    );

    create table tm_field_template_choice (
        id int8 not null,
        last_modification_id int8,
        value_date_time timestamp,
        value_decimal numeric(19, 2),
        value_integer int8,
        value_string varchar(1024),
        field_template_id int8,
        primary key (id)
    );

    create table tm_phone (
        id int8 not null,
        comment_text varchar(1024),
        display_index int4 not null,
        last_modification_id int8,
        number_type varchar(1) not null,
        phone varchar(15),
        phone_ext varchar(15),
        status varchar(1) not null,
        contact_id int8 not null,
        primary key (id)
    );

    create table tm_serv_loc_serv_type (
        id int8 not null,
        last_modification_id int8,
        service_location_id int8 not null,
        service_type_id int8 not null,
        primary key (id)
    );

    create table tm_service_location (
        id int8 not null,
        description varchar(1024),
        last_modification_id int8,
        name varchar(255) not null,
        status varchar(1) not null,
        address_id int8,
        company_id int8,
        contact_id int8,
        primary key (id)
    );

    create table tm_service_type (
        id int8 not null,
        allow_ext_recurring varchar(1) not null,
        allow_ext_regular varchar(1) not null,
        allow_ext_reschedule varchar(1) not null,
        allow_int_recurring varchar(1) not null,
        allow_int_regular varchar(1) not null,
        allow_int_reschedule varchar(1) not null,
        appointment_interval int4 not null,
        concurrency_limit int4,
        default_service_duration int4,
        description varchar(1024),
        ext_res_cap_unit varchar(1),
        ext_allow_delete varchar(1),
        ext_auto_cancell varchar(1),
        ext_auto_confirm varchar(1),
        ext_cancel_dedl int4,
        ext_cancel_dedl_unit varchar(1),
        ext_reschedule_dedl int4,
        ext_reschedule_dedl_unit varchar(1),
        ext_res_cap int4,
        ext_res_disclosure varchar(1),
        int_res_cap_unit varchar(1),
        int_allow_delete varchar(1),
        int_auto_cancell varchar(1),
        int_auto_confirm varchar(1),
        int_cancel_dedl int4,
        int_cancel_dedl_unit varchar(1),
        int_reschedule_dedl int4,
        int_reschedule_dedl_unit varchar(1),
        int_res_cap int4,
        int_res_disclosure varchar(1),
        kind varchar(1) not null,
        last_modification_id int8,
        lead_in int4,
        lead_out int4,
        name varchar(255) not null,
        notify_client varchar(1),
        personel_selection varchar(1),
        schedule_mode varchar(1) not null,
        status varchar(1) not null,
        valid_from timestamp,
        valid_to timestamp,
        company_id int8,
        primary key (id)
    );

    create table tm_vacation (
        owner_type varchar(1) not null,
        id int8 not null,
        end_day timestamp not null,
        last_modification_id int8,
        start_day timestamp not null,
        company_id int8,
        employee_id int8,
        service_location_id int8,
        primary key (id)
    );

    create table tm_web_url (
        id int8 not null,
        comment_text varchar(1024),
        display_index int4 not null,
        last_modification_id int8,
        status varchar(1) not null,
        web_url_address varchar(255) not null,
        contact_id int8 not null,
        primary key (id)
    );

    create table tm_working_hour (
        owner_type varchar(1) not null,
        id int8 not null,
        end_time timestamp not null,
        last_modification_id int8,
        start_time timestamp not null,
        week_day varchar(15) not null,
        company_id int8,
        employee_id int8,
        serv_loc_serv_type_id int8,
        service_location_id int8,
        primary key (id)
    );

    create index idx_company_name on tm_company (name);

    create index idx_company_address_fk on tm_company (address_id);

    create index idx_company_contact_fk on tm_company (contact_id);

    create index idx_contact_email_fk on tm_email (contact_id);

    create index idx_employee_contact_fk on tm_employee (contact_id);

    create index idx_employee_company_fk on tm_employee (company_id);

    create index idx_est_service_fk on tm_employees_service_type (service_type_id);

    create index idx_est_employee_fk on tm_employees_service_type (employee_id);

    create index idx_field_field_templ_fk on tm_field (field_template_id);

    create index idx_field_company_fk on tm_field (company_id);

    create index idx_field_employee_fk on tm_field (employee_id);

    create index idx_field_serv_loc_fk on tm_field (service_location_id);

    create index idx_choice_field_templ_fk on tm_field_template_choice (field_template_id);

    create index idx_contact_phone_fk on tm_phone (contact_id);

    create index idx_slst_serv_type_fk on tm_serv_loc_serv_type (service_type_id);

    create index idx_slst_serv_location_fk on tm_serv_loc_serv_type (service_location_id);

    create index idx_serv_loc_comopany_fk on tm_service_location (company_id);

    create index idx_serv_loc_address_fk on tm_service_location (address_id);

    create index idx_serv_loc_contact_fk on tm_service_location (contact_id);

    create index idx_serv_type_comopany_fk on tm_service_type (company_id);

    create index idx_vacation_company_fk on tm_vacation (company_id);

    create index idx_vacation_employee_fk on tm_vacation (employee_id);

    create index idx_vacation_serv_loc_fk on tm_vacation (service_location_id);

    create index idx_contact_web_url_fk on tm_web_url (contact_id);

    create index idx_wrk_hour_company_fk on tm_working_hour (company_id);

    create index idx_wrk_hour_employee_fk on tm_working_hour (employee_id);

    create index idx_wrk_hour_slst_fk on tm_working_hour (serv_loc_serv_type_id);

    create index idx_wrk_hour_serv_loc_fk on tm_working_hour (service_location_id);

    alter table tm_company 
        add constraint company_address_fk 
        foreign key (address_id) 
        references tm_address;

    alter table tm_company 
        add constraint company_contact_fk 
        foreign key (contact_id) 
        references tm_contact;

    alter table tm_email 
        add constraint FK_f2qt2qvke84yj56byeuc8jl4b 
        foreign key (contact_id) 
        references tm_contact;

    alter table tm_employee 
        add constraint employee_company_fk 
        foreign key (company_id) 
        references tm_company;

    alter table tm_employee 
        add constraint employee_contact_fk 
        foreign key (contact_id) 
        references tm_contact;

    alter table tm_employees_service_type 
        add constraint est_employee_fk 
        foreign key (employee_id) 
        references tm_employee;

    alter table tm_employees_service_type 
        add constraint est_service_fk 
        foreign key (service_type_id) 
        references tm_service_type;

    alter table tm_field 
        add constraint field_field_templ_fk 
        foreign key (field_template_id) 
        references tm_field_template;

    alter table tm_field 
        add constraint field_company_fk 
        foreign key (company_id) 
        references tm_company;

    alter table tm_field 
        add constraint field_employee_fk 
        foreign key (employee_id) 
        references tm_employee;

    alter table tm_field 
        add constraint field_serv_loc_fk 
        foreign key (service_location_id) 
        references tm_service_location;

    alter table tm_field_template_choice 
        add constraint choice_field_templ_fk 
        foreign key (field_template_id) 
        references tm_field_template;

    alter table tm_phone 
        add constraint FK_a1ldrexcl7dgf5d3qow8yc2uw 
        foreign key (contact_id) 
        references tm_contact;

    alter table tm_serv_loc_serv_type 
        add constraint slst_serv_location_fk 
        foreign key (service_location_id) 
        references tm_service_location;

    alter table tm_serv_loc_serv_type 
        add constraint slst_serv_type_fk 
        foreign key (service_type_id) 
        references tm_service_type;

    alter table tm_service_location 
        add constraint serv_loc_address_fk 
        foreign key (address_id) 
        references tm_address;

    alter table tm_service_location 
        add constraint serv_loc_company_fk 
        foreign key (company_id) 
        references tm_company;

    alter table tm_service_location 
        add constraint serv_loc_contact_fk 
        foreign key (contact_id) 
        references tm_contact;

    alter table tm_service_type 
        add constraint serv_type_company_fk 
        foreign key (company_id) 
        references tm_company;

    alter table tm_vacation 
        add constraint vacation_company_fk 
        foreign key (company_id) 
        references tm_company;

    alter table tm_vacation 
        add constraint vacation_employee_fk 
        foreign key (employee_id) 
        references tm_employee;

    alter table tm_vacation 
        add constraint vacation_serv_loc_fk 
        foreign key (service_location_id) 
        references tm_service_location;

    alter table tm_web_url 
        add constraint FK_c753namiojgiudseut7frk533 
        foreign key (contact_id) 
        references tm_contact;

    alter table tm_working_hour 
        add constraint wrk_hour_company_fk 
        foreign key (company_id) 
        references tm_company;

    alter table tm_working_hour 
        add constraint wrk_hour_employee_fk 
        foreign key (employee_id) 
        references tm_employee;

    alter table tm_working_hour 
        add constraint wrk_hour_slst_fk 
        foreign key (serv_loc_serv_type_id) 
        references tm_serv_loc_serv_type;

    alter table tm_working_hour 
        add constraint wrk_hour_serv_loc_fk 
        foreign key (service_location_id) 
        references tm_service_location;

    create sequence seq_tm_address_id start 1 increment 1;

    create sequence seq_tm_company_id start 100 increment 1;

    create sequence seq_tm_contact_id start 1 increment 1;

    create sequence seq_tm_email_id start 1 increment 1;

    create sequence seq_tm_empl_serv_type_id start 1 increment 1;

    create sequence seq_tm_employee_id start 1 increment 1;

    create sequence seq_tm_field_id start 1 increment 1;

    create sequence seq_tm_field_templ_id start 1 increment 1;

    create sequence seq_tm_fld_templ_choice_id start 1 increment 1;

    create sequence seq_tm_phone_id start 1 increment 1;

    create sequence seq_tm_serv_loc_serv_type_id start 1 increment 1;

    create sequence seq_tm_service_location_id start 1 increment 1;

    create sequence seq_tm_service_type_id start 1 increment 1;

    create sequence seq_tm_vacation_id start 1 increment 1;

    create sequence seq_tm_web_url_id start 1 increment 1;

    create sequence seq_tm_working_hours_id start 1 increment 1;
