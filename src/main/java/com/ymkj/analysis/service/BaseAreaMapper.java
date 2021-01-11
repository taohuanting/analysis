package com.ymkj.analysis.service;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ymkj.analysis.entity.domain.BaseArea;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tao
 */
@Mapper
public interface BaseAreaMapper extends BaseMapper<BaseArea> {
    /**
     * 查询最大序号
     *
     * @return result
     */
    Long selectMaxSerialNum();
}
