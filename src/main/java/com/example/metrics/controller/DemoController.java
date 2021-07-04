package com.example.metrics.controller;

import com.codahale.metrics.Meter;
import com.example.metrics.dto.ResponseData;
import com.example.metrics.gauge.AttendanceRatioGauge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //By default, Spring resolves @Autowired entries by type
    //If more than one bean of the same type is available in the container, the framework will throw a fatal exception.
    @Autowired
    private Meter meter1;

    @Autowired
    private AttendanceRatioGauge attendanceRatioGauge;

    @GetMapping("sample1")
    public ResponseData test1() {
        meter1.mark(); // increase everytime internal, can get it with ```meter.getCount()```

        String str = String.format("1 MIN: %s, 5 MIN: %s, 15MIN: %s, MEAN RATE: %s", meter1.getOneMinuteRate(), meter1.getFiveMinuteRate(), meter1.getFifteenMinuteRate(), meter1.getMeanRate());


        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.setMessage(str);

        return responseData;
    }

    @GetMapping("sample2")
    public ResponseData test2() {
        attendanceRatioGauge.addAttendanceCount();

        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.setMessage(String.valueOf(attendanceRatioGauge.getValue()));

        return responseData;
    }
// 6:30
}
