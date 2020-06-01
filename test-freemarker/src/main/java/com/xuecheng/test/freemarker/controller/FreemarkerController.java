package com.xuecheng.test.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/freemarker")
@Controller//此处不能使用RestController
public class FreemarkerController
{
	//	@Autowired
	//	RestTemplate restTemplate;

	@RequestMapping("/test1")
	public String freemarker(Map<String, Object> map)
	{
		map.put("name", "黑马程序员");
		//返回模板文件名称
		return "test1";
	}

}
