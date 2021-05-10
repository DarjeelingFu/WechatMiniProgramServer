package org.oucgroup.wxprogram.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DemoPageController {
    @RequestMapping("/demo")
    public String demoPage() {
        return "Hello, world! " + new Date();
    }
}
