package cn.tqktqk.work.problemreportingsystem.dao;

import cn.tqktqk.work.problemreportingsystem.model.entity.ProjectProblemsEntity;
import cn.tqktqk.work.problemreportingsystem.model.result.ProblemsResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.ProblemVo;
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
public interface ProjectProblemsMapper extends BaseMapper<ProjectProblemsEntity> {

    List<ProblemsResult> selectProblemsResults(@Param("groupId") Long groupId, @Param("level") Integer level, @Param("status") Integer status, @Param("department") Long department);

    int insertVo(@Param("groupId") Long groupId, @Param("vo") ProblemVo vo);
}
