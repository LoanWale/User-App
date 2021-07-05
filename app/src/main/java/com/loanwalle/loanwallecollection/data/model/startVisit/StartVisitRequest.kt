package com.loanwalle.loanwallecollection.data.model.startVisit

object StartVisitRequestBodies{
data class StartVisitRequest(
    val company_id: Int,
    val executive_start_letitude: Double,
    val executive_start_longitude: Double,
    val followup_satarted_at: String,
    val lead_id: String,
    val product_id: Int,
    val user_id: Int,
    val loan_no: String,
)}