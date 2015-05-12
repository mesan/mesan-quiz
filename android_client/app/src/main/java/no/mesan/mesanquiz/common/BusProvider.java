package no.mesan.mesanquiz.common;

import com.squareup.otto.Bus;

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
