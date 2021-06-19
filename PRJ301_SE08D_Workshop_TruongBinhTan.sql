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
	orderId INT NOT NULL PRIMARY KEY CLUSTERED,
	customerId varchar(20) FOREIGN KEY REFERENCES tbl_User(userId),
	orderDate date,
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

INSERT INTO tbl_Mobile VALUES ('1', '128 GB, nano‑SIM and eSIM, 6.7 inches, 1284 x 2778 pixels', 1500.0, 'iPhone 12 128GB', 2020, 15, 0);
INSERT INTO tbl_Mobile VALUES ('2', '256 GB, nano‑SIM and eSIM, 6.7 inches, 1284 x 2778 pixels', 2000.0, 'iPhone 12 256GB', 2020, 10, 0);
INSERT INTO tbl_Mobile VALUES ('3', '512 GB, nano‑SIM and eSIM, 6.7 inches, 1284 x 2778 pixels', 2100.0, 'iPhone 12 512GB', 2020, 13, 0);
INSERT INTO tbl_Mobile VALUES ('4', '128 GB, nano‑SIM and eSIM, 6.1 inches, 828 x 1792 pixels', 900.0, 'iPhone 11 128GB', 2019, 20, 0);
INSERT INTO tbl_Mobile VALUES ('5', '256 GB, nano‑SIM and eSIM, 6.1 inches, 828 x 1792 pixels', 1000.0, 'iPhone 11 256GB', 2019, 17, 0);