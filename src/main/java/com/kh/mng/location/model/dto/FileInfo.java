package com.kh.mng.location.model.dto;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileInfo {
    private String originName;
    private String changeName;
    private String path;
}
