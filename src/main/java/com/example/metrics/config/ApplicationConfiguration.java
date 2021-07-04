package com.example.metrics.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.example.metrics.gauge.AttendanceRatioGauge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ApplicationConfiguration {

    // A Meter measures event occurrences count and rate:

    @Bean
    public MetricRegistry metrics() {
        MetricRegistry metrics = new MetricRegistry();
        return metrics;
    }

    @Bean
    public Meter meter1(MetricRegistry metrics) {
        return metrics.meter("requests1");
    }

    @Bean
    public Meter meter2(MetricRegistry metrics) {
        return metrics.meter("requests2");
    }

    @Bean
    public AttendanceRatioGauge attendanceRatioGauge() {
        return new AttendanceRatioGauge(0, 200);
    }

    @Bean
    public ConsoleReporter consoleReporter(MetricRegistry metrics) {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
        return reporter;
    }

}
