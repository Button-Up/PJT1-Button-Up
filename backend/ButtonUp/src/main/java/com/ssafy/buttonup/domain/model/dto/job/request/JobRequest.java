package com.ssafy.buttonup.domain.model.dto.job.request;

import com.ssafy.buttonup.domain.model.entity.job.PayTerm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 직업 요청 DTO
 *
 * @author jiun kim
 * created on 2022-02-05
 */
@ApiModel(value = "직업 추가 요청 정보")
@Getter
@Builder
public class JobRequest {
    /* 급여 지급 주기 */
    @ApiModelProperty(value = "급여 지급 주기", required = true, example = "주급")
    private final PayTerm payTerm;

    /* 급여 */
    @ApiModelProperty(value = "급여", required = true)
    private final int pay;

    /* 직업명 */
    @ApiModelProperty(value = "직업명", required = true)
    private final String name;

    /* 이미지 키 */
    @ApiModelProperty(value = "이미지 키")
    private final long jobImageSeq;
    
    /* 부모 키 */
    @ApiModelProperty(value = "부모 키", required = true)
    private final long parentSeq;

    /* 할일 리스트 */
    @ApiModelProperty(value = "할일 리스트", required = true)
    @Setter
    private List<String> ToDoContents;
}
