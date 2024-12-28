package com.spds.pmaintain.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.spds.common.annotation.Excel;
import com.spds.common.core.domain.BaseEntity;

/**
 * 维修管理对象 pmaintain
 * 
 * @author hlp
 * @date 2024-12-28
 */
public class Pmaintain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 楼名 */
    @Excel(name = "楼名")
    private String buildingName;

    /** 房间号 */
    @Excel(name = "房间号")
    private String roomNumber;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String description;

    /** 维修状态 */
    @Excel(name = "维修状态")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBuildingName(String buildingName) 
    {
        this.buildingName = buildingName;
    }

    public String getBuildingName() 
    {
        return buildingName;
    }
    public void setRoomNumber(String roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() 
    {
        return roomNumber;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("buildingName", getBuildingName())
            .append("roomNumber", getRoomNumber())
            .append("time", getTime())
            .append("description", getDescription())
            .append("status", getStatus())
            .toString();
    }
}
