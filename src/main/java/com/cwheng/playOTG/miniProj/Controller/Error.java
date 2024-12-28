package com.cwheng.playOTG.miniProj.Controller;

public enum Error {
    SESSION_EXPIRED,
    ACCESS_DENIED,
    INVALID_CREDENTIALS,
    EXISTING_ACCOUNT;

    @Override
    public String toString(){
        return this.name();
    }
}
