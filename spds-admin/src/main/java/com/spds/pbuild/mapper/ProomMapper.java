package com.spds.pbuild.mapper;

import java.util.List;
import com.spds.pbuild.domain.Proom;

/**
 * 房间管理Mapper接口
 * 
 * @author hlp
 * @date 2024-12-26
 */
public interface ProomMapper 
{
    /**
     * 查询房间管理
     * 
     * @param roomId 房间管理主键
     * @return 房间管理
     */
    public Proom selectProomByRoomId(Long roomId);

    /**
     * 查询房间管理列表
     * 
     * @param proom 房间管理
     * @return 房间管理集合
     */
    public List<Proom> selectProomList(Proom proom);

    /**
     * 新增房间管理
     * 
     * @param proom 房间管理
     * @return 结果
     */
    public int insertProom(Proom proom);

    /**
     * 修改房间管理
     * 
     * @param proom 房间管理
     * @return 结果
     */
    public int updateProom(Proom proom);

    /**
     * 删除房间管理
     * 
     * @param roomId 房间管理主键
     * @return 结果
     */
    public int deleteProomByRoomId(Long roomId);

    /**
     * 批量删除房间管理
     * 
     * @param roomIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProomByRoomIds(Long[] roomIds);
}
