package com.geo.service.impl;

import com.geo.dao.mapper.FireProTeamMapper;
import com.geo.domain.po.FireProTeamPO;
import com.geo.domain.vo.DataVO;
import com.geo.domain.vo.QueryInfo;
import com.geo.service.FireProTeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-23 0:30
 */

@Service
public class FireProTeamServiceImpl implements FireProTeamService {
   @Resource
   FireProTeamMapper mapper;

   @Override
   public DataVO selectAll(QueryInfo queryInfo) {
      PageHelper.startPage(queryInfo.getPagenum(),queryInfo.getPagesize());
      List<FireProTeamPO> FireProTeam = mapper.selectAll();
      PageInfo<FireProTeamPO> pi = new PageInfo<>(FireProTeam);
      return new DataVO(pi.getTotal(),pi.getList());
   }

   @Override
   public FireProTeamPO selectByTeamId(String id) {
      return mapper.selectByTeamId(id);
   }

   @Override
   public int updateByTeamId(FireProTeamPO team) {
      return mapper.updateByTeamId(team);
   }

   @Override
   public int deleteByTeamId(String id) {
      return mapper.deleteByTeamId(id);
   }

   @Override
   public int insertTeam(FireProTeamPO team) {
      return mapper.insertTeam(team);
   }
}
