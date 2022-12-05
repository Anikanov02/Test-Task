package com.test.task.frontend.client.handlers.content;

import com.google.gwt.user.client.ui.*;
import com.test.task.frontend.Constants;
import com.test.task.frontend.client.domain.dto.ContractDto;

public class ContractDataComposite extends Composite {
    private final Label infoLabel;
    private final Label idLabel;
    private final TextBox subscriptionDate;
    private final TextBox startDate;
    private final TextBox expirationDate;
    private final TextBox sumInsured;
    private final TextBox contractSum;
    private final ListBox isArchived;
    private ContractDto model;

    public ContractDataComposite() {
        final VerticalPanel panel = new VerticalPanel();
        infoLabel = new Label();
        idLabel = new Label("id: ");
        subscriptionDate = new TextBox();
        startDate = new TextBox();
        expirationDate = new TextBox();
        sumInsured = new TextBox();
        contractSum = new TextBox();
        isArchived = new ListBox();
        isArchived.addItem("false");
        isArchived.addItem("true");
        isArchived.setMultipleSelect(false);
        panel.add(infoLabel);
        panel.add(idLabel);
        panel.add(new Label("subscriptionDate: "));
        panel.add(subscriptionDate);
        panel.add(new Label("startDate: "));
        panel.add(startDate);
        panel.add(new Label("expirationDate: "));
        panel.add(expirationDate);
        panel.add(new Label("sumInsured: "));
        panel.add(sumInsured);
        panel.add(new Label("contractSum: "));
        panel.add(contractSum);
        panel.add(new Label("isArchived: "));
        panel.add(isArchived);
        refresh();
        initWidget(panel);
    }

    public void loadData(ContractDto data) {
        model = data;
        idLabel.setText("id: " + data.getId().toString());
        subscriptionDate.setText(data.getSubscriptionDate());
        startDate.setText(data.getStartDate());
        expirationDate.setText(data.getExpirationDate());
        sumInsured.setText(data.getSumInsured().toString());
        contractSum.setText(data.getContractSum().toString());
        isArchived.setItemSelected(data.getArchived() ? 1 : 0, true);
    }

    public boolean validated() {
        if(model != null) {
            if(!subscriptionDate.getText().matches(Constants.DATE_REGEX)) {
                message("Subscription date should be of pattern yyyy-MM-dd");
                return false;
            }

            if(!startDate.getText().matches(Constants.DATE_REGEX)) {
                message("Start date should be of pattern yyyy-MM-dd");
                return false;
            }

            if(!expirationDate.getText().matches(Constants.DATE_REGEX)) {
                message("Expiration date should be of pattern yyyy-MM-dd");
                return false;
            }

            if(sumInsured.getText().equals("")) {
                message("You should specify sum insured");
                return false;
            }

            if(contractSum.getText().equals("")) {
                message("You should specify contract sum");
                return false;
            }

            if(!isArchived.isItemSelected(0) && !isArchived.isItemSelected(1)) {
                message("Select whether contract is archived or not");
                return false;
            }
        }
        return true;
    }

    public void refresh() {
        infoLabel.setText("");
        infoLabel.setVisible(false);
    }

    public void message(String message) {
        infoLabel.setText(message);
        infoLabel.getElement().getStyle().setColor("red");
        infoLabel.setVisible(true);
    }

    public ContractDto getModel() {
        return model;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public TextBox getSubscriptionDate() {
        return subscriptionDate;
    }

    public TextBox getStartDate() {
        return startDate;
    }

    public TextBox getExpirationDate() {
        return expirationDate;
    }

    public TextBox getSumInsured() {
        return sumInsured;
    }

    public TextBox getContractSum() {
        return contractSum;
    }

    public ListBox getIsArchived() {
        return isArchived;
    }
}
