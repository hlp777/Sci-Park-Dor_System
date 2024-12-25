package com.spds.puser.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spds.puser.mapper.PuserMapper;
import com.spds.puser.domain.Puser;
import com.spds.puser.service.IPuserService;

/**
 * 用户管理Service业务层处理
 * 
 * @author hlp
 * @date 2024-12-25
 */
@Service
public class PuserServiceImpl implements IPuserService 
{
    @Autowired
    private PuserMapper puserMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public Puser selectPuserById(Long id)
    {
        return puserMapper.selectPuserById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param puser 用户管理
     * @return 用户管理
     */
    @Override
    public List<Puser> selectPuserList(Puser puser)
    {
        return puserMapper.selectPuserList(puser);
    }

    /**
     * 新增用户管理
     * 
     * @param puser 用户管理
     * @return 结果
     */
    @Override
    public int insertPuser(Puser puser)
    {
        return puserMapper.insertPuser(puser);
    }

    /**
     * 修改用户管理
     * 
     * @param puser 用户管理
     * @return 结果
     */
    @Override
    public int updatePuser(Puser puser)
    {
        return puserMapper.updatePuser(puser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deletePuserByIds(Long[] ids)
    {
        return puserMapper.deletePuserByIds(ids);
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Override
    public int deletePuserById(Long id)
    {
        return puserMapper.deletePuserById(id);
    }
}
