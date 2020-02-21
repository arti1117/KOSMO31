package com.kosmo.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.constant.CommonConstant;
import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.MemberVO;
import com.kosmo.service.BookingsService;
import com.kosmo.service.MemberService;
import com.kosmo.service.SeatService;

@Controller
public class MemberController {

//	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	private MemberService memberService;

	@Inject
	private BookingsService bookingsService;
	
	@Inject
	private SeatService seatService;
	
	
	// joinMember : 회원가입
	@RequestMapping(value = "/member/insertJoinProc", method = RequestMethod.GET)
	public String insertJoinProc(MemberVO memberBean, Model model, 
			HttpSession session) throws Exception {
		
		String rtnUrl = "";
		// 확인용
		
		session.setAttribute("insertBean", memberBean);
		
		try {
			int rtnVal =  memberService.insertMember(memberBean);
			
			//성공
			if(rtnVal > 0) {
			
				rtnUrl = "index";
				//session.removeAttribute("insertBean");
				session.setAttribute("loginBean", memberBean);
			} 
			//실패
			else {
				rtnUrl = "index";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			//response
			model.addAttribute("result", "fail");
			model.addAttribute("resultMsg", e.getMessage());
			
			rtnUrl = "member/joinMember";
		}
		return "redirect:" + rtnUrl;
	}
	
	
	// 멤버리스트/검색
	// 해당 아이디에 상응하는 회원정보 출력 + 예매정보 출력
	@RequestMapping(value="/member/selectMember")
	public String selectMember(MemberVO memberBean, 
			HttpSession session, HttpServletRequest request,
			Model model, BookingsVO reserveBean) throws Exception {
		
		memberBean = (MemberVO)session.getAttribute(CommonConstant.LOGIN_MEMBER_BEAN);
		reserveBean.setId(memberBean.getId());
		
		MemberVO mBean = memberService.selectMember(memberBean);
		List<BookingsVO> revlist =bookingsService.selectBook(reserveBean);
		
		// 예약정보, 회원정보 저장
		model.addAttribute("reserveList", revlist);
		model.addAttribute("memberBean", mBean);
		
		return "/member/memberInfo";
	}
	
	
	// id 중복체크
	@RequestMapping(value="/member/idDuplCheckAjax")
	@ResponseBody
	public Map<String,Object> selectMemberAjax(MemberVO memberBean, 
			Model model, HttpSession session) throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO mBean =  memberService.selectMember(memberBean);
			
			if(mBean != null ){
				map.put(CommonConstant.RESULT, CommonConstant.RESULT_FAIL);
				map.put(CommonConstant.RESULT_MSG, "중복된 id입니다");			
			}
			else{
				map.put(CommonConstant.RESULT, CommonConstant.RESULT_OK);
				map.put(CommonConstant.RESULT_MSG, "이용가능한 id입니다");	
			}
			return map;
	}
	
	//	email 중복체크
	@RequestMapping(value="/member/emailDupleCheckAjax")
	@ResponseBody
	public Map<String,Object> emailDupleCheckAjax(MemberVO memberBean, 
			Model model, HttpSession session) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO mBean =  memberService.emailcheck(memberBean);
			
