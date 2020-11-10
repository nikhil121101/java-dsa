package javaLab.lab11;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static String currentDirectory = "C:\\Users\\ANSHIKA\\IdeaProjects\\java dsa\\src\\javaLab\\lab11";

    static class FileDetails {
        String path;
        int lines , words;

        FileDetails() {

        }

        FileDetails(String path , int lines , int words) {
            this.path = path;
            this.words = words;
            this.lines = lines;
        }

        public String toString() {
            return "path : " + path + "\n" + "lines - " + lines + "\nwords - " + words + "\n";
        }

    }

    static FileDetails getFileDetails(String fileName) throws IOException {
        String path = currentDirectory + "\\" + fileName;
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        int lines = 0 , words = 0;
        while(true) {
            String line = br.readLine();
            if(line == null || line.isEmpty()) break;
            lines++;
            String[] wordsArray = line.split(" ");
            words += wordsArray.length;
        }
        return new FileDetails(path , lines , words);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000 , 3);
        System.out.println("Waiting for client to join");
        Socket connection = serverSocket.accept();
        System.out.println("Client accepted");
        System.out.println("waiting for client to send request");
        DataInputStream in = new DataInputStream(connection.getInputStream());
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        String inputFileName = in.readUTF();
        //System.out.println("inputFileName : " + inputFileName);
        File currentFolder = new File(currentDirectory);
        boolean found = false;
        for(File file : currentFolder.listFiles()) {
            if(file.getName().equals(inputFileName)) {
                found = true;
                FileDetails fileDetails = getFileDetails(inputFileName);
                // TODO send details
                out.writeUTF(fileDetails.toString());
            }
        }
        if(!found) {
            int i = 0;
            StringBuilder sb = new StringBuilder("");
            sb.append("Choose one of these files and reply back\n");
            for(File file : currentFolder.listFiles()) {
                if(i == 3) {
                    break;
                }
                if(file.getName().startsWith(inputFileName.substring(0 , 2))) {
                    sb.append(file.getName()).append("\n");
                    i++;
                }
            }
            out.writeUTF(sb.toString());
            String newFileName = in.readUTF();
            if(!newFileName.equals("quit")) {
                //System.out.println("newFileName : " + newFileName);
                for(File file : currentFolder.listFiles()) {
                    if(file.getName().equals(newFileName)) {
                        //TODO send file details
                        FileDetails fileDetails = getFileDetails(newFileName);
                        out.writeUTF(fileDetails.toString());
                    }
                }
            }
        }
        serverSocket.close();
        connection.close();
        in.close();
        out.close();
    }

}
