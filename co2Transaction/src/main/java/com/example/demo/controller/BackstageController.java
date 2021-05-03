package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.mapper.BackstageAccountMapper;
import com.example.demo.mapper.Co2CountryMapper;
import com.example.demo.mapper.Co2TransactionRecordsMapper;
import com.example.demo.mapper.Co2UserMapper;
import com.example.demo.model.BackstageAccount;
import com.example.demo.model.Co2Country;
import com.example.demo.model.Co2TransactionRecords;
import com.example.demo.model.Co2User;

@Controller

@RequestMapping("/back")
public class BackstageController {

	@Autowired
	private Co2TransactionRecordsMapper co2TransactionRecordsMapper;
	@Autowired
	private BackstageAccountMapper backstageAccountMapper;
	@Autowired
	private Co2CountryMapper co2CountryMapper;
	@Autowired
	Co2UserMapper co2UserMapper;

	// 查询所有员工信息
	@RequestMapping("/findAll")
	public List<Co2TransactionRecords> findAll() {

		
		
		return this.co2TransactionRecordsMapper.selectAll();

	}

	// 根据姓名查员工信息
	@RequestMapping("/findByUserName")
	public BackstageAccount findByUserName(String userName) {
		return this.backstageAccountMapper.findByaccountName(userName);
	}

	// 未授权提示页面
	@RequestMapping("/noAuth")
	public String noAuth() {
		return "/noAuth";
	}

	/**
	 * 跳转登入
	 * 
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin() {

		return "/login";
	}

	/**
	 * 测试thymeleaf
	 */
	@RequestMapping("/testThymeleaf")
	public String testThymeleaf(Model model, UsernamePasswordToken token) {
		// 把数据存入model
//    	model.getAttribute(attributeName)
//    	System.out.println("Username============="+token.getUsername());
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();

		model.addAttribute("name", backstageAccount.getAccountName());

		return "test";

	}

	
	/**
	 * 使用者列表
	 *
	 * @return
	 */
	@RequestMapping("/userTables")
	public String userTables(Model model) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();

//		System.out.println("進來了ADD");
		
		Date date = new Date();

		List<Co2User> list=co2UserMapper.selectAll();
		
	
//		System.out.println("00000=="+list.get(0).getMovieUserName());
		
		model.addAttribute("list", list);

		model.addAttribute("name", backstageAccount.getAccountName());

		return "userTables";
	}
	
	
	/**
	 * 電影列表
	 *
	 * @return
	 */
	@RequestMapping("/movieslist")
	public String movieslist(Model model) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();

//		System.out.println("進來了ADD");
		
		Date date = new Date();

	//	List<MovieUserAccount> list=movieUserAccountMapper.selectAll();
		 List<Co2Country> list=co2CountryMapper.selectAllCountryInf();
	 
		//List<MovieHome> arrayList = movieHomeMapper.selectMovieModel();
		model.addAttribute("list", list);

		model.addAttribute("name", backstageAccount.getAccountName());

		return "movieslist";
	}
	
	
	/**
	 * 刪除用户
	 *
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Model model,String id) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();

//		System.out.println("進來了ADD");
		
		System.out.println("id=="+id);
		
		Date date = new Date();


		backstageAccountMapper.delete(id);
		List<BackstageAccount> backstageAccountList = backstageAccountMapper.selectAll();
		model.addAttribute("list", backstageAccountList);

		model.addAttribute("name", backstageAccount.getAccountName());

		return "add";
	}
	
	

	/**
	 * 停權用户
	 *
	 * @return
	 */
	@RequestMapping("/stope")
	public String stope(Model model,String id,String status) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();

//		System.out.println("進來了ADD");
		
		System.out.println("id=="+id);
		
		if (status.equals("1")) {
			status="2";
		}else {
			status="1";
		}
		
		Date date = new Date();


		backstageAccountMapper.stope(id, status);
		List<BackstageAccount> backstageAccountList = backstageAccountMapper.selectAll();
		model.addAttribute("list", backstageAccountList);

		model.addAttribute("name", backstageAccount.getAccountName());

		return "add";
	}
	
	/**
	 * 添加用户
	 *
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();

//		System.out.println("進來了ADD");
		
		Date date = new Date();

		List<BackstageAccount> backstageAccountList = backstageAccountMapper.selectAll();
		
		model.addAttribute("list", backstageAccountList);

		model.addAttribute("name", backstageAccount.getAccountName());

		return "add";
	}

	/**
	 * 添加用户
	 *
	 * @return
	 */
	@RequestMapping("/changePassword")
	public String changePassword(Model model) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();

