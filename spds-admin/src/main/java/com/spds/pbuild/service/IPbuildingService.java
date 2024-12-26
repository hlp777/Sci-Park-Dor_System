package com.spds.pbuild.service;

import java.util.List;
import com.spds.pbuild.domain.Pbuilding;

/**
 * 楼层管理Service接口
 * 
 * @author hlp
 * @date 2024-12-26
 */
public interface IPbuildingService 
{
    /**
     * 查询楼层管理
     * 
     * @param buildingId 楼层管理主键
     * @return 楼层管理
     */
    public Pbuilding selectPbuildingByBuildingId(Long buildingId);

    /**
     * 查询楼层管理列表
     * 
     * @param pbuilding 楼层管理
     * @return 楼层管理集合
     */
    public List<Pbuilding> selectPbuildingList(Pbuilding pbuilding);

    /**
     * 新增楼层管理
     * 
     * @param pbuilding 楼层管理
     * @return 结果
     */
    public int insertPbuilding(Pbuilding pbuilding);

    /**
     * 修改楼层管理
     * 
     * @param pbuilding 楼层管理
     * @return 结果
     */
    public int updatePbuilding(Pbuilding pbuilding);

    /**
     * 批量删除楼层管理
     * 
     * @param buildingIds 需要删除的楼层管理主键集合
     * @return 结果
     */
    public int deletePbuildingByBuildingIds(Long[] buildingIds);

    /**
     * 删除楼层管理信息
     * 
     * @param buildingId 楼层管理主键
     * @return 结果
     */
    public int deletePbuildingByBuildingId(Long buildingId);
}
