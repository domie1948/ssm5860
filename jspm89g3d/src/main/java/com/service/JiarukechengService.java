package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JiarukechengEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JiarukechengVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JiarukechengView;


/**
 * 加入课程
 *
 * @author 
 * @email 
 * @date 2021-01-26 15:28:03
 */
public interface JiarukechengService extends IService<JiarukechengEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiarukechengVO> selectListVO(Wrapper<JiarukechengEntity> wrapper);
   	
   	JiarukechengVO selectVO(@Param("ew") Wrapper<JiarukechengEntity> wrapper);
   	
   	List<JiarukechengView> selectListView(Wrapper<JiarukechengEntity> wrapper);
   	
   	JiarukechengView selectView(@Param("ew") Wrapper<JiarukechengEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiarukechengEntity> wrapper);
   	
}

