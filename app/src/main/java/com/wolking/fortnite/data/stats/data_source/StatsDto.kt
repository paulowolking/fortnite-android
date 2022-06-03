package com.wolking.fortnite.data.stats.data_source

class StatsDto {
    var account: AccountDto? = null
    var battlePass: BattlePassDto? = null
    var stats: StatsUserDto? = null
}

class AccountDto(
    var id: String? = null,
    var name: String? = null
)

class BattlePassDto(
    var level: String? = null,
    var progress: String? = null
)

class StatsUserDto(
    var all: AllDto? = null
)

class AllDto(
    var overall: OverallDto? = null,
    var solo: SoloDto? = null,
    var duo: DuoDto? = null,
    var trio: TrioDto? = null,
    var squad: SquadDto? = null
)

class OverallDto(
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

class SoloDto(
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

class DuoDto(
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

class TrioDto(
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

class SquadDto(
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