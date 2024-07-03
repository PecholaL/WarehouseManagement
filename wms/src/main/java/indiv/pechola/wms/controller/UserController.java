package indiv.pechola.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.pechola.wms.common.QueryPageParam;
import indiv.pechola.wms.common.Result;
import indiv.pechola.wms.entity.User;
import indiv.pechola.wms.service.UserService;
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
 * @since 2024-06-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    // 增
    @PostMapping("/save")
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    // 删
    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return userService.removeById(id);
    }

    // 改
    @PostMapping("/modify")
    public boolean mod(@RequestBody User user) {
        return userService.updateById(user);
    }

    // 新增或修改
    @PostMapping("saveOrModify")
    public boolean saveOrModify(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    // 查 模糊查询
    @PostMapping("/listP")
    public List<User> listP(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(user.getName())) {
            lambdaQueryWrapper.like(User::getName, user.getName());
        }
        return userService.list(lambdaQueryWrapper);
    }

    @PostMapping("/listPage")
    public List<User> listP(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("pageName");

        Page<User> page = new Page<>();
        page.setCurrent(query.getPagNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, name);

        IPage res = userService.page(page, lambdaQueryWrapper);

        return res.getRecords();
    }

    // 查 分页
    @PostMapping("/listC")
    public List<User> listC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("pageName");

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, name);

        Page<User> page = new Page<>();
        page.setCurrent(query.getPagNum());
        page.setSize(query.getPageSize());

        IPage res = userService.pageC(page, lambdaQueryWrapper);

        return res.getRecords();
    }

    // 查 分页
    @PostMapping("/listR")
    public Result listR(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("pageName");

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, name);

        Page<User> page = new Page<>();
        page.setCurrent(query.getPagNum());
        page.setSize(query.getPageSize());

        IPage res = userService.pageC(page, lambdaQueryWrapper);

        return Result.success(res.getTotal(), res.getRecords());
    }
}
