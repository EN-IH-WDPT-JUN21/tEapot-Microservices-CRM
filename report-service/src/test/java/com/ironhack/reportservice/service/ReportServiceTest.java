package com.ironhack.reportservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//class ReportServiceTest {
//
//    @Autowired
//    ReportService reportService;
//
//    List<Integer> nums= new ArrayList<>();
//
//    @Test
//    void getMedian() {
//        nums = List.of(3,45,78,2,5);
//        assertEquals(5, reportService.getMedian(nums));
//    }
//
//    @Test
//    void getMax() {
//        nums = List.of(3,45,78,2,5);
//        assertEquals(78, reportService.getMax(nums));
//    }
//
//    @Test
//    void getMin() {
//        nums = List.of(3,45,78,2,5);
//        assertEquals(2, reportService.getMin(nums));
//    }
//
//    @Test
//    void getAvg() {
//        nums = List.of(3,45,78,2,5);
//        assertEquals(26.6, reportService.getAvg(nums));
//    }
//}