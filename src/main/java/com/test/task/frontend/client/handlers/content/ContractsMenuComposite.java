package com.test.task.frontend.client.handlers.content;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.SingleSelectionModel;
import com.test.task.frontend.client.GlobalSessionInfo;
import com.test.task.frontend.client.domain.dto.ContractDto;
import com.test.task.frontend.client.service.ContractRequestService;
import com.test.task.frontend.client.service.UserRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ContractsMenuComposite extends Composite {
    private final Label infoLabel;
    private final Button update;
    private final Button createContract;
    private final Button deleteButton;
    private final CellList<Number> contractsList;
    private final ContractDataComposite contractDataComposite;
    private List<ContractDto> contracts;
    private final ContractRequestService contractService;
    private final UserRequestService userService;

    public ContractsMenuComposite() {
        contracts = new ArrayList<>();
        contractService = GWT.create(ContractRequestService.class);
        userService = GWT.create(UserRequestService.class);

        final HorizontalPanel panel = new HorizontalPanel();
        final VerticalPanel manipulationPanel = new VerticalPanel();

        infoLabel = new Label();
        update = new Button("Update");
        update.setEnabled(false);
        deleteButton = new Button("Delete contract");
        deleteButton.setEnabled(false);
        createContract = new Button("Create new");
        manipulationPanel.add(infoLabel);
        manipulationPanel.add(update);
        manipulationPanel.add(deleteButton);
        manipulationPanel.add(createContract);

        contractsList = new CellList<Number>(new NumberCell());
        contractDataComposite = new ContractDataComposite();
        contractDataComposite.refresh();

        panel.add(manipulationPanel);
        panel.add(contractsList);
        panel.add(contractDataComposite);
        initWidget(panel);

        final SingleSelectionModel<Number> selectionModel = new SingleSelectionModel<Number>();
        contractsList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(event -> {
            Number selected = selectionModel.getSelectedObject();
            if (selected != null && contracts.stream().anyMatch(contractDto -> contractDto.getId().equals(selected.longValue()))) {
                update.setEnabled(true);
                deleteButton.setEnabled(true);
                final ContractDto contract = contracts.stream().filter(contractDto -> contractDto.getId().equals(selected.longValue())).findFirst().get();
                contractDataComposite.loadData(contract);
            }
        });

        update.addClickHandler(event -> {
            contractDataComposite.refresh();
            if (((SingleSelectionModel<Number>) contractsList.getSelectionModel()).getSelectedObject() == null) {
                update.setEnabled(false);
                return;
            }

            if (contractDataComposite.validated()) {
                ContractDto contractDto = new ContractDto(contractDataComposite.getSubscriptionDate().getText().trim(),
                        contractDataComposite.getStartDate().getText().trim(),
                        contractDataComposite.getExpirationDate().getText().trim(),
                        new BigDecimal(contractDataComposite.getSumInsured().getText().trim()),
                        new BigDecimal(contractDataComposite.getContractSum().getText().trim()),
                        Boolean.parseBoolean(contractDataComposite.getIsArchived().getSelectedValue().trim()));
                contractService.editContractData(contractDataComposite.getModel().getId(), contractDto, new MethodCallback<ContractDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        message("updating contract failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, ContractDto contractDto) {
                        loadContracts();
                        message("successfully updated contract data");
                    }
                });
            }
        });

        createContract.addClickHandler(event -> {
            contractDataComposite.refresh();
            if (contractDataComposite.validated()) {
                ContractDto contractDto = new ContractDto(contractDataComposite.getSubscriptionDate().getText().trim(),
                        contractDataComposite.getStartDate().getText().trim(),
                        contractDataComposite.getExpirationDate().getText().trim(),
                        new BigDecimal(contractDataComposite.getSumInsured().getText().trim()),
                        new BigDecimal(contractDataComposite.getContractSum().getText().trim()),
                        Boolean.parseBoolean(contractDataComposite.getIsArchived().getSelectedValue().trim()));
                contractService.newContract(contractDto, new MethodCallback<ContractDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        message("creating contract failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, ContractDto contractDto) {
                        loadContracts();
                        message("contract created successfully");
                    }
                });
            }
        });

        deleteButton.addClickHandler(event -> {
            contractDataComposite.refresh();
            if (Window.confirm("Confirm deleting contract?")) {
                contractService.deleteContract(contractDataComposite.getModel().getId(), new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        message("failed deleting contract");
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        message("successfully deleted contract");
                        loadContracts();
                    }
                });
            }
        });
    }

    public void loadContracts() {
        userService.getContracts(GlobalSessionInfo.userDto.getId(), new MethodCallback<List<ContractDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                message("Some problem occurred while loading contracts data: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<ContractDto> data) {
                contracts = data;
                contractsList.setRowCount(contracts.size());
                contractsList.setRowData(contracts.stream().map(ContractDto::getId).collect(Collectors.toList()));
            }
        });
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
}
