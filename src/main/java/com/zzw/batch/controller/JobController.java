package com.zzw.batch.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private Job job;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobLauncher jobLauncher;

    @GetMapping("operator/{message}")
    public String operator(@PathVariable String message) throws JobParametersInvalidException, JobInstanceAlreadyExistsException, NoSuchJobException {
        jobOperator.start("job","message=" + message);
        return "success";
    }

    @GetMapping("launcher/{message}")
    public String launcher(@PathVariable String message) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = new JobParametersBuilder()
                .addString("message",message)
                .toJobParameters();
        jobLauncher.run(job,parameters);
        return "success";
    }
}
