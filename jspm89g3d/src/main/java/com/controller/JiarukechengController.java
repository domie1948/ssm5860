package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.JiarukechengEntity;
import com.entity.view.JiarukechengView;

import com.service.JiarukechengService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 加入课程
 * 后端接口
 * @author 
 * @email 
 * @date 2021-01-26 15:28:03
 */
@RestController
@RequestMapping("/jiarukecheng")
public class JiarukechengController {
    @Autowired
    private JiarukechengService jiarukechengService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiarukechengEntity jiarukecheng, HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			jiarukecheng.setGonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			jiarukecheng.setYonghuming((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JiarukechengEntity> ew = new EntityWrapper<JiarukechengEntity>();
    	PageUtils page = jiarukechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiarukecheng), params), params));
		request.setAttribute("data", page);
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiarukechengEntity jiarukecheng, HttpServletRequest request){
        EntityWrapper<JiarukechengEntity> ew = new EntityWrapper<JiarukechengEntity>();
    	PageUtils page = jiarukechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiarukecheng), params), params));
		request.setAttribute("data", page);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiarukechengEntity jiarukecheng){
       	EntityWrapper<JiarukechengEntity> ew = new EntityWrapper<JiarukechengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiarukecheng, "jiarukecheng")); 
        return R.ok().put("data", jiarukechengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiarukechengEntity jiarukecheng){
        EntityWrapper< JiarukechengEntity> ew = new EntityWrapper< JiarukechengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiarukecheng, "jiarukecheng")); 
		JiarukechengView jiarukechengView =  jiarukechengService.selectView(ew);
		return R.ok("查询加入课程成功").put("data", jiarukechengView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiarukechengEntity jiarukecheng = jiarukechengService.selectById(id);
        return R.ok().put("data", jiarukecheng);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiarukechengEntity jiarukecheng = jiarukechengService.selectById(id);
        return R.ok().put("data", jiarukecheng);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiarukechengEntity jiarukecheng, HttpServletRequest request){
    	jiarukecheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiarukecheng);

        jiarukechengService.insert(jiarukecheng);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiarukechengEntity jiarukecheng, HttpServletRequest request){
    	jiarukecheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiarukecheng);

        jiarukechengService.insert(jiarukecheng);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JiarukechengEntity jiarukecheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiarukecheng);
        jiarukechengService.updateById(jiarukecheng);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiarukechengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<JiarukechengEntity> wrapper = new EntityWrapper<JiarukechengEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			wrapper.eq("gonghao", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			wrapper.eq("yonghuming", (String)request.getSession().getAttribute("username"));
		}

		int count = jiarukechengService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
	


}
