package com.android.fundamentals.domain

import com.android.fundamentals.data.models.Actor

class ActorsDataSource {
    fun getActors(): List<Actor> {
        return listOf(
            Actor("Alicia Vikander", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg", true),
            Actor("Amanda Seyfried", "https://image.ibb.co/j142xJ/Amanda_Seyfried.jpg", false),
            Actor("Anne Hathaway", "https://image.ibb.co/euy6Py/Anne_Hathaway.jpg", true),
            Actor("Emma Stone", "https://image.ibb.co/dJY6Py/Emma_Stone.jpg", true),
            Actor("Keira Knightley", "https://image.ibb.co/cxX0jy/Keira_Knightley.jpg", false),
            Actor("George Clooney", "https://image.ibb.co/ce1t4y/George_Clooney.jpg", true),
            Actor("Lucy Liu", "https://image.ibb.co/dWurrd/Lucy_Liu.jpg", false),
            Actor("Matthew McConaughey", "https://image.ibb.co/e3JHWd/Matthew_Mc_Conaughey.jpg", true),
            Actor("Morgan Freeman", "https://image.ibb.co/h9GhxJ/Morgan_Freeman.jpg", true),
            Actor("Ryan Gosling", "https://image.ibb.co/buLLjy/Ryan_Gosling.jpg", false),
            Actor("Will Smith", "https://image.ibb.co/gxoUcJ/Will_Smith.jpg", false),
            Actor("Robert de Niro", "https://image.ibb.co/e6T6Py/Robert_de_Niro.jpg", true),
            Actor("Zoe Saldana", "https://image.ibb.co/i9WRPy/Zoe_Saldana.jpg", false)
        )
    }
}