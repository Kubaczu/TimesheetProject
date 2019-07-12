package com.project.timesheet.Entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class YearService {

    @Autowired
    YearRepository yearRepository;

    public void enterHours(int userID, int YearID, int hours){

    }

//    public void addDay(Year year) {
//        yearRepository.save(year);
//    }
//
//    public void editDay(Year year) {
//        yearRepository.deleteById(year.getId());
//        yearRepository.save(year);
//    }
//
//    public void createWholeYearTable() {
//        Year year = new Year();
//        List<String> codeSQL = new ArrayList<>();
//
//        String entity;
//
//        DayOfWeek dayOfWeek = DayOfWeek.TUESDAY;
//        Month month = Month.JANUARY;
//        int id = 1;
//        for (int miesiac = 1; miesiac <= 12; miesiac++) {
//            year.setMonth(month);
//            for (int dzien = 1; dzien <= month.length(false); dzien++) {
//                year.setDayOfMonth(dzien);
//                year.setDayOfWeek(dayOfWeek);
//                dayOfWeek = dayOfWeek.plus(1);
////                System.out.println("dzien miesiaca: " + year.getDayOfMonth() + " miesiac: " + year.month + " dzien tygodnia: " + year.getDayOfWeek());
////                yearRepository.save(year);
//                entity = "INSERT INTO `timesheet`.`year` (`ID`, `month`, `day_of_month`, `day_of_week`) VALUES ('" + id + "', '" + year.getMonth() + "', '" + year.getDayOfMonth() + "', '" + year.getDayOfWeek() + "');";
//                codeSQL.add(entity);
//                id++;
//            }
//
//            month = month.plus(1);
//        }
//        System.out.println(codeSQL.toString());
////        yearRepository.save(year);
//    }
}
