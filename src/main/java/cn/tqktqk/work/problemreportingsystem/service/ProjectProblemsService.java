package cn.tqktqk.work.problemreportingsystem.service;

import cn.tqktqk.work.problemreportingsystem.dao.DepartmentsMapper;
import cn.tqktqk.work.problemreportingsystem.dao.ProjectMembersMapper;
import cn.tqktqk.work.problemreportingsystem.dao.ProjectProblemsMapper;
import cn.tqktqk.work.problemreportingsystem.dao.UsersMapper;
import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.entity.ProjectMembersEntity;
import cn.tqktqk.work.problemreportingsystem.model.entity.ProjectProblemsEntity;
import cn.tqktqk.work.problemreportingsystem.model.entity.UsersEntity;
import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import cn.tqktqk.work.problemreportingsystem.model.enums.StatusEnum;
import cn.tqktqk.work.problemreportingsystem.model.result.DepartmentResult;
import cn.tqktqk.work.problemreportingsystem.model.result.ProblemsResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.ProblemVo;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import cn.tqktqk.work.problemreportingsystem.utils.ResponsePage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
 * @Classname ProjectProblemsService
 * @Description TODO
 * @Date 2020/6/4 2:37 下午
 * @Author 麦麦麦阁
 */
@Service
public class ProjectProblemsService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private ProjectProblemsMapper projectProblemsMapper;

    @Autowired
    private ProjectMembersMapper projectMembersMapper;

    @Autowired
    private DepartmentsMapper departmentsMapper;


    public ResponsePage<ProblemsResult> list(Long groupId, Long userId, Integer level, Integer status, Long department) {
        //首先判断该用户是否是该项目组的成员
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        wrapper.eq("group_id",groupId);
        ProjectMembersEntity projectMembersEntity = projectMembersMapper.selectOne(wrapper);
        if (projectMembersEntity==null){
            throw new ServerException(ResponseEnum.CUSTOM,"你不属于该项目组，无法查看");
        }
        List<ProblemsResult> results = projectProblemsMapper.selectProblemsResults(groupId,level,status,department);
        //判断是否为空
        if (results.isEmpty()){
            return ResponsePage.success(results,0L,1L);
        }

        //获取这个组的所有成员id,查找他们的名字，转换成Map<userId,name>
        List<Long> userIds = projectMembersMapper.selectUserIdsByGroupId(groupId);
        QueryWrapper userWrapper = new QueryWrapper();
        wrapper.in("id",userIds);
        List<UsersEntity> usersList = usersMapper.selectList(userWrapper);
        Map<Long, String> userIdNicknameMap = usersList.stream().collect(Collectors.toMap(UsersEntity::getId, UsersEntity::getNickname));

        //获得部门列表并且转换成Map<id,name>
        List<DepartmentResult> departmentResults = departmentsMapper.select();
        Map<Long, String> departmentNameMap = departmentResults.stream().collect(Collectors.toMap(DepartmentResult::getId, DepartmentResult::getName));

        //残缺数据填充
        results.stream().forEach(p->{
            p.setUserName(userIdNicknameMap.getOrDefault(p.getUserId(),""));
            p.setDepartmentName(departmentNameMap.getOrDefault(p.getDepartmentId(),""));
            if (p.getFinishId()!=null){
                p.setFinishName(userIdNicknameMap.getOrDefault(p.getFinishId(),""));
            }
        });
        return ResponsePage.success(results,results.size(),1L);
    }

    public Response store(Long groupId, ProblemVo vo) {
        //首先判断该用户是否是该项目组的成员
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",vo.getUserId());
        wrapper.eq("group_id",groupId);
        ProjectMembersEntity projectMembersEntity = projectMembersMapper.selectOne(wrapper);
        if (projectMembersEntity==null){
            throw new ServerException(ResponseEnum.CUSTOM,"你不属于该项目组，无法操作");
        }
        projectProblemsMapper.insertVo(groupId,vo);
        return Response.success();
    }

    public Response deleted(Long problemsId, Long userId) {
        //首先判断是否是自己上报的问题
        ProjectProblemsEntity projectProblem = projectProblemsMapper.selectById(problemsId);
        if (projectProblem==null){
            throw new ServerException(ResponseEnum.CUSTOM,"问题不存在，无法操作");
        }
        if (!projectProblem.getUserId().equals(userId)){
            throw new ServerException(ResponseEnum.CUSTOM,"该问题不是你上报的，无法进行删除");
        }
        projectProblemsMapper.deleteById(problemsId);
        return Response.success();
    }

    public Response fix(Long problemsId, Long userId) {
        //首先判断该用户是否是该项目组的成员
        ProjectProblemsEntity projectProblem = projectProblemsMapper.selectById(problemsId);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        wrapper.eq("group_id",projectProblem.getGroupId());
        ProjectMembersEntity projectMembersEntity = projectMembersMapper.selectOne(wrapper);
        if (projectMembersEntity==null){
            throw new ServerException(ResponseEnum.CUSTOM,"你不属于该项目组，无法操作");
        }
        if (projectProblem.getStatus().equals(StatusEnum.已解决.getStatus())){
            throw new ServerException(ResponseEnum.CUSTOM,"该问题已经处理");
        }
        //解决该问题
        projectProblem.setFinishId(userId);
        projectProblem.setFinishTime(LocalDateTime.now());
        projectProblem.setStatus(StatusEnum.已解决.getStatus());
        //修改操作
        projectProblemsMapper.updateById(projectProblem);
        return Response.success();
    }
}
