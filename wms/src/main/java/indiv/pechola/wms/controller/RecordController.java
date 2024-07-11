package indiv.pechola.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.pechola.wms.common.QueryPageParam;
import indiv.pechola.wms.common.Result;
import indiv.pechola.wms.entity.Goods;
import indiv.pechola.wms.entity.Record;
import indiv.pechola.wms.service.GoodsService;
import indiv.pechola.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pechola
 * @since 2024-07-09
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoods());
        int modCount = record.getCount();
        if("2".equals(record.getInOrOut())) {
            modCount = -1 * modCount;
            record.setCount(modCount);
        }
        int curCount = goods.getCount() + modCount;
        goods.setCount(curCount);
        goodsService.updateById(goods);
        return recordService.save(record)? Result.success() : Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String storage = (String) param.get("storage");
        String goodstype = (String) param.get("goodstype");
        String roleId = (String) param.get("roleId");
        String userId = (String) param.get("userId");

        Page<Record> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply(" r.goods=g.id and g.storage=s.id and g.goodsType=gt.id ");

        if(roleId.equals("2")) {
            queryWrapper.apply("r.userId= "+userId);
        }
        if(StringUtils.isNotBlank(name) && !name.equals("null")) {
            queryWrapper.like("g.name", name);
        }
        if(StringUtils.isNotBlank(storage) && !storage.equals("null")) {
            queryWrapper.eq("s.id", storage);
        }
        if(StringUtils.isNotBlank(goodstype) && !goodstype.equals("null")) {
            queryWrapper.eq("gt.id", goodstype);
        }
        IPage res = recordService.pageCustom(page, queryWrapper);

        return Result.success(res.getTotal(), res.getRecords());
    }
}
