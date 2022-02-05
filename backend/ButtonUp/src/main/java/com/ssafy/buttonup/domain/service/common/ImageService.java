package com.ssafy.buttonup.domain.service.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * 이미지 저장 관련 서비스
 *
 * @author jiun kim
 * created on 2022-02-05
 */
public class ImageService {
    private static final String IMAGE_PATH = "D:\\rlajiun\\common-pjt";

    protected String uploadFile(MultipartFile file) {
        // 시간과 originalFilename으로 매핑 시켜서 src 주소를 만들어 낸다.
        Date date = new Date();
        StringBuilder sb = new StringBuilder();

        // file image 가 없을 경우
        if (file.isEmpty()) {
            return null;
        } else {
            sb.append(date.getTime());
            sb.append(file.getOriginalFilename());

            File dest = new File(IMAGE_PATH + "\\" + sb);
            try {
                file.transferTo(dest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // db에 파일 위치랑 번호 등록
        }
        return sb.toString();
    }

    protected boolean deleteFile(String filePath) {
        File file = new File(IMAGE_PATH + "\\" + filePath);
        return file.delete();
    }
}
