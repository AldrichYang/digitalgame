-- 用户信息表
CREATE TABLE user_info
(
  id          BIGINT
    PRIMARY KEY,
  user_name   VARCHAR(50),
  mobile_num  VARCHAR(20),
  is_enable   INT DEFAULT 1,
  update_time VARCHAR(30),
  create_time VARCHAR(30)
);

CREATE TABLE user_finance_account
(
  id      BIGINT PRIMARY KEY,
  balance DECIMAL NOT NULL DEFAULT 0.0,
  locking DECIMAL NOT NULL DEFAULT 0.0,
  version INT DEFAULT 1,
  create_time VARCHAR(30),
  update_time VARCHAR(30)
)