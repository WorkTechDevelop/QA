package database.util;

import java.io.IOException;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;

public final class SqlLoaderQueryFromResources {

    private SqlLoaderQueryFromResources() {
    }

    public static String load(String fileName) {
        String path = "sql_query/" + fileName + ".sql";
        try (var is = currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalArgumentException("SQL file not found: " + path);
            }
            return new String(is.readAllBytes(), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load SQL file: " + path, e);
        }
    }
}
