package com.geo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-23 0:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataVO<T> {
   private long totle;
   private List<T> data;
}
