package cn.tqktqk.work.problemreportingsystem.service;

import cn.tqktqk.work.problemreportingsystem.dao.ProjectGroupsMapper;
import cn.tqktqk.work.problemreportingsystem.dao.ProjectMembersMapper;
import cn.tqktqk.work.problemreportingsystem.dao.UsersMapper;
import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.entity.ProjectGroupsEntity;
import cn.tqktqk.work.problemreportingsystem.model.entity.ProjectMembersEntity;
import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import cn.tqktqk.work.problemreportingsystem.model.result.ProjectGroupResult;
import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.GroupVo;
import cn.tqktqk.work.problemreportingsystem.model.vo.MemberStoreVo;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import cn.tqktqk.work.problemreportingsystem.utils.ResponsePage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @Classname ProjectGroupService
 * @Description TODO
 * @Date 2020/6/3 6:27 下午
 * @Author 麦麦麦阁
 */
@Service
public class ProjectGroupService {

    @Autowired
    private ProjectGroupsMapper projectGroupsMapper;

    @Autowired
    private ProjectMembersMapper projectMembersMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Transactional(rollbackFor = Exception.class)
    public Response store(GroupVo vo) {
        //判断是否项目重名
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name",vo.getName());
        if (projectGroupsMapper.selectOne(wrapper)!=null) {
            throw new ServerException(ResponseEnum.GROUP_IS_EXISTS);
        }
        //插入项目组
        ProjectGroupsEntity projectGroup = new ProjectGroupsEntity();
        projectGroup.setLeaderId(vo.getUserId());
        projectGroup.setName(vo.getName());
        projectGroupsMapper.insert(projectGroup);
        //将创建人插入到项目组成员中
        ProjectMembersEntity projectMember = new ProjectMembersEntity();
        projectMember.setUserId(vo.getUserId());
        projectMember.setGroupId(projectGroup.getId());
        projectMembersMapper.insert(projectMember);
        return Response.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Response deleted(Long groupId, Long userId) {
        ProjectGroupsEntity projectGroupsEntity = projectGroupsMapper.selectById(groupId);
        if (projectGroupsEntity==null){
            throw new ServerException(ResponseEnum.GROUP_NOT_EXISTS);
        }
        if (!projectGroupsEntity.getLeaderId().equals(userId)){
            throw new ServerException(ResponseEnum.CUSTOM,"该项目组不属于你，无法删除");
        }
        //删除项目组
        projectGroupsMapper.deleteById(groupId);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", groupId);
        //删除项目组对应的成员
        projectMembersMapper.delete(wrapper);
        return Response.success();
    }

    public ResponsePage<ProjectGroupResult> index(Long userId) {
        //首先获得所有项目组id
        List<Long> groupIds = projectMembersMapper.selectGroupIdByUserId(userId);
        if (groupIds==null||groupIds.isEmpty()){
            return ResponsePage.success();
        }
        List<ProjectGroupResult> results = projectGroupsMapper.selectProjectGroupResults(groupIds);
        return ResponsePage.success(results,results.size(),1L);
    }

    public ResponsePage<ProjectGroupResult> indexForLeader(Long userId) {
        List<ProjectGroupResult> results = projectGroupsMapper.selectProjectGroupResultsByUserId(userId);
        return ResponsePage.success(results,results.size(),1L);
    }

    public Response storeMember(MemberStoreVo vo) {
        //判断是否是自己的项目
        ProjectGroupsEntity projectGroupsEntity = projectGroupsMapper.selectById(vo.getGroupId());
        if (projectGroupsEntity==null){
            throw new ServerException(ResponseEnum.GROUP_NOT_EXISTS);
        }
        if (!projectGroupsEntity.getLeaderId().equals(vo.getUserId())){
            throw new ServerException(ResponseEnum.CUSTOM,"该项目组不属于你，无法删除");
        }
        //判断是否有该成员
        ProjectMembersEntity projectMembers = projectMembersMapper.selectByMemberStoreVo(vo);
        if (projectMembers!=null){
            throw new ServerException(ResponseEnum.CUSTOM,"该成员以及存在于该项目组");
        }
        //插入成员
        projectMembers = new ProjectMembersEntity();
        projectMembers.setGroupId(vo.getGroupId());
        projectMembers.setUserId(vo.getTargetId());
        projectMembersMapper.insert(projectMembers);
        return Response.success();
    }

    public ResponsePage<UserInfoResult> memberInfos(Long groupId) {
        if (projectGroupsMapper.selectById(groupId)==null) {
            throw new ServerException(ResponseEnum.GROUP_NOT_EXISTS);
        }
        //拿到该项目组所有成员id
        List<Long> userIds = projectMembersMapper.selectUserIdsByGroupId(groupId);
        //获取对应信息
        List<UserInfoResult> results = usersMapper.selectUserInfoResultsByIds(userIds);
        return ResponsePage.success(results,results.size(),1L);
    }
}
