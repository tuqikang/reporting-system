package cn.tqktqk.work.problemreportingsystem.controller;

import cn.tqktqk.work.problemreportingsystem.model.result.DepartmentResult;
import cn.tqktqk.work.problemreportingsystem.model.result.UserSimpleResult;
import cn.tqktqk.work.problemreportingsystem.service.DepartmentService;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import cn.tqktqk.work.problemreportingsystem.utils.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
 * @Classname DepartmentManageController
 * @Description TODO
 * @Date 2020/6/3 4:22 下午
 * @Author 麦麦麦阁
 */
@RestController
@Api(value = "部门（方向）管理")
@RequestMapping("/department")
public class DepartmentManageController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @ApiOperation("获取部门列表")
    public ResponsePage<DepartmentResult> select(){
        return departmentService.select();
    }

    @GetMapping("{department}")
    @ApiOperation("获取部门人员列表")
    public ResponsePage<UserSimpleResult> selectForDepartment(@PathVariable("department") Long department){
        return departmentService.selectForDepartment(department);
    }

    @PostMapping
    @ApiModelProperty("插入一个部门")
    public Response store(@ApiParam("部门名称") @RequestParam("name") String name){
        return departmentService.store(name);
    }

    @DeleteMapping("/{id}")
    @ApiModelProperty("删除一个部门")
    public Response deleted(@PathVariable("id") Long id){
        return departmentService.deleted(id);
    }
}
