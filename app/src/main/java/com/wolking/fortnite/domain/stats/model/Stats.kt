package com.wolking.fortnite.domain.stats.model

class Stats(
    val name: String?,
    val level: Int?,
    val progress: Int?,
    val wins: Double?,
    val kd: Double?,
    val kills: Double?,
    val solo: Solo?,
    val duo: Duo?,
    val trio: Trio?,
    val squad: Squad?,
)


class Solo(
    val score: Double? = null,
    val wins: Double? = null,
    val kills: Double? = null,
    val deaths: Double? = null,
    val matches: Double? = null,
    val kd: Double? = null,
    val top10: Double? = null,
    val top25: Double? = null
)

class Duo(
    val score: Double? = null,
    val wins: Double? = null,
    val kills: Double? = null,
    val deaths: Double? = null,
    val matches: Double? = null,
    val kd: Double? = null,
    val top5: Double? = null,
    val top12: Double? = null
)

open class Trio(
    val score: Double? = null,
    val wins: Double? = null,
    val kills: Double? = null,
    val deaths: Double? = null,
    val matches: Double? = null,
    val kd: Double? = null,
    val top3: Double? = null,
    val top6: Double? = null
)

class Squad(
    val score: Double? = null,
    val wins: Double? = null,
    val kills: Double? = null,
    val deaths: Double? = null,
    val matches: Double? = null,
    val kd: Double? = null,
    val top3: Double? = null,
    val top6: Double? = null
)