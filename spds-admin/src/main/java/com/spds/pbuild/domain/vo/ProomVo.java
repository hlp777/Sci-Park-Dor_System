package com.spds.pbuild.domain.vo;

import com.spds.pbuild.domain.Proom;

/**
 * @Author: Huanglp
 * @Date: 2024-12-26-20:59
 * @Description:
 */
public class ProomVo extends Proom {
    private String buildingName;

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
