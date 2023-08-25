package com.geo.dao.mapper;

import com.geo.domain.po.FireProTeamPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-22 23:41
 */
@Repository
public interface FireProTeamMapper {
   List<FireProTeamPO> selectAll();
   FireProTeamPO selectByTeamId(String id);
   int updateByTeamId(FireProTeamPO team);
   int deleteByTeamId(String id);

   int insertTeam(FireProTeamPO team);
}
