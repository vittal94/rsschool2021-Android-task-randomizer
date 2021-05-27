package com.rsschool.android2021

interface FragmentListener {
    fun openFirstFragment(previousNumber: Int)

    fun openSecondFragment(min: Int, max: Int)
}