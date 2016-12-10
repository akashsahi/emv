drop table if exists BRAND cascade;
drop table if exists CATEGORY cascade;
drop table if exists DOCUMENTS cascade;
drop table if exists USERS cascade;
drop table if exists FEEDBACK cascade;

drop sequence if exists BRAND_SEQ;
drop sequence if exists CATEGORY_SEQ;
drop sequence if exists DOCUMENTS_SEQ;
drop sequence if exists USERS_SEQ;
drop sequence if exists FEEDBACK_SEQ;

create sequence BRAND_SEQ start 1 increment 1;
create sequence CATEGORY_SEQ start 1 increment 1;
create sequence DOCUMENTS_SEQ start 1 increment 1;
create sequence USERS_SEQ start 1 increment 1;
create sequence FEEDBACK_SEQ start 1 increment 1;


 create table BRAND (
        BRAND_ID int8 not null,
        BRAND_DESCRIPTION varchar(255),
        BRAND_NAME varchar(255),
        STATUS int4 not null,
        CATEGORY_ID int8,
        CREATED_BY int8 not null,
        CREATED_DATE timestamp,
        UPDATED_BY int8,
        UPDATED_DATE timestamp,
        VERSION int4 not null,
        primary key (BRAND_ID)
    );
    
   create table CATEGORY (
        CATEGORY_ID int8 not null,
        CATEGORY_DESCRIPTION varchar(255),
        CATEGORY_NAME varchar(255),
        STATUS int4 not null,
        CREATED_BY int8 not null,
        CREATED_DATE timestamp,
        UPDATED_BY int8,
        UPDATED_DATE timestamp,
        VERSION int4 not null,
        primary key (CATEGORY_ID)
    );
    
    create table DOCUMENTS (
        DOCUMENT_ID int8 not null,
        DOCUMENT_NAME varchar(255),
        DOCUMENT_DESCRIPTION varchar(255),
        START_DATE timestamp,
        END_DATE timestamp,
        CATEGORY_ID int8,
        STATUS int4 not null,
        CREATED_BY int8 not null,
        CREATED_DATE timestamp,
        UPDATED_BY int8,
        UPDATED_DATE timestamp,
        VERSION int4 not null,
        primary key (DOCUMENT_ID)
    );
     
     create table USERS (
        USER_ID int8 not null,
        EMAIL varchar(255),
        FIRST_NAME varchar(255),
        LAST_NAME varchar(255),
        STATUS int4 not null,
        USER_NAME varchar(255),
        BRAND_ID int8,
        CREATED_BY int8 not null,
        CREATED_DATE timestamp,
        UPDATED_BY int8,
        UPDATED_DATE timestamp,
        VERSION int4 not null,
        primary key (USER_ID)
    );
    
    create table FEEDBACK (
        FEEDBACK_ID int8 not null,
        FEEDBACK varchar(4000),
        STATUS int4 not null,
        CREATED_BY int8 not null,
        CREATED_DATE timestamp,
        UPDATED_BY int8,
        UPDATED_DATE timestamp,
        VERSION int4 not null,
        primary key (FEEDBACK_ID)
    );
    
    ALTER TABLE BRAND
    ADD CONSTRAINT UQ_BRAND_NAME UNIQUE (BRAND_NAME);
    ALTER TABLE BRAND 
        add constraint FK_BRAND_CATEGORY_ID
        foreign key (CATEGORY_ID) 
        references CATEGORY;
   ALTER TABLE DOCUMENTS
    ADD CONSTRAINT UQ_DOCUMENTS_NAME UNIQUE (DOCUMENTS_NAME);
    ALTER TABLE DOCUMENTS 
        add constraint FK_DOCUMENTS_CATEGORY_ID 
        foreign key (CATEGORY_ID) 
        references CATEGORY;
     ALTER TABLE USERS 
        add constraint FK_BRAND_BRAND_ID 
        foreign key (BRAND_ID) 
        references BRAND;