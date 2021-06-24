CREATE DATABASE PRJ301_SE08D_Workshop_TruongBinhTan;
GO

USE PRJ301_SE08D_Workshop_TruongBinhTan;
Go

CREATE TABLE tbl_Mobile (
    mobileId varchar(10) PRIMARY KEY,
    description varchar(250) NOT NULL,
    price float,
	mobileName varchar(20) NOT NULL,
	yearOfProduction int,
	quantity int,
	notSale bit 
);
GO

CREATE TABLE tbl_User (
	userId varchar(20) PRIMARY KEY,
	password int NOT NULL,
	fullName varchar(50) NOT NULL,
	role int
);
GO

CREATE TABLE tbl_Orders (
	orderId INT NOT NULL PRIMARY KEY CLUSTERED,
	customerId varchar(20) FOREIGN KEY REFERENCES tbl_User(userId) ON DELETE CASCADE,
	orderDate date,
	total float
);
GO

CREATE TABLE tbl_OrderDetails (
	orderDetailId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	orderId int FOREIGN KEY REFERENCES tbl_Orders(orderId) ON DELETE CASCADE,
	mobileId varchar(10) FOREIGN KEY REFERENCES tbl_Mobile(mobileId) ON DELETE CASCADE,
	unitPrice float,
	quantity int,
);
GO

INSERT INTO tbl_User (userId, password, fullName, role) VALUES 
('user', 123, 'User', 0),
('staff', 123, 'Staff', 2);
GO

INSERT INTO tbl_Mobile (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale) VALUES 
	('1', '128 GB, nano‑SIM and eSIM, 6.7 inches, 1284 x 2778 pixels', 1500.0, 'iPhone 12 128GB', 2020, 15, 0),
	('2', '256 GB, nano‑SIM and eSIM, 6.7 inches, 1284 x 2778 pixels', 2000.0, 'iPhone 12 256GB', 2020, 10, 0),
	('3', '512 GB, nano‑SIM and eSIM, 6.7 inches, 1284 x 2778 pixels', 2100.0, 'iPhone 12 512GB', 2020, 13, 0),
	('4', '128 GB, nano‑SIM and eSIM, 6.1 inches, 828 x 1792 pixels', 900.0, 'iPhone 11 128GB', 2019, 20, 0),
	('5', '256 GB, nano‑SIM and eSIM, 6.1 inches, 828 x 1792 pixels', 1000.0, 'iPhone 11 256GB', 2019, 17, 0),
	('6', 'Ram 8GB, Snapdragon 850, 5.7 inches', 800.0, 'Xiao Mi 8', 2021, 5, 1),
	('7', 'Ram 12GB, Snapdragon 940, 6.2 inches', 1500.0, 'Xiao Mi Note 8', 2021, 10, 1);
GO