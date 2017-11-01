package digitalgame.dao;

import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByPage(@Param("whereCond") String whereCond);

    int updateByPrimaryKeySelective(UserInfo record);
    UserInfo  selectByPrimaryKey(Integer id);

    List<UserAccountVo> selectUserAccountByPage(@Param("whereCond") String whereCond);

    UserInfo selectByNickName(@Param("nickName") String nickName);
}