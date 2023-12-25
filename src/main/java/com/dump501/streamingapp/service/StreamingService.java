package com.dump501.streamingapp.service;

import com.dump501.streamingapp.config.StorageProperties;
import com.dump501.streamingapp.model.Video;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import static com.dump501.streamingapp.constands.ApplicationConstants.*;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final Logger logger = LoggerFactory.getLogger(StreamingService.class);
    private final VideoService videoService;
    private Path rootLocation;
    private final StorageProperties properties;


    public ResponseEntity<byte[]> prepareContent(final UUID uuid, final String range){
        try{
            Video video = videoService.getById(uuid);
            final String fileKey = video.getName();
            String[] parts = fileKey.split("[.]");
            String fileType = parts[1];
            long rangeStart = 0;
            long rangeEnd = CHUNK_SIZE;
            Long fileSize = getFileSize(fileKey);

//            if it's the first load ei the range is null
            if(range == null){
                return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                        .header(CONTENT_TYPE, VIDEO_CONTENT + fileType)
                        .header(ACCEPT_RANGES, BYTES)
                        .header(CONTENT_LENGTH, String.valueOf(rangeEnd))
                        .header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)
                        .body(readByteRangeNew(fileKey, rangeStart, rangeEnd)); // read the file content and convert it as byte array 👌
            }

            String[] ranges = range.split("-");
            rangeStart = Long.parseLong(ranges[0].substring(6)); // we count 6 char for 'bytes='
            if(ranges.length > 1){
                rangeEnd = Long.parseLong(ranges[1]);
            } else {
                rangeEnd = rangeStart + CHUNK_SIZE;
            }

            rangeEnd = Math.min(rangeEnd, fileSize-1);
            final byte[] data = readByteRangeNew(fileKey, rangeStart, rangeEnd);
            final String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
            HttpStatus httpStatus = HttpStatus.PARTIAL_CONTENT;
            if(rangeEnd >= fileSize){
                httpStatus = HttpStatus.OK;
            }
            return ResponseEntity.status(httpStatus)
                    .header(CONTENT_TYPE, VIDEO_CONTENT + fileType)
                    .header(ACCEPT_RANGES, BYTES)
                    .header(CONTENT_LENGTH, contentLength)
                    .header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)
                    .body(data);

        } catch (IOException e){
            logger.error("Exception while reading the file", e);
        }

        return null;

    }

    public byte[] readByteRangeNew(String filename, long start, long end) throws IOException{
        Path path = Paths.get(getFilePath(), filename);
        byte[] data = Files.readAllBytes(path);
        byte[] result = new byte[(int) (end - start) + 1];
        System.arraycopy(data, (int) start, result, 0, (int) (end - start) + 1);
        return result;
    }

    private String getFilePath(){
        Path rootLocation  = Paths.get(properties.getLocation());
        return rootLocation.toAbsolutePath().toString();
//        URL url = this.getClass().getResource(VIDEO);
//        assert url != null;
//        return new File(url.getFile()).getAbsolutePath();
    }

    private Long sizeFromFile(Path path){
        try{
            return Files.size(path);
        } catch (IOException e) {
            logger.error("Error while getting the file size", e);
        }

        return 0L;
    }

    public Long getFileSize(String fileName){
        return Optional.ofNullable(fileName)
                .map((file -> Paths.get(getFilePath(), file)))
                .map(this::sizeFromFile)
                .orElse(0L);
    }

    public Path load(String filename) {
        URL url = this.getClass().getResource("/images");
        assert url != null;
        return new File(url.getFile()).toPath().resolve(filename);
    }



    public Resource loadResouce(String filename) {
        try{
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            } else {
                logger.error("Connot read the file "+filename);
            }
        } catch (IOException e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
