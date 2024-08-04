export type PlayerStats = {
    name: string,
    uniqueId: string,
    group: string,
    stats?: {
        general?: GeneralStats
        fnaf?: FNaFStats
    }
}

export type GeneralStats = {
    balance?: number,
    language?: string
}

export type FNaFStats = {
    exp?: number,
    played?: Record<string, number>,
    wins?: Record<string, number>
}