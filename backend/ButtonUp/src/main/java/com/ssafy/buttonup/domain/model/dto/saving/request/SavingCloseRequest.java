package com.ssafy.buttonup.domain.model.dto.saving.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "적금 통장 해지 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavingCloseRequest {
    /* 적금 해지할 자녀 키 */
    @ApiModelProperty(value = "적금 해지하는 자녀 키", required = true)
    private long childSeq;

    /* 해지하는 적금 키 */
    @ApiModelProperty(value = "해지할 적금 키", required = true)
    private long savingSeq;

}
