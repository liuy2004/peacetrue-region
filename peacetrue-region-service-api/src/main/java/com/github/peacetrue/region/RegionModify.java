package com.github.peacetrue.region;

import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegionModify extends OperatorCapableImpl<String> {

    private static final long serialVersionUID = 0L;

    /** 编码 */
    @NotNull
    @Min(1)
    private Long id;
    /** 编码 */
    @NotNull
    @Size(min = 1, max = 7)
    private String code;
    /** 名称 */
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    /** 备注 */
    @Size(min = 1, max = 255)
    private String remark;
    /** 上一级主键 */
    @Min(1)
    private Long parentId;

}