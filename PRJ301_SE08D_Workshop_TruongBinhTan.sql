CREATE DATABASE PRJ301_SE08D_Workshop_TruongBinhTan;
USE PRJ301_SE08D_Workshop_TruongBinhTan;

CREATE TABLE tbl_Mobile (
    mobileId varchar(10) PRIMARY KEY,
    description varchar(250) NOT NULL,
    price float,
	mobileName varchar(20) NOT NULL,
	yearOfProduction int,
	quantity int,
	notSale bit 
);

CREATE TABLE tbl_User (
	userId varchar(20) PRIMARY KEY,
	password int NOT NULL,
	fullName varchar(50) NOT NULL,
	role int
);

CREATE TABLE tbl_Orders (
	orderId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	customerId varchar(20) FOREIGN KEY REFERENCES tbl_User(userId),
	orderDate varchar(20),
	total float
);

CREATE TABLE tbl_OrderDetails (
	orderDetailId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	orderId int FOREIGN KEY REFERENCES tbl_Orders(orderId),
	mobileId varchar(10) FOREIGN KEY REFERENCES tbl_Mobile(mobileId),
	unitPrice float,
	quantity int,
);

INSERT INTO tbl_User VALUES ('user', 123, 'User', 0);
INSERT INTO tbl_User VALUES ('staff', 123, 'Staff', 2);