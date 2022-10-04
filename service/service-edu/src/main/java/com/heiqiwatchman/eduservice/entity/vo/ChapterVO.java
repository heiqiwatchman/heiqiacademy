package com.heiqiwatchman.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVO {

    private String id;

    private String title;

    //表示小节
    private List<VideoVO> children = new ArrayList<>();
}
