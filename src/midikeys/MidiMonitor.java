package midikeys;

import javax.sound.midi.*;
import java.util.List;

/*
 * https://stackoverflow.com/questions/6937760/java-getting-input-from-midi-keyboard
 */

public class MidiMonitor {

    public static void main(String[] args) {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                //does the device have any transmitters?
                //if it does, add it to the device list
                System.out.println(infos[i]);

                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();
                //and for each transmitter

                for (int j = 0; j < transmitters.size(); j++) {
                    //create a new receiver
                    transmitters.get(j).setReceiver(
                            //using my own MidiInputReceiver
                            new MidiInputReceiver(device.getDeviceInfo().toString())
                    );
                }

                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

                //open each device
                device.open();
                //if code gets this far without throwing an exception
                //print a success message
                System.out.println(device.getDeviceInfo() + " Was Opened");


            } catch (MidiUnavailableException e) {
            }
        }

    }

    public static class MidiInputReceiver implements Receiver {
        public String name;
        public Receiver receiver;
        Synthesizer synthesizer;
        Receiver synthReceiver;

        public MidiInputReceiver(String name) throws MidiUnavailableException {
            this.name = name;
            this.receiver = MidiSystem.getReceiver();

            synthesizer = MidiSystem.getSynthesizer();
            synthReceiver = synthesizer.getReceiver();
            synthesizer.open();

        }

        public void send(MidiMessage msg, long timeStamp) {
            System.err.print("midi received: ");
            System.err.println(byteArrayToHex(msg.getMessage()));

            if (msg instanceof ShortMessage) {
                ShortMessage shortMsg = (ShortMessage)msg;

                System.err.print("channel: " + shortMsg.getChannel());
                System.err.print(" command: " + shortMsg.getCommand());
                System.err.print(" data1: " + shortMsg.getData1());
                System.err.println(" data2: " + shortMsg.getData2());

            }
        }

        public void close() {
        }

        public static String byteArrayToHex(byte[] a) {
            StringBuilder sb = new StringBuilder(a.length * 2);
            for (byte b : a)
                sb.append(String.format("%02x", b));
            return sb.toString();
        }
    }
}