//		System.out.println("進來了ADD");
		
		
		model.addAttribute("name", backstageAccount.getAccountName());

		return "changePassword";
	}
	
	/*
	 * 更改密碼
	 * */
	@PostMapping("/updatePassword")
	public String updatePassword(String chackPassword,String oldPassword,String NewPassword) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();

		System.out.println("進來了updatePassword");
		//登入的帳號名稱
		
		
		backstageAccountMapper.updateByPassWord(backstageAccount.getAccountName(), NewPassword);
	//	model.addAttribute("name", backstageAccount.getAccountName());

		return "login";
	}
	/**
	 * 更新用户信息
	 *
	 * @return
	 */
	@RequestMapping("update")
	public String update() {

		return "user/update";

	}

	/*
	 * 註冊
	 * 
	 * @return register.html
	 */
	@RequestMapping("/register")
	public String register(Model model) {

		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();
		model.addAttribute("name", backstageAccount.getAccountName());

		return "register";
	}

	/**
	 * 跳入主要畫面
	 *
	 * @return @RequestMapping("startbootstrap") public String startbootstrap(Model
	 *         model) {
	 * 
	 *         Subject subject = SecurityUtils.getSubject();
	 * 
	 *         BackstageAccount backstageAccount= (BackstageAccount)
	 *         subject.getPrincipal();
	 * 
	 *         model.addAttribute("name",backstageAccount.getAccountName());
	 * 
	 *         return "index"; }
	 */

	/**
	 * 新建後台人員帳號(編輯用) 只能新增權限user:editor的帳號
	 *
	 * @return
	 */
	@RequestMapping("insertBackstageAccountForEditor")
	public String insertBackstageAccountForEditor(String accountName, String accountPassword, Integer accountStatus,
			Model model) {
		// 權限有分
		// user:admin
		// user:editor
		// user:contributor
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount userAccount = (BackstageAccount) subject.getPrincipal();
		// String userAccountPermissionLevel =userAccount.getAccountPermissionLevel();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		Integer in = 0;
		BackstageAccount backstageAccount = new BackstageAccount();
		backstageAccount.setAccountName(accountName);
		backstageAccount.setAccountPassword(accountPassword);
		// backstageAccount.setAccountPermissionLevel("user:contributor");
		backstageAccount.setAccountStatus(accountStatus);
		backstageAccount.setAccountLastLoginTime(str);
		if (backstageAccountMapper.determineName(accountName) != 0) {
			in = backstageAccountMapper.insert(backstageAccount);

		}
		return "OK" + in;
	}

	/*
	 * 檢查帳號是否重複
	 * 
	 */
