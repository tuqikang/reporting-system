package cn.tqktqk.work.problemreportingsystem.service;

import cn.tqktqk.work.problemreportingsystem.dao.DepartmentsMapper;
import cn.tqktqk.work.problemreportingsystem.dao.UsersMapper;
import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.entity.DepartmentsEntity;
import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import cn.tqktqk.work.problemreportingsystem.model.result.DepartmentResult;
import cn.tqktqk.work.problemreportingsystem.model.result.UserSimpleResult;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import cn.tqktqk.work.problemreportingsystem.utils.ResponsePage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Classname DepartmentService
 * @Description TODO
 * @Date 2020/6/3 6:12 下午
 * @Author 麦麦麦阁
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Autowired
    private UsersMapper usersMapper;

    public ResponsePage<DepartmentResult> select() {
        List<DepartmentResult> results = departmentsMapper.select();
        return ResponsePage.success(results,results.size(),1L);
    }

    public Response store(String name) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name",name);
        if (departmentsMapper.selectOne(wrapper)!=null) {
            throw new ServerException(ResponseEnum.CUSTOM,"该部门已存在");
        }
        departmentsMapper.insert(new DepartmentsEntity(null,name,1));
        return Response.success();
    }

    public Response deleted(Long id) {
        departmentsMapper.deleteById(id);
        return Response.success();
    }

    public ResponsePage<UserSimpleResult> selectForDepartment(Long department) {
        List<UserSimpleResult> results = usersMapper.selectUserSimpleResultsByDepId(department);
        return ResponsePage.success(results,results.size(),1L);
    }
}
