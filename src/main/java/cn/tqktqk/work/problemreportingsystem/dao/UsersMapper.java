package cn.tqktqk.work.problemreportingsystem.dao;

import cn.tqktqk.work.problemreportingsystem.model.entity.UsersEntity;
import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
}
