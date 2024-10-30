package com.example.device_weixiu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.device_weixiu.domain.BaoXiu;
import com.example.device_weixiu.domain.Result;
import com.example.device_weixiu.domain.User;
import com.example.device_weixiu.domain.WeiXiu;
import com.example.device_weixiu.service.BaoXiuService;
import com.example.device_weixiu.service.WeiXiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 10642
 */

@RestController
@RequestMapping("/weiXiu")
@CrossOrigin("*")
public class WeiXiuController {

    @Autowired
    private WeiXiuService weiXiuService;



    @PostMapping("/save")
    public Result save(@RequestBody WeiXiu weiXiu) {
        weiXiu.setState(null);
        weiXiuService.saveOrUpdate(weiXiu);
        return Result.ok();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        weiXiuService.removeById(id);
        return Result.ok();
    }

    /**
     *
     */
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
        IPage<WeiXiu> userPage = new Page<>(page,limit);
        return Result.ok(weiXiuService.page(userPage));
    }

    @PutMapping("/wancheng/{id}")
    public Result wancheng(@PathVariable Integer id) {
        WeiXiu weiXiu = new WeiXiu();
        weiXiu.setId(id);
        weiXiu.setState("已完成");
        return Result.ok(weiXiuService.updateById(weiXiu));
    }


}
