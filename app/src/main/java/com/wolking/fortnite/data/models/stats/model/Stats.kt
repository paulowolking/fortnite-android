package com.wolking.fortnite.data.models.stats.model

class Stats {
    var account: Account? = null
    var battlePass: BattlePass? = null
    var stats: StatsUser? = null
}

class Account(
    var id: String? = null,
    var name: String? = null
)

class BattlePass(
    var level: String? = null,
    var progress: String? = null
)

class StatsUser(
    var all: All? = null
)

class All(
    var overall: Overall? = null,
    var solo: Solo? = null,
    var duo: Duo? = null,
    var trio: Trio? = null,
    var squad: Squad? = null
)

class Overall(
    var score: Double? = null,
    var scorePerMin: Double? = null,
    var scorePerMatch: Double? = null,
    var wins: Double? = null,
    var top3: Double? = null,
    var top5: Double? = null,
    var top6: Double? = null,
    var top10: Double? = null,
    var top12: Double? = null,
    var top25: Double? = null,
    var kills: Double? = null,
    var killsPerMin: Double? = null,
    var killsPerMatch: Double? = null,
    var deaths: Double? = null,
    var kd: Double? = null,
    var matches: Double? = null,
    var winRate: Double? = null,
    var minutesPlayed: Double? = null,
    var playersOutlived: Double? = null
)

class Solo(
    var score: Double? = null,
    var scorePerMin: Double? = null,
    var scorePerMatch: Double? = null,
    var wins: Double? = null,
    var top10: Double? = null,
    var top25: Double? = null,
    var kills: Double? = null,
    var killsPerMin: Double? = null,
    var killsPerMatch: Double? = null,
    var deaths: Double? = null,
    var kd: Double? = null,
    var matches: Double? = null,
    var winRate: Double? = null,
    var minutesPlayed: Double? = null,
    var playersOutlived: Double? = null
)

class Duo(
    var score: Double? = null,
    var scorePerMin: Double? = null,
    var scorePerMatch: Double? = null,
    var wins: Double? = null,
    var top5: Double? = null,
    var top12: Double? = null,
    var kills: Double? = null,
    var killsPerMin: Double? = null,
    var killsPerMatch: Double? = null,
    var deaths: Double? = null,
    var kd: Double? = null,
    var matches: Double? = null,
    var winRate: Double? = null,
    var minutesPlayed: Double? = null,
    var playersOutlived: Double? = null
)

class Trio(
    var score: Double? = null,
    var scorePerMin: Double? = null,
    var scorePerMatch: Double? = null,
    var wins: Double? = null,
    var top3: Double? = null,
    var top6: Double? = null,
    var kills: Double? = null,
    var killsPerMin: Double? = null,
    var killsPerMatch: Double? = null,
    var deaths: Double? = null,
    var kd: Double? = null,
    var matches: Double? = null,
    var winRate: Double? = null,
    var minutesPlayed: Double? = null,
    var playersOutlived: Double? = null
)

class Squad(
    var score: Double? = null,
    var scorePerMin: Double? = null,
    var scorePerMatch: Double? = null,
    var wins: Double? = null,
    var top3: Double? = null,
    var top6: Double? = null,
    var kills: Double? = null,
    var killsPerMin: Double? = null,
    var killsPerMatch: Double? = null,
    var deaths: Double? = null,
    var kd: Double? = null,
    var matches: Double? = null,
    var winRate: Double? = null,
    var minutesPlayed: Double? = null,
    var playersOutlived: Double? = null
)