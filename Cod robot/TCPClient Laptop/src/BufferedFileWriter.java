import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;

public class BufferedFileWriter {
    private BufferedWriter writer;
    private int flushInterval;
    private int writeCount;

    public BufferedFileWriter(String path, boolean append, int flushInterval) {
        this.flushInterval = flushInterval;
        try{
            writer = new BufferedWriter(new FileWriter(path, append));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String line){
        try{
            writer.write(line);
            writer.newLine();
            writeCount++;
            if(writeCount>=flushInterval){
                writer.flush();
                writeCount = 0;
            }
        }catch (IOException e) {}
    }
    public void flush() throws IOException {
        writer.flush();
    }
    public void close(){
        try{
            writer.close();
        } catch (IOException e) {
        }
    }
}
