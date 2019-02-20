/**
 * 
 */
package com.wpm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wpm.wd.FileWatcher;

/**
 * @author pengmingwang
 *
 */
@SpringBootApplication
public class FileManagerApp {

    private static final Log LOG = LogFactory.getLog(FileManagerApp.class);

    private static void initLog() {
        final String logConfFileName = (String) System.getProperties().get("log4j.configuration");
        DOMConfigurator.configure(logConfFileName);
        LOG.info("Load log configuration file from " + logConfFileName);
    }

    private static void initWatcher() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new FileWatcher());

    }

    public static void main(String[] args) {
        initLog();
        initWatcher();
        SpringApplication.run(FileManagerApp.class, args);
    }

}
