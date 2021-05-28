package com.example.popularlibraries

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClick(btnCounter: ButtonNumber) {
        when (btnCounter) {
            ButtonNumber.ONE -> {
                val nextValue = model.next(0)
                view.setButton1Text("$nextValue")
            }
            ButtonNumber.TWO -> {
                val nextValue = model.next(1)
                view.setButton2Text("$nextValue")
            }
            ButtonNumber.THREE -> {
                val nextValue = model.next(2)
                view.setButton3Text("$nextValue")
            }
        }
    }
}