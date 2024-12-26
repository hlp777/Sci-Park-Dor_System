package com.spds.pbuild.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.spds.pbuild.domain.vo.ProomVo;
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
import com.spds.pbuild.domain.Proom;
import com.spds.pbuild.service.IProomService;
import com.spds.common.utils.poi.ExcelUtil;
import com.spds.common.core.page.TableDataInfo;

/**
 * 房间管理Controller
 *
 * @author hlp
 * @date 2024-12-26
 */
@RestController
@RequestMapping("/pbuild/proom")
public class ProomController extends BaseController
{
    @Autowired
    private IProomService proomService;

    /**
     * 查询房间管理列表
     */
    @PreAuthorize("@ss.hasPermi('pbuild:proom:list')")
    @GetMapping("/list")
    public TableDataInfo list(Proom proom)
    {
        //startPage();
        //List<Proom> list = proomService.selectProomList(proom);
        //return getDataTable(list);

        startPage();
        List<ProomVo> list = proomService.selectProomListVo(proom);
        return getDataTable(list);

    }

    /**
     * 导出房间管理列表
     */
    @PreAuthorize("@ss.hasPermi('pbuild:proom:export')")
    @Log(title = "房间管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Proom proom)
    {
        List<Proom> list = proomService.selectProomList(proom);
        ExcelUtil<Proom> util = new ExcelUtil<Proom>(Proom.class);
        util.exportExcel(response, list, "房间管理数据");
    }

    /**
     * 获取房间管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('pbuild:proom:query')")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return success(proomService.selectProomByRoomId(roomId));
    }

    /**
     * 新增房间管理
     */
    @PreAuthorize("@ss.hasPermi('pbuild:proom:add')")
    @Log(title = "房间管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Proom proom)
    {
        return toAjax(proomService.insertProom(proom));
    }

    /**
     * 修改房间管理
     */
    @PreAuthorize("@ss.hasPermi('pbuild:proom:edit')")
    @Log(title = "房间管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Proom proom)
    {
        return toAjax(proomService.updateProom(proom));
    }

    /**
     * 删除房间管理
     */
    @PreAuthorize("@ss.hasPermi('pbuild:proom:remove')")
    @Log(title = "房间管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds)
    {
        return toAjax(proomService.deleteProomByRoomIds(roomIds));
    }
}
