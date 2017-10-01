-- 系统模块
-- 管理员表 system_admin

-- 用户模块
-- 用户信息表 user_info
-- 用户资金账户表 user_finance_account
-- 用户资金操作表 user_finance_log
-- 资金操作类型 finance_operation

-- 游戏模块
-- 玩家指令表 game_play_cmd
-- 玩家指令结果表 game_play_result

-- 平台模块
-- 用户信息表
CREATE TABLE user_info
(
  id          BIGINT
    PRIMARY KEY,
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
  id      BIGINT PRIMARY KEY,
  balance DECIMAL NOT NULL DEFAULT 0.0,
  locking DECIMAL NOT NULL DEFAULT 0.0,
  version INT DEFAULT 1,
  create_time VARCHAR(30),
  update_time VARCHAR(30)
);

-- admin info信息
CREATE TABLE admin_info (
 id          BIGINT
    PRIMARY KEY,
  admin_name   VARCHAR(50),
  admin_code   VARCHAR(50),
  password     VARCHAR(50),
  mobile_num  VARCHAR(20),
  is_enable   INT DEFAULT 1,
  update_time VARCHAR(30),
  create_time VARCHAR(30)

);