package com.example.device_weixiu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.device_weixiu.domain.Result;
import com.example.device_weixiu.domain.User;
import com.example.device_weixiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author 10642
 */

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, user.getName());
        queryWrapper.eq(User::getPassword, user.getPassword());
        queryWrapper.last("limit 1");
        userService.getOne(queryWrapper);
        return Result.ok(user);
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        Assert.notNull(user.getName(),"联系人名字不能为空");
            userService.saveOrUpdate(user);

        return Result.ok();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.ok();
    }

    /**
     * 分页查询所有联系人
     */
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
        IPage<User> userPage = new Page<>(page,limit);
        return Result.ok(userService.page(userPage));
    }


}
