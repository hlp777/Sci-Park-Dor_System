package com.spds.pbuild.service.impl;

import java.util.List;

import com.spds.pbuild.domain.vo.ProomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spds.pbuild.mapper.ProomMapper;
import com.spds.pbuild.domain.Proom;
import com.spds.pbuild.service.IProomService;

/**
 * 房间管理Service业务层处理
 *
 * @author hlp
 * @date 2024-12-26
 */
@Service
public class ProomServiceImpl implements IProomService
{
    @Autowired
    private ProomMapper proomMapper;

    /**
     * 查询房间管理
     *
     * @param roomId 房间管理主键
     * @return 房间管理
     */
    @Override
    public Proom selectProomByRoomId(Long roomId)
    {
        return proomMapper.selectProomByRoomId(roomId);
    }

    /**
     * 查询房间管理列表
     *
     * @param proom 房间管理
     * @return 房间管理
     */
    @Override
    public List<Proom> selectProomList(Proom proom)
    {
        return proomMapper.selectProomList(proom);
    }

    /**
     * 新增房间管理
     *
     * @param proom 房间管理
     * @return 结果
     */
    @Override
    public int insertProom(Proom proom)
    {
        return proomMapper.insertProom(proom);
    }

    /**
     * 修改房间管理
     *
     * @param proom 房间管理
     * @return 结果
     */
    @Override
    public int updateProom(Proom proom)
    {
        return proomMapper.updateProom(proom);
    }

    /**
     * 批量删除房间管理
     *
     * @param roomIds 需要删除的房间管理主键
     * @return 结果
     */
    @Override
    public int deleteProomByRoomIds(Long[] roomIds)
    {
        return proomMapper.deleteProomByRoomIds(roomIds);
    }

    /**
     * 删除房间管理信息
     *
     * @param roomId 房间管理主键
     * @return 结果
     */
    @Override
    public int deleteProomByRoomId(Long roomId)
    {
        return proomMapper.deleteProomByRoomId(roomId);
    }

    /**
     * 通过联表的方式查询房间管理列表
     *
     * @param proom 房间管理
     * @return 房间管理集合
     */
    @Override
    public List<ProomVo> selectProomListVo(Proom proom) {
        return proomMapper.selectProomListVo(proom);
    }
}
