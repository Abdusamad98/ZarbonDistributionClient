package com.example.zarbondistributionclient.data.sources.local

import android.content.Context
import android.content.SharedPreferences

class SAVER(context: Context) {

    companion object {

        private lateinit var preferences: SharedPreferences
        fun init(context: Context) {
            preferences = context.getSharedPreferences("SAVER", Context.MODE_PRIVATE)
        }

        var token: String
            set(value) {
                preferences.edit().putString("token", value).apply()
            }
            get() {
                return preferences.getString("token", "")!!
            }

        fun setAgentId(agentId: Int) {
            preferences.edit().putInt("agent_id", agentId).apply()
        }

        fun getAgentId() = preferences.getInt("agent_id", 0)


        //Save Password
        fun setPassword(password: String) {
            preferences.edit().putString("password", password).apply()
        }

        fun getPassword() = preferences.getString("password", "")


        //Save Login
        fun setLogin(login: String) {
            preferences.edit().putString("login", login).apply()
        }

        fun getLogin() = preferences.getString("login", "")

        //Save First Name
        fun setFirstName(firstName: String) {
            preferences.edit().putString("first_name", firstName).apply()
        }

        fun getFirstName() = preferences.getString("first_name", "")


        //Save Last Name
        fun setLastName(firstName: String) {
            preferences.edit().putString("last_name", firstName).apply()
        }

        fun getLastName() = preferences.getString("last_name", "")


    }
}