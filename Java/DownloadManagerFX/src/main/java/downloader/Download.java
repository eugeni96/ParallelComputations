package downloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.jnlp.DownloadService;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;


/**
 * Created by Евгений on 10.11.2015.
 */
public class Download extends RecursiveAction {

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

    @Override
    protected void compute() {
        System.out.print("Start download: " + fileName + "\n");
        fullPath = outputFolder + '/' + fileName;
        Path target = FileSystems.getDefault().getPath(fullPath);
        try (InputStream in = url.openStream()){
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.print("Finish download: " + fileName + "\n");
        }
        catch (IOException e)
        {
            System.out.print(e.getMessage());
        }


        String fileType = fullPath.substring(fullPath.lastIndexOf(".")+1,fullPath.length());
        if (Objects.equals(fileType, "html")) {
            if (depth > 0) {
                File input = new File(fullPath);
                Document doc = Jsoup.parse(input, "UTF-8");
                Elements links = doc.select("a[href]");
                List<RecursiveAction> actions = new ArrayList<>();
                for (Element elem : links) {
                    String newFilePath = elem.attr("href");
                    String newFileType = newFilePath.substring(newFilePath.lastIndexOf(".")+1,newFilePath.length());
                    if (Objects.equals(newFileType, "html")) {
                        URL newUrl = new URL(newFilePath);
                        addDownload(newUrl, outputFolder, depth - 1);
                    }
                }
            }
        }



    }
}
