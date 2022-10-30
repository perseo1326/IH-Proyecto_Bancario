package com.josko.proyecto_bancario.enums;

public enum RoleEnum {
    ROLE_ADMIN,
    ROLE_MODERATOR,
    ROLE_USER;

    public boolean contains(String valor) {

        if (RoleEnum.ROLE_ADMIN.toString().equals(valor) ||
                RoleEnum.ROLE_MODERATOR.toString().equals(valor) ||
                RoleEnum.ROLE_USER.toString().equals(valor) ) {
            return true;
        }
        return false;
    }

    public RoleEnum getValue(String valor) {

        if (RoleEnum.ROLE_ADMIN.toString().equals(valor))
            return RoleEnum.ROLE_ADMIN;
        else if (RoleEnum.ROLE_MODERATOR.toString().equals(valor))
            return RoleEnum.ROLE_MODERATOR;
        else if (RoleEnum.ROLE_USER.toString().equals(valor))
            return RoleEnum.ROLE_USER;
        else
            return null;
    }

}
