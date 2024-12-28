package com.spds.pmaintain.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spds.pmaintain.mapper.PmaintainMapper;
import com.spds.pmaintain.domain.Pmaintain;
import com.spds.pmaintain.service.IPmaintainService;

/**
 * 维修管理Service业务层处理
 * 
 * @author hlp
 * @date 2024-12-28
 */
@Service
public class PmaintainServiceImpl implements IPmaintainService 
{
    @Autowired
    private PmaintainMapper pmaintainMapper;

    /**
     * 查询维修管理
     * 
     * @param id 维修管理主键
     * @return 维修管理
     */
    @Override
    public Pmaintain selectPmaintainById(Long id)
    {
        return pmaintainMapper.selectPmaintainById(id);
    }

    /**
     * 查询维修管理列表
     * 
     * @param pmaintain 维修管理
     * @return 维修管理
     */
    @Override
    public List<Pmaintain> selectPmaintainList(Pmaintain pmaintain)
    {
        return pmaintainMapper.selectPmaintainList(pmaintain);
    }

    /**
     * 新增维修管理
     * 
     * @param pmaintain 维修管理
     * @return 结果
     */
    @Override
    public int insertPmaintain(Pmaintain pmaintain)
    {
        return pmaintainMapper.insertPmaintain(pmaintain);
    }

    /**
     * 修改维修管理
     * 
     * @param pmaintain 维修管理
     * @return 结果
     */
    @Override
    public int updatePmaintain(Pmaintain pmaintain)
    {
        return pmaintainMapper.updatePmaintain(pmaintain);
    }

    /**
     * 批量删除维修管理
     * 
     * @param ids 需要删除的维修管理主键
     * @return 结果
     */
    @Override
    public int deletePmaintainByIds(Long[] ids)
    {
        return pmaintainMapper.deletePmaintainByIds(ids);
    }

    /**
     * 删除维修管理信息
     * 
     * @param id 维修管理主键
     * @return 结果
     */
    @Override
    public int deletePmaintainById(Long id)
    {
        return pmaintainMapper.deletePmaintainById(id);
    }
}
