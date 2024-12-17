package me.kroppeb.aoc.helpers

@DslMarker
public annotation class MarkedA // Style 1: orange, keyword-like

@DslMarker
public annotation class MarkedB // Style 2: purple

@DslMarker
public annotation class MarkedC // Style 3: aqua

@DslMarker
public annotation class MarkedD // Style 4: green

public typealias MarkKeyword = MarkedA