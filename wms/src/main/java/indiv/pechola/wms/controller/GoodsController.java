package indiv.pechola.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.pechola.wms.common.QueryPageParam;
import indiv.pechola.wms.common.Result;
import indiv.pechola.wms.entity.Goods;
import indiv.pechola.wms.service.GoodsService;
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
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;
    @GetMapping("/list")
    public List<Goods> list() {
        return goodsService.list();
    }

    // 增
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        return goodsService.save(goods)? Result.success() : Result.fail();
    }

    // 删
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return goodsService.removeById(id)? Result.success() : Result.fail();
    }

    // 改
    @PostMapping("/modify")
    public Result mod(@RequestBody Goods goods) {
        return goodsService.updateById(goods)? Result.success() : Result.fail();
    }

    // 新增或修改
    @PostMapping("saveOrModify")
    public boolean saveOrModify(@RequestBody Goods goods) {
        return goodsService.saveOrUpdate(goods);
    }

    // 查 模糊查询
    @PostMapping("/listP")
    public Result listP(@RequestBody Goods goods) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(goods.getName())) {
            lambdaQueryWrapper.like(Goods::getName, goods.getName());
        }
        return Result.success(goodsService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");

        Page<Goods> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !name.equals("null")) {
            lambdaQueryWrapper.like(Goods::getName, name);
        }
        IPage res = goodsService.page(page, lambdaQueryWrapper);

        return Result.success(res.getTotal(), res.getRecords());
    }
}
