package com.example.device_weixiu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.device_weixiu.domain.BaoXiu;
import com.example.device_weixiu.domain.Result;
import com.example.device_weixiu.service.BaoXiuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 10642
 */

@RestController
@RequestMapping("/baoXiu")
@CrossOrigin("*")
public class BaoXiuController {

    @Resource
    private BaoXiuService baoXiuService;



    @PostMapping("/save")
    public Result save(@RequestBody BaoXiu baoXiu) {
        baoXiuService.saveOrUpdate(baoXiu);
        return Result.ok();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        baoXiuService.removeById(id);
        return Result.ok();
    }

    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
        IPage<BaoXiu> userPage = new Page<>(page,limit);
        return Result.ok(baoXiuService.page(userPage));
    }


}
