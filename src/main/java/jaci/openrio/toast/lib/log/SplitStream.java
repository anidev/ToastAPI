package jaci.openrio.toast.lib.log;

import java.io.IOException;
import java.io.OutputStream;

public class SplitStream extends OutputStream {

    OutputStream[] outs;

    public SplitStream(OutputStream... streams) {
        this.outs = streams;
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream stream : outs)
            stream.write(b);
    }

    @Override
    public void write(byte b[]) throws IOException {
        for (OutputStream stream : outs)
            stream.write(b);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        for (OutputStream stream : outs)
            stream.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        for (OutputStream stream : outs)
            stream.flush();
    }

    @Override
    public void close() throws IOException {
        for (OutputStream stream : outs)
            stream.close();
    }
}
