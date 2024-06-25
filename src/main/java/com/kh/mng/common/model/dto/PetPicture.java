package com.kh.mng.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PetPicture {
    private int picNo;
    private String originName;
    private String changeName;
    private String filePath;
    private int userNo;
    private int petNo;
}
