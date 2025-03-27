package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesManager {

    private FilesManager() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void deleteFile(File dirPath) {
        if (dirPath == null || !dirPath.exists()) {
            ReportManager.warn("Directory does not exist: ", dirPath.toString());
            return;
        }

        File[] files = dirPath.listFiles();
        if (files == null) {
            ReportManager.warn("Failed to list files in: ", dirPath.toString());
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                deleteFile(file);
            } else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException ex) {
                    ReportManager.error("Failed to delete file: ", file.toString());
                }
            }
        }
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            ReportManager.warn("No files found in directory: ", folderPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile;
    }

    public static void cleanDirectory(File dirPath) {
        if (!dirPath.exists()) {
            ReportManager.warn("Directory does not exist: ", dirPath.toString());
            dirPath.mkdirs();
            ReportManager.info("Directory created: ", dirPath.toString());
        }

        try {
            FileUtils.cleanDirectory(dirPath);          // no exception is thrown if files are in use
        } catch (IOException e) {
            ReportManager.error(e.getMessage());
        }

    }
}
