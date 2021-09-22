package com.example.metauniversity.service;

import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.File.dto.fileDto;
import com.example.metauniversity.repository.FileRepository;
import com.example.metauniversity.repository.UserFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FileService {

    private final FileRepository fileRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public File uploadThumbnailImage(String dirName, MultipartFile file) {
        if (file != null) {
            fileDto uploadFile = s3Uploader.upload(dirName, file);
            return fileRepository.save(File.create(uploadFile));
        } else {
            throw new RuntimeException("프로필 이미지 업로드 오류");
        }
    }
}
