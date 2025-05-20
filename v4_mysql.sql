-- Tạo database
CREATE DATABASE IF NOT EXISTS atm_simulator;
USE atm_simulator;

-- Bảng users
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(50) CHARACTER SET utf8mb4,
    email VARCHAR(50) UNIQUE,
    phone_number VARCHAR(15),
    user_password VARCHAR(50) CHARACTER SET utf8mb4,
    date_of_birth DATE,
    create_at DATETIME
);

-- Thêm dữ liệu vào bảng users
INSERT INTO users (full_name, email, phone_number, user_password, date_of_birth, create_at)  
VALUES  
    ('Nguyen Van A', 'nguyenvana@example.com', '0123456789','password123', '1995-05-10', CURRENT_TIMESTAMP),  
    ('Tran Thi B', 'tranthib@example.com', '0987654321', 'securepass456','1998-08-25', CURRENT_TIMESTAMP),  
    ('Le Van C', 'levanc@example.com', '0911223344','passC789', '2000-12-01', CURRENT_TIMESTAMP),  
    ('Pham D', 'phamd@example.com', '0909090909', 'd_secret_001', '1992-07-14', CURRENT_TIMESTAMP),  
    ('Hoang E', 'hoange@example.com', '0888999777', 'Estrongpass','1997-03-30', CURRENT_TIMESTAMP),
    ('Đỗ Xuân Hậu', '21130349@st.hcmuaf.edu.vn', '0123456789','21130349', '2006-12-01', CURRENT_TIMESTAMP),  
    ('Ma Nguyên Vũ', '23130388@st.hcmuaf.edu.vn', '0123456789', '23130388','2006-12-01', CURRENT_TIMESTAMP),  
    ('Lê Quang Trường', '23130355@st.hcmuaf.edu.vn', '0123456789','23130355', '2006-12-01', CURRENT_TIMESTAMP),  
    ('Nguyên Khắc Trường Đạt', '23130049@st.hcmuaf.edu.vn', '0123456789', '23130049', '2006-12-01', CURRENT_TIMESTAMP),  
    ('Phạm Trần Quốc Tú', '23130363@st.hcmuaf.edu.vn', '0123456789', '23130363', '2006-12-01', CURRENT_TIMESTAMP),  
    ('Nguyễn Bảo Tâm', '23130286@st.hcmuaf.edu.vn', '0123456789', '23130286','2006-12-01', CURRENT_TIMESTAMP);

-- Bảng accounts
CREATE TABLE accounts (
    user_id INT,
    account_number VARCHAR(20) UNIQUE,
    pin_hash VARCHAR(10),
    balance DECIMAL(18,2),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Thêm dữ liệu vào accounts
INSERT INTO accounts (user_id, account_number, pin_hash, balance)
VALUES 
(1, '1000000001', '098231', 500.00),
(2, '1000000002', '124412', 1000.00),
(3, '1000000003', '124256', 750.50),
(4, '1000000004', '245214', 250.00),
(5, '1000000005', '234521', 0.00),
(6, '1000000006', '123456', 0.00),
(7, '1000000007', '123456', 0.00),
(8, '1000000008', '123456', 0.50),
(9, '1000000009', '123456', 0.00),
(10, '1000000010', '123456', 0.00),
(11, '1000000011', '123456', 0.00);

-- Bảng transactions
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20),
    transaction_type VARCHAR(10),
    date_create DATETIME,
    description TEXT CHARACTER SET utf8mb4,
    CONSTRAINT chk_transaction_type CHECK (transaction_type IN ('withdraw', 'deposit', 'transfer')),
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

-- Thêm dữ liệu vào transactions
INSERT INTO transactions (account_number, transaction_type, date_create, description)
VALUES 
('1000000001', 'deposit', CURRENT_TIMESTAMP, 'Nạp 100.0000 VND vào tài khoản'),
('1000000002', 'withdraw', CURRENT_TIMESTAMP, 'Rút tiền 100.000 VND'),
('1000000003', 'transfer', CURRENT_TIMESTAMP, 'Chuyển khoản 100.000 VND vào tài khoản số 1000000003'),
('1000000004', 'deposit', CURRENT_TIMESTAMP, 'Nạp 100.0000 VND vào tài khoản'),
('1000000005', 'withdraw', CURRENT_TIMESTAMP, 'Rút tiền 100.000 VND');

-- Bảng atm
CREATE TABLE atm (
    atm_id INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(18,2),
    atm_status VARCHAR(10),
    CONSTRAINT chk_atm_status CHECK (atm_status IN ('Active', 'Inactive', 'Maintance'))
);

-- Thêm dữ liệu vào ATM
INSERT INTO atm (amount, atm_status)
VALUES (99999999, 'Active');
