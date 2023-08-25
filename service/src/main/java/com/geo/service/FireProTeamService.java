package com.geo.service;

import com.geo.domain.po.FireProTeamPO;
import com.geo.domain.vo.DataVO;
import com.geo.domain.vo.QueryInfo;

import java.util.List;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-23 0:28
 */
public interface FireProTeamService {
   DataVO selectAll(QueryInfo queryInfo);
   FireProTeamPO selectByTeamId(String id);
   int updateByTeamId(FireProTeamPO team);
   int deleteByTeamId(String id);
   int insertTeam(FireProTeamPO team);
}
