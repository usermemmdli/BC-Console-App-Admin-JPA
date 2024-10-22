package org.example.constants;

import lombok.Getter;
import org.example.strategy.*;

import java.util.Arrays;

@Getter
public enum Menu {
    SHOW_ALL_CUSTOMER(1, "Show all customer", new ShowAllCustomerStrategy()),
    ADD_CUSTOMER(2, "Add new customer", new CreateCustomerStrategy()),
    IS_ACTIVE_CARD(3, "Is active customer", new IsActiveCardStrategy()),
    DELETE_CUSTOMER(4, "Delete customer", new DeleteCustomerStrategy()),
    CREATE_CARD(5, "Create new card", new CreateCardStrategy()),
    SHOW_ALL_TRANSACTIONS(6, "Show all transactions", new ShowAllTransactionsStrategy());

    private final int index;
    private final String description;
    private final Strategy strategy;

    Menu(int index, String description, Strategy strategy) {
        this.index = index;
        this.description = description;
        this.strategy = strategy;
    }

    public static Menu getMenuByIndex(int index) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getIndex() == index)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
