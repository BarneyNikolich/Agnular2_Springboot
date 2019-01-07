package com.barney.scheduler_example.controllers;

import com.barney.scheduler_example.services.SchedularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SchedulerController
{
    private SchedularService schedularService;

    @Autowired
    SchedulerController(SchedularService schedularService)
    {
        this.schedularService = schedularService;
    }

    @PostMapping
    @ResponseBody
    public String scheduleJob(@RequestParam("name") String name)
    {
        return this.schedularService.addJobToPool(name);
    }

    @PostMapping(value = "cancel")
    public void cancelJob(@RequestParam("name") String name)
    {
        this.schedularService.cancelJob(name);
    }

}
