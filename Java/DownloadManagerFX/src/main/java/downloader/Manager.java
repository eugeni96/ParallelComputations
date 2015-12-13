package downloader;

import javafx.concurrent.Task;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public void addDownload(URL url, String outputFolder, int depth) throws IOException
    {
        downloadService.submit(createDownloadTask(url, outputFolder));
    }

    private ExecutorService downloadService = Executors.newScheduledThreadPool(8);

    private Task<Void> createDownloadTask(URL url, String outputFolder){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Download download = new Download(url, outputFolder);
                downloads.add(download);
                downloadService.submit(Executors.callable(download));

            return null;
            }
        };
    }

}
