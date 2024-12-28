package com.spds.pmaintain.controller;

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
import com.spds.pmaintain.domain.Pmaintain;
import com.spds.pmaintain.service.IPmaintainService;
import com.spds.common.utils.poi.ExcelUtil;
import com.spds.common.core.page.TableDataInfo;

/**
 * 维修管理Controller
 * 
 * @author hlp
 * @date 2024-12-28
 */
@RestController
@RequestMapping("/pmaintain/pmaintain")
public class PmaintainController extends BaseController
{
    @Autowired
    private IPmaintainService pmaintainService;

    /**
     * 查询维修管理列表
     */
    @PreAuthorize("@ss.hasPermi('pmaintain:pmaintain:list')")
    @GetMapping("/list")
    public TableDataInfo list(Pmaintain pmaintain)
    {
        startPage();
        List<Pmaintain> list = pmaintainService.selectPmaintainList(pmaintain);
        return getDataTable(list);
    }

    /**
     * 导出维修管理列表
     */
    @PreAuthorize("@ss.hasPermi('pmaintain:pmaintain:export')")
    @Log(title = "维修管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Pmaintain pmaintain)
    {
        List<Pmaintain> list = pmaintainService.selectPmaintainList(pmaintain);
        ExcelUtil<Pmaintain> util = new ExcelUtil<Pmaintain>(Pmaintain.class);
        util.exportExcel(response, list, "维修管理数据");
    }

    /**
     * 获取维修管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('pmaintain:pmaintain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmaintainService.selectPmaintainById(id));
    }

    /**
     * 新增维修管理
     */
    @PreAuthorize("@ss.hasPermi('pmaintain:pmaintain:add')")
    @Log(title = "维修管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pmaintain pmaintain)
    {
        return toAjax(pmaintainService.insertPmaintain(pmaintain));
    }

    /**
     * 修改维修管理
     */
    @PreAuthorize("@ss.hasPermi('pmaintain:pmaintain:edit')")
    @Log(title = "维修管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pmaintain pmaintain)
    {
        return toAjax(pmaintainService.updatePmaintain(pmaintain));
    }

    /**
     * 删除维修管理
     */
    @PreAuthorize("@ss.hasPermi('pmaintain:pmaintain:remove')")
    @Log(title = "维修管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmaintainService.deletePmaintainByIds(ids));
    }
}
