package cn.tqktqk.work.problemreportingsystem.dao;

import cn.tqktqk.work.problemreportingsystem.model.entity.DepartmentsEntity;
import cn.tqktqk.work.problemreportingsystem.model.result.DepartmentResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2020-06-03
 */
public interface DepartmentsMapper extends BaseMapper<DepartmentsEntity> {

    List<DepartmentResult> select();

}
