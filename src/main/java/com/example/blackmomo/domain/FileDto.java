package com.example.blackmomo.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileDto {

    private int    Id; // 파일 일련번호
    private String origFilename; // 원래 퍼일명
    private String filename; // 변경 파일명
    private String filePath; // 저장 경로
}
