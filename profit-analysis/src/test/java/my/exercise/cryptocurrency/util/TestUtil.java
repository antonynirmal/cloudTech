package my.exercise.cryptocurrency.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

import static org.junit.Assert.fail;

@Slf4j
public class TestUtil {
    public static String readResourceFile(String filename) throws IOException {
        // ensure that the input stream is closed when we are done with it
        try (InputStream in = TestUtil.class.getClassLoader().getResourceAsStream(
            filename)) {
            byte[] response = IOUtils.toByteArray(in);
            log.debug("Processing response: " + response);
            return new String(response);
        }
    }

    public static InputStream readResourceFileAsStream(String filename) throws IOException {
        InputStream in = TestUtil.class.getClassLoader().getResourceAsStream(
            filename);
        return in;
    }

    public static byte[] readResourceFileAsByteArray(String filename) throws IOException {
        // ensure that the input stream is closed when we are done with it
        try (InputStream in = TestUtil.class.getClassLoader().getResourceAsStream(
            filename)) {
            return IOUtils.toByteArray(in);
        }
    }

    public static <T> T timeSupplier(Supplier<T> supplier, String msg) {
        long startTime = System.currentTimeMillis();
        T result = supplier.get();
        long duration = System.currentTimeMillis() - startTime;
        log.info("It took: " + (duration / 1000.0F) + " seconds to run " + msg);
        return result;
    }

    public static <T extends Throwable> T runAndExpectException(Class<T> exceptionClass,
        String exceptionMessage, Runnable runnable) {
        boolean findOriginalExceptionCause = false;
        return runAndExpectException(exceptionClass, exceptionMessage, findOriginalExceptionCause,
            runnable);
    }

    public static <T extends Throwable> T runAndExpectException(Class<T> exceptionClass,
        String exceptionMessage, boolean findOriginalCause, Runnable runnable) {
        try {
            runnable.run();
            fail("Expected exception but none thrown");
        } catch (Throwable e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            Throwable causeException = findOriginalCause ? ((rootCause == null) ? e : rootCause)
                : e;
            if (exceptionClass != causeException.getClass()) {
                fail("Expected exception: " + exceptionClass.getName() + " but got: " + e.getClass()
                    .getName());
            }

            if (exceptionMessage != null && (causeException.getMessage() == null || !causeException
                .getMessage().contains(exceptionMessage))) {
                fail("Expected exception message '" + exceptionMessage + "' not found within: '"
                    + causeException.getMessage() + "'");
            }
            return (T) e;
        }

        // put this her just to stop the ide from complaining.
        return null;
    }

    public static long timeRunnable(Runnable runnable, String msg) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long duration = System.currentTimeMillis() - startTime;
        log.info("It took: " + (duration / 1000.0F) + " seconds to run " + msg);
        return duration;
    }
}
