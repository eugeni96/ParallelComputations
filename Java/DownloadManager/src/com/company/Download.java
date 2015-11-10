package com.company;

import javax.jnlp.DownloadService;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


/**
 * Created by Евгений on 10.11.2015.
 */
public class Download implements Runnable {

    private String name;

    private Thread thread;

    private URL url;

    private String outputFolder;

    private String fileName;

    public Download(String name, URL url, String outputFolder)
    {
        this.name = name;
        this.url = url;
        this.outputFolder = outputFolder;
        String fileURL = url.getFile();
        fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1);
    }

    @Override
    public void run() {
        System.out.print("Start download: " + fileName + "\n");
        HttpURLConnection conn = null;
        Path target = FileSystems.getDefault().getPath(outputFolder + '/' + fileName);
        try (InputStream in = url.openStream()){
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.print("Finish download: " + fileName + "\n");
        }
        catch (IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void start()
    {
        if(thread == null)
        {
            thread = new Thread(this, name);
            thread.start();
        }
    }
}
