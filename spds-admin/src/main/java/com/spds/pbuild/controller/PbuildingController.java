package com.spds.pbuild.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spds.common.annotation.Log;
import com.spds.common.core.controller.BaseController;
import com.spds.common.core.domain.AjaxResult;
import com.spds.common.enums.BusinessType;
import com.spds.pbuild.domain.Pbuilding;
import com.spds.pbuild.service.IPbuildingService;
import com.spds.common.utils.poi.ExcelUtil;
import com.spds.common.core.page.TableDataInfo;

/**
 * 楼层管理Controller
 * 
 * @author hlp
 * @date 2024-12-26
 */
@RestController
@RequestMapping("/pbuild/pbuilding")
public class PbuildingController extends BaseController
{
    @Autowired
    private IPbuildingService pbuildingService;

    /**
     * 查询楼层管理列表
     */
    @PreAuthorize("@ss.hasPermi('pbuild:pbuilding:list')")
    @GetMapping("/list")
    public TableDataInfo list(Pbuilding pbuilding)
    {
        startPage();
        List<Pbuilding> list = pbuildingService.selectPbuildingList(pbuilding);
        return getDataTable(list);
    }

    /**
     * 导出楼层管理列表
     */
    @PreAuthorize("@ss.hasPermi('pbuild:pbuilding:export')")
    @Log(title = "楼层管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Pbuilding pbuilding)
    {
        List<Pbuilding> list = pbuildingService.selectPbuildingList(pbuilding);
        ExcelUtil<Pbuilding> util = new ExcelUtil<Pbuilding>(Pbuilding.class);
        util.exportExcel(response, list, "楼层管理数据");
    }

    /**
     * 获取楼层管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('pbuild:pbuilding:query')")
    @GetMapping(value = "/{buildingId}")
    public AjaxResult getInfo(@PathVariable("buildingId") Long buildingId)
    {
        return success(pbuildingService.selectPbuildingByBuildingId(buildingId));
    }

    /**
     * 新增楼层管理
     */
    @PreAuthorize("@ss.hasPermi('pbuild:pbuilding:add')")
    @Log(title = "楼层管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pbuilding pbuilding)
    {
        return toAjax(pbuildingService.insertPbuilding(pbuilding));
    }

    /**
     * 修改楼层管理
     */
    @PreAuthorize("@ss.hasPermi('pbuild:pbuilding:edit')")
    @Log(title = "楼层管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pbuilding pbuilding)
    {
        return toAjax(pbuildingService.updatePbuilding(pbuilding));
    }

    /**
     * 删除楼层管理
     */
    @PreAuthorize("@ss.hasPermi('pbuild:pbuilding:remove')")
    @Log(title = "楼层管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{buildingIds}")
    public AjaxResult remove(@PathVariable Long[] buildingIds)
    {
        return toAjax(pbuildingService.deletePbuildingByBuildingIds(buildingIds));
    }
}
