package com.josko.proyecto_bancario.enums;

public enum AccountStatusEnum {
    FROZEN,
    ACTIVE;

    public boolean contains(String valor) {

        if (AccountStatusEnum.ACTIVE.toString().equals(valor) ||
                AccountStatusEnum.FROZEN.toString().equals(valor) ) {
            return true;
        }
        return false;
    }

    public AccountStatusEnum getValue(String valor) {

        if (AccountStatusEnum.ACTIVE.toString().equals(valor))
            return AccountStatusEnum.ACTIVE;
        else if (AccountStatusEnum.FROZEN.toString().equals(valor)) {
            return AccountStatusEnum.FROZEN;
        } else {
            return null;
        }
    }

}
