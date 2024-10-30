package com.example.device_weixiu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.device_weixiu.domain.Result;
import com.example.device_weixiu.domain.Spare;
import com.example.device_weixiu.domain.WeiXiu;
import com.example.device_weixiu.service.SpareService;
import com.example.device_weixiu.service.WeiXiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 10642
 */

@RestController
@RequestMapping("/spare")
@CrossOrigin("*")
public class SpareController {

    @Autowired
    private SpareService spareService;



    @PostMapping("/save")
    public Result save(@RequestBody Spare spare) {
        spareService.saveOrUpdate(spare);
        return Result.ok();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        spareService.removeById(id);
        return Result.ok();
    }



    @PutMapping("/state/{id}")
    public Result state(@PathVariable Integer id){
        Spare spare = spareService.getById(id);
        if ("入库".equals(spare)){
            spare.setState("出库");
            spareService.updateById(spare);
        }
        if ("出库".equals(spare)){
            spare.setState("入库");
            spareService.updateById(spare);
        }
        return Result.ok();
    }

    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
        IPage<Spare> userPage = new Page<>(page,limit);
        return Result.ok(spareService.page(userPage));
    }


}
