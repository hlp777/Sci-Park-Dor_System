package com.spds.pdormallo.controller;

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
import com.spds.pdormallo.domain.Pdormallo;
import com.spds.pdormallo.service.IPdormalloService;
import com.spds.common.utils.poi.ExcelUtil;
import com.spds.common.core.page.TableDataInfo;

/**
 * 宿舍分配Controller
 * 
 * @author hlp
 * @date 2024-12-27
 */
@RestController
@RequestMapping("/pdormallo/pdormallo")
public class PdormalloController extends BaseController
{
    @Autowired
    private IPdormalloService pdormalloService;

    /**
     * 查询宿舍分配列表
     */
    @PreAuthorize("@ss.hasPermi('pdormallo:pdormallo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Pdormallo pdormallo)
    {
        startPage();
        List<Pdormallo> list = pdormalloService.selectPdormalloList(pdormallo);
        return getDataTable(list);
    }

    /**
     * 导出宿舍分配列表
     */
    @PreAuthorize("@ss.hasPermi('pdormallo:pdormallo:export')")
    @Log(title = "宿舍分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Pdormallo pdormallo)
    {
        List<Pdormallo> list = pdormalloService.selectPdormalloList(pdormallo);
        ExcelUtil<Pdormallo> util = new ExcelUtil<Pdormallo>(Pdormallo.class);
        util.exportExcel(response, list, "宿舍分配数据");
    }

    /**
     * 获取宿舍分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('pdormallo:pdormallo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pdormalloService.selectPdormalloById(id));
    }

    /**
     * 新增宿舍分配
     */
    @PreAuthorize("@ss.hasPermi('pdormallo:pdormallo:add')")
    @Log(title = "宿舍分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pdormallo pdormallo)
    {
        return toAjax(pdormalloService.insertPdormallo(pdormallo));
    }

    /**
     * 修改宿舍分配
     */
    @PreAuthorize("@ss.hasPermi('pdormallo:pdormallo:edit')")
    @Log(title = "宿舍分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pdormallo pdormallo)
    {
        return toAjax(pdormalloService.updatePdormallo(pdormallo));
    }

    /**
     * 删除宿舍分配
     */
    @PreAuthorize("@ss.hasPermi('pdormallo:pdormallo:remove')")
    @Log(title = "宿舍分配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pdormalloService.deletePdormalloByIds(ids));
    }
}
