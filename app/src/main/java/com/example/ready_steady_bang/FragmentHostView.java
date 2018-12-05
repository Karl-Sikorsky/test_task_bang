package com.example.ready_steady_bang;

public interface FragmentHostView {
    void sendCommand(int commandCode);

    int receiveCommand();
}
