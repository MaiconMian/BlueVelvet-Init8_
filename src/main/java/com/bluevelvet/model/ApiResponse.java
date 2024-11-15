package com.bluevelvet.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<String> {
    private String status;
    private String data;
}
