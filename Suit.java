package com.kciray;

import com.kciray.beans.factory.annotation.MyInjection;
import com.kciray.beans.factory.stereotype.Resource;

@Resource
public class Suit {
    @MyInjection
    private Pocket pocket;

    public void setPocket(Pocket pocket) {
        this.pocket = pocket;
    }

    public Pocket getPocket(){ return pocket;}
}
