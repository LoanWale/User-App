package com.loanwalle.loanwallecollection.Repositry

import com.loanwalle.loanwallecollection.Model.User

class UserReprository {
    companion object{
        fun setUserData():List<User>{
            var name = ArrayList<User>()
            name.add(User("ABC","1234567890"))
            name.add(User("DEF","O123456789"))
            name.add(User("GHI","9087654321"))
            name.add(User("JKL","8790654321"))
            name.add(User("MNO","9567804321"))
            name.add(User("PQR","9412356780"))
            name.add(User("STU","9312456780"))

            return name
        }
    }
}