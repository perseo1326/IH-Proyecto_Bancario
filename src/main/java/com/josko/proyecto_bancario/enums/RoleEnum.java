package com.josko.proyecto_bancario.enums;

public enum RoleEnum {
    ROL_ADMIN,
    ROL_USER,
    ROL_THIRDPARTY;

    public boolean contains(String valor) {

        if (RoleEnum.ROL_ADMIN.toString().equals(valor) ||
                RoleEnum.ROL_USER.toString().equals(valor) ||
                RoleEnum.ROL_THIRDPARTY.toString().equals(valor) ) {
            return true;
        }
        return false;
    }

    public RoleEnum getValue(String valor) {

        if (RoleEnum.ROL_ADMIN.toString().equals(valor))
            return RoleEnum.ROL_ADMIN;
        else if (RoleEnum.ROL_USER.toString().equals(valor))
            return RoleEnum.ROL_USER;
        else if (RoleEnum.ROL_THIRDPARTY.toString().equals(valor))
            return RoleEnum.ROL_THIRDPARTY;
        else
            return null;
    }

}
