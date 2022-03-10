package com.greedy.byat.profile.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping("/mypage")
	public String showPic(Model model) {
		
		int memberNo = ((MemberDTO) model.getAttribute("loginMember")).getNo();
		
		AttachmentDTO attachment = profileService.selectAttachment(memberNo);
		
		model.addAttribute("attachment", attachment);
		
		System.out.println(model.getAttribute("loginMember"));
		System.out.println(model.getAttribute("attachment"));
		
		return "/member/profile";
	}

	@PostMapping(value="/modify", produces = "application/json; charset=UTF-8")
	public String modifyProfile(@ModelAttribute MemberDTO member, HttpServletRequest request, RedirectAttributes rttr, Model model) {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		member = (MemberDTO) model.getAttribute("loginMember");
		
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);
		
		System.out.println(member);
		
		String message = profileService.modifyProfile(member);
		
		System.out.println(message);
		
		request.getSession().setAttribute("loginMember", member);
		
		System.out.println(request.getSession().getAttribute("loginMember"));
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/profile/mypage";
	}
	
	@PostMapping("/modifypwd")
	public String modifyPassword(@ModelAttribute MemberDTO member, HttpServletRequest request, RedirectAttributes rttr, Model model) {
		
		String message = null;
		member = (MemberDTO) model.getAttribute("loginMember");
		
		System.out.println(request.getParameter("requestOriginPwd"));

		String requestOriginPwd = request.getParameter("requestOriginPwd");
		System.out.println("requestOriginPwd(encoded): " + requestOriginPwd);
		
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
			/* 파일 경로가 존재하지 않는 경우 경로 디렉토리 생성 */
			if(!uploadDirectory.exists() || !thumbnailDirectory.exists()) {

				System.out.println("업로드 디렉토리 생성 : " + uploadDirectory.mkdirs());
				System.out.println("섬네일 디렉토리 생성 : " + thumbnailDirectory.mkdirs());
			}

			try {

				if(uploadedImg.getSize() > 0) {

					String orgName = uploadedImg.getOriginalFilename();
					System.out.println("orgName : " + orgName);
					String ext = orgName.substring(orgName.lastIndexOf("."));
					String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
					System.out.println("변경한 이름 : " + savedName);

					uploadedImg.transferTo(new File(uploadDirectory + "/" + savedName));

					attachment.setOrgName(orgName);
					attachment.setSavedName(savedName);
					attachment.setPath(fileUploadPath);

					/* thumbnail로 변환할 사이즈 지정 */
					int width = 120;
					int height = 100;

					/* thumbnail로 변환 */
					Thumbnails.of(uploadDirectory + "/" + savedName).forceSize(width, height)
					.toFile(thumbnailDirectory + "/thumbnail_" + savedName);

					/*  웹서버에 접근 가능한 경로 형태의 thumbnail 저장 경로 지정 */
					attachment.setThumbnailPath("/resources/upload/thumbnail/thumbnail_" + savedName);
				}

				/* 해당하는 멤버의 식별자 삽입 */
				attachment.setMemberNo(((MemberDTO) model.getAttribute("loginMember")).getNo());

				System.out.println("attachment :" + attachment);

				message = profileService.registAttachment(attachment);

				rttr.addFlashAttribute("message", message);

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();

				/* Exception 발생 시 파일 삭제 */
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
