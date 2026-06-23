-- 1. 일반 유저 데이터 삽입 (비밀번호: user123!! 의 BCrypt 암호화 값)
INSERT INTO member (id, membercode, name, password, birth, phonenum, homenum, address, address1, address2, email, snsagr, emailagr, carnum, parkuse)
VALUES ('user123', 'M001', '일반유저', 'user123!!', '1994-01-01', '010-1234-5678', '02-123-4567', '서울시', '강남구', '테헤란로', 'user@example.com', true, true, '12가3456', NOW());

-- 2. 관리자 데이터 삽입 (비밀번호: manager123!! 의 BCrypt 암호화 값)
INSERT INTO member (id, membercode, name, password, birth, phonenum, homenum, address, address1, address2, email, snsagr, emailagr, carnum, parkuse)
VALUES ('manager123', 'M002', '관리자', 'manager123!!', '1987-01-01', '010-9876-5432', '02-987-6543', '서울시', '송파구', '올림픽로', 'manager@example.com', true, true, '34나5678', NOW());


-- 3. 유저 권한 매핑 (숫자 인덱스 유지)
INSERT INTO member_role_list (member_id, role_list) VALUES ('user123', 0);
INSERT INTO member_role_list (member_id, role_list) VALUES ('manager123', 1);