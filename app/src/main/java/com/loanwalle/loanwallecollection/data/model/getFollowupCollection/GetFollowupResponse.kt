package com.loanwalle.loanwallecollection.data.model.getFollowupCollection

data class GetFollowupResponse(
    val `data`: List<Data>,
    val message: String,
    val status: String
)