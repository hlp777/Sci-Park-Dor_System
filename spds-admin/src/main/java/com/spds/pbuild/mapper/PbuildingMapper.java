package com.spds.pbuild.mapper;

import java.util.List;
import com.spds.pbuild.domain.Pbuilding;
import com.spds.pbuild.domain.Proom;

/**
 * 楼层管理Mapper接口
 * 
 * @author hlp
 * @date 2024-12-26
 */
public interface PbuildingMapper 
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
     * 删除楼层管理
     * 
     * @param buildingId 楼层管理主键
     * @return 结果
     */
    public int deletePbuildingByBuildingId(Long buildingId);

    /**
     * 批量删除楼层管理
     * 
     * @param buildingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePbuildingByBuildingIds(Long[] buildingIds);

    /**
     * 批量删除房间管理
     * 
     * @param buildingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProomByBuildingIds(Long[] buildingIds);
    
    /**
     * 批量新增房间管理
     * 
     * @param proomList 房间管理列表
     * @return 结果
     */
    public int batchProom(List<Proom> proomList);
    

    /**
     * 通过楼层管理主键删除房间管理信息
     * 
     * @param buildingId 楼层管理ID
     * @return 结果
     */
    public int deleteProomByBuildingId(Long buildingId);
}
