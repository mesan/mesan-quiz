package no.mesan.mesanquiz.common;

import com.squareup.otto.Bus;

/**
 * Created by n06849 on 22.04.2015.
 */
public class BusProvider {
    private static no.mesan.mesanquiz.common.MainThreadBus bus;

    private BusProvider () {
    }

    public static Bus getInstance() {
        if(bus == null) {
            bus = new no.mesan.mesanquiz.common.MainThreadBus();
        }
        return bus;
    }
}
