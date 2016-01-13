package compressTest;

import com.google.common.base.Charsets;
import com.google.common.io.Closer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by root on 15-6-24.
 */
public  class GZIPUtil {
    private static final Logger logger = LoggerFactory.getLogger(GZIPUtil.class);
    public static final int BUFFER = 1024;

    public GZIPUtil() {
    }

    public static byte[] compress(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data );
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            compress(bais, baos);
            byte[] e = baos.toByteArray();
            return e;
        } catch (IOException var7) {
            logger.error("", var7);
        } finally {
            Closer.close(new Closeable[]{bais, baos});
        }

        return null;
    }

    public static byte[] decompress(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            decompress(bais, baos);
            byte[] e = baos.toByteArray();
            return e;
        } catch (IOException var7) {
            logger.error("", var7);
        } finally {
            Closer.close(new Closeable[]{bais, baos});
        }

        return null;
    }

    public static void compress(InputStream is, OutputStream os) throws IOException {
        GZIPOutputStream gos = null;

        try {
            gos = new GZIPOutputStream(os);
            byte[] data = new byte[1024];

            int count;
            while((count = is.read(data, 0, 1024)) != -1) {
                gos.write(data, 0, count);
            }
        } finally {
            Closer.close(new Closeable[]{gos});
        }

    }

    public static void decompress(InputStream is, OutputStream os) throws IOException {
        GZIPInputStream gis = null;

        try {
            gis = new GZIPInputStream(is);
            byte[] data = new byte[1024];

            int count;
            while((count = gis.read(data, 0, 1024)) != -1) {
                os.write(data, 0, count);
            }
        } finally {
            Closer.close(new Closeable[]{gis});
        }

    }

    static class Closer{
        private static final Logger log = LoggerFactory.getLogger(Closer.class);
        private Closeable[] cs;

        public Closer(Closeable... cs) {
            this.cs = cs;
        }

        public static void close(Closeable... cs) {
            if(cs != null) {
                Closeable[] arr$ = cs;
                int len$ = cs.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Closeable c = arr$[i$];
                    if(c != null) {
                        try {
                            c.close();
                        } catch (Throwable var6) {
                            log.warn("资源关闭时出错:" + c.getClass().getName(), var6);
                        }
                    }
                }

            }
        }

        public static void close(ResultSet... cs) {
            if(cs != null) {
                ResultSet[] arr$ = cs;
                int len$ = cs.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    ResultSet c = arr$[i$];
                    if(c != null) {
                        try {
                            c.close();
                        } catch (Throwable var6) {
                            log.warn("资源关闭时出错:" + c.getClass().getName(), var6);
                        }
                    }
                }

            }
        }

        public static void close(Statement... cs) {
            if(cs != null) {
                Statement[] arr$ = cs;
                int len$ = cs.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Statement c = arr$[i$];
                    if(c != null) {
                        try {
                            c.close();
                        } catch (Throwable var6) {
                            log.warn("资源关闭时出错:" + c.getClass().getName(), var6);
                        }
                    }
                }

            }
        }

        public static void close(Iterable<Closeable> cs) {
            if(cs != null) {
                Iterator i$ = cs.iterator();

                while(i$.hasNext()) {
                    Closeable c = (Closeable)i$.next();
                    if(c != null) {
                        try {
                            c.close();
                        } catch (Throwable var4) {
                            log.warn("资源关闭时出错:" + c.getClass().getName(), var4);
                        }
                    }
                }

            }
        }

        public void recycle() {
            close(this.cs);
            this.cs = null;
        }
    }

}
