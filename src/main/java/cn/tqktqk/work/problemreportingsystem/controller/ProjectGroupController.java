package cn.tqktqk.work.problemreportingsystem.controller;

import cn.tqktqk.work.problemreportingsystem.model.result.ProjectGroupResult;
import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.GroupVo;
import cn.tqktqk.work.problemreportingsystem.model.vo.MemberStoreVo;
import cn.tqktqk.work.problemreportingsystem.service.ProjectGroupService;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import cn.tqktqk.work.problemreportingsystem.utils.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @Classname ProjectGroupController
 * @Description TODO
 * @Date 2020/6/3 6:23 下午
 * @Author 麦麦麦阁
 */
@RestController
@RequestMapping("/project/group")
@Api("项目组接口")
public class ProjectGroupController {

    @Autowired
    private ProjectGroupService projectGroupService;

    @GetMapping("/{userid}")
    @ApiOperation("获取我参与的项目组")
    public ResponsePage<ProjectGroupResult> index(@PathVariable("userid")Long userId){
        return projectGroupService.index(userId);
    }

    @GetMapping("/leader/{userid}")
    @ApiOperation("获取我领导的项目组")
    public ResponsePage<ProjectGroupResult> indexForLeader(@PathVariable("userid")Long userId){
        return projectGroupService.indexForLeader(userId);
    }

    @PostMapping
    @ApiOperation("新增项目组")
    public Response store(@RequestBody GroupVo vo){
        return projectGroupService.store(vo);
    }

    @DeleteMapping("/{groupId}")
    @ApiOperation("删除项目组")
    public Response deleted(@PathVariable("groupId")Long groupId,@ApiParam("用户id") @RequestParam("userId")Long userId){
        return projectGroupService.deleted(groupId,userId);
    }

    @GetMapping("/member/{groupId}")
    @ApiOperation("查看某个项目组的所有成员")
    public ResponsePage<UserInfoResult> memberInfos(@PathVariable("groupId")Long groupId){
        return projectGroupService.memberInfos(groupId);
    }

    @PostMapping("/member")
    @ApiOperation("添加成员到某个小组")
    public Response storeMember(@RequestBody MemberStoreVo vo){
        return projectGroupService.storeMember(vo);
    }
}
