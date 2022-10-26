package com.josko.proyecto_bancario.enums;

public enum AccountTypeEnum {
    
    CHECKING,
    STUDENTCHECKING,
    SAVINGS,
    CREDITCARD;

    public boolean contains(String valor) {

        if (AccountTypeEnum.CHECKING.toString().equals(valor) ||
                AccountTypeEnum.STUDENTCHECKING.toString().equals(valor) ||
                AccountTypeEnum.SAVINGS.toString().equals(valor) ||
                AccountTypeEnum.CREDITCARD.toString().equals(valor) ) {
            return true;
        }
        return false;
    }

    public AccountTypeEnum getValue(String valor) {

        if (AccountTypeEnum.CHECKING.toString().equals(valor))
            return AccountTypeEnum.CHECKING;
        else if (AccountTypeEnum.STUDENTCHECKING.toString().equals(valor))
            return AccountTypeEnum.STUDENTCHECKING;
        else if (AccountTypeEnum.SAVINGS.toString().equals(valor))
            return AccountTypeEnum.SAVINGS;
        else if (AccountTypeEnum.CREDITCARD.toString().equals(valor))
            return AccountTypeEnum.CREDITCARD;
        else
            return null;
    }



}
