package wcl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wcl.pojo.Subscribe;
import wcl.pojo.SubscribeExample;

@Mapper
public interface SubscribeMapper {
    long countByExample(SubscribeExample example);

    int deleteByExample(SubscribeExample example);

    int deleteByPrimaryKey(Integer subscribeId);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    List<Subscribe> selectByExample(SubscribeExample example);

    Subscribe selectByPrimaryKey(Integer subscribeId);

    int updateByExampleSelective(@Param("record") Subscribe record, @Param("example") SubscribeExample example);

    int updateByExample(@Param("record") Subscribe record, @Param("example") SubscribeExample example);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);
}