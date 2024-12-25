package com.spds.puser.controller;
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
import com.spds.puser.domain.Puser;
import com.spds.puser.service.IPuserService;
import com.spds.common.utils.poi.ExcelUtil;
import com.spds.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 *
 * @author hlp
 * @date 2024-12-25
 */
@RestController
@RequestMapping("/puser/puser")
public class PuserController extends BaseController
{
    @Autowired
    private IPuserService puserService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('puser:puser:list')")
    @GetMapping("/list")
    public TableDataInfo list(Puser puser)
    {
        startPage();
        List<Puser> list = puserService.selectPuserList(puser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('puser:puser:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Puser puser)
    {
        List<Puser> list = puserService.selectPuserList(puser);
        ExcelUtil<Puser> util = new ExcelUtil<Puser>(Puser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('puser:puser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(puserService.selectPuserById(id));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('puser:puser:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Puser puser)
    {
        return toAjax(puserService.insertPuser(puser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('puser:puser:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Puser puser)
    {
        return toAjax(puserService.updatePuser(puser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('puser:puser:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(puserService.deletePuserByIds(ids));
    }
}
