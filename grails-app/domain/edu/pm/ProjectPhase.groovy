package edu.pm

enum ProjectPhase {
    BRIEFING(0),
    SCOPING(1),
    INTERACTION(2),
    DEVELOPMENT(3),
    QA(4),
    RELEASE(5)

    private int value;

    ProjectPhase(int value) {
        this.value = value
    }

    int getValue() {
        return value
    }
}