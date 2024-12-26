package com.spds.pbuild.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.spds.common.annotation.Excel;
import com.spds.common.core.domain.BaseEntity;

/**
 * 房间管理对象 proom
 * 
 * @author hlp
 * @date 2024-12-26
 */
public class Proom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 房间id */
    private Long roomId;

    /** 楼号 */
    @Excel(name = "楼号")
    private Long buildingId;

    /** 房间号 */
    @Excel(name = "房间号")
    private String roomNumber;

    /** 容量 */
    @Excel(name = "容量")
    private Long capacity;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }
    public void setRoomNumber(String roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() 
    {
        return roomNumber;
    }
    public void setCapacity(Long capacity) 
    {
        this.capacity = capacity;
    }

    public Long getCapacity() 
    {
        return capacity;
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
            .append("roomId", getRoomId())
            .append("buildingId", getBuildingId())
            .append("roomNumber", getRoomNumber())
            .append("capacity", getCapacity())
            .append("status", getStatus())
            .toString();
    }
}
