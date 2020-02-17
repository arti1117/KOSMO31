package com.kosmo.dto;

// 예약정보 관리하는 Bean
public class BookingsVO {

	// DB 연동 변수
	private String no;
	private String id;
	private String code;
	private String hall;
	private String seatShow;
	private String seatHidden;

	// 기타 변수 - 예약 관련
	private String titleKor;
	private String opTime;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSeatShow() {
		return seatShow;
	}
	public void setSeatShow(String seatShow) {
		this.seatShow = seatShow;
	}
	public String getSeatHidden() {
		return seatHidden;
	}
	public void setSeatHidden(String seatHidden) {
		this.seatHidden = seatHidden;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	public String getTitleKor() {
		return titleKor;
	}
	public void setTitleKor(String titleKor) {
		this.titleKor = titleKor;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	
}