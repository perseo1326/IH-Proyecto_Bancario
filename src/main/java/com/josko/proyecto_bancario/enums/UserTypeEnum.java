package com.josko.proyecto_bancario.enums;

public enum UserTypeEnum {

    ADMIN,
    ACCOUNTHOLDER,
    THIRDPARTY;

    public boolean contains(String valor) {

        if (UserTypeEnum.ADMIN.toString().equals(valor) ||
                UserTypeEnum.ACCOUNTHOLDER.toString().equals(valor) ||
                UserTypeEnum.THIRDPARTY.toString().equals(valor) ) {
            return true;
        }
        return false;
    }

    public UserTypeEnum getValue(String valor) {

        if (UserTypeEnum.ADMIN.toString().equals(valor))
            return UserTypeEnum.ADMIN;
        else if (UserTypeEnum.ACCOUNTHOLDER.toString().equals(valor))
            return UserTypeEnum.ACCOUNTHOLDER;
        else if (UserTypeEnum.THIRDPARTY.toString().equals(valor))
            return UserTypeEnum.THIRDPARTY;
        else
            return null;
    }
}



