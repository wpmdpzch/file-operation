/**
 * 
 */
package com.wpm.wd;

import java.io.File;

/**
 * @author pengmingwang
 *
 */
public abstract class FileActionCallback {

    public void delete(File file) {
    };

    public void modify(File file) {
    };

    public void create(File file) {
    };
}
