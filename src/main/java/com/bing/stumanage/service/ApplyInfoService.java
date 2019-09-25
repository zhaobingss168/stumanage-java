package com.bing.stumanage.service;

import com.bing.stumanage.uitls.PageUtil;

public interface ApplyInfoService {
    PageUtil queryByPage(Integer page, Integer size);
}
