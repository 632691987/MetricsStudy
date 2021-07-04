package com.example.metrics.gauge;

import com.codahale.metrics.RatioGauge;

public class AttendanceRatioGauge extends RatioGauge {

    private int attendanceCount;
    private int courseCount;

    @Override
    protected Ratio getRatio() {
        return Ratio.of(attendanceCount, courseCount);
    }

    public AttendanceRatioGauge(int attendanceCount, int courseCount) {
        this.attendanceCount = attendanceCount;
        this.courseCount = courseCount;
    }

    public void addAttendanceCount() {
        this.attendanceCount++;
    }


}