package com.polyanski.vadym.ua.killer;

import java.util.List;

/**
 * Created by vadym on 10.08.2016.
 */
public interface KillerOfPrograms {
    public void killProcess(String serviceName) throws Exception;
    public boolean isProcessRunging(String serviceName) throws Exception;
    public void mainAllKill(List<String> fileNames) throws Exception;
}
