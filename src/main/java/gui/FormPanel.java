package gui;

import persons.clients.SimpleClient;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okButton;
    private FormListener formListener;
    private JList ageList;

    public FormPanel() {
        Dimension dimension = getPreferredSize();
        dimension.width = 250;
        setPreferredSize(dimension);

        ageList = new JList();
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Password: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        okButton = new JButton("Login");

        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement("18 or under");
        ageModel.addElement("18 to 65");
        ageModel.addElement("65 or over");

        ageList.setModel(ageModel);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                FormEvent event = new FormEvent(this, name, occupation);
                if(formListener != null){
                    formListener.formEventOccured(event);
                }
            }
        });
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(nameField, gridBagConstraints);

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gridBagConstraints);

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gridBagConstraints);


        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 2.0;

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okButton, gridBagConstraints);
    }
    public void setFormListener(FormListener formListener){
        this.formListener = formListener;
    }
}
