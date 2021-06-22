package wcl.controller;

import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wcl.dao.UserInfoMapper;
import wcl.pojo.Check;
import wcl.pojo.UserInfo;
import wcl.pojo.UserInfoExample;
import wcl.util.Result;
import wcl.vo.UserInfoVO;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserController {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@GetMapping(value = "/")
	public ModelAndView index(ModelAndView mav,HttpSession session) {
		Object userid = session.getAttribute("userid");
		if (userid != null) {
			mav.addObject("userid", userid);
			mav.setViewName("index");
			return mav;
		} else {
			mav.setViewName("login");
			return mav;
		}
	}
	
	@GetMapping(value = "/logout")
	public ModelAndView logout(ModelAndView mav,HttpSession session) {
		session.invalidate();
		mav.setViewName("login");
		return mav;
	}

	@GetMapping(value = "/room")
	public ModelAndView room(ModelAndView mav,HttpSession session,String roomId) {
		mav.addObject("roomId",roomId);
		mav.setViewName("fullCalendar");
		return mav;
	}

	@ResponseBody
	@PostMapping("/captcha")
	public Map<String,String> captcha(HttpSession session) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
		String verCode = specCaptcha.text().toLowerCase();
		session.setAttribute("verCode", verCode);
		map.put("image", specCaptcha.toBase64());
		return map;
	}

	@ResponseBody
	@PostMapping(value = "/user/login")
	public Result index(UserInfoVO userInfoVO, ModelAndView mav, HttpSession session) {
		Check c = userInfoVO.checkForLogin();
		if (c.isCheck()) {
			return Result.fail(c.getMsg());
		}
		/*if (!userInfoVO.getVerCode().toLowerCase().equals(session.getAttribute("verCode"))) {
			return Result.fail("验证码无效");
		}*/
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userInfoVO.getUserId());
		long count = userInfoMapper.countByExample(example);
		if (count == 0L){
			return Result.fail("用户名不存在");
		}
		criteria.andUserPasswordEqualTo(DigestUtils.md5DigestAsHex(userInfoVO.getUserPassword().getBytes()));
		long count2 = userInfoMapper.countByExample(example);
		if (count2 == 1L) {
			UserInfo entity = userInfoMapper.selectByPrimaryKey(userInfoVO.getUserId());
			session.setAttribute("userid", userInfoVO.getUserId());
			session.setAttribute("nickname", entity.getUserNickname());
			return Result.success();
		} else {
			return Result.fail("密码错误");
		}
	}
	
	@ResponseBody
	@PostMapping(value = "/user/register")
	public Result register(UserInfoVO userInfoVO,HttpSession session) {
		Check c = userInfoVO.checkForRegister();
		if (c.isCheck()) {
			return Result.fail(c.getMsg());
		}
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userInfoVO.getUserId());
		long count1 = userInfoMapper.countByExample(example);
		if (count1 > 0L){
			return Result.fail("用户名已经存在");
		}
		criteria.andUserPasswordEqualTo(DigestUtils.md5DigestAsHex(userInfoVO.getUserPassword().getBytes()));
		UserInfo entity = new UserInfo();
		BeanUtils.copyProperties(userInfoVO,entity);
		int row = userInfoMapper.insertSelective(entity);
		if (row > 0) {
			session.setAttribute("userid", userInfoVO.getUserId());
			session.setAttribute("nickname", userInfoVO.getUserNickname());
			return Result.success();
		} else {
			return Result.fail("注册失败");
		}
	}

	@ResponseBody
	@PostMapping(value = "/user/password")
	public Result password(UserInfoVO userInfoVO,HttpSession session) {
		Object userid = session.getAttribute("userid");
		if (userid == null) {
			return Result.fail("请先登陆");
		}
		Check c = userInfoVO.checkForPassword();
		if (c.isCheck()) {
			return Result.fail(c.getMsg());
		}
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userInfoVO.getUserId());
		criteria.andUserPasswordEqualTo(DigestUtils.md5DigestAsHex(userInfoVO.getUserPassword().getBytes()));
		long count = userInfoMapper.countByExample(example);
		if (count == 1L) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(userid.toString());
			userInfo.setUserPassword(DigestUtils.md5DigestAsHex(userInfoVO.getNewPassword().getBytes()));
			int row = userInfoMapper.updateByPrimaryKeySelective(userInfo);
			if (row == 1) {
				return Result.success();
			} else {
				return Result.fail("修改密码失败");
			}
		} else {
			return Result.fail("原密码错误");
		}
	}

}
