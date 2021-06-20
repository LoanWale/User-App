package com.loanwalle.loanwallecollection.data.model.loanDetails

data class Data(
    val balance_due: Int,
    val day_past_due: Int,
    val disbursal_date: String,
    val interest_payable: Int,
    val loan_no: String,
    val loan_sanctioned: String,
    val mobile_no: String,
    val name: String,
    val net_disbursal_amount: String,
    val payable_amount: Int,
    val payment_recived: String,
    val penal_interest_per_day: Int,
    val penel_roi: String,
    val processing_fee: String,
    val repayment_date: String,
    val roi: String,
    val tenure: String,
    val total_payable: Int
)