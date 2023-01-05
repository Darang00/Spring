package kr.or.kosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	//메인화면 보여주는거
	@RequestMapping(value="index.ajax")
	public String index() {
		return "index";
	}
}
