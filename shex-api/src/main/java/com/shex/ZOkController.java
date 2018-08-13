package com.shex;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangZp on 2018/8/13.
 */
@RestController
public class ZOkController {

    @GetMapping(value = "ok.html")
    public String ok(){
        return "ok 2345";
    }
}
