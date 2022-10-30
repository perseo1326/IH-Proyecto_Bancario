package com.josko.proyecto_bancario.enums;

public enum RoleEnum {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_THIRDPARTY;

    public boolean contains(String valor) {

        if (RoleEnum.ROLE_ADMIN.toString().equals(valor) ||
                RoleEnum.ROLE_USER.toString().equals(valor) ||
                RoleEnum.ROLE_THIRDPARTY.toString().equals(valor) ) {
            return true;
        }
        return false;
    }

    public RoleEnum getValue(String valor) {

        if (RoleEnum.ROLE_ADMIN.toString().equals(valor))
            return RoleEnum.ROLE_ADMIN;
        else if (RoleEnum.ROLE_USER.toString().equals(valor))
            return RoleEnum.ROLE_USER;
        else if (RoleEnum.ROLE_THIRDPARTY.toString().equals(valor))
            return RoleEnum.ROLE_THIRDPARTY;
        else
            return null;
    }

}
