package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Created by Евгений on 10.11.2015.
 */
public class Manager {

    private List<Download> downloads;

    public Manager(List<Download> downloads)
    {
        this.downloads = downloads;
    }

    public List<Download> getDownloads() {
        return downloads;
    }

    public Download addDownload(URL url, String outputFolder) throws IOException
    {
        Download download = new Download(url, outputFolder);
        downloads.add(download);
        download.start();
        return download;
    }
}
