package com.shex;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangZp on 2018/8/13.
 */
@RestController
public class Zok {

    @GetMapping(value = "ok.htm")
    public String ok(){
        return "ok 2345";
    }
}
