package cn.tqktqk.work.problemreportingsystem.dao;

import cn.tqktqk.work.problemreportingsystem.model.entity.UserDepartmentEntity;
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
public interface UserDepartmentMapper extends BaseMapper<UserDepartmentEntity> {

    int updateByUserId(@Param("userId") Long userId, @Param("departmentsId") Long departmentsId);
}
