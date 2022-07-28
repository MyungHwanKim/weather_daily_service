package com.zerobase.weather.controller;

import com.zerobase.weather.service.DiaryService;
import com.zerobase.weather.domain.Diary;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", notes = "예시")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "생성할 날짜", example = "2020-02-02") LocalDate date,
                     @RequestBody @ApiParam(value = "생성할 일기 내용", example = "02월 02일부터 열심히 일기를 쓰기로 했다.") String text){
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "조회할 날짜", example = "2020-02-02") LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간 중의 모든 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 시작날짜", example = "2020-02-02") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 마지막날짜", example = "2020-02-02") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날짜의 일기 데이터를 수정합니다.")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "수정할 날짜", example = "2020-02-02") LocalDate date,
                     @RequestBody @ApiParam(value = "생성할 일기 내용", example = "02월 02일부터 열심히 일기를 쓰기로 했다.") String text) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 일기 데이터를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "삭제할 날짜", example = "2020-02-02") LocalDate date) {
        diaryService.deleteDiary(date);
    }

}
