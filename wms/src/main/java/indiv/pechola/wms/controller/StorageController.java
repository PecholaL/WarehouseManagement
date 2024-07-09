package indiv.pechola.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.pechola.wms.common.QueryPageParam;
import indiv.pechola.wms.common.Result;
import indiv.pechola.wms.entity.Storage;
import indiv.pechola.wms.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pechola
 * @since 2024-07-09
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;
    @GetMapping("/list")
    public Result list() {
        List<Storage> list = storageService.lambdaQuery().list();
        return Result.success(list);
    }

    // 增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage)? Result.success() : Result.fail();
    }

    // 删
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return storageService.removeById(id)? Result.success() : Result.fail();
    }

    // 改
    @PostMapping("/modify")
    public Result mod(@RequestBody Storage storage) {
        return storageService.updateById(storage)? Result.success() : Result.fail();
    }

    // 新增或修改
    @PostMapping("saveOrModify")
    public boolean saveOrModify(@RequestBody Storage storage) {
        return storageService.saveOrUpdate(storage);
    }

    // 查 模糊查询
    @PostMapping("/listP")
    public Result listP(@RequestBody Storage storage) {
        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(storage.getName())) {
            lambdaQueryWrapper.like(Storage::getName, storage.getName());
        }
        return Result.success(storageService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");

        Page<Storage> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !name.equals("null")) {
            lambdaQueryWrapper.like(Storage::getName, name);
        }
        IPage res = storageService.page(page, lambdaQueryWrapper);

        return Result.success(res.getTotal(), res.getRecords());
    }
}
