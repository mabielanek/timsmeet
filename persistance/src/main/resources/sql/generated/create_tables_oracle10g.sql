
    drop table tm_address cascade constraints;

    drop table tm_company cascade constraints;

    drop table tm_contact cascade constraints;

    drop table tm_email cascade constraints;

    drop table tm_employee cascade constraints;

    drop table tm_employees_service_type cascade constraints;

    drop table tm_field cascade constraints;

    drop table tm_field_template cascade constraints;

    drop table tm_field_template_choice cascade constraints;

    drop table tm_phone cascade constraints;

    drop table tm_serv_loc_serv_type cascade constraints;

    drop table tm_service_location cascade constraints;

    drop table tm_service_type cascade constraints;

    drop table tm_vacation cascade constraints;

    drop table tm_web_url cascade constraints;

    drop table tm_working_hour cascade constraints;

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
        id number(19,0) not null,
        address1 varchar2(255 char),
        address2 varchar2(255 char),
        city varchar2(255 char),
        comment_text varchar2(1024 char),
        country varchar2(255 char),
        display_index number(10,0) not null,
        last_modification_id number(19,0),
        state varchar2(40 char),
        status varchar2(1 char) not null,
        zip_code varchar2(15 char),
        primary key (id)
    );

    create table tm_company (
        id number(19,0) not null,
        last_modification_id number(19,0),
        name varchar2(255 char) not null,
        status varchar2(1 char) not null,
        address_id number(19,0),
        contact_id number(19,0),
        primary key (id)
    );

    create table tm_contact (
        id number(19,0) not null,
        last_modification_id number(19,0),
        status varchar2(1 char) not null,
        primary key (id)
    );

    create table tm_email (
        id number(19,0) not null,
        comment_text varchar2(1024 char),
        display_index number(10,0) not null,
        email_address varchar2(255 char) not null,
        last_modification_id number(19,0),
        status varchar2(1 char) not null,
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table tm_employee (
        id number(19,0) not null,
        email_preferences varchar2(1 char) not null,
        first_name varchar2(100 char),
        last_modification_id number(19,0),
        last_name varchar2(100 char),
        status varchar2(1 char) not null,
        title varchar2(100 char),
        company_id number(19,0),
        contact_id number(19,0),
        primary key (id)
    );

    create table tm_employees_service_type (
        id number(19,0) not null,
        last_modification_id number(19,0),
        employee_id number(19,0) not null,
        service_type_id number(19,0) not null,
        primary key (id)
    );

    create table tm_field (
        owner_type varchar2(1 char) not null,
        id number(19,0) not null,
        last_modification_id number(19,0),
        value_date_time timestamp,
        value_decimal number(19,2),
        value_integer number(19,0),
        value_string varchar2(1024 char),
        field_template_id number(19,0),
        company_id number(19,0),
        employee_id number(19,0),
        service_location_id number(19,0),
        primary key (id)
    );

    create table tm_field_template (
        id number(19,0) not null,
        display_type varchar2(1 char) not null,
        last_modification_id number(19,0),
        name varchar2(255 char) not null,
        status varchar2(1 char) not null,
        value_type varchar2(1 char) not null,
        primary key (id)
    );

    create table tm_field_template_choice (
        id number(19,0) not null,
        last_modification_id number(19,0),
        value_date_time timestamp,
        value_decimal number(19,2),
        value_integer number(19,0),
        value_string varchar2(1024 char),
        field_template_id number(19,0),
        primary key (id)
    );

    create table tm_phone (
        id number(19,0) not null,
        comment_text varchar2(1024 char),
        display_index number(10,0) not null,
        last_modification_id number(19,0),
        number_type varchar2(1 char) not null,
        phone varchar2(15 char),
        phone_ext varchar2(15 char),
        status varchar2(1 char) not null,
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table tm_serv_loc_serv_type (
        id number(19,0) not null,
        last_modification_id number(19,0),
        service_location_id number(19,0) not null,
        service_type_id number(19,0) not null,
        primary key (id)
    );

    create table tm_service_location (
        id number(19,0) not null,
        description varchar2(1024 char),
        last_modification_id number(19,0),
        name varchar2(255 char) not null,
        status varchar2(1 char) not null,
        address_id number(19,0),
        company_id number(19,0),
        contact_id number(19,0),
        primary key (id)
    );

    create table tm_service_type (
        id number(19,0) not null,
        allow_ext_recurring varchar2(1 char) not null,
        allow_ext_regular varchar2(1 char) not null,
        allow_ext_reschedule varchar2(1 char) not null,
        allow_int_recurring varchar2(1 char) not null,
        allow_int_regular varchar2(1 char) not null,
        allow_int_reschedule varchar2(1 char) not null,
        appointment_interval number(10,0) not null,
        concurrency_limit number(10,0),
        default_service_duration number(10,0),
        description varchar2(1024 char),
        ext_res_cap_unit varchar2(1 char),
        ext_allow_delete varchar2(1 char),
        ext_auto_cancell varchar2(1 char),
        ext_auto_confirm varchar2(1 char),
        ext_cancel_dedl number(10,0),
        ext_cancel_dedl_unit varchar2(1 char),
        ext_reschedule_dedl number(10,0),
        ext_reschedule_dedl_unit varchar2(1 char),
        ext_res_cap number(10,0),
        ext_res_disclosure varchar2(1 char),
        int_res_cap_unit varchar2(1 char),
        int_allow_delete varchar2(1 char),
        int_auto_cancell varchar2(1 char),
        int_auto_confirm varchar2(1 char),
        int_cancel_dedl number(10,0),
        int_cancel_dedl_unit varchar2(1 char),
        int_reschedule_dedl number(10,0),
        int_reschedule_dedl_unit varchar2(1 char),
        int_res_cap number(10,0),
        int_res_disclosure varchar2(1 char),
        kind varchar2(1 char) not null,
        last_modification_id number(19,0),
        lead_in number(10,0),
        lead_out number(10,0),
        name varchar2(255 char) not null,
        notify_client varchar2(1 char),
        personel_selection varchar2(1 char),
        schedule_mode varchar2(1 char) not null,
        status varchar2(1 char) not null,
        valid_from timestamp,
        valid_to timestamp,
        company_id number(19,0),
        primary key (id)
    );

    create table tm_vacation (
        owner_type varchar2(1 char) not null,
        id number(19,0) not null,
        end_day timestamp not null,
        last_modification_id number(19,0),
        start_day timestamp not null,
        company_id number(19,0),
        employee_id number(19,0),
        service_location_id number(19,0),
        primary key (id)
    );

    create table tm_web_url (
        id number(19,0) not null,
        comment_text varchar2(1024 char),
        display_index number(10,0) not null,
        last_modification_id number(19,0),
        status varchar2(1 char) not null,
        web_url_address varchar2(255 char) not null,
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table tm_working_hour (
        owner_type varchar2(1 char) not null,
        id number(19,0) not null,
        end_time timestamp not null,
        last_modification_id number(19,0),
        start_time timestamp not null,
        week_day varchar2(15 char) not null,
        company_id number(19,0),
        employee_id number(19,0),
        serv_loc_serv_type_id number(19,0),
        service_location_id number(19,0),
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

    create sequence seq_tm_address_id start with 1 increment by 1;

    create sequence seq_tm_company_id start with 100 increment by 1;

    create sequence seq_tm_contact_id start with 1 increment by 1;

    create sequence seq_tm_email_id start with 1 increment by 1;

    create sequence seq_tm_empl_serv_type_id start with 1 increment by 1;

    create sequence seq_tm_employee_id start with 1 increment by 1;

    create sequence seq_tm_field_id start with 1 increment by 1;

    create sequence seq_tm_field_templ_id start with 1 increment by 1;

    create sequence seq_tm_fld_templ_choice_id start with 1 increment by 1;

    create sequence seq_tm_phone_id start with 1 increment by 1;

    create sequence seq_tm_serv_loc_serv_type_id start with 1 increment by 1;

    create sequence seq_tm_service_location_id start with 1 increment by 1;

    create sequence seq_tm_service_type_id start with 1 increment by 1;

    create sequence seq_tm_vacation_id start with 1 increment by 1;

    create sequence seq_tm_web_url_id start with 1 increment by 1;

    create sequence seq_tm_working_hours_id start with 1 increment by 1;
