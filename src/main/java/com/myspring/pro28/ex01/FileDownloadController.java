package com.myspring.pro28.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FileDownloadController {
	
	private static String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";

	@RequestMapping("/download")
	public void download(@RequestParam("imageFileName") String imageFileName,
			                 HttpServletResponse response)throws Exception {
		
		// 서버의 특정경로에 있는 파일을 다운로드하는 방식
		OutputStream out = response.getOutputStream();
		
		String downFile = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File file = new File(downFile);
		
		// 한글 파일이름으로도 다운로드 가능하게 변환
		String fileName = new String(imageFileName.getBytes("utf-8"), "ISO-8859-1");
		// point
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + fileName); 
		// 헤더에 파일 이름을 설정한다
		
		FileInputStream in = new FileInputStream(file);
		
		byte[] buffer = new byte[1024 * 8];	// 8KB 씩 읽는다
		
		while (true) {
			int count = in.read(buffer);
			if (count == -1)
				break;
			out.write(buffer, 0, count); // byte 배열 buffer를 0부터 count만큼 OutputStream으로 보낸다 
		}
		in.close();
		out.close();
	}

}
