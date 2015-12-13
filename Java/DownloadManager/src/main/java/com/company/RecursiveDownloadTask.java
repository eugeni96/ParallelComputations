package com.company;

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
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Yauheni on 12/13/2015.
 */
public class RecursiveDownloadTask extends RecursiveAction {

    private URL url;
    private String outputFolder;
    private int depth;

    public RecursiveDownloadTask(URL url, String outputFolder, int depth) {
        this.url = url;
        this.outputFolder = outputFolder;
        this.depth = depth;
    }

    @Override
    protected void compute() {
        try {
            Download download = new Download(url, outputFolder);
            //downloads.add(download);
            String filePath = download.call();
            String fileType = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length());
            if (Objects.equals(fileType, "html") && depth > 0) {

                List<RecursiveDownloadTask> newTasks = new ArrayList<>();

                File input = new File(filePath);
                Document doc = Jsoup.parse(input, "UTF-8");
                Elements links = doc.select("a[href]");
                for (Element elem : links) {
                    String newFilePath = elem.attr("href");
                    //String newFileType = newFilePath.substring(newFilePath.lastIndexOf(".")+1,newFilePath.length());
                    UrlValidator urlValidator = new UrlValidator();
                    if (urlValidator.isValid(newFilePath)) {
                        URL newUrl = new URL(newFilePath);
                        newTasks.add(new RecursiveDownloadTask(newUrl, outputFolder, depth - 1));
                        // addDownload(newUrl, outputFolder, depth - 1);
                    }
                }

                invokeAll(newTasks);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }
}
