package com.example.blackmomo.service;

import com.example.blackmomo.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.blackmomo.domain.FileDto;

import java.io.File;

@Service
public class FileService {

    @Autowired
    FileMapper fileMapper;

    /**
     * <p>업로드한 파일에 대한 정보를 기록</p>
     * @param fileDto
     * @return
     */
    public int saveFile(FileDto fileDto) throws Exception {

        fileMapper.saveFile(fileDto);
        return fileMapper.selectFile();
    }

    public FileDto selectFile(int Id) {
        return fileMapper.viewFile(Id);
    }

    public FileDto getFile(int fileId) {
        return fileMapper.fileDownload(fileId);
    }

    public FileDto findSelectEdit(int fileId) {
        return fileMapper.findSelectEdit(fileId);
    }

    public FileDto fileName(int id) {
       return fileMapper.fileName(id);
    }

    public void updateFile(FileDto fileDto) {
        fileMapper.fileUpdate(fileDto);
    }

    public void fileDelete(int fileId) {
        System.out.println("파일 번호 확인 ::: " + fileId);
        fileMapper.fileDelete(fileId);
    }

    /**
     * <p>id 값을 사용하여 파일에 대한 정보를 가져옴</p>
     * @param id
     * @return
     */
    /*@Transactional
    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }*/
}
