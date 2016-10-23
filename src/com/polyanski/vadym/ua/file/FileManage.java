package com.polyanski.vadym.ua.file;

import java.io.IOException;
import java.util.List;

/**
 * Created by vadym on 10.08.2016.
 */
public interface FileManage {
    public void addString(String programName) throws IOException;
    public List<String> getAllStrings() throws IOException;
    public void addToFile() throws IOException;
    public void clearAll() throws IOException;
}
