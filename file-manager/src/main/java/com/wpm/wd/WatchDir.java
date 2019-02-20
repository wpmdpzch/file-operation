package com.wpm.wd;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengmingwang
 *
 */
public class WatchDir {
    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final boolean subDir;

    public WatchDir(File file, boolean subDir, FileActionCallback callback) throws Exception {
        if (!file.isDirectory())
            throw new Exception(file.getAbsolutePath() + "is not a directory!");

        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();
        this.subDir = subDir;

        Path dir = Paths.get(file.getAbsolutePath());

        if (subDir) {
            registerAll(dir);
        } else {
            register(dir);
        }
        processEvents(callback);
    }

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    /**
     * @param dir
     * @throws IOException
     */
    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        keys.put(key, dir);
    }

    /**
     * @param start
     * @throws IOException
     */
    private void registerAll(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @SuppressWarnings("rawtypes")
    void processEvents(FileActionCallback callback) {
        for (;;) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("Operation unrecognized");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                Kind kind = event.kind();

                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                // Changes in directories may be files or directories
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);
                File file = child.toFile();
                if (kind.name().equals(FileAction.DELETE.getValue())) {
                    callback.delete(file);
                } else if (kind.name().equals(FileAction.CREATE.getValue())) {
                    callback.create(file);
                } else if (kind.name().equals(FileAction.MODIFY.getValue())) {
                    callback.modify(file);
                } else {
                    continue;
                }

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (subDir && (kind == StandardWatchEventKinds.ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                    }
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                // Remove inaccessible directories
                // Because it is possible to remove the directory, it will not be accessible.
                keys.remove(key);
                // If none of the directories to be monitored exists, execution is interrupted
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
}
