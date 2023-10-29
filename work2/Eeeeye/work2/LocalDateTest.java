package com.Eeeeye.base.work2;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LocalDateTest {
     public static void main(String[] args) {
          LocalDate nowDate = LocalDate.now();
          System.out.println("当前日期为"+nowDate);
          LocalDate setDate = LocalDate.of(2002, 2,2);
          System.out.println("构造日期为"+setDate);
          int setDateYear = setDate.getYear();
          int nowYear = nowDate.getYear();
          System.out.println("构造的年份为"+setDateYear);
          int setDateMonth = setDate.getMonthValue();
          System.out.println("构造的月份为"+setDateMonth);
          DayOfWeek dayOfWeek = setDate.getDayOfWeek();
          System.out.println("周几？"+dayOfWeek);
     }
}
