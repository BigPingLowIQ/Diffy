import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        String SERVER="192.168.43.1";
        int PORT=5000;

        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
        String file_name = (formatter.format(now)+".csv");
        BufferedFileWriter writer = new BufferedFileWriter(file_name,true,100);

        while(true) {
            try {
                Socket socket = new Socket(SERVER, PORT);

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    writer.writeLine(line);
                }

            } catch (Exception e) {
                System.out.println("Waiting...");
                writer.flush();
                Thread.sleep(1000);
//                e.printStackTrace();
            }
        }
    }

}