CREATE TABLE user_info
(
  ID          INTEGER PRIMARY KEY,
  user_name   VARCHAR(50),
  mobile_num  VARCHAR(20),
  nick_name   VARCHAR(50),
  is_enable   INT DEFAULT 1,
  update_time VARCHAR(30),
  create_time VARCHAR(30)
);

-- 用户账户信息
CREATE TABLE user_finance_account
(
  id          INTEGER PRIMARY KEY,
  user_id     INTEGER,
  balance     DECIMAL NOT NULL DEFAULT 0.0,
  locking     DECIMAL NOT NULL DEFAULT 0.0,
  version     INT              DEFAULT 1,
  create_time VARCHAR(30),
  update_time VARCHAR(30)
);

-- admin info信息
CREATE TABLE admin_info (
  id          INTEGER PRIMARY KEY,
  admin_name  VARCHAR(50),
  admin_code  VARCHAR(50),
  password    VARCHAR(50),
  mobile_num  VARCHAR(20),
  is_enable   INT DEFAULT 1,
  update_time VARCHAR(30),
  create_time VARCHAR(30)

);

CREATE TABLE odds_info
(
  id          INTEGER PRIMARY KEY  NOT NULL,
  odds_name   VARCHAR(50) NOT NULL,
  odds_number NUMERIC ,
  create_time VARCHAR(30),
  update_time VARCHAR(30)
);

-- 用户账户信息历史表
CREATE TABLE user_finance_account_log
(
  id      INTEGER PRIMARY KEY ,
  ufc_id INTEGER ,
  balance DECIMAL NOT NULL DEFAULT 0.0,
  money DECIMAL NOT NULL DEFAULT 0.0,
  periods INTEGER DEFAULT 0,
  oper_type INT DEFAULT 1,
  create_time VARCHAR(30),
  update_time VARCHAR(30)
);

-- 用户账户信息历史表
CREATE TABLE system_finance_account_report
(
  id      INTEGER PRIMARY KEY ,
  report_date VARCHAR(30),
  betting_money DECIMAL NOT NULL DEFAULT 0.0,
  winning_money DECIMAL NOT NULL DEFAULT 0.0,
  platform_money DECIMAL NOT NULL DEFAULT 0.0,
  create_time VARCHAR(30),
  update_time VARCHAR(30)
);

-- 中奖结果保存表
CREATE TABLE bet_result
(
    id INTEGER PRIMARY KEY,
    betUser VARCHAR,
    betUserId INT,
    resultNumber DECIMAL,
    betNumber DECIMAL,
    betType VARCHAR,
    betDate VARCHAR,
    resultDate VARCHAR,
    createDate VARCHAR,
    updateDate VARCHAR
);

--投注信息
CREATE TABLE bet_info
(
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  openId int not null,
  betMan VARCHAR NOT NULL,
  betItem varchar not null,
  betMoney DECIMAL not null,
  returnMoney DECIMAL (18,2) not null default 0,
  status integer,
  create_time VARCHAR,
  update_time VARCHAR
);

--开奖信息
create table open_info
(
  id integer primary key AUTOINCREMENT  not null,
  openNO integer not null,
  openNum varchar(10) ,
  openResult varchar(50),
  openTime varchar(50),
  create_time varchar(50),
  update_time varchar(50)
);

