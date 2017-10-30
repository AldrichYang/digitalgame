package digitalgame.dao;

import digitalgame.model.po.BetInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BetInfoMapper {

    /**
     * 批量新增投注内容
     * @param list
     * @return
     */
    int addBatch(List<BetInfo> list);

}