			if(mBean != null ){
				map.put(CommonConstant.RESULT, CommonConstant.RESULT_FAIL);
				map.put(CommonConstant.RESULT_MSG, "중복된 email입니다");			
			}
			else{
				map.put(CommonConstant.RESULT, CommonConstant.RESULT_OK);
				map.put(CommonConstant.RESULT_MSG, "이용가능한 email입니다");	
			}
			return map;
	}
	
	// 로그인 진행
	@RequestMapping(value="/member/loginProc")
	public String loginProc(MemberVO mBean, HttpSession session,
			Model model, HttpServletResponse response) throws Exception {
		
		boolean resBool = memberService.selectLoginMember(mBean);

		if(resBool) {
			MemberVO selBean = memberService.selectMember(mBean);
			//세션에 저장                            
			session.setAttribute("loginBean", selBean);
			
			MemberVO tmp = (MemberVO)session.getAttribute("loginBean");
			String str="";
			str=tmp.getId();
			
			if(str.equals("admin")){
				return "redirect:/admin/adminIndex";
			}
			else {
				 return "redirect:/index";
			}
		}
		
		PrintWriter out;
		
		try {
			mBean.setId(null);
			response.setCharacterEncoding("EUC-KR");
			out = response.getWriter();
			out.println("<script type='text/javascript'>"
					+ "alert('아이디와 비밀번호를 제대로 확인해 주세요.');"
					+ "history.back();"
					+ "</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/index";
	}
	
	// 회원목록 조회
	@RequestMapping(value="/member/selectMemberList")
	public String selectMemberList(MemberVO mBean, Model model) throws Exception {
	
		List<MemberVO> list = memberService.selectMemberList(mBean);
		
		model.addAttribute("memberList", list);

		return "/admin/memberList";
	}
	
	
	//로그아웃
	@RequestMapping(value="/member/logout")
	public String logout(MemberVO mBean, HttpSession session) {
		
		session.removeAttribute("loginBean");
		
		return "redirect:/index";
	}

	//회원정보
	@RequestMapping(value="/member/updateMember")
	public String memberInfo(MemberVO bean, Model model,
			HttpSession session) throws Exception {
		
		MemberVO mBean = memberService.selectMember(bean);
		
		model.addAttribute("memberBean", mBean);
		
		return "/member/updateMember";

	}
	
	// updateMember : 회원정보수정
	@RequestMapping(value="/member/updateMemberForm")
	public String updateMember(MemberVO mBean, Model model,
			HttpSession session) throws Exception {
		
		int intVal = memberService.updateMember(mBean);
		
		if(intVal > 0) {
			//업데이트 성공
			
			MemberVO tmp = (MemberVO)session.getAttribute("loginBean");
			String str="";
			str=tmp.getId();

			if(str.equals("admin")){
				return "redirect:/member/selectMemberList";
			}
			return "redirect:/member/selectMember?memberId=" + mBean.getId();
			
		}
		
		model.addAttribute("result", "fail");
		model.addAttribute("resultMsg", "회원정보 수정에 실패하였습니다.");
		
		return "redirect:/index";
	}
			
	
	// deleteMember : 회원탈퇴, 회원정보 삭제
	@RequestMapping(value="/member/delete")
	public String delete(MemberVO mBean, Model model, 
			HttpSession session, BookingsVO rBean) throws Exception {
		
		int intVal1 = memberService.deleteMember(mBean);
		rBean.setId(mBean.getId());
		
		int intVal2 = seatService.updateSeat(rBean);
	
		if(intVal1 > 0 && intVal2 > 0) {
			
			MemberVO tmp = (MemberVO)session.getAttribute("loginBean");
			String str="";
			str=tmp.getId();

			if(str.equals("admin")){
				return "redirect:/member/selectMemberList";
			}
				return "redirect:/index";
			
		}
		
		model.addAttribute("result", "fail");
		model.addAttribute("resultMsg", "회원정보 삭제에 실패 하였습니다.");
		
		return "redirect:/member/selectMember?memberId=" + mBean.getId();
	}
	
	@RequestMapping(value="/member/deleteSeat")
	public String deleteSeat(BookingsVO reserveBean, Model model, 
			HttpSession session) throws Exception {
		
		int intVal1 = seatService.deleteSeat(reserveBean);
		int intVal2 = bookingsService.deleteBook(reserveBean);

		if(intVal1 > 0 && intVal2 > 0) {
			
			MemberVO tmp = (MemberVO)session.getAttribute("loginBean");
			
			String str="";
			
			str=tmp.getId();

			if(str.equals("admin")){
				return "redirect:/member/selectMemberList";
			}
				return "redirect:/member/selectMember?memberId=" + reserveBean.getId();
		}
			
		model.addAttribute("result", "fail");
		model.addAttribute("resultMsg", "영화 예약 삭제에 실패 하였습니다.");
		
		return "redirect:/member/selectMember?memberId=" + reserveBean.getId();
	}
	
	
	@RequestMapping(value="/member/deleteSeatAjax")
	@ResponseBody
	public Map<String, Object>deleteSeatAjax(BookingsVO reserveBean, HttpSession session) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String result = CommonConstant.RESULT_FAIL;
		
		try{
			int intVal1 = seatService.deleteSeat(reserveBean);
			int intVal2 = bookingsService.deleteBook(reserveBean);
			//성공
			if(intVal1>0 && intVal2>0){
				result = CommonConstant.RESULT_OK;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		map.put(CommonConstant.RESULT, result);
		
		return map;
	}
	

	
}
