package com.rdas.common.message.model;

public enum StepDefinitionMark {
    ACCEPTED(0) {
        @Override
        public boolean isAccepted() {
            return true;
        }
    },
    DATAGEN_START(1) {
        @Override
        public boolean isDataGenStarted() {
            return true;
        }
    },
    DATAGEN_END(2) {
        @Override
        public boolean isDataGenEnd() {
            return true;
        }
    },
    RPTGEN_START(3) {
        @Override
        public boolean isReportGenStarted() {
            return true;
        }
    },
    RPTAGEN_END(4) {
        @Override
        public boolean isReportGenEnd() {
            return true;
        }
    }
    ;

    public boolean isAccepted() {
        return false;
    }
    public boolean isDataGenStarted() {
        return false;
    }
    public boolean isDataGenEnd() {
        return false;
    }
    public boolean isReportGenStarted() {
        return false;
    }
    public boolean isReportGenEnd() {
        return false;
    }

    private int sequence;

    StepDefinitionMark(int sequenceNumber) {
        sequence = sequenceNumber;
    }
}
