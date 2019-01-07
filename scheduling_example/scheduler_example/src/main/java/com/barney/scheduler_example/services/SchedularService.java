package com.barney.scheduler_example.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class SchedularService
{
    private Map<String, Future<String>> jobPool = new HashMap();

    ExecutorService executorService = Executors.newFixedThreadPool(10);


    public String addJobToPool(String username)
    {
        if (!this.jobPool.containsKey(username))
        {
            JobCallable jobCallable = new JobCallable(username);
            Future<String> job = executorService.submit(jobCallable);
            this.jobPool.put(username, job);

            try
            {
                String result = job.get();
                return "Completed job for user: " + result;
            }
            catch (InterruptedException | CancellationException | ExecutionException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public void cancelJob(String username)
    {
        if (this.jobPool.containsKey(username))
        {
            Future<String> job = this.jobPool.get(username);
            job.cancel(true);
            this.jobPool.remove(username);
        }
    }

}
