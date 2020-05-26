package gui;

import account.Profile.ClientList;
import facade.BankFacade;
import services.transactions.mediators.ServiceMediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private JButton button;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private ServiceMediator serviceMediator;
    private ClientList clientList;
    private BankFacade bankFacade;

    public MainFrame(final ServiceMediator serviceMediator, final ClientList clientList) {
        super("Hello");
        this.serviceMediator = serviceMediator;
        this.clientList = clientList;
        setLayout(new BorderLayout());
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        toolbar.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }
        });


        formPanel.setFormListener(new FormListener() {
            public void formEventOccured(FormEvent e) {
                String name = e.getName();
                String occupation = e.getOccupation();
                bankFacade = new BankFacade(name, occupation);
                textPanel.appendText(name + ": " + occupation + "\n");
            }
        });
        button = new JButton("click");
        add(textPanel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        add(toolbar, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPanel.appendText("Clicked \n");
            }
        });

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
