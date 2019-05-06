package contracts;

import java.util.Set;

public interface Hardware {
    int decreaseComponentValue(int value);
    int increaseComponentValue(int value);
    String getName();
    int getMaximumMemory();
    int getMaximumCapacity();
    int getUsedMemory();
    int getUsedCapacity();
    void installSoftware(Software software);
    void releaseSoftware(String softwareName);
    Set<Software> getInstalledSoftwareComponents();
}
