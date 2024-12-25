package com.spds.puser.mapper;

import java.util.List;
import com.spds.puser.domain.Puser;

/**
 * 用户管理Mapper接口
 * 
 * @author hlp
 * @date 2024-12-25
 */
public interface PuserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public Puser selectPuserById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param puser 用户管理
     * @return 用户管理集合
     */
    public List<Puser> selectPuserList(Puser puser);

    /**
     * 新增用户管理
     * 
     * @param puser 用户管理
     * @return 结果
     */
    public int insertPuser(Puser puser);

    /**
     * 修改用户管理
     * 
     * @param puser 用户管理
     * @return 结果
     */
    public int updatePuser(Puser puser);

    /**
     * 删除用户管理
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deletePuserById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePuserByIds(Long[] ids);
}
