package digitalgame.dao;

import digitalgame.model.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByPage(int pageNo, int size);

    int updateByPrimaryKeySelective(UserInfo record);
}