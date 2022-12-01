package com.test.task.client.handlers.content;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.test.task.client.domain.dto.ContractDto;
import com.test.task.client.service.ContractRequestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ContractsMenuComposite extends Composite {
    private Label infoLabel;
    private Button update;
    private Button createContract;
    private CellList<Number> contractsList;
    private ContractDataComposite contractDataComposite;
    private List<ContractDto> contracts;
    private ContractRequestService contractService;
    public ContractsMenuComposite() {
        contracts = new ArrayList<>();
        contractService = GWT.create(ContractRequestService.class);

        final HorizontalPanel panel = new HorizontalPanel();
        final VerticalPanel manipulationPanel = new VerticalPanel();

        infoLabel = new Label();
        update = new Button("Update");
        update.setEnabled(false);
        createContract = new Button("Create new");
        manipulationPanel.add(infoLabel);
        manipulationPanel.add(update);
        manipulationPanel.add(createContract);

        contractsList = new CellList<Number>(new NumberCell());
        contractDataComposite = new ContractDataComposite();

        final SingleSelectionModel<Number> selectionModel = new SingleSelectionModel<Number>();
        contractsList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                Number selected = selectionModel.getSelectedObject();
                if (selected != null && contracts.stream().anyMatch(contractDto -> contractDto.getId().equals(selected.longValue()))) {
                    final ContractDto contract = contracts.stream().filter(contractDto -> contractDto.getId().equals(selected.longValue())).findFirst().get();
                    contractDataComposite.loadData(contract);
                    update.setEnabled(true);
                }
            }
        });

        panel.add(manipulationPanel);
        panel.add(contractsList);
        panel.add(contractDataComposite);
        initWidget(panel);

        update.addClickHandler(event -> {
            contractDataComposite.refresh();
            if(((SingleSelectionModel<Number>)contractsList.getSelectionModel()).getSelectedObject() == null) {
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
                        contractDataComposite.message("creating user failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, ContractDto contractDto) {
                        contracts = contracts.stream().filter(c -> !c.getId().equals(contractDto.getId())).collect(Collectors.toList());
                        contracts.add(contractDto);
                        loadContracts(contracts);
                        contractDataComposite.message("successfully updated contract data");
                    }
                });
            }
        });

        createContract.addClickHandler(event -> {
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
                        contractDataComposite.message("creating user failed with error: " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, ContractDto contractDto) {
                        contracts.add(contractDto);
                        loadContracts(contracts);
                        contractDataComposite.message("successfully updated contract data");
                    }
                });
            }
        });
    }

    public void loadContracts(List<ContractDto> contracts) {
        contractsList.setRowCount(contracts.size());
        contractsList.setRowData(contracts.stream().map(ContractDto::getId).collect(Collectors.toList()));
    }

    public CellList<Number> getContractsList() {
        return contractsList;
    }

    public void setContractsList(CellList<Number> contractsList) {
        this.contractsList = contractsList;
    }

    public ContractDataComposite getContractDataComposite() {
        return contractDataComposite;
    }

    public void setContractDataComposite(ContractDataComposite contractDataComposite) {
        this.contractDataComposite = contractDataComposite;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getCreateContract() {
        return createContract;
    }

    public void setCreateContract(Button createContract) {
        this.createContract = createContract;
    }

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public ContractRequestService getContractService() {
        return contractService;
    }

    public void setContractService(ContractRequestService contractService) {
        this.contractService = contractService;
    }
}
