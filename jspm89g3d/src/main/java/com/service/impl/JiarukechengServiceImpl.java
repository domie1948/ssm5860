package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.JiarukechengDao;
import com.entity.JiarukechengEntity;
import com.service.JiarukechengService;
import com.entity.vo.JiarukechengVO;
import com.entity.view.JiarukechengView;

@Service("jiarukechengService")
public class JiarukechengServiceImpl extends ServiceImpl<JiarukechengDao, JiarukechengEntity> implements JiarukechengService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiarukechengEntity> page = this.selectPage(
                new Query<JiarukechengEntity>(params).getPage(),
                new EntityWrapper<JiarukechengEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiarukechengEntity> wrapper) {
		  Page<JiarukechengView> page =new Query<JiarukechengView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<JiarukechengVO> selectListVO(Wrapper<JiarukechengEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JiarukechengVO selectVO(Wrapper<JiarukechengEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JiarukechengView> selectListView(Wrapper<JiarukechengEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiarukechengView selectView(Wrapper<JiarukechengEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
