package com.company;

import javax.jnlp.DownloadService;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Callable;


/**
 * Created by Евгений on 10.11.2015.
 */
public class Download implements Callable<String> {

    private String name;

    private URL url;

    private String outputFolder;

    private String fileName;

    private String fullPath;

    public Download(URL url, String outputFolder)
    {
        this.url = url;
        this.outputFolder = outputFolder;
        String fileURL = url.getFile();
        fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1);
    }


    @Override
    public String call() throws Exception {
        System.out.print("Start download: " + fileName + "\n");
        fullPath = outputFolder + '/' + fileName;
        Path target = FileSystems.getDefault().getPath(fullPath);
        try (InputStream in = url.openStream()){
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.print("Finish download: " + fileName + "\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fullPath;
    }
}
