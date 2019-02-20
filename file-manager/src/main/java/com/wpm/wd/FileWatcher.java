package com.wpm.wd;

import java.io.File;

/**
 * @author pengmingwang
 *
 */
public class FileWatcher implements Runnable {

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        final File file = new File("/home/pengmingwang/Downloads");
        try {
            new WatchDir(file, true, new FileActionCallback() {
                @Override
                public void create(File file) {
                    System.out.println("文件已创建\t" + file.getAbsolutePath());
                    // add some actions
                }

                @Override
                public void delete(File file) {
                    System.out.println("文件已删除\t" + file.getAbsolutePath());
                    // add some actions
                }

                @Override
                public void modify(File file) {
                    System.out.println("文件已修改\t" + file.getAbsolutePath());
                    // add some actions
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("正在监视文件夹:" + file.getAbsolutePath() + "的变化");
    }

}
