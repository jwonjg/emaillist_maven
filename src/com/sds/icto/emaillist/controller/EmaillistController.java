package com.sds.icto.emaillist.controller;

import java.util.List;

import org.springframework.asm.commons.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.emaillist.dao.EmailListDao;
import com.sds.icto.emaillist.vo.EmailListVo;

@Controller
public class EmaillistController {

	@Autowired
	EmailListDao emailListDao;
	
	@RequestMapping("/index")
	public String index(Model model){
		List<EmailListVo> list = emailListDao.fetchList();
		model.addAttribute("list", list);
		return "/view/show_emaillist.jsp";
	}
	
	@RequestMapping("/form")
	public String form(){
		return "/view/form_emaillist.jsp";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email){
		EmailListVo vo = new EmailListVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		emailListDao.insert(vo);
		return "redirect:/index";
	}
	
	
}
