package com.spds.pdormallo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spds.pdormallo.mapper.PdormalloMapper;
import com.spds.pdormallo.domain.Pdormallo;
import com.spds.pdormallo.service.IPdormalloService;

/**
 * 宿舍分配Service业务层处理
 * 
 * @author hlp
 * @date 2024-12-27
 */
@Service
public class PdormalloServiceImpl implements IPdormalloService 
{
    @Autowired
    private PdormalloMapper pdormalloMapper;

    /**
     * 查询宿舍分配
     * 
     * @param id 宿舍分配主键
     * @return 宿舍分配
     */
    @Override
    public Pdormallo selectPdormalloById(Long id)
    {
        return pdormalloMapper.selectPdormalloById(id);
    }

    /**
     * 查询宿舍分配列表
     * 
     * @param pdormallo 宿舍分配
     * @return 宿舍分配
     */
    @Override
    public List<Pdormallo> selectPdormalloList(Pdormallo pdormallo)
    {
        return pdormalloMapper.selectPdormalloList(pdormallo);
    }

    /**
     * 新增宿舍分配
     * 
     * @param pdormallo 宿舍分配
     * @return 结果
     */
    @Override
    public int insertPdormallo(Pdormallo pdormallo)
    {
        return pdormalloMapper.insertPdormallo(pdormallo);
    }

    /**
     * 修改宿舍分配
     * 
     * @param pdormallo 宿舍分配
     * @return 结果
     */
    @Override
    public int updatePdormallo(Pdormallo pdormallo)
    {
        return pdormalloMapper.updatePdormallo(pdormallo);
    }

    /**
     * 批量删除宿舍分配
     * 
     * @param ids 需要删除的宿舍分配主键
     * @return 结果
     */
    @Override
    public int deletePdormalloByIds(Long[] ids)
    {
        return pdormalloMapper.deletePdormalloByIds(ids);
    }

    /**
     * 删除宿舍分配信息
     * 
     * @param id 宿舍分配主键
     * @return 结果
     */
    @Override
    public int deletePdormalloById(Long id)
    {
        return pdormalloMapper.deletePdormalloById(id);
    }
}
