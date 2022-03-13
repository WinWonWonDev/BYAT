package com.greedy.byat.management.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.byat.management.model.dao.ManagementMapper;
import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.member.model.dto.MemberDTO;


/** 
* <pre>
* Class : ManagementServiceImpl
* Comment : ManagementController에 필요한 메소드들의 로직을 작성하는 Service
* History
* 2022/02/24 (이소현) 처음 작성, 관리 목록 조회
* 2022/02/25 (이소현) 회원 계정 수정,
* 2022/02/26 (이소현) 회원 계정 수정, 삭제, 삭제된 멤버 목록 조회, 삭제된 멤버 검색
* 2022/02/27 (이소현) 회원 계정 삭제 jsp 작성, 삭제된 계정 복구
* </pre>
* @version 4
* @author 이소현
* @see ManagementService, ManagementController, ManagementDTO, PermitDTO, ManagementMapper  
*
*/
@Service
public class ManagementServiceImpl implements ManagementService {
 
	private final ManagementMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public ManagementServiceImpl(ManagementMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.mapper = mapper;
	}

   /**
    * 메소드 selectManagementList에 관한 문서화 주석
    * @return controller에 managementList를 return
    */
	@Override
	public List<ManagementDTO> selectManagementList() {
		
		List<ManagementDTO> managementList = mapper.selectManagementList();
		
		return managementList;
	}

   /**
    * 메소드 registManagement에 관한 문서화 주석
    * @param MemberDTO member jsp에서 넘기는 멤버 이름,사번  MemberDTO형식에 담아 전달하기 위한 파라미터 
    * @param String managementRoleforCreate 멤버 권한의 값을 String으로 담아 그 값에 따라 permitNo를 지정해주기 위한 파라미터
    * @return 멤버 생성 성공 여부에 따른 결과 값이 int result 담아 return
    */
	@Override
	public int registManagement(MemberDTO member, String managementRoleforCreate) {

		String pwd = passwordEncoder.encode(member.getId());
		int permitNo = 0;
		
		if(managementRoleforCreate.equals("PM")) {
			permitNo = 2;
		} else if(managementRoleforCreate.equals("일반 멤버")) {
			permitNo = 3;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("permitNo", permitNo);
		map.put("id", member.getId());
		map.put("name", member.getName());
		map.put("pwd", pwd);
		
		int result = mapper.insertManagement(map);
		
		if(result > 0) {
			int noticeSettingByOneMember = mapper.insertNoticeSettingByOneMember();
			
		}
		
		return result;
	}

   /**
    * 메소드 modifyManagement에 관한 문서화 주석
    * @param ManagementDTO manegement jsp에서 넘기는 멤버 이름,사번, 권한을  ManagementDTO형식에 담아 전달하기 위한 파라미터 
    * @return 멤버 계정 수정 성공 여부에 따른 결과 값을 int result에 담아 return
    */
	@Override
	public int modifyManagement(ManagementDTO management) {

		int permitCode = 0;
		
		if("PM".equals(management.getPermitName())) {
			management.setPermitCode(2) ;
		} else if("일반 멤버".equals(management.getPermitName())) {
			management.setPermitCode(3);
		}
		
		int result = mapper.updateManagement(management);
		
		return result;
	}

   /**
    * 메소드 removeManagement에 관한 문서화 주석
    * @param int memberNo 삭제할 멤버의 넘버를 담은 파라미터
    * @return 멤버 삭제 성공 여부에 따른 결과 값을 int result에 담아 return
    */
	@Override
	public int removeManagement(int memberNo) {
		
		int result = mapper.deleteManagement(memberNo);
		
		return result;
	}

   /**
    * 메소드 selectManagementRemovedList에 관한 문서화 주석
    * @return controller에 managementDeletedList를 return
    */
	@Override
	public List<ManagementDTO> selectManagementRemovedList() {

		List<ManagementDTO> managementDeletedList = mapper.selectManagementDeletedList();
		
		return managementDeletedList;
	}

   /**
    * 메소드 restoreManagement에 관한 문서화 주석
    * @param int no 복구할 멤버의 넘버를 담은 파라미터
    * @return controller에 복구 성공 결과를 담은 int result를 return
    */
	@Override
	public int restoreManagement(int no) {
		
		int result = mapper.restoreManagement(no);
		
		return result;
	}

}


