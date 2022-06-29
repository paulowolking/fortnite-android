package com.wolking.fortnite.data.stats.data_source

import com.wolking.fortnite.domain.stats.model.*

class StatsDto {
    var account: AccountDto? = null
    var battlePass: BattlePassDto? = null
    var stats: StatsUserDto? = null

    fun toDomain(): Stats {
        return Stats(
            name = account?.name,
            level = battlePass?.level?.toInt(),
            progress = battlePass?.progress?.toInt(),
            wins = stats?.all?.overall?.wins,
            kd = stats?.all?.overall?.kd,
            kills = stats?.all?.overall?.kills,
            solo = Solo(
                score = stats?.all?.solo?.score,
                wins = stats?.all?.solo?.wins,
                kills = stats?.all?.solo?.kills,
                deaths = stats?.all?.solo?.deaths,
                matches = stats?.all?.solo?.matches,
                kd = stats?.all?.solo?.kd,
                top10 = stats?.all?.solo?.top10,
                top25 = stats?.all?.solo?.top25
            ),
            duo = Duo(
                score = stats?.all?.duo?.score,
                wins = stats?.all?.duo?.wins,
                kills = stats?.all?.duo?.kills,
                deaths = stats?.all?.duo?.deaths,
                matches = stats?.all?.duo?.matches,
                kd = stats?.all?.duo?.kd,
                top5 = stats?.all?.duo?.top5,
                top12 = stats?.all?.duo?.top12
            ),
            trio = Trio(
                score = stats?.all?.trio?.score,
                wins = stats?.all?.trio?.wins,
                kills = stats?.all?.trio?.kills,
                deaths = stats?.all?.trio?.deaths,
                matches = stats?.all?.trio?.matches,
                kd = stats?.all?.trio?.kd,
                top3 = stats?.all?.trio?.top3,
                top6 = stats?.all?.trio?.top6
            ),
            squad = Squad(
                score = stats?.all?.squad?.score,
                wins = stats?.all?.squad?.wins,
                kills = stats?.all?.squad?.kills,
                deaths = stats?.all?.squad?.deaths,
                matches = stats?.all?.squad?.matches,
                kd = stats?.all?.squad?.kd,
                top3 = stats?.all?.squad?.top3,
                top6 = stats?.all?.squad?.top6
            )
        )
    }
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