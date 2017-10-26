
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