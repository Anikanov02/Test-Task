package com.test.task.client.handlers.content;

import com.google.gwt.user.client.ui.*;
import com.test.task.Constants;
import com.test.task.client.domain.dto.ContractDto;

public class ContractDataComposite extends Composite {
    private Label infoLabel;
    private Label idLabel;

    private TextBox subscriptionDate;

    private TextBox startDate;

    private TextBox expirationDate;

    private TextBox sumInsured;

    private TextBox contractSum;

    private ListBox isArchived;

    public ContractDto getModel() {
        return model;
    }

    public void setModel(ContractDto model) {
        this.model = model;
    }

    private ContractDto model;

    public ContractDataComposite() {
        final VerticalPanel panel = new VerticalPanel();
        infoLabel = new Label();
        idLabel = new Label();
        subscriptionDate = new TextBox();
        startDate = new TextBox();
        expirationDate = new TextBox();
        sumInsured = new TextBox();
        contractSum = new TextBox();
        isArchived = new ListBox();
        isArchived.setMultipleSelect(false);
        panel.add(infoLabel);
        panel.add(idLabel);
        panel.add(subscriptionDate);
        panel.add(startDate);
        panel.add(expirationDate);
        panel.add(sumInsured);
        panel.add(contractSum);
        panel.add(isArchived);
        refresh();
        initWidget(panel);
    }

    public void loadData(ContractDto data) {
        model = data;
        idLabel.setText(data.getId().toString());
        subscriptionDate.setText(data.getSubscriptionDate());
        startDate.setText(data.getStartDate());
        expirationDate.setText(data.getExpirationDate());
        sumInsured.setText(data.getSumInsured().toString());
        contractSum.setText(data.getContractSum().toString());
        isArchived.setItemSelected(data.getArchived() ? 0 : 1, true);
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
        } else {
            return false;
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

    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Label idLabel) {
        this.idLabel = idLabel;
    }

    public TextBox getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(TextBox subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public TextBox getStartDate() {
        return startDate;
    }

    public void setStartDate(TextBox startDate) {
        this.startDate = startDate;
    }

    public TextBox getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(TextBox expirationDate) {
        this.expirationDate = expirationDate;
    }

    public TextBox getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(TextBox sumInsured) {
        this.sumInsured = sumInsured;
    }

    public TextBox getContractSum() {
        return contractSum;
    }

    public void setContractSum(TextBox contractSum) {
        this.contractSum = contractSum;
    }

    public ListBox getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(ListBox isArchived) {
        this.isArchived = isArchived;
    }
}
