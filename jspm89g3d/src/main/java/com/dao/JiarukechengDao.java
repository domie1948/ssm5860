package com.dao;

import com.entity.JiarukechengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JiarukechengVO;
import com.entity.view.JiarukechengView;


/**
 * 加入课程
 * 
 * @author 
 * @email 
 * @date 2021-01-26 15:28:03
 */
public interface JiarukechengDao extends BaseMapper<JiarukechengEntity> {
	
	List<JiarukechengVO> selectListVO(@Param("ew") Wrapper<JiarukechengEntity> wrapper);
	
	JiarukechengVO selectVO(@Param("ew") Wrapper<JiarukechengEntity> wrapper);
	
	List<JiarukechengView> selectListView(@Param("ew") Wrapper<JiarukechengEntity> wrapper);

	List<JiarukechengView> selectListView(Pagination page,@Param("ew") Wrapper<JiarukechengEntity> wrapper);
	
	JiarukechengView selectView(@Param("ew") Wrapper<JiarukechengEntity> wrapper);
	
}
