package cn.tqktqk.work.problemreportingsystem.dao;

import cn.tqktqk.work.problemreportingsystem.model.entity.ProjectMembersEntity;
import cn.tqktqk.work.problemreportingsystem.model.vo.MemberStoreVo;
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
public interface ProjectMembersMapper extends BaseMapper<ProjectMembersEntity> {

    List<Long> selectGroupIdByUserId(@Param("userId") Long userId);

    ProjectMembersEntity selectByMemberStoreVo(@Param("vo") MemberStoreVo vo);

    List<Long> selectUserIdsByGroupId(@Param("groupId") Long groupId);
}
