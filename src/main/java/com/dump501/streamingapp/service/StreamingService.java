package com.dump501.streamingapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static com.dump501.streamingapp.constands.ApplicationConstants.*;

@Service
public class StreamingService {
    private final Logger logger = LoggerFactory.getLogger(StreamingService.class);
    public ResponseEntity<byte[]> prepareContent(final String fileName, final String fileType, final String range){
        try{
            final String fileKey = fileName + "." + fileType;
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
            rangeStart = Long.parseLong(ranges[0].substring(6)); // we count 6 char for bytes=
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
        URL url = this.getClass().getResource(VIDEO);
        assert url != null;
        return new File(url.getFile()).getAbsolutePath();
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
}
