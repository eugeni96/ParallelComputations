package com.company;

import javafx.concurrent.Task;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by Евгений on 10.11.2015.
 */
public class Manager {

    private List<Download> downloads;

    private List<RecursiveDownloadTask> downloadTasks;

    private ForkJoinPool downloadPool;

    public Manager()
    {
        downloads = new ArrayList<>();
        downloadTasks = new ArrayList<>();
        downloadPool = new ForkJoinPool(8);
    }

    public List<Download> getDownloads() {
        return downloads;
    }



    public void addDownload(URL url, String outputFolder, int depth) throws Exception
    {
        RecursiveDownloadTask downloadTask = new RecursiveDownloadTask(url, outputFolder, depth);
        downloadTasks.add(downloadTask);
        downloadPool.invoke(downloadTask);
    }



}
