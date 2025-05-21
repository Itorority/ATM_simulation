-- Chạy dùng dưới  đầu tiên --------------------------------
create database atm_simulator;

-- chạy dòng nayd tiếp theo --------------------------------------
use atm_simulator;

-- bôi đen từ dòng này đến hết r chạy -------------------------------
create table users (
    user_id INT IDENTITY(1,1)   ,
    full_name nvarchar(50) ,
    email varchar(50) unique,
    phone_number varchar(15) ,
    user_password nvarchar(50),
    date_of_birth DATE ,
    create_at datetime 

)

-- add more 1 attibutes to users table 

INSERT INTO users (full_name, email, phone_number,user_password, date_of_birth, create_at)  
VALUES  
    ('Nguyen Van A', 'nguyenvana@example.com', '0123456789','password123', '1995-05-10', GETDATE() ),  
    ('Tran Thi B', 'tranthib@example.com', '0987654321', 'securepass456','1998-08-25', GETDATE() ),  
    ('Le Van C', 'levanc@example.com', '0911223344','passC789', '2000-12-01', GETDATE()),  
    ('Pham D', 'phamd@example.com', '0909090909', 'd_secret_001', '1992-07-14', GETDATE()),  
    ('Hoang E', 'hoange@example.com', '0888999777', 'Estrongpass','1997-03-30', GETDATE());


INSERT INTO users (full_name, email, phone_number,user_password, date_of_birth, create_at)  
VALUES  
    ('Đỗ Xuân Hậu', '21130349@st.hcmuaf.edu.vn', '0123456789','21130349', '2006-12-01', GETDATE() ),  
    ('Ma Nguyên Vũ', '23130388@st.hcmuaf.edu.vn', '0123456789', '23130388','2006-12-01', GETDATE() ),  
    ('Lê Quang Trường', '23130355@st.hcmuaf.edu.vn', '0123456789','23130355', '2006-12-01', GETDATE()),  
    ('Nguyên Khắc Trường Đạt', '23130049@st.hcmuaf.edu.vn', '0123456789', '23130049', '2006-12-01', GETDATE()),  
     ('Phạm Trần Quốc Tú', '23130363@st.hcmuaf.edu.vn', '0123456789', '23130363', '2006-12-01', GETDATE()),  
    ('Nguyễn Bảo Tâm', '23130286@st.hcmuaf.edu.vn', '0123456789', '23130286','2006-12-01', GETDATE());

select * from users ;



-- create table account table 
create table accounts (
    user_id int  ,
    account_number varchar(20) UNIQUE ,
    pin_hash varchar(10),
    balance DECIMAL(18,2)   -- min value -9999999999999999.99 to max value9999999999999999.99
)

-- Giả sử bảng users đã có các user_id tương ứng
INSERT INTO accounts (user_id, account_number, pin_hash, balance)
VALUES 
(1, '1000000001', '098231', 500.00),  -- User 1 có số dư 500
(2, '1000000002', '124412', 1000.00), -- User 2 có số dư 1000
(3, '1000000003', '124256', 750.50),  -- User 3 có số dư 750.50
(4, '1000000004', '245214', 250.00),  -- User 4 có số dư 250
(5, '1000000005', '234521', 0.00);    -- User 5 có số dư 0


INSERT INTO accounts (user_id, account_number, pin_hash, balance)
VALUES 
(6, '1000000006', '123456', 0.00),  -- User 1 có số dư 500
(7, '1000000007', '123456', 0.00), -- User 2 có số dư 1000
(8, '1000000008', '123456', 0.50),  -- User 3 có số dư 750.50
(9, '1000000009', '123456', 0.00),  -- User 4 có số dư 250
(10, '1000000010', '123456', 0.00),    -- User 5 có số dư 0
(11, '1000000011', '123456', 0.00);    -- User 5 có số dư 0


select * from accounts;
select * from users ;

-- create transactions tables
create table transactions (
    transaction_id  int IDENTITY(1,1)  ,
    account_number varchar(20) UNIQUE ,
    transaction_type VARCHAR(10) CHECK (transaction_type IN ('withdraw', 'deposit', 'transfer')),
    date_create datetime ,
    description nvarchar(max)
)

-- insert data 
INSERT INTO transactions (account_number, transaction_type, date_create, description)
VALUES 
('1000000001', 'deposit', GETDATE(), N'Nạp 100.0000 VND vào tài khoản'),
('1000000002', 'withdraw', GETDATE(), N'Rút tiền 100.000 VND'),
('1000000003', 'transfer', GETDATE(), N'Chuyển khoản 100.000 VND vào tài khoản số 1000000003'),
('1000000004', 'deposit', GETDATE(), N'Nạp 100.0000 VND vào tài khoản'),
('1000000005', 'withdraw', GETDATE(), N'Rút tiền 100.000 VND');

select * from transactions ;
-- create atm table 
create table atm (
    amount DECIMAL(18,2) ,
    atm_id INT IDENTITY(1,1)    ,
    atm_status  VARCHAR(10) CHECK (atm_status IN ('Active', 'Inactive', 'Maintance'))
)

insert into atm (amount  ,atm_status) values (99999999 , 'Active');

select * from atm ;