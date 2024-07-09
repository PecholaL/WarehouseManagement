package indiv.pechola.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.pechola.wms.common.QueryPageParam;
import indiv.pechola.wms.common.Result;
import indiv.pechola.wms.entity.Goodstype;
import indiv.pechola.wms.service.GoodstypeService;
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
@RequestMapping("/goodstype")
public class GoodstypeController {

    @Autowired
    GoodstypeService goodstypeService;
    @GetMapping("/list")
    public List<Goodstype> list() {
        return goodstypeService.list();
    }

    // 增
    @PostMapping("/save")
    public Result save(@RequestBody Goodstype goodstype) {
        return goodstypeService.save(goodstype)? Result.success() : Result.fail();
    }

    // 删
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return goodstypeService.removeById(id)? Result.success() : Result.fail();
    }

    // 改
    @PostMapping("/modify")
    public Result mod(@RequestBody Goodstype goodstype) {
        return goodstypeService.updateById(goodstype)? Result.success() : Result.fail();
    }

    // 新增或修改
    @PostMapping("saveOrModify")
    public boolean saveOrModify(@RequestBody Goodstype goodstype) {
        return goodstypeService.saveOrUpdate(goodstype);
    }

    // 查 模糊查询
    @PostMapping("/listP")
    public Result listP(@RequestBody Goodstype goodstype) {
        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(goodstype.getName())) {
            lambdaQueryWrapper.like(Goodstype::getName, goodstype.getName());
        }
        return Result.success(goodstypeService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");

        Page<Goodstype> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !name.equals("null")) {
            lambdaQueryWrapper.like(Goodstype::getName, name);
        }
        IPage res = goodstypeService.page(page, lambdaQueryWrapper);

        return Result.success(res.getTotal(), res.getRecords());
    }
}
