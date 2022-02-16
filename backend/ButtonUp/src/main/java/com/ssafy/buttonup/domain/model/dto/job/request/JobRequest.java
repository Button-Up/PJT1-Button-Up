package com.ssafy.buttonup.domain.model.dto.job.request;

import com.ssafy.buttonup.domain.model.entity.job.PayTerm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * 직업 요청 DTO
 *
 * @author Jiun Kim,SeungYeon Hwang
 * created on 2022-02-05
 */
@ApiModel(value = "직업 추가 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    /* 급여 지급 주기 */
    @ApiModelProperty(value = "급여 지급 주기", required = true, example = "주급")
    private PayTerm payTerm;

    /* 급여 */
    @ApiModelProperty(value = "급여", required = true)
    private int pay;

    /* 직업명 */
    @ApiModelProperty(value = "직업명", required = true)
    private String name;

    /* 이미지 키 */
    @ApiModelProperty(value = "이미지 경로")
    private String jobImagePath;
    
    /* 부모 키 */
    @ApiModelProperty(value = "부모 키", required = true)
    private long parentSeq;

    /* 할일 리스트 */
    @ApiModelProperty(value = "할일 리스트", required = true)
    @Setter
    private List<String> ToDoContents;
}
