package com.geo.web.controller;


import com.geo.domain.po.FireProTeamPO;
import com.geo.domain.resp.Result;
import com.geo.domain.resp.Status;
import com.geo.domain.vo.DataVO;
import com.geo.domain.vo.QueryInfo;
import com.geo.service.FireProTeamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-22 11:01
 */
@CrossOrigin//解决跨域
@RestController
@RequestMapping("/firePro")
public class FireProtecController {

    @Resource
    FireProTeamService service;

    @GetMapping("/selectAll")
    public Result selectAll(QueryInfo queryInfo) {
        DataVO dataVo = service.selectAll(queryInfo);
        Result result = new Result(Status.SUCCESS);
        result.setData(dataVo);
        return result;
    }


    @GetMapping("selectTeam/{id}")
    public Result selectByTeamId(@PathVariable String id) {
        FireProTeamPO team = service.selectByTeamId(id);
//      // 查询前端仍是表格渲染，因此需要用可迭代的集合返回。
        ArrayList<FireProTeamPO> list = new ArrayList<>();
        list.add(team);
        if (list == null)
            return new Result(Status.FAIL);
        return success(list);
    }

    @PostMapping("/update")
    public Result updateByTeamId(@RequestBody FireProTeamPO team) {
        int flag = service.updateByTeamId(team);
        if (flag == 1) {
            return new Result(Status.SUCCESS);
        } else {
            return new Result(Status.FAIL);
        }

    }

    @DeleteMapping("/delete/{id}")
    public Result deleteByTeamId(@PathVariable String id) {
        int flag = service.deleteByTeamId(id);
        if (flag == 1) {
            return new Result(Status.SUCCESS);
        } else {
            return new Result(Status.FAIL);
        }


    }

    @PostMapping("/insert")
    public Result insertTeam(@RequestBody FireProTeamPO team) {
        int flag = service.insertTeam(team);
        if (flag == 1) {
            return new Result(Status.CREATED);
        } else {
            return new Result(Status.FAIL);
        }
    }


    public Result success(Object O) {
        Result result = new Result(Status.SUCCESS);
        result.setData(O);
        return result;
    }

}
