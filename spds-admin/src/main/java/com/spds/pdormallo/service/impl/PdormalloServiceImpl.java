package com.spds.pdormallo.service.impl;

import java.util.List;

import com.spds.pbuild.domain.Pbuilding;
import com.spds.pbuild.domain.Proom;
import com.spds.pbuild.domain.vo.ProomVo;
import com.spds.pbuild.mapper.PbuildingMapper;
import com.spds.pbuild.mapper.ProomMapper;
import com.spds.puser.domain.Puser;
import com.spds.puser.mapper.PuserMapper;
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

    @Autowired
    private PuserMapper puserMapper;

    @Autowired
    private PbuildingMapper pbuildingMapper;

    @Autowired
    private ProomMapper proomMapper;

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
        Puser puser = new Puser();
        puser.setUsername(pdormallo.getUserName());

        Pbuilding pbuilding = new Pbuilding();
        pbuilding.setBuildingName(pdormallo.getBuildingName());

        Proom proom = new Proom();
        proom.setRoomNumber(pdormallo.getRoomNumber());
        if (puserMapper.selectPuserList(puser).size() == 0) {
            return -1;
        }else if(pbuildingMapper.selectPbuildingList(pbuilding).size() == 0){
            return -1;
        }else if(proomMapper.selectProomList(proom).size() == 0){
            return -1;
        }else {
            Pdormallo pdormallo1 = new Pdormallo();
            pdormallo1.setUserName(pdormallo.getUserName());

            Pdormallo pdormallo2 = new Pdormallo();
            pdormallo2.setRoomNumber(pdormallo.getRoomNumber());
            pdormallo2.setBuildingName(pdormallo.getBuildingName());

            ProomVo proom1 = new ProomVo();
            proom1.setRoomNumber(pdormallo.getRoomNumber());
            proom1.setBuildingName(pdormallo.getBuildingName());
            Long capacity = proomMapper.selectProomListVo(proom1).get(0).getCapacity();

            System.out.println(capacity.toString());
            System.out.println(pdormalloMapper.selectPdormalloList(pdormallo2).size());
            // 判断该宿舍是否已满
            if(pdormalloMapper.selectPdormalloList(pdormallo2).size() + 1 <= Integer.parseInt(capacity.toString())){
                //判断该学生是否已分配宿舍
                if(pdormalloMapper.selectPdormalloList(pdormallo1).size() == 0){
                    return pdormalloMapper.insertPdormallo(pdormallo);
                }else {
                    return -2;
                }
            }else {
                return -3;
            }

//            Pdormallo pdormallo1 = new Pdormallo();
//            pdormallo1.setUserName(pdormallo.getUserName());
//            if(pdormalloMapper.selectPdormalloList(pdormallo1).size() == 0){
//                return pdormalloMapper.insertPdormallo(pdormallo);
//            }else {
//                return -2;
//            }
        }

        //return pdormalloMapper.insertPdormallo(pdormallo);
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
        Puser puser = new Puser();
        puser.setUsername(pdormallo.getUserName());

        Pbuilding pbuilding = new Pbuilding();
        pbuilding.setBuildingName(pdormallo.getBuildingName());

        Proom proom = new Proom();
        proom.setRoomNumber(pdormallo.getRoomNumber());
        if (puserMapper.selectPuserList(puser).size() == 0) {
            return -1;
        }else if(pbuildingMapper.selectPbuildingList(pbuilding).size() == 0){
            return -1;
        }else if(proomMapper.selectProomList(proom).size() == 0){
            return -1;
        }else {
            return pdormalloMapper.updatePdormallo(pdormallo);
        }

        //return pdormalloMapper.updatePdormallo(pdormallo);
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
