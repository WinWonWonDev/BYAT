package com.greedy.byat.profile.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;
import com.greedy.byat.profile.model.service.ProfileService;

import net.coobird.thumbnailator.Thumbnails;

/**
 * <pre>
 * Class : ProfileController
 * Comment : "/profile/*" 요청을 처리하는 Controller
 * History
 * 2021/03/03 (황인수) 처음 작성함
 * 2021/03/04 (황인수) MemberMapper 와 겹치는 부분 수정
 * </pre>
 * @version 2
 * @author 황인수
 * @see ProfileService, ProfileServiceImpl, MemberMapper, BCryptPasswordEncoder, MemberDTO, AttachmentDTO
 * */
@Controller
@RequestMapping("/profile")
@SessionAttributes("loginMember")
public class ProfileController {

	private final ProfileService profileService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public ProfileController(ProfileService profileService, BCryptPasswordEncoder passwordEncoder) {
		
		this.profileService = profileService;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * 메소드 goProfile에 관한 문서화 주석
	 * @param model 세션의 값을 꺼내오기 위한 Model 객체
	 * @param session 세션에 값을 넣어주기 위한 HttpSession 객체
	 * @return url 이동해 줄 jsp의 경로
	 * */
	@RequestMapping("/mypage")
	public String goProfile(Model model, HttpSession session) {
		
		int memberNo = ((MemberDTO) model.getAttribute("loginMember")).getNo();
		
		AttachmentDTO attachment = profileService.selectAttachment(memberNo);
		
		session.setAttribute("attachment", attachment);
		model.addAttribute("attachment", attachment);
		
		System.out.println(session.getAttribute("attachment"));
		
		return "/member/profile";
	}
	
	/** 
	 * 메소드 modifyProfile에 관한 문서화 주석
	 * @param member 수정하려는 멤버의 정보
	 * @param request 요청 정보
	 * @param rttr redirect 객체를 전달하기 위한 RedirectAttributes 객체
	 * @return url redirect 해 줄 경로
	 * */
	@PostMapping(value="/modify", produces = "application/json; charset=UTF-8")
	public String modifyProfile(@ModelAttribute MemberDTO member, HttpServletRequest request, RedirectAttributes rttr, Model model) {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		member = (MemberDTO) model.getAttribute("loginMember");
		
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);
		
		String message = profileService.modifyProfile(member);
		
		request.getSession().setAttribute("loginMember", member);
		
		System.out.println(request.getSession().getAttribute("loginMember"));
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/profile/mypage";
	}
	
	/**
	 * 메소드 modifyPassword에 관한 문서화 주석
	 * @param member 변경하려는 멤버의 정보
	 * @param request 요청 정보
	 * @param rttr redirect 객체를 전달하기 위한 RedirectAttributes 객체
	 * @return url redirect 해 줄 경로
	 * */
	@PostMapping("/modifypwd")
	public String modifyPassword(@ModelAttribute MemberDTO member, HttpServletRequest request, RedirectAttributes rttr, Model model) {
		
		String message = null;
		member = (MemberDTO) model.getAttribute("loginMember");

		String requestOriginPwd = request.getParameter("requestOriginPwd");
		
		if(!profileService.isPwdMatch(member, requestOriginPwd)) {
			message = "입력하신 비밀번호가 일치하지 않습니다!";
		} else {
			String requestNewPwd = request.getParameter("requestNewPwd");
			String requestNewAgain = request.getParameter("requestNewPwdAgain");
			
			if(requestNewPwd != null) {
				if(!requestNewPwd.equals(requestNewAgain)) {
					message = "변경할 비밀번호가 일치하지 않습니다.";					
				} else {
					String encodedPwd = passwordEncoder.encode(requestNewPwd);
					message = profileService.modifyPassword(encodedPwd, member.getId());
					request.getSession().setAttribute("loginMember", member);
				}
			} else {
				message = "변경할 비밀번호를 입력해주세요 !!!";
			}
		}
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/profile/mypage";
	}
	
	@PostMapping("/uploadimage")
	public String uploadPic(@ModelAttribute AttachmentDTO attachment, HttpServletRequest request, RedirectAttributes rttr,
			@RequestParam("uploadedImg") MultipartFile uploadedImg, Model model) {


		String message = null;

		String rootLocation = request.getSession().getServletContext().getRealPath("resources");

		String fileUploadPath = rootLocation + "/upload/original";
		String thumbnailPath = rootLocation + "/upload/thumbnail";

		File uploadDirectory = new File(fileUploadPath);
		File thumbnailDirectory = new File(thumbnailPath);

		if(!uploadedImg.isEmpty()) {
			
			if(!uploadDirectory.exists() || !thumbnailDirectory.exists()) {

				System.out.println("업로드 디렉토리 생성 : " + uploadDirectory.mkdirs());
				System.out.println("섬네일 디렉토리 생성 : " + thumbnailDirectory.mkdirs());
			}

			try {

				if(uploadedImg.getSize() > 0) {

					String orgName = uploadedImg.getOriginalFilename();
					String ext = orgName.substring(orgName.lastIndexOf("."));
					String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

					uploadedImg.transferTo(new File(uploadDirectory + "/" + savedName));

					attachment.setOrgName(orgName);
					attachment.setSavedName(savedName);
					attachment.setPath(fileUploadPath);
					
					int width = 50;
					int height = 55;

					Thumbnails.of(uploadDirectory + "/" + savedName).forceSize(width, height)
					.toFile(thumbnailDirectory + "/thumbnail_" + savedName);

					attachment.setThumbnailPath("/resources/upload/thumbnail/thumbnail_" + savedName);
				}

				attachment.setMemberNo(((MemberDTO) model.getAttribute("loginMember")).getNo());

				message = profileService.registAttachment(attachment);

				rttr.addFlashAttribute("message", message);

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();

				File deleteFile = new File(uploadDirectory + "/" + attachment.getSavedName());
				boolean isDeleted1 = deleteFile.delete();

				File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + attachment.getSavedName());
				boolean isDeleted2 = deleteThumbnail.delete();

				if(isDeleted1 && isDeleted2) {
					System.out.println("업로드 실패로 인한 사진 삭제 완료 !!!");
					e.printStackTrace();
				} else {
					e.printStackTrace();
				}
			}
		} else {
			message = "업로드된 사진이 없습니다. 사진을 선택 후 업로드해주세요 !!!";
			rttr.addFlashAttribute("message", message);
		}

		return "redirect:/profile/mypage";
	}
}
