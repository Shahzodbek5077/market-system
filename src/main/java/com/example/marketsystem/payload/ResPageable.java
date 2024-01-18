package com.example.marketsystem.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResPageable {
    private int page;
    private int size;
    private long totalElements;
    private int totalPage;
    private Object object;
}
