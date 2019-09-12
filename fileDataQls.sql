create database qlb_sua;

create table user
(
	id int auto_increment primary key,
    first_name nvarchar(70),
    last_name nvarchar(50),
    username nvarchar(50),
    password varchar(16),
    is_staff tinyint(1),
    email varchar(70),
    phone_number varchar(12),
    address nvarchar(50),
    date_of_birth date,
    gender nvarchar(10)
);

create table company
(
	id int auto_increment primary key,
    name nvarchar(30),
    phone_number varchar(12),
    address nvarchar(50)
    
);
create table product
(
	id int auto_increment primary key,
    name nvarchar(70),
    price double,
    capacity varchar(50),
    product_type nvarchar(50),
    company_id int,
    constraint FK_CompanyId  foreign key(company_id) references company(id)
);

create table worehouse
(
	id int auto_increment primary key,
    amount int,
    product_id int,
    constraint FK_Product foreign key(product_id) references product(id)
);
create table transaction_history
(
	id int auto_increment primary key,
    amount int,
    date_import datetime,
    date_export datetime,
    note text,
    product_id int,
    user_id int,
    constraint FK_Transaction_User foreign key(user_id) references user(id),
    constraint FK_Transaction_Product foreign key(product_id) references product(id)
);

create table bill
(
	id int auto_increment primary key,
    name nvarchar(60),
    address nvarchar(70),
    phone_number varchar(12),
    date_trading datetime,
    note text,
    total_money double
);

create table detail_bill
(
	id int auto_increment primary key,
    amount int,
    total_money double,
    price double,
    bill_id int,
    product_id int,
    constraint FK_DetailBill_Bill foreign key(bill_id) references bill(id),
    constraint FK_DetailBill_Product foreign key(product_id) references product(id)
)



