package org.iot.dsa.dslink.simulator;

import org.iot.dsa.DSRuntime;
import org.iot.dsa.node.DSBool;
import org.iot.dsa.node.DSElement;
import org.iot.dsa.node.DSInfo;
import org.iot.dsa.node.DSNode;

public class PumpNode extends DSNode implements Runnable {

    private final DSInfo speed = getInfo(Constants.SPEED);

    public PumpNode() { }

    public PumpNode(int pollRate) {
        setPumpDataNodeMetrics();
        startTimer(pollRate);
    }

    @Override
    protected void declareDefaults() {
        declareDefault(Constants.BOOLEAN, DSBool.valueOf(false));
        declareDefault(Constants.SPEED, DSElement.make(Util.getFloatRandom(10.00,1.00))).setReadOnly(true);
    }

    private void setPumpDataNodeMetrics() {
        put(speed.getName(), Util.getFloatRandom(10.00,1.00));
    }

    @Override
    public void run() {
        setPumpDataNodeMetrics();
    }

    private void startTimer(int seconds) {
        DSRuntime.run(this, System.currentTimeMillis() + (seconds * 1000), (seconds * 1000));
    }
}