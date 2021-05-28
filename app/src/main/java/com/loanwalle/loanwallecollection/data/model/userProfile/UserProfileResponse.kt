package com.loanwalle.loanwallecollection.data.model.userProfile

data class UserProfileResponse(
    val `data`: List<Data>,
    val message: String,
    val status: String
)