//    @RequestMapping("chackName")
//    public Integer chackName(String userName){
//    	   	System.out.println(backstageAccountMapper.determineName(userName));
//    	return backstageAccountMapper.determineName(userName);
//    }
	/**
	 * 更新用户信息
	 *
	 * @return
	 */
	@RequestMapping("chackName")
	@ResponseBody
	public String chackName(String userName) {

		Integer in = backstageAccountMapper.determineName(userName);

		if (in > 0) {
			return "1";
		}

		return "0";

	}

	/**
	 * 新建後台人員帳號(給Admin) 能新增權限 editor，contributor
	 *
	 * @return
	 */
	@RequestMapping("insertBackstageAccountForAdmin")
	public String insertBackstageAccountForAdmin(@RequestParam String accountName,
			@RequestParam String accountPassword,
			@RequestParam String accountPermissionLevel, Model model) {

		
		
		String level = "";

		List<BackstageAccount> backstageAccountList = backstageAccountMapper.selectAll();

		Subject subject = SecurityUtils.getSubject();

		BackstageAccount userAccount = (BackstageAccount) subject.getPrincipal();

		String userAccountPermissionLevel = userAccount.getAccountPermissionLevel();

		//System.out.println("accountName====" + accountName);

		if (!accountPermissionLevel.equals("後台權限等級") || accountName != "" || accountPassword != "") {

			if (accountPermissionLevel.equals("小編")) {

				level = "user:editor";

			} else {

				level = "user:contributor";

			}
			System.out.println("level=="+level);

			// 權限有分
			// user:admin
			// user:editor
			// user:contributor

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(date);
			Integer in = 0;
			BackstageAccount backstageAccount = new BackstageAccount();
			backstageAccount.setAccountName(accountName);
			backstageAccount.setAccountPassword(accountPassword);
			backstageAccount.setAccountPermissionLevel(level);
			backstageAccount.setAccountStatus(1);
			backstageAccount.setAccountLastLoginTime(str);
			boolean permissionLevel = (level.equals("user:editor")
					|| level.equals("user:contributor"));
			if (backstageAccountMapper.determineName(accountName) == 0 && permissionLevel) {
				in = backstageAccountMapper.insert(backstageAccount);

			
			}

			backstageAccountList=backstageAccountMapper.selectAll();
			
			model.addAttribute("name", userAccount.getAccountName());

			model.addAttribute("list", backstageAccountList);

			return "add";
		}

//		System.out.println("backstageAccountList==" + backstageAccountList.get(0).getAccountName());

		model.addAttribute("list", backstageAccountList);

//		System.out.println("userAccount.getAccountName==" + userAccount.getAccountName());

		model.addAttribute("name", userAccount.getAccountName());

		return "add";
	}

	/**
	 * 跳入主要畫面
	 *
	 * @return
	 */
	@RequestMapping("tables")
	public String tables(Model model) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();
		SimpleDateFormat sdf = new SimpleDateFormat("E yyyy/MM/dd");
	
	
		
		List<Co2TransactionRecords>   list= findAll();
//    	System.out.println("list===="+list.get(0).getTxhash());
    //	System.out.println(list.get(0).getUsdtCustomerTradingTime());
//		System.out.println(list.get(0).getItemKindForCoin());
    	
//		for (int i = 0; i < list.size(); i++) {
//			
//			if (list.get(i).getTokenName().equals("Tree")) {
//				
//				list.remove(i);
//				
//			}
//			
//		}
		
		model.addAttribute("name", backstageAccount.getAccountName());
    	model.addAttribute("list",list);
		return "tables";
	}

	/**
	 * 測試路徑
	 *
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		Subject subject = SecurityUtils.getSubject();
		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();
//    	List<ListTableForUserName>   list= accountTransactionHistoryMapper.selectListTableForUserName();
//    	System.out.println("list===="+list.get(0).getTxhash());

		model.addAttribute("name", backstageAccount.getAccountName());
//    	model.addAttribute("list",list);

		return "tables";
	}

	@RequestMapping("NewFile1")
	public String NewFile1(Model model) {
//		Subject subject = SecurityUtils.getSubject();
//		BackstageAccount backstageAccount = (BackstageAccount) subject.getPrincipal();
//    	List<ListTableForUserName>   list= accountTransactionHistoryMapper.selectListTableForUserName();
//    	System.out.println("list===="+list.get(0).getTxhash());

//		model.addAttribute("name", backstageAccount.getAccountName());
//    	model.addAttribute("list",list);

		return "NewFile1";
	}
	
	/**
	 * 登录逻辑处理
	 *
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String name, String password, Model model) {

		// 打印数据
		/**
		 * 使用Shiro编写认证操作
		 */
		// 1.获取Subject
		Subject subject = SecurityUtils.getSubject();

//		System.out.println("name" + name);
//		System.out.println("password" + password);
		// 2.封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);

		// 3.执行登录方法
		try {
			if(name == null || name.equals("")) {

				return "login";
			}
			subject.login(token);
//            List<ListTableForUserName>   list= accountTransactionHistoryMapper.selectListTableForUserName();

			// 更新登入日期
			Date date = new Date();

			backstageAccountMapper.updateByLoginTime(name, date);

			model.addAttribute("name", name);
//            model.addAttribute("listTable",list);
			return "redirect:/back/tables";

			// 登陆成功
		} catch (UnknownAccountException e) {
			// e.printStackTrace();
			// 登入失败：用户名不存在
			model.addAttribute("msg", "用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {

			// 密码错误
			model.addAttribute("msg", "密碼錯誤");
			return "login";
		}
	}
}