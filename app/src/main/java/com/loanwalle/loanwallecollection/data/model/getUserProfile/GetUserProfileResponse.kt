package com.loanwalle.loanwallecollection.data.model.getUserProfile

data class GetUserProfileResponse(
    val `data`: List<Data>,
    val message: String,
    val status: String
)