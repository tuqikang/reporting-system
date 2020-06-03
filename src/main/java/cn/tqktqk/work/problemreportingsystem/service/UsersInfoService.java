package cn.tqktqk.work.problemreportingsystem.service;

import cn.tqktqk.work.problemreportingsystem.dao.DepartmentsMapper;
import cn.tqktqk.work.problemreportingsystem.dao.UserDepartmentMapper;
import cn.tqktqk.work.problemreportingsystem.dao.UsersMapper;
import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import cn.tqktqk.work.problemreportingsystem.model.result.UserInfoResult;
import cn.tqktqk.work.problemreportingsystem.model.vo.UserUpdateVo;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Classname UsersInfoService
 * @Description TODO
 * @Date 2020/6/3 4:00 下午
 * @Author 麦麦麦阁
 */
@Service
public class UsersInfoService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserDepartmentMapper userDepartmentMapper;

    @Autowired
    private DepartmentsMapper departmentsMapper;


    public Response<UserInfoResult> index(Long userId) {
        return Response.success(usersMapper.selectUserInfoByUserId(userId));
    }

    public Response update(Long userId, UserUpdateVo vo) {
        if (StringUtils.isEmpty(vo.getNickname())) {
            throw new ServerException(ResponseEnum.CUSTOM,"昵称不能为空");
        }
        //判断部门是否存在
        if (departmentsMapper.selectById(vo.getDepartmentsId())==null) {
            throw new ServerException(ResponseEnum.CUSTOM,"部门不存在");
        }
        //修改昵称
        usersMapper.updateNickname(userId,vo.getNickname());
        //修改部门
        userDepartmentMapper.updateByUserId(userId,vo.getDepartmentsId());
        return Response.success();
    }
}
