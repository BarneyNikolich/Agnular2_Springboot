package com.barney.scheduler_example.services;

import java.util.concurrent.Callable;

public class JobCallable implements Callable
{

    private String username;

    JobCallable(String username)
    {
        this.username = username;
    }

    @Override
    public Object call() throws Exception
    {
        for (int i = 0; i < 5000; i++)
        {
            System.out.println(i + " --> " + this.username);
            Thread.sleep(100);
        }

        return username;
    }

}
