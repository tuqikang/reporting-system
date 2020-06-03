package cn.tqktqk.work.problemreportingsystem.dao;

import cn.tqktqk.work.problemreportingsystem.model.entity.UsersEntity;
import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import cn.tqktqk.work.problemreportingsystem.model.result.UserSimpleResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2020-06-03
 */
public interface UsersMapper extends BaseMapper<UsersEntity> {

    UserInfoResult selectUserInfoByUserId(@Param("userId") Long userId);

    int updateNickname(@Param("userId") Long userId, @Param("nickname") String nickname);

    int updatePassword(@Param("userId") Long userId, @Param("password") String password);

    List<UserInfoResult> selectUserInfoResultsByIds(@Param("userIds") List<Long> userIds);

    List<UserSimpleResult> selectUserSimpleResultsByDepId(@Param("department") Long department);
}
