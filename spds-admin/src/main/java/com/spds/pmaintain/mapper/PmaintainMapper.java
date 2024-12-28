package com.spds.pmaintain.mapper;

import java.util.List;
import com.spds.pmaintain.domain.Pmaintain;

/**
 * 维修管理Mapper接口
 * 
 * @author hlp
 * @date 2024-12-28
 */
public interface PmaintainMapper 
{
    /**
     * 查询维修管理
     * 
     * @param id 维修管理主键
     * @return 维修管理
     */
    public Pmaintain selectPmaintainById(Long id);

    /**
     * 查询维修管理列表
     * 
     * @param pmaintain 维修管理
     * @return 维修管理集合
     */
    public List<Pmaintain> selectPmaintainList(Pmaintain pmaintain);

    /**
     * 新增维修管理
     * 
     * @param pmaintain 维修管理
     * @return 结果
     */
    public int insertPmaintain(Pmaintain pmaintain);

    /**
     * 修改维修管理
     * 
     * @param pmaintain 维修管理
     * @return 结果
     */
    public int updatePmaintain(Pmaintain pmaintain);

    /**
     * 删除维修管理
     * 
     * @param id 维修管理主键
     * @return 结果
     */
    public int deletePmaintainById(Long id);

    /**
     * 批量删除维修管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePmaintainByIds(Long[] ids);
}
