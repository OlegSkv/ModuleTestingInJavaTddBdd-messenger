package com.epam.ld.module2.testing;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Optional;

public class TestExecutionInfo implements TestWatcher {

    private static final Path reportFile;

    static {
        // using reportFile static initialization to delete file before
        // running all the tests. If we need to have access to something at runtime, consider:
        // https://stackoverflow.com/questions/43282798/in-junit-5-how-to-run-code-before-all-tests
        reportFile = Paths.get("build/reports/testExecutionReport.txt");
        File file = reportFile.toFile();
        if ((file.exists())) {
            if (!file.delete()) {
                throw new RuntimeException("Error during deletion of " + reportFile.toAbsolutePath());
            }
        }
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        writeToFile(context, " : DISABLED\n");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        writeToFile(context, " : PASSED\n");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        writeToFile(context, " : ABORTED\n");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        writeToFile(context, " : FAILED\n");
    }

    private void writeToFile(ExtensionContext context, String status) {
        String methodName = getMethodName(context);
        String className = context.getRequiredTestMethod().getDeclaringClass().getSimpleName();
        String time = LocalDateTime.now().toString();
        try {
            Files.write(reportFile, (time + " " + className + " -> " + methodName + status)
                    .getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getMethodName(ExtensionContext context) {
        return context.getRequiredTestMethod().getName();
    }
}
