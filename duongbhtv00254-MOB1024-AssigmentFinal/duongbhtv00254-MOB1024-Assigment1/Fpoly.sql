create database Fpoly
use Fpoly
use master
CREATE TABLE STUDENTS (
    MASV NVARCHAR(50) not null PRIMARY KEY,
    Hoten NVARCHAR(50)  null,
    Email VARCHAR(50)  null,
    SoDT VARCHAR(10)  null,
    Gioitinh BIT  null,
    Diachi NVARCHAR(50)  null,
    Hinh NVARCHAR(50) null
);

CREATE TABLE GRADE (
    ID INT not null PRIMARY KEY,
    MASV NVARCHAR(50) FOREIGN KEY REFERENCES STUDENTS(MASV),
    TiengAnh float  null,
    Tinhoc float  null,
    GDTC float  null
);

CREATE TABLE USERS (
    username NVARCHAR(50) not null PRIMARY KEY,
    passwords NVARCHAR(50)  null,
    roles NVARCHAR(50)  null
);
insert into STUDENTS values
('sv01','Bui Hoang Duong','duongkaka@gmail.com','0999999999','1','Binh Duong',null),
('sv02','Le Van Dat','datgg@gmail.com','0888888888','1','TPHCM',null),
('sv03','Nguyen Thi Phung','phungez@gmail.com','0777777777','0','Nghe An',null),
('sv04','Trinh Tran Phuong Tuan','vitinhtu@gmail.com','0555555555','1','Ben Tre',null),
('sv05','Le Duong Bao Lam','Diem@gmail.com','0555555555','0','Dong Nai',null)
INSERT INTO GRADE VALUES
(1, 'sv01', 8.5, 7.5, 9.0),
(2, 'sv02', 6.0, 7.0, 8.0),
(3, 'sv03', 9.0, 8.5, 7.5),
(4, 'sv04', 5.5, 6.5, 7.0),
(5, 'sv05', 7.5, 8.0, 6.5);
insert into USERS values
('admin1','duongit1','GV'),
('admin2','datdz1','CBDT')