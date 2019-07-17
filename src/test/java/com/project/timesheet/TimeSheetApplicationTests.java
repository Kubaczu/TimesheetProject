package com.project.timesheet;

import com.project.timesheet.Entities.TimeEntryRepository;
import com.project.timesheet.User.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSheetApplicationTests {
	@Autowired
	UserService userService;

	@Autowired
	TimeEntryRepository timeEntryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void checkCalculations(){

		userService.calculateSalary(timeEntryRepository.showHoursByDate("2019-07-01", "2019-07-31"));
	}

}
