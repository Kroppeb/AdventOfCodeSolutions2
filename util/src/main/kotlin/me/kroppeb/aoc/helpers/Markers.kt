package me.kroppeb.aoc.helpers

@DslMarker
annotation class MarkedA // Style 1: orange, keyword-like

@DslMarker
annotation class MarkedB // Style 2: purple

@DslMarker
annotation class MarkedC // Style 3: aqua

@DslMarker
annotation class MarkedD // Style 4: green

typealias MarkKeyword = MarkedA