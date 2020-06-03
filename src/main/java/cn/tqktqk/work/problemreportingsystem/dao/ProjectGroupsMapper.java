package cn.tqktqk.work.problemreportingsystem.dao;

import cn.tqktqk.work.problemreportingsystem.model.entity.ProjectGroupsEntity;
import cn.tqktqk.work.problemreportingsystem.model.result.ProjectGroupResult;
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
public interface ProjectGroupsMapper extends BaseMapper<ProjectGroupsEntity> {

    List<ProjectGroupResult> selectProjectGroupResults(@Param("groupIds") List<Long> groupIds);

    List<ProjectGroupResult> selectProjectGroupResultsByUserId(Long userId);
}
