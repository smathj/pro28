package com.myspring.pro28.ex01;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FileUploadController  {
	private static final String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	
	// 업로드 할 수 있는 화면 return
	@RequestMapping(value="/form")
	public String form() {
	    return "uploadForm";
	  }
	
	// 업로드 로직 수행
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest,HttpServletResponse response)
	  throws Exception{
		
		multipartRequest.setCharacterEncoding("utf-8");
		
		Map map = new HashMap();
		
		Enumeration enu=multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()){							// 전송된 매개변수 값을
			String name=(String)enu.nextElement();				// key, value로 map에 저장한다
			String value=multipartRequest.getParameter(name);
			//System.out.println(name+", "+value);
			map.put(name,value);
		}
		
		List fileList= fileProcess(multipartRequest);
		
		map.put("fileList", fileList);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("map", map);
		mav.setViewName("result");
		
		return mav;
	}
	
	
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception{
		
		List<String> fileList= new ArrayList<String>();
		
		// 첨부된 파일 이름을 가져온다
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasNext()){
			
			String fileName = fileNames.next();
			// 파일 이름에 대한 MultipartFile 객체를 가져온다
			MultipartFile mFile = multipartRequest.getFile(fileName);
			// 실제 파일 이름을 가져온다
			String originalFileName=mFile.getOriginalFilename();
			// 파일 이름을 List에 추가한다
			fileList.add(originalFileName);
			// 파일 객체를 생성한다
			File file = new File(CURR_IMAGE_REPO_PATH +"\\"+ fileName);
			// 파일 사이즈로 첨부된 파일이 있는지 확인한다
			if(mFile.getSize()!=0){ 
				if(! file.exists()){					// 경로에 파일이 없으면..
					if(file.getParentFile().mkdirs()){	// 해당 경로의 부모디렉터리 까지 생성한다
						file.createNewFile();			// 파일을 생성한다 ( 그냥 더미 1개 )
					}
				}
				// 임시로 저장된 multipartFile을 실제 파일로 전송한다
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+ originalFileName));
			}
		}
		return fileList;
	}
}
