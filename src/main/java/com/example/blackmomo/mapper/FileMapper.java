package com.example.blackmomo.mapper;

import com.example.blackmomo.domain.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.io.File;

@Mapper
public interface FileMapper {


    public int saveFile(FileDto fileDto) throws Exception;

    int selectFile();

    FileDto viewFile(int Id);

    FileDto fileDownload(int fileId);

    FileDto findSelectEdit(int fileId);

    FileDto fileName(int id);

    void fileUpdate(FileDto fileDto);

    void fileDelete(int id);
}
