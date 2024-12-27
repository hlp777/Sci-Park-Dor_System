package com.spds.pdormallo.mapper;

import java.util.List;
import com.spds.pdormallo.domain.Pdormallo;

/**
 * 宿舍分配Mapper接口
 * 
 * @author hlp
 * @date 2024-12-27
 */
public interface PdormalloMapper 
{
    /**
     * 查询宿舍分配
     * 
     * @param id 宿舍分配主键
     * @return 宿舍分配
     */
    public Pdormallo selectPdormalloById(Long id);

    /**
     * 查询宿舍分配列表
     * 
     * @param pdormallo 宿舍分配
     * @return 宿舍分配集合
     */
    public List<Pdormallo> selectPdormalloList(Pdormallo pdormallo);

    /**
     * 新增宿舍分配
     * 
     * @param pdormallo 宿舍分配
     * @return 结果
     */
    public int insertPdormallo(Pdormallo pdormallo);

    /**
     * 修改宿舍分配
     * 
     * @param pdormallo 宿舍分配
     * @return 结果
     */
    public int updatePdormallo(Pdormallo pdormallo);

    /**
     * 删除宿舍分配
     * 
     * @param id 宿舍分配主键
     * @return 结果
     */
    public int deletePdormalloById(Long id);

    /**
     * 批量删除宿舍分配
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePdormalloByIds(Long[] ids);
}
