package com.example.OnlineLibrary.file;

import static java.io.File.separator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

  @Value("${application.file.uploads.book-cover-output-path}")
  private String coverImageUploadPath;

  public String saveBookCover(@NonNull final MultipartFile coverImage, @NonNull final Long userId) {
    final String coverImageUploadSubPath = "users" + separator + userId;
    return uploadFile(coverImage, coverImageUploadSubPath);
  }

  private String uploadFile(final @NonNull MultipartFile coverImage, @NonNull final String coverImageUploadSubPath) {
    final String uploadPath = coverImageUploadPath + separator + coverImageUploadSubPath;
    File targetFolder = new File(uploadPath);
    if(!targetFolder.exists()) {
      boolean folderCreated = targetFolder.mkdirs();
      if (!folderCreated) {
        log.warn("Failed to create target folder");
        return null;
      }
    }
    final String fileExtension = getCoverImageExtension(coverImage.getOriginalFilename());
    String targetCoverImagePath = uploadPath + separator + System.currentTimeMillis() + "." + fileExtension;
    Path targetPath = Paths.get(targetCoverImagePath);
    try {
      Files.write(targetPath, coverImage.getBytes());
      log.info("Cover for book was saved to: " + targetCoverImagePath);
      return targetCoverImagePath;
    } catch (IOException e) {
      log.error("Cover for book was not saved", e);
    }
    return null;
  }

  private String getCoverImageExtension(final String coverImageName) {
    if(coverImageName == null || coverImageName.isEmpty()) {
      return "";
    }
    int lastDotIndex = coverImageName.lastIndexOf(".");
    if (lastDotIndex == -1) {
      return "";
    }
    return coverImageName.substring(lastDotIndex + 1).toLowerCase();
  }

}
