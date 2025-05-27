package com.example.OnlineLibrary.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

  public static byte[] readCoverFromLocation(final String coverImageUrl) {
    if (StringUtils.isBlank(coverImageUrl)) {
      return null;
    }
    try {
        Path coverImagePath = new File(coverImageUrl).toPath();
        return Files.readAllBytes(coverImagePath);
    } catch (IOException e){
      log.warn("Failed to read cover image from path {}", coverImageUrl);
    }
    return null;
  }

}
