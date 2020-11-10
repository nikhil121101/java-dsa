package javaLab.lab11;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket connection = new Socket("192.168.43.168" , 2000);
        DataInputStream in = new DataInputStream(connection.getInputStream());
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter filename to get details");
        String fileName = br.readLine();

        out.writeUTF(fileName);

        String response = in.readUTF();
        boolean flag = false;
        //System.out.println("response : " + response);
        if(response.startsWith("Choose one of these files and reply back")) {
            //TODO choose file
            String[] str = response.split("\n");
            System.out.println("Enter file not found you can choose one of these files or quit");
            System.out.println("enter 1 for " + str[1]);
            System.out.println("enter 2 for " + str[2]);
            System.out.println("enter 3 for " + str[3]);
            System.out.println("enter 4 to quit ");
            int token = Integer.parseInt(br.readLine());
            if(token == 1) {
                out.writeUTF(str[1]);
            }
            else if(token == 2) {
                out.writeUTF(str[2]);
            }
            else if(token == 3) {
                out.writeUTF(str[3]);
            }
            else {
                out.writeUTF("quit");
                flag = true;
            }
            if(!flag)
            response = in.readUTF();
            //System.out.println("response : " + response);
        }
        if(!flag)
        System.out.println(response);

        connection.close();
        in.close();
        out.close();
    }
}
/*

192.168.43.168

 */