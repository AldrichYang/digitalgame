
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