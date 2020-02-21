package com.kosmo.control;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosmo.bean.PagingBean;
import com.kosmo.constant.CommonConstant;
import com.kosmo.dto.MovieVO;
import com.kosmo.dto.SeatVO;
import com.kosmo.service.MovieService;
import com.kosmo.service.SeatService;

@Controller
public class MovieController {

	@Inject
	private MovieService movieService;
	
	@Inject
	private SeatService seatService;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@RequestMapping(value = "/admin/insertMovieFileProc", method = RequestMethod.POST)
	@ResponseBody
	public String uploadForm(MultipartHttpServletRequest req,
			HttpServletResponse res, HttpSession session) throws Exception {
		
		Iterator<String> itr = req.getFileNames();
		MultipartFile file = req.getFile(itr.next());
		String originFileName = file.getOriginalFilename();
		String filePath = "";
		
		try{
			
			logger.info("originalName: " + originFileName);
			logger.info("size: " + file.getSize());
			logger.info("contentType: " + file.getContentType());
			
			filePath = uploadFile(file.getOriginalFilename(), file.getBytes(), session);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return filePath;
	}

	
	private String uploadFile(String originalName, byte[] fileData, HttpSession session) throws Exception {

		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_" + originalName;
		
		//String rootPath = "C:/DEV/Project_team1/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ReserveMovie";
		String rootPath = session.getServletContext().getRealPath("/");
		String imgPath = "/images/index/";
		String tempPath = "/images/temp/";                           // OK
		
		File targetFolder = new File(rootPath + imgPath);
		if(!targetFolder.exists()){
			targetFolder.mkdirs();
		}
		File target = new File(rootPath + imgPath, originalName);
		FileCopyUtils.copy(fileData, target);
		
		File targetTempFolder = new File(rootPath + tempPath);
		if(!targetTempFolder.exists()){
			targetTempFolder.mkdirs();
		}
		File targetTemp = new File(rootPath + tempPath, savedName);
		FileCopyUtils.copy(fileData, targetTemp);

		String path = imgPath + originalName;
		
		return path;
		
	}

	@RequestMapping(value ="/admin/insertMovieProc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertMovieProc(MovieVO movieBean)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String result = CommonConstant.RESULT_FAIL;
		String resultMsg = "영화 등록에 실패 하였습니다.";
		try{
			int rtnVal = movieService.insertMovie(movieBean);
			//성공
			if(rtnVal>0){
				result = CommonConstant.RESULT_OK;
				resultMsg = "영화를 등록하였습니다.";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		map.put(CommonConstant.RESULT, result);
		map.put(CommonConstant.RESULT_MSG, resultMsg);
		return map;
	}

	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		logger.info("originalName: ");
	}



	// insertHallSeat : 영화관 좌석 등록
	@RequestMapping("/admin/insertHallSeat")
	@ResponseBody
	public Map<String, Object> insertHallSeat(SeatVO seatBean, MovieVO movieBean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = CommonConstant.RESULT_FAIL;
		String resultMsg = "영화관 좌석 등록에 실패 하였습니다.";
		try{
			int rtnVal = 0;
			int i = 0;
			System.out.println(movieBean.getHall());
			if( movieBean.getHall().equals("A") ){
				for( i=1; i<=100 ; i++){
					seatBean.setSeatNo(i);
					rtnVal = seatService.insertHallSeat(seatBean);
				}
			}
			else if( movieBean.getHall().equals("B")){
				for( i=1; i<=70 ; i++){
					seatBean.setSeatNo(i);
					rtnVal = seatService.insertHallSeat(seatBean);
				}
			}
			else if( movieBean.getHall().equals("C")){
				for( i=1; i<=50 ; i++){
					seatBean.setSeatNo(i);
					rtnVal = seatService.insertHallSeat(seatBean);
				}
			}
			else {
				resultMsg = "영화관을 선택해주세요.";
			}
			//성공
			if(rtnVal>0){
				result = CommonConstant.RESULT_OK;
				resultMsg = "영화관 좌석을 등록하였습니다.";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		map.put(CommonConstant.RESULT, result);
		map.put(CommonConstant.RESULT_MSG, resultMsg);
		return map;
	}

	// updateMovie : 영화수정

	// deleteMovie : 영화삭제

	// selectMovieList : 영화리스트
	@RequestMapping("/movie/selectMovieList")
	@ResponseBody
	public Map<String, Object> selectMovieList(MovieVO mBean, PagingBean pagingBean, Model model){

		Map<String, Object> map = new HashMap<String, Object>();

		String result = CommonConstant.RESULT_FAIL;
		String resultMsg = "영화 리스트 조회를 실패 하였습니다.";

		try {
			//전체 리스트 갯수
			int totCount = movieService.selectListTotCount(pagingBean);

			pagingBean.calcPage(totCount);

			//리스트 데이터
			List<MovieVO> list = movieService.selectMovieList(pagingBean);

			for(int i = 0; i < list.size(); ++i){
				MovieVO tmpBean = list.get(i);
				
				System.out.println(tmpBean.getCode());
				
				int totSeatCount = seatService.selectTotalSeatCount(list.get(i));
				int revSeatCount = seatService.selectRevSeatCount(list.get(i));

				list.get(i).setTotSeat(totSeatCount);
				list.get(i).setRevSeat(revSeatCount);
			}

			result = CommonConstant.RESULT_OK;
			resultMsg = "회원 리스트 조회에 성공 하였습니다.";

			map.put("pagingBean", pagingBean);
			map.put("movieList", list);

		} catch(Exception e) {
			e.printStackTrace();
		}

		map.put(CommonConstant.RESULT, result);
		map.put(CommonConstant.RESULT_MSG, resultMsg);

		return map;
	}

	@RequestMapping("/movie/selectMovie")
	public String selectMovie(MovieVO movieBean, PagingBean pagingBean, 
			HttpSession session) throws Exception {
		System.out.println(movieBean.getCode());

		//영화 선택시, 그 영화에 관한 정보를 session에 담아둔다. (rev테이블 채워야 해서)
		MovieVO mBean = movieService.selectMovie(movieBean);

		if(mBean != null) {
			System.out.println( movieBean.getCode());
		} 

		//실어 나른다.
		session.setAttribute(CommonConstant.SEAT_MOIVE_BEAN, mBean);

		return "redirect:/member/reserveMovie";
	}

	// 영화정보 수정화면 출력
	@RequestMapping("/admin/updateMovie")
	public String updateMovie(MovieVO movieBean,Model model) throws Exception {
		//클릭한 영화코드에 해당하는 영화정보 저장
		MovieVO mvBean = movieService.selectMovie(movieBean); 
		
		if(mvBean != null) 	{
		
		System.out.println( mvBean.getTitleKor());
		System.out.println( mvBean.getTitleEng());
		// 지워도 작동되는지 확인
	} 

		model.addAttribute("movieBean", mvBean);

		return "/admin/updateMovie";
	}
	


		// 영화 수정정보 입력
		@RequestMapping("/admin/updateMovieProc")
		@ResponseBody
		public Map<String, Object> updateMovieProc(MovieVO movieBean){
			Map<String, Object> map = new HashMap<String, Object>();
			String result = CommonConstant.RESULT_FAIL;
			String resultMsg = "영화 수정에 실패 하였습니다.";
			try{
				int rtnVal = movieService.updateMovie(movieBean);
				//성공
				if(rtnVal>0){
					result = CommonConstant.RESULT_OK;
					resultMsg = "영화를 수정하였습니다.";
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			map.put(CommonConstant.RESULT, result);
			map.put(CommonConstant.RESULT_MSG, resultMsg);
			return map;
		}

	
	// addHall : 영화관추가 

	// deleteHall : 영화관 삭제
	/*version 68
	@RequestMapping("/member/deleteHall")
	public String deleteHall(MovieBean movieBean, SeatBean seatBean, Model model, HttpSession session){

		// 영화가 여러개 체크될때를 대비해 좀더 수정이 필요할듯함 ...

		seatBean.setCode(movieBean.getCode());

		int movieVal =  movieService.deleteMovie(movieBean);
		int seatVal = movieService.deleteSeat(seatBean);

		if(movieVal > 0 && seatVal > 0) {
			return "redirect:/movie/adminIndex.do";
		}
		model.addAttribute("result", "fail");
		model.addAttribute("resultMsg", "회원정보 삭제에 실패 하였습니다.");

		return "redirect:/movie/adminIndex.do";
	}	
}
	 */

	
}
