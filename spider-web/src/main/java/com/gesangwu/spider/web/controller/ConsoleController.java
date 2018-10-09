package com.gesangwu.spider.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.web.tool.AjaxResult;
import com.gesangwu.spider.engine.kshape.task.CoverNegTask;
import com.gesangwu.spider.engine.kshape.task.FallRiseTask;
import com.gesangwu.spider.engine.kshape.task.GroundSkyTask;
import com.gesangwu.spider.engine.kshape.task.ImmortalGuiderTask;
import com.gesangwu.spider.engine.kshape.task.JumpUpTask;
import com.gesangwu.spider.engine.kshape.task.UpperShadowTask;
import com.gesangwu.spider.engine.kshape.task.YiZiTask;
import com.gesangwu.spider.engine.task.KLineSinaTask;
import com.gesangwu.spider.engine.task.LianBanTask;

@Controller
@RequestMapping("/console")
public class ConsoleController {
	
	@Resource
	private KLineSinaTask klTask;
	@Resource
	private LianBanTask lbTask;
	@Resource
	private CoverNegTask cnTask;
	@Resource
	private FallRiseTask frTask;
	@Resource
	private GroundSkyTask gsTask;
	@Resource
	private ImmortalGuiderTask igTask;
	@Resource
	private JumpUpTask juTask;
	@Resource
	private UpperShadowTask usTask;
	@Resource
	private YiZiTask yzTask;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView console(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("console");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public AjaxResult doConsole(HttpServletRequest request){
		System.out.println("Begin task......");
		long start = System.currentTimeMillis();
		klTask.execute();
		long t1 = System.currentTimeMillis();
		System.out.println("KLine task used:" + (t1 - start));
		lbTask.execute();
		long t2 = System.currentTimeMillis();
		System.out.println("LianBan task used:" + (t2 - t1));
		yzTask.execute();
		long t3 = System.currentTimeMillis();
		System.out.println("YiZi task used:" + (t3 - t2));
		usTask.execute();
		long t4 = System.currentTimeMillis();
		System.out.println("UpShadow task used:" + (t4 - t3));
		juTask.execute();
		long t5 = System.currentTimeMillis();
		System.out.println("JumpUp task used:" + (t5 - t4));
		igTask.execute();
		long t6 = System.currentTimeMillis();
		System.out.println("ImmortalGuider task used:" + (t6 - t5));
		gsTask.execute();
		long t7 = System.currentTimeMillis();
		System.out.println("GroundSky task used:" + (t7 - t6));
		frTask.execute();
		long t8 = System.currentTimeMillis();
		System.out.println("FallRise task used:" + (t8 - t7));
		cnTask.execute();
		long t9 = System.currentTimeMillis();
		System.out.println("CoverNeg task used:" + (t9 - t8));
		System.out.println("End task!!!");
		return new AjaxResult(true, null);
	}
}
