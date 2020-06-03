package cn.tqktqk.work.problemreportingsystem.controller;

import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.UserUpdateVo;
import cn.tqktqk.work.problemreportingsystem.service.UsersInfoService;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @Classname UsersController
 * @Description TODO
 * @Date 2020/6/3 3:21 下午
 * @Author 麦麦麦阁
 */
@RestController
@Api(value = "用户基本资料接口")
@RequestMapping("/users/info")
public class UsersController {

    @Autowired
    private UsersInfoService usersInfoService;

    @GetMapping("/{id}")
    @ApiOperation("获取个人信息")
    public Response<UserInfoResult> index(@PathVariable("id") Long userId){
        return usersInfoService.index(userId);
    }

    @PutMapping("/{id}")
    @ApiOperation("修改个人信息")
    public Response update(@PathVariable("id") Long userId,@RequestBody UserUpdateVo vo){
        return usersInfoService.update(userId,vo);
    }
}
