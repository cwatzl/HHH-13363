create table vendor_address
(
    id            int auto_increment primary key,
    post_code     nvarchar(255),
    city          nvarchar(255),
    street        nvarchar(255),
    street_number nvarchar(255)
);

create table vendor
(
    id         int auto_increment primary key,
    name       varchar(255),
    address_id int,

    foreign key (address_id) references vendor_address (id)
);

create table product
(
    id                              int auto_increment primary key,
    name                            varchar(255),
    vendor_id                       int,
    vendor_relationship_description varchar(255),

    foreign key (vendor_id) references vendor (id)
);
