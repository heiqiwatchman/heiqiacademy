package com.heiqiwatchman.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongfengw
 * @create 2022-09-26 22:07
 * @Description:
 * @Version 1.0
 */
@Data
public class SubjectNestedVO {

    private String id;
    private String title;
    private List<SubjectVO> children = new ArrayList<>();
}
