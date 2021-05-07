package javaLab.endsemLab.serVerClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {

    static class Details implements Serializable{
        String name;
        int rollNo;
        Details(String name , int rollNo) {
            this.name = name;
            this.rollNo = rollNo;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Details)) {
                return false;
            }
            Details d = (Details)obj;
            return this.name.equals(d.name) || this.rollNo == d.rollNo;
        }
    }

    static JFrame frame1;
    static JFrame adminFrame;
    static JFrame userFrame;


    static class AddDetailsForm extends JFrame {
        AddDetailsForm(String name) {
            super(name);
            JPanel addDetailsPanel = new JPanel(new GridLayout(2 , 2));
            JLabel nameLabel = new JLabel("nameLabel");
            nameLabel.setText("Enter name");
            JTextField nameText = new JTextField("");
            JLabel rollNoLabel = new JLabel("rollNoLabel");
            rollNoLabel.setText("Enter rollNo");
            JTextField rollNoText = new JTextField("");
            addDetailsPanel.add(nameLabel);addDetailsPanel.add(nameText);
            addDetailsPanel.add(rollNoLabel);addDetailsPanel.add(rollNoText);
            JPanel container = new JPanel(new GridLayout(2 , 1));
            container.add(addDetailsPanel);
            JButton submit = new JButton("submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        out.writeUTF("1");
                        out.writeUTF(nameText.getText() + " " + rollNoText.getText());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            container.add(submit);
            add(container);
            setSize(new Dimension(400 , 400));
            setVisible(true);
        }
    }



    static class DeleteDetailsForm extends JFrame {
        DeleteDetailsForm(String name) {
            super(name);
            JPanel addDetailsPanel = new JPanel(new GridLayout(2 , 2));
            JLabel nameLabel = new JLabel("nameLabel");
            nameLabel.setText("Enter name");
            JTextField nameText = new JTextField("");
            JLabel rollNoLabel = new JLabel("rollNoLabel");
            rollNoLabel.setText("Enter rollNo");
            JTextField rollNoText = new JTextField("");
            addDetailsPanel.add(nameLabel);addDetailsPanel.add(nameText);
            addDetailsPanel.add(rollNoLabel);addDetailsPanel.add(rollNoText);
            JPanel container = new JPanel(new GridLayout(2 , 1));
            container.add(addDetailsPanel);
            JButton submit = new JButton("delete");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        out.writeUTF("2");
                        out.writeUTF(nameText.getText() + " " + rollNoText.getText());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            container.add(submit);
            add(container);
            setSize(new Dimension(400 , 400));
            setVisible(true);
        }
    }

    static class modifyDetailsForm extends JFrame {
        modifyDetailsForm(String name) {
            super(name);
            JPanel prevDetailsPanel = new JPanel(new GridLayout(2 , 2));
            JLabel nameLabel = new JLabel();
            nameLabel.setText("Enter name");
            JTextField nameText = new JTextField("");
            JLabel rollNoLabel = new JLabel();
            rollNoLabel.setText("Enter rollNo");
            JTextField rollNoText = new JTextField("");
            prevDetailsPanel.add(nameLabel);prevDetailsPanel.add(nameText);
            prevDetailsPanel.add(rollNoLabel);prevDetailsPanel.add(rollNoText);

            JPanel newDetailsPanel = new JPanel(new GridLayout(2 , 2));
            JLabel newNameLabel = new JLabel();
            newNameLabel.setText("Enter name");
            JTextField newNameText = new JTextField("");
            JLabel newNollNoLabel = new JLabel();
            newNollNoLabel.setText("Enter rollNo");
            JTextField newRollNoText = new JTextField("");
            newDetailsPanel.add(newNameLabel);newDetailsPanel.add(newNameText);
            newDetailsPanel.add(newNollNoLabel);newDetailsPanel.add(newRollNoText);

            JPanel container = new JPanel(new GridLayout(5 , 1));
            JLabel prevDetailsLabel = new JLabel();
            prevDetailsLabel.setText("Previous details");
            container.add(prevDetailsLabel);
            container.add(prevDetailsPanel);
            JLabel newDetailsLabel = new JLabel();
            newDetailsLabel.setText("new details");
            container.add(newDetailsLabel);
            container.add(newDetailsPanel);

            JButton submit = new JButton("submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Details prevDetails = new Details(nameText.getText() , Integer.parseInt(rollNoText.getText()));
                    Details newDetails = new Details(newNameText.getText() , Integer.parseInt(newRollNoText.getText()));
                    try {
                        out.writeUTF("3");
                        out.writeUTF(prevDetails.name + " " + prevDetails.rollNo);
                        out.writeUTF(newDetails.name + " " + newDetails.rollNo);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            container.add(submit);
            add(container);
            setSize(new Dimension(400 , 400));
            setVisible(true);
        }
    }

    static class ViewDetailsByRollNo extends JFrame {
        ViewDetailsByRollNo(String name) {
            super(name);
            JPanel detailsPanel = new JPanel(new GridLayout(1 , 2));
            JLabel rollNoLabel = new JLabel();
            rollNoLabel.setText("Enter rollNo");
            JTextField rollNoText = new JTextField("");
            detailsPanel.add(rollNoLabel);detailsPanel.add(rollNoText);

            JPanel resPanel = new JPanel(new GridLayout(1 , 2));
            JLabel label = new JLabel("Student details : ");
            JLabel res = new JLabel();
            resPanel.add(label);resPanel.add(res);

            JPanel container = new JPanel(new GridLayout(3 , 1));
            container.add(detailsPanel);
            container.add(resPanel);

            JButton submit = new JButton("Get Details");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Details details = new Details("" , Integer.parseInt(rollNoText.getText()));

                    try {
                        out.writeUTF("4");
                        out.writeUTF(String.valueOf(details.rollNo));
                        res.setText(in.readUTF());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            container.add(submit);
            add(container);
            setSize(new Dimension(400 , 400));
            setVisible(true);
        }
    }

    static class ViewDetailsByName extends JFrame {
        ViewDetailsByName(String name) {
            super(name);
            JPanel detailsPanel = new JPanel(new GridLayout(1 , 2));
            JLabel nameLable = new JLabel();
            nameLable.setText("Enter name");
            JTextField nameText = new JTextField("");
            detailsPanel.add(nameLable);detailsPanel.add(nameText);

            JPanel resPanel = new JPanel(new GridLayout(1 , 2));
            JLabel label = new JLabel("Student details : ");
            JLabel res = new JLabel();
            resPanel.add(label);resPanel.add(res);

            JPanel container = new JPanel(new GridLayout(3 , 1));
            container.add(detailsPanel);
            container.add(resPanel);

            JButton submit = new JButton("Get Details");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Details details = new Details(nameText.getText() , 0);
                    try {
                        out.writeUTF("5");
                        out.writeUTF(details.name);
                        res.setText(in.readUTF());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            container.add(submit);
            add(container);
            setSize(new Dimension(400 , 400));
            setVisible(true);
        }
    }

    static Socket connection;
    static DataInputStream in;
    static DataOutputStream out;

    public static void main(String[] args) throws IOException {


        connection = new Socket("192.168.43.168" , 2000);
        in = new DataInputStream(connection.getInputStream());
        out = new DataOutputStream(connection.getOutputStream());

        //add details form
        JPanel addDetailsPanel = new JPanel(new GridLayout(2 , 2));



        JButton addDetails = new JButton("addDetails");
        addDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adminFrame.setVisible(false);
                new AddDetailsForm("addDetails");
            }
        });
        JButton deleteDetails = new JButton("deleteDetails");
        deleteDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adminFrame.setVisible(false);
                new DeleteDetailsForm("deleteDetails");
            }
        });
        JButton modifyDetails = new JButton("modifyDetails");
        modifyDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adminFrame.setVisible(false);
                new modifyDetailsForm("modifyDetails");
            }
        });

        JPanel adminPanel = new JPanel(new GridLayout(3 , 1));
        adminPanel.add(addDetails);adminPanel.add(deleteDetails);adminPanel.add(modifyDetails);
        adminFrame = new JFrame("adminFrame");
        adminFrame.add(adminPanel);
        adminFrame.setSize(new Dimension(400 , 400));

        JButton viewByRollNo = new JButton("viewByRollNo");
        viewByRollNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //userFrame.setVisible(false);
                new ViewDetailsByRollNo("ViewDetailsByRollNo");
            }
        });
        JButton viewByName = new JButton("viewByName");
        viewByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //userFrame.setVisible(false);
                new ViewDetailsByName("ViewDetailsByRollNo");
            }
        });
        JPanel userPanel = new JPanel(new GridLayout(2 , 1));
        userPanel.add(viewByName);userPanel.add(viewByRollNo);
        userFrame = new JFrame("userFrame");
        userFrame.add(userPanel);
        userFrame.setSize(new Dimension(400 , 400));

        frame1= new JFrame("frame1");
        JButton admin = new JButton("admin");
        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.setVisible(true);
                //frame1.setVisible(false);
            }
        });

        JButton user = new JButton("user");
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFrame.setVisible(true);
                //frame1.setVisible(false);
            }
        });
        JPanel panel = new JPanel(new GridLayout(2 , 1));
        panel.add(admin);
        panel.add(user);
        frame1.add(panel);
        frame1.setSize(new Dimension(400 , 400));
        frame1.setVisible(true);
    }
}
/*
SSID:	nik
Protocol:	Wi-Fi 4 (802.11n)
Security type:	WPA2-Personal
Network band:	2.4 GHz
Network channel:	1
IPv6 address:	2409:4063:428b:a584:181:9c80:220d:1e91
Link-local IPv6 address:	fe80::181:9c80:220d:1e91%4
IPv4 address:	192.168.43.168
IPv4 DNS servers:	192.168.43.1
Manufacturer:	Intel Corporation
Description:	Intel(R) Dual Band Wireless-AC 8265
Driver version:	20.70.13.2
Physical address (MAC):	76-1A-83-B3-35-94

 */