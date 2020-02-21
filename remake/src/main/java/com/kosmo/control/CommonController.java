package com.kosmo.control;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosmo.dto.MemberVO;
import com.kosmo.dto.MovieVO;
import com.kosmo.dto.SeatVO;
import com.kosmo.service.MovieService;
import com.kosmo.service.SeatService;

@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	

	@Inject
	private MovieService movieService;
	
	@Inject
	private SeatService seatService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		
		logger.info("index");
		
		return "index";
	}
	
	@RequestMapping(value = "/admin/adminIndex")
	public String adminIndex() {
		
		logger.info("Administrator Index");
		
		return "admin/adminIndex";
	}
	
	@RequestMapping(value = "/admin/adminForm")
	public String adminForm() {
		
		logger.info("Administrator Formmat");
		
		return "admin/adminForm";
	}
	
	@RequestMapping(value = "/admin/memberList")
	public String memberList() {
		
		logger.info("Administrator Member List");
		
		return "admin/memberList";
	}
	
	@RequestMapping(value = "/member/joinMember")
	public String joinMember() {

		logger.info("Member Sign Up");
		
		return "member/joinMember";
	}
		
	@RequestMapping(value = "/member/memberInfo")
	public String memberInfo() {
		
		logger.info("Member Information");
		
		return "member/memberInfo";
	}
	
	@RequestMapping(value = "/member/reserveMovie")
	public String reserveMovie(MovieVO bean) {

		logger.info("Reserve a Movie Ticket");
		
		return "member/reserveMovie";
	}
	
	@RequestMapping(value="/admin/delete")
	public String delete(MovieVO movieBean, SeatVO seatBean, MemberVO memberBean,
			HttpSession session) throws Exception {

		String tmpCode = movieBean.getCode();
		
		if( tmpCode.indexOf(",") < 0){
			movieService.deleteMovie(movieBean);
			seatService.deleteHall(seatBean);
		}else{
			String[] tmpArray = tmpCode.split(",");
			
			for( int i=0; i<tmpArray.length;i++){
				movieBean.setCode(tmpArray[i]);
				movieService.deleteMovie(movieBean);
				seatBean.setCode(tmpArray[i]);
				seatService.deleteHall(seatBean);
			}
		}
		
		session.setAttribute("memberBean", memberBean);
		
		return "redirect:admin/adminIndex";
	}
		

	
	
}
