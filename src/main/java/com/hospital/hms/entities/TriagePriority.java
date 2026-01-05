package com.hospital.hms.entities;

public enum TriagePriority {
CRITICAL(3),
SERIOUS(2),
STABLE(1);

private final int level;

TriagePriority(int level) {
    this.level = level;
}

public int getLevel(){
    return level;
}

}
