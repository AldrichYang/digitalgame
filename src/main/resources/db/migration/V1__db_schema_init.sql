-- 用户信息表
CREATE TABLE user_info
(
  ID INTEGER PRIMARY KEY ,
  user_name   VARCHAR(50),
  mobile_num  VARCHAR(20),
  nick_name   VARCHAR(50),
  is_enable   INT DEFAULT 1,
  update_time VARCHAR(30),
  create_time VARCHAR(30)
) ;

-- 用户账户信息
CREATE TABLE user_finance_account
(
  id      INTEGER PRIMARY KEY ,
  balance DECIMAL NOT NULL DEFAULT 0.0,
  locking DECIMAL NOT NULL DEFAULT 0.0,
  version INT DEFAULT 1,
  create_time VARCHAR(30),
  update_time VARCHAR(30)
);

-- admin info信息
CREATE TABLE admin_info (
  id          INTEGER  PRIMARY KEY ,
  admin_name   VARCHAR(50),
  admin_code   VARCHAR(50),
  password     VARCHAR(50),
  mobile_num  VARCHAR(20),
  is_enable   INT DEFAULT 1,
  update_time VARCHAR(30),
  create_time VARCHAR(30)

)