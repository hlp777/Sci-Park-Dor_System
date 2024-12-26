package com.spds.pbuild.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.spds.common.annotation.Excel;
import com.spds.common.core.domain.BaseEntity;

/**
 * 楼层管理对象 pbuilding
 * 
 * @author hlp
 * @date 2024-12-26
 */
public class Pbuilding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 楼号 */
    private Long buildingId;

    /** 楼名 */
    @Excel(name = "楼名")
    private String buildingName;

    /** 地址 */
    @Excel(name = "地址")
    private String location;

    /** 房间管理信息 */
    private List<Proom> proomList;

    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }
    public void setBuildingName(String buildingName) 
    {
        this.buildingName = buildingName;
    }

    public String getBuildingName() 
    {
        return buildingName;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    public List<Proom> getProomList()
    {
        return proomList;
    }

    public void setProomList(List<Proom> proomList)
    {
        this.proomList = proomList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("buildingId", getBuildingId())
            .append("buildingName", getBuildingName())
            .append("location", getLocation())
            .append("proomList", getProomList())
            .toString();
    }
}
