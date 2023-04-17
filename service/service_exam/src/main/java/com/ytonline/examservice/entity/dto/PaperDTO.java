package com.ytonline.examservice.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PaperDTO {

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String paperId;

}
