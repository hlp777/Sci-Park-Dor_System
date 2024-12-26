package com.spds.pbuild.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.spds.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.spds.pbuild.domain.Proom;
import com.spds.pbuild.mapper.PbuildingMapper;
import com.spds.pbuild.domain.Pbuilding;
import com.spds.pbuild.service.IPbuildingService;

/**
 * 楼层管理Service业务层处理
 * 
 * @author hlp
 * @date 2024-12-26
 */
@Service
public class PbuildingServiceImpl implements IPbuildingService 
{
    @Autowired
    private PbuildingMapper pbuildingMapper;

    /**
     * 查询楼层管理
     * 
     * @param buildingId 楼层管理主键
     * @return 楼层管理
     */
    @Override
    public Pbuilding selectPbuildingByBuildingId(Long buildingId)
    {
        return pbuildingMapper.selectPbuildingByBuildingId(buildingId);
    }

    /**
     * 查询楼层管理列表
     * 
     * @param pbuilding 楼层管理
     * @return 楼层管理
     */
    @Override
    public List<Pbuilding> selectPbuildingList(Pbuilding pbuilding)
    {
        return pbuildingMapper.selectPbuildingList(pbuilding);
    }

    /**
     * 新增楼层管理
     * 
     * @param pbuilding 楼层管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPbuilding(Pbuilding pbuilding)
    {
        int rows = pbuildingMapper.insertPbuilding(pbuilding);
        insertProom(pbuilding);
        return rows;
    }

    /**
     * 修改楼层管理
     * 
     * @param pbuilding 楼层管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePbuilding(Pbuilding pbuilding)
    {
        pbuildingMapper.deleteProomByBuildingId(pbuilding.getBuildingId());
        insertProom(pbuilding);
        return pbuildingMapper.updatePbuilding(pbuilding);
    }

    /**
     * 批量删除楼层管理
     * 
     * @param buildingIds 需要删除的楼层管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePbuildingByBuildingIds(Long[] buildingIds)
    {
        pbuildingMapper.deleteProomByBuildingIds(buildingIds);
        return pbuildingMapper.deletePbuildingByBuildingIds(buildingIds);
    }

    /**
     * 删除楼层管理信息
     * 
     * @param buildingId 楼层管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePbuildingByBuildingId(Long buildingId)
    {
        pbuildingMapper.deleteProomByBuildingId(buildingId);
        return pbuildingMapper.deletePbuildingByBuildingId(buildingId);
    }

    /**
     * 新增房间管理信息
     * 
     * @param pbuilding 楼层管理对象
     */
    public void insertProom(Pbuilding pbuilding)
    {
        List<Proom> proomList = pbuilding.getProomList();
        Long buildingId = pbuilding.getBuildingId();
        if (StringUtils.isNotNull(proomList))
        {
            List<Proom> list = new ArrayList<Proom>();
            for (Proom proom : proomList)
            {
                proom.setBuildingId(buildingId);
                list.add(proom);
            }
            if (list.size() > 0)
            {
                pbuildingMapper.batchProom(list);
            }
        }
    }
}
