package com.adeo.kviewmodel.demo

import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main() = SwingUtilities.invokeLater {
    val window = JFrame()
    window.title = "KViewModel Demo"
    window.setSize(800, 600)
}