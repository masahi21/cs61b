package gitlet;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * A temp directory class for Java NIO2.
 * @author ato (https://gist.github.com/ato/6774390)
 */
class TempDirectory {

    /**
     * The path.
     */
    final Path path;

    /**
     * Starts the temporary directory.
     * @param start
     * @param prefix
     */
    TempDirectory(Path start, String prefix) {
        try {
            this.path = Files.createTempDirectory(start, prefix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the path.
     * @return
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * Deletes the temporary directory when exited.
     */
    public void deleteOnExit() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                TempDirectory.this.delete();
            }
        });
    }

    /**
     * Deletes the temporary directory.
     */
    public void delete() {
        if (!Files.exists(this.path)) {
            return;
        }
        try {
            Files.walkFileTree(this.path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult postVisitDirectory(Path dir,
                        IOException exc) throws IOException {
                    Files.deleteIfExists(dir);
                    return super.postVisitDirectory(dir, exc);
                }

                @Override
                public FileVisitResult visitFile(Path file,
                        BasicFileAttributes attrs) throws IOException {
                    Files.deleteIfExists(file);
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
