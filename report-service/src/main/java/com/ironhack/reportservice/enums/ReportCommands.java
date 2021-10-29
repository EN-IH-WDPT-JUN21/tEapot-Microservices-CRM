package com.ironhack.reportservice.enums;

public enum ReportCommands {
    REPORT_LEAD_BY_SALESREP("report-lead-by-salesrep"),
    REPORT_OPP_BY_SALESREP("report-opportunity-by-salesrep"),
    REPORT_CLOSE_W_BY_SALESREP("report-closed-won-by-salesrep"),
    REPORT_CLOSE_L_BY_SALESREP("report-closed-lost-by-salesrep"),
    REPORT_OPEN_BY_SALESREP("report-open-by-salesrep"),
    REPORT_OPP_BY_PRODUCT("report-opportunity-by-product"),
    REPORT_CLOSE_W_BY_PRODUCT("report-closed-won-by-product"),
    REPORT_CLOSE_L_BY_PRODUCT("report-closed-lost-by-product"),
    REPORT_OPEN_BY_PRODUCT("report-open-by-product"),
    REPORT_OPP_BY_COUNTRY("report-opportunity-by-country"),
    REPORT_CLOSE_W_BY_COUNTRY("report-closed-won-by-country"),
    REPORT_CLOSE_L_BY_COUNTRY("report-closed-lost-by-country"),
    REPORT_OPEN_BY_COUNTRY("report-open-by-country"),
    REPORT_OPP_BY_CITY("report-opportunity-by-city"),
    REPORT_CLOSE_W_BY_CITY("report-closed-won-by-city"),
    REPORT_CLOSE_L_BY_CITY("report-closed-lost-by-city"),
    REPORT_OPEN_BY_CITY("report-open-by-city"),
    REPORT_OPP_BY_INDUSTRY("report-opportunity-by-industry"),
    REPORT_CLOSE_W_BY_INDUSTRY("report-closed-won-by-industry"),
    REPORT_CLOSE_L_BY_INDUSTRY("report-closed-lost-by-industry"),
    REPORT_OPEN_BY_INDUSTRY("report-open-by-industry"),
    MEAN_EMPCOUNT("mean-employeecount"),
    MEDIAN_EMPCOUNT("median-employeecount"),
    MAX_EMPCOUNT("max-employeecount"),
    MIN_EMPCOUNT("min-employeecount"),
    MEAN_QUANT("mean-quantity"),
    MED_QUANT("median-quantity"),
    MAX_QUANT("max-quantity"),
    MIN_QUANT("min-quantity"),
    MEAN_OPPS_PERR_ACC("mean-opps-per-account"),
    MED_OPPS_PERR_ACC("median-opps-per-account"),
    MAX_OPPS_PERR_ACC("max-opps-per-account"),
    MIN_OPPS_PERR_ACC("min-opps-per-account"),
    NONE("");

    public String value;

    ReportCommands(String value) {
        this.value=value;
    }

    public static ReportCommands getCommandType(String cValue) {
        for (ReportCommands command: values()) {
            if(command.value.equals(cValue))
                return command;
        }
        return ReportCommands.NONE;
    }

}
