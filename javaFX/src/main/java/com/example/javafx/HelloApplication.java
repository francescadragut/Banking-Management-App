package com.example.javafx;

import com.example.javafx.account.Account;
import com.example.javafx.account.AccountFactory;
import com.example.javafx.account.AccountInterface;
import com.example.javafx.client.Client;
import com.example.javafx.exceptions.BlockedException;
import com.example.javafx.exceptions.DeposeException;
import com.example.javafx.exceptions.RetrieveException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


@SuppressWarnings("serial")
public class HelloApplication extends JFrame {

    private JPanel contentPane;
    private JPanel opsPane;
    private JTextField txfImagePath;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HelloApplication frame = new HelloApplication();
                    frame.setVisible(true);
                } catch (Exception | BlockedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    @SuppressWarnings("unchecked")
    public HelloApplication() throws DeposeException, BlockedException {


        setResizable(false);
        setTitle("Client-side GUI for Banking Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setVisible(true);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel welcomeMessage = new JLabel("Enter your account details below:");
        welcomeMessage.setBounds(10, 10, 300, 20);
        contentPane.add(welcomeMessage);

        JLabel lblAccountType = new JLabel("Account Type:");
        lblAccountType.setBounds(10, 40, 130, 20);
        contentPane.add(lblAccountType);

        JTextField fieldAccountType = new JTextField(" ");
        fieldAccountType.setBounds(150, 40, 100, 20);
        contentPane.add(fieldAccountType);


        JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setBounds(10, 70, 130, 20);
        contentPane.add(lblAccountNumber);


        JTextField fieldAccountNumber = new JTextField(" ");
        fieldAccountNumber.setBounds(150, 70, 100, 20);
        contentPane.add(fieldAccountNumber);


        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(10, 100, 100, 20);
        contentPane.add(btnSubmit);

        Client client1 = (new Client.ClientBuilder("", "", Account.TYPE.UNDEF, "", 1398)).dob("").build();


        JComponent lblDeposit = new JLabel("Possible operations for this account:");
        lblDeposit.setBounds(10, 180, 1000, 14);
        lblDeposit.setBorder(new EmptyBorder(5, 5, 5, 5));
        lblDeposit.setVisible(false);
        contentPane.add(lblDeposit);

        JButton btnDeposit = new JButton("Deposit sum");
        btnDeposit.setBounds(10, 210, 150, 23);
        btnDeposit.setVisible(false);
        contentPane.add(btnDeposit);

        JButton btnRetrieve = new JButton("Retrieve sum");
        btnRetrieve.setBounds(10, 240, 150, 23);
        btnRetrieve.setVisible(false);
        contentPane.add(btnRetrieve);

        JButton btnTransfer = new JButton("Transfer sum");
        btnTransfer.setBounds(10, 270, 150, 23);
        btnTransfer.setVisible(false);
        contentPane.add(btnTransfer);

        JButton btnBlock = new JButton("Block account");
        btnBlock.setBounds(10, 300, 150, 23);
        btnBlock.setVisible(false);
        contentPane.add(btnBlock);

        JButton btnUnblock = new JButton("Unblock account");
        btnUnblock.setBounds(10, 330, 150, 23);
        btnUnblock.setVisible(false);
        contentPane.add(btnUnblock);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountType = fieldAccountType.getText();
                Account.TYPE accType = null;
                if (accountType.equals("EUR")){
                    accType = Account.TYPE.EUR;
                }
                else if (accountType.equals("RON")){
                    accType = Account.TYPE.RON;
                }
                String accountNumber = fieldAccountNumber.getText();

                btnDeposit.setVisible(true);
                btnRetrieve.setVisible(true);
                btnTransfer.setVisible(true);
                btnBlock.setVisible(true);
                btnUnblock.setVisible(true);

                final AccountFactory accountFactory = new AccountFactory();
                AccountInterface accountInterface = null;
                try {
                    accountInterface = accountFactory.accountFactory(accType, accountNumber, client1.getAmount());
                } catch (DeposeException | BlockedException ex) {
                    JOptionPane.showMessageDialog(null, "Account not found!");
                }
                AccountInterface finalAccountInterface = accountInterface;

                btnDeposit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String amount = JOptionPane.showInputDialog("Enter amount to deposit:");
                        try {
                            finalAccountInterface.depose(Double.parseDouble(amount));
                            JOptionPane.showMessageDialog(null, "Deposit successful!" + "\n" + "New balance (interest included): " + finalAccountInterface.getTotalAmount());
                        } catch (DeposeException | BlockedException ex) {
                            JOptionPane.showMessageDialog(null, "Deposit failed!" + "\nReason: " + ex.getMessage());
                        }
                    }
                });

                btnRetrieve.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String amount = JOptionPane.showInputDialog("Enter amount to retrieve:");

                        try {
                            finalAccountInterface.retrieve(Double.parseDouble(amount));
                            JOptionPane.showMessageDialog(null, "Retrieve successful!" + "\n" + "New balance (interest included): " + finalAccountInterface.getTotalAmount());
                        } catch (RetrieveException | BlockedException ex) {
                            JOptionPane.showMessageDialog(null, "Retrieve failed!" + "\n" + "Reason: " + ex.getMessage());
                        }

                    }
                });

                btnTransfer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        AccountInterface accountInterface1 = null;
                        String initialBalance = null;
                        String initialBalance1 = null;
                        try {
                            accountInterface1 = accountFactory.accountFactory(Account.TYPE.RON, "RON987",937);
                            accountInterface1.setAccountNumber("RON987");
                            accountInterface1.setAmount(937.0);
                            initialBalance = String.valueOf(finalAccountInterface.getTotalAmount());
                            initialBalance1 = String.valueOf(accountInterface1.getTotalAmount());

                        } catch (DeposeException | BlockedException ex) {
                            JOptionPane.showMessageDialog(null, "Transfer failed!" + "\n" + "Reason: " + ex.getMessage());
                        }

                        String amount = JOptionPane.showInputDialog("Enter amount to transfer:");
                        String accountNumber = JOptionPane.showInputDialog("Enter account number to transfer to:");

                        if (Objects.equals(accountNumber, "RON987")){
                            Double newAmount = null;
                            try {
                                if (finalAccountInterface.getType().equals(Account.TYPE.EUR) && accountInterface1.getType().equals(Account.TYPE.RON)){
                                    newAmount = Double.parseDouble(amount) * 4.8;
                                    finalAccountInterface.transfer(accountInterface1, Double.parseDouble(amount));
                                }
                                else if (finalAccountInterface.getType().equals(Account.TYPE.RON) && accountInterface1.getType().equals(Account.TYPE.EUR)){
                                    newAmount = Double.parseDouble(amount) / 4.8;
                                    finalAccountInterface.transfer(accountInterface1, Double.parseDouble(amount));
                                }
                                else {
                                    newAmount = Double.parseDouble(amount);
                                    finalAccountInterface.transfer(accountInterface1, Double.parseDouble(amount));
                                }

                                String initialFirstString = "Inital balance (interest included) for account " + finalAccountInterface.getAccountNumber() + ": " + String.valueOf(initialBalance);
                                String finalFirstString = "Final balance (interest included) for account " + finalAccountInterface.getAccountNumber() + ": " + String.valueOf(finalAccountInterface.getTotalAmount());
                                String initialSecondString = "Inital balance (interest included) for account " + accountInterface1.getAccountNumber() + ": " + String.valueOf(initialBalance1);
                                String finalSecondString = "Final balance (interest included) for account " + accountInterface1.getAccountNumber() + ": " + String.valueOf(accountInterface1.getTotalAmount());
                                String sumTransfered = "Sum transfered: " + amount + " " + finalAccountInterface.getType() + " / " + newAmount + " " + accountInterface1.getType();

                                String transferDetails = "Transfer successful!\n\n" + initialFirstString + "\n" + finalFirstString + "\n\n" + initialSecondString + "\n" + finalSecondString + "\n\n" + sumTransfered;

                                JOptionPane.showMessageDialog(null, transferDetails);
                            } catch (DeposeException | RetrieveException | BlockedException ex) {
                                JOptionPane.showMessageDialog(null, "Transfer failed!" + "\n" + "Reason: " + ex.getMessage());
                            }

                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Transfer failed!" + "\n" + "Reason: " + "Account number not found!");
                        }


                    }
                });

                btnBlock.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        finalAccountInterface.blockAccount();
                        JOptionPane.showMessageDialog(null, "Account blocked!");
                    }
                });

                btnUnblock.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        finalAccountInterface.unblockAccount();
                        JOptionPane.showMessageDialog(null, "Account unblocked!");
                    }
                });

                try {
                    Client cl1 = (new Client.ClientBuilder("Mihai Popescu", "Cluj", accType, accountNumber, client1.getAmount())).dob("10.06.2001").build();
                    JLabel clientData = new JLabel("Name: " + cl1.getName() + " Address: " + cl1.getAddress() + " DOB: " + cl1.getDOB() + " Account Type: " + accountType + " Account Number: " + cl1.getAccountNumber() + " Amount: " + cl1.getAmount());
                    clientData.setBounds(10, 140, 1000, 14);
                    contentPane.add(clientData);


                    lblDeposit.setVisible(true);


                } catch (DeposeException | BlockedException ex) {
                    JOptionPane.showMessageDialog(null, "Account creation failed!" + "\n" + "Reason: " + ex.getMessage());
                }
            }
        });
    }


}
