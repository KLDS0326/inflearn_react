package org.zerock.apiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
//상품등록 - multipart/ 조회 : DB에 파일은 안됨.
public class ProductDTO {

    private Long pno;
    private String pname;
    private int price;
    private String pdesc;
    private boolean delFlag; // 삭제가 되는 것 처럼 표시임.

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();
    //파일이름 > 이미 업로드된 애들
    @Builder.Default
    private List<String> uploadedFileNames = new ArrayList<>();
